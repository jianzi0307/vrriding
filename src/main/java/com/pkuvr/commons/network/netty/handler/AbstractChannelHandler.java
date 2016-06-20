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
package com.pkuvr.commons.network.netty.handler;

import com.pkuvr.commons.network.netty.packet.AbstractClientPacket;
import com.pkuvr.commons.network.netty.packet.AbstractServerPacket;
import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.network.GameServerChannelHandler;
import com.pkuvr.game_server.network.serverpackets.BaseSendPacket;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 继承自 netty 的信道
 * <p>类说明:</p>
 * <p>文件名： AbstractChannelHandler.java</p>
 * <p>创建人及时间：	宋士龙 2012-7-26</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 */
public abstract class AbstractChannelHandler extends SimpleChannelUpstreamHandler {
    private static final Logger log = Logger.getLogger(AbstractChannelHandler.class);
    protected final AbstractPacketHandlerFactory<? extends AbstractChannelHandler> abstractPacketHandlerFactory;
    protected State state;
    protected InetAddress inetAddress;
    protected Channel channel;

//	private ICacheManager<String,Boolean> repeatRequestCache = GetBeanHelper.getRequestCacheManager(); // 重复请求cache
//	private ICacheManager<String,ArrayDeque<String>> queueResponseCache = GetBeanHelper.getQueueResponseCahceManager(); // 顺序返回队列cache
//	@SuppressWarnings("rawtypes")
//	private ICacheManager<String,AbstractServerPacket> queueServerPacketCache = GetBeanHelper.getQueueServerPacketCacheManager(); // 待发送的顺序返回cache

    public AbstractChannelHandler(AbstractPacketHandlerFactory<? extends AbstractChannelHandler> aphf) {
        super();
        abstractPacketHandlerFactory = aphf;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
        state = State.CONNECTED;
        channel = ctx.getChannel();
        inetAddress = ((InetSocketAddress) e.getChannel().getRemoteAddress()).getAddress();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        if (e.getCause().getClass().equals(IOException.class)) { // server force close
            log.error("NETTY: Exception caught: IOException: ", e.getCause());

            e.getChannel().close();
        } else if (e.getCause().getClass().equals(NullPointerException.class)) {
            log.error("NETTY: Exception caught: NullPointerException: ", e.getCause());
        } else {
            log.error("NETTY: Exception caught: ", e.getCause());

            e.getChannel().close();
        }
    }

    public void close() {
        channel.close();
    }

    public void close(AbstractServerPacket<? extends AbstractChannelHandler> lastpacket) {
        sendPacket(lastpacket);
        close();
    }

    public String getIP() {
        return inetAddress.getHostAddress();
    }

    public byte[] getByteIP() {
        return inetAddress.getAddress();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return the channel
     */
    public Channel getChannel() {
        return channel;
    }

    @SuppressWarnings("unchecked")
    public void sendPacket(AbstractServerPacket<? extends AbstractChannelHandler> abstractserverpacket) {
        AbstractServerPacket<AbstractChannelHandler> spacket = (AbstractServerPacket<AbstractChannelHandler>) abstractserverpacket;

        // 李豪杰 2014.12.17: 判断是否是BaseServerPacket类
        boolean isBaseSendPacket = spacket.getClass().getSimpleName().equals(BaseSendPacket.class.getSimpleName());
        if (isBaseSendPacket) {
            // 不需要设置Opcode
        } else {
            spacket.setOpCode(abstractPacketHandlerFactory.getServerPacketopCode((Class<? extends AbstractServerPacket<AbstractChannelHandler>>) spacket.getClass()));
        }

        //log.info(String.format("before [Sent packet] : %s , OpCode : 0x%02X %n", spacket.getClass().getSimpleName(), spacket.getOpCode()));
        //检查是否为顺序返回
        //boolean isQueueRes=checkQueueResponse(channel,spacket);
        //不是顺序返回，正常发送
        //if (!isQueueRes){
        spacket.write(this);
        if (channel.isOpen()) {
            channel.write(spacket.getBuf());
            log.info(String.format("[Sent packet] : %s , OpCode : 0x%02X %n", spacket.getClass().getSimpleName(), spacket.getOpCode()));
        }

        //检查返回信息，删除重复请求标识
        //checkRepeatResponse(channel,spacket);
        //}
    }

    @SuppressWarnings("unchecked")
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
        /**
         * Packet is frame decoded and decrypted at this stage Here packet will be read and submitted to execution
         */
        AbstractClientPacket<? extends AbstractChannelHandler> packet = abstractPacketHandlerFactory.handleClientPacket((ChannelBuffer) e.getMessage(), this);
        AbstractClientPacket<GameServerChannelHandler> p = null;

        try {
            p = (AbstractClientPacket<GameServerChannelHandler>) packet;
        } catch (Exception e2) {
        }

        if (packet != null && packet.read()) {
            //检查请求信息，若为重复请求，添加重复请求标识
            //if (checkRepeatRequest(ctx,packet)){
            //检查请求信息，若为顺序请求，添加请求对应的返回操作码到顺序队列中
            //checkQueueRequest(ctx,packet);
            log.info(String.format("[Receive packet] : %s , OpCode : 0x%02X , RoleId : %s  %n ", packet.getClass().getSimpleName(), packet.getOpCode(), p.getChannelHandler().getRoleId()));
            packet.run();
            //}
        }
    }

}
