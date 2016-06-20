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
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * 报文帧编码器
 *
 * @author lyahim
 */
public class PacketFrameEncoder extends OneToOneEncoder {
    private final int LENGTH;

    public PacketFrameEncoder(int length) {
        super();
        LENGTH = length;
    }

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        ChannelBuffer buf = (ChannelBuffer) msg;
        switch (LENGTH) {
            case 1:
                buf.setByte(0, buf.readableBytes());
                break;
            case 2:
                buf.setShort(0, buf.readableBytes());
                break;
            case 4:
                buf.setInt(0, buf.readableBytes());
                break;
        }

        GameServerSendService.getInstance().encryptIfNeed(ctx.getChannel().getId(), buf);

        return buf;
    }

}
