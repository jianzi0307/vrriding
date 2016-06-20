/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkuvr.commons.network.netty.coder;

import com.pkuvr.game_server.network.GameServerSendService;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 报文帧解码器
 *
 * @author lyahim
 */
public class PacketFrameDecoder extends LengthFieldBasedFrameDecoder {
    public static final Logger log = LoggerFactory.getLogger(PacketFrameDecoder.class);

    private static final int MAX_PACKET_LENGTH = 32768;
    private static final int LENGTH_FIELD_OFFSET = 0;
    private static final int LENGTH_FIELD_LENGTH = 2;
    private static final int LENGTH_FIELD_ADJUSTMENT = -2;
    private static final int INITIAL_BYTES_TO_STRIP = 2;

    private ChannelBuffer cachedMsg = null;
    private ChannelBuffer tmpBufFromMsg = null;

    private short length = 0;

    public PacketFrameDecoder() {
        super(MAX_PACKET_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_FIELD_ADJUSTMENT, INITIAL_BYTES_TO_STRIP);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (cachedMsg == null) {
            cachedMsg = newCumulationBuffer(ctx, 8192 * 4);
        }
        ChannelBuffer bufFromMsg = (ChannelBuffer) e.getMessage();
        //log.info("bufFromMsg = " + bufFromMsg.readableBytes());

        //log.info("cachedMsg.writerIndex = " + cachedMsg.writerIndex());
        cachedMsg.writeBytes(bufFromMsg);


        while (true) {
            if (length == 0) {
                if (cachedMsg.readableBytes() < 2) {
                    break;
                }

                // 解密前四个字节
                byte[] headBytes = new byte[2];
                cachedMsg.readBytes(headBytes);
                //log.info("before decrypt headBytes======" + headBytes.toString());
                GameServerSendService.getInstance().decryptBytesIfNeed(ctx.getChannel().getId(), headBytes);
                //log.info("after decrypt headBytes======" + headBytes.toString());

                length = 0;
                for (int i = headBytes.length - 1; i >= 0; i--) {
                    length <<= 8;
                    length |= (headBytes[i] & 0x00ff);
                }
            }

            //log.info("length = " + length + " cachedMsg.readableBytes()=" + cachedMsg.readableBytes());
            if (length < 0) {
                log.info("length is wrong = " + length);
            }

            if (length > 0 && length - 2 > cachedMsg.readableBytes()) {
                break;
            }

            byte[] contentBytes = new byte[length - 2];
            cachedMsg.readBytes(contentBytes);
            //log.info("contentBytes.length = " + contentBytes.length);

            GameServerSendService.getInstance().decryptBytesIfNeed(ctx.getChannel().getId(), contentBytes);

            transportSuper(ctx, e, length, contentBytes);
            length = 0;

        }
        cachedMsg.discardReadBytes();
    }

    private void transportSuper(ChannelHandlerContext ctx, MessageEvent e, short len, byte[] content) throws Exception {
        ChannelBuffer bufFromMsg = (ChannelBuffer) e.getMessage();
        //log.info("transportSuper bufFromMsg.capacity=" + bufFromMsg.capacity() + " content.lenth=" + content.length);

        if (null != tmpBufFromMsg) {
            tmpBufFromMsg.clear();
        }
        bufFromMsg.clear();

        // 这里必须保证要写入值，不然的话decode的调用会被上层逻辑跳过
        bufFromMsg.writeShort(len);
        if (bufFromMsg.capacity() >= len) {
            bufFromMsg.writeBytes(content);
        } else {
            if (null == tmpBufFromMsg || tmpBufFromMsg.capacity() < len) {
                tmpBufFromMsg = bufFromMsg.factory().getBuffer(bufFromMsg.order(), len);
            }

            tmpBufFromMsg.writeShort(len);
            tmpBufFromMsg.writeBytes(content);
            log.info("transportSuper after dispose tmpBufMsg.capacity=" + tmpBufFromMsg.capacity());
        }

        super.messageReceived(ctx, e);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel ch, ChannelBuffer msg) throws Exception {
        log.info("decode msg.capacity=" + msg.capacity());

        if (null != tmpBufFromMsg && tmpBufFromMsg.readableBytes() > 0) {
            log.info("decode tmpBufFromMsg.capacity=" + tmpBufFromMsg.capacity());

            msg.readerIndex(msg.readableBytes());
            return super.decode(ctx, ch, tmpBufFromMsg);
        } else {
            return super.decode(ctx, ch, msg);
        }
    }
}
