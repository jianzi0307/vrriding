package com.pkuvr.game_server.network;

import com.pkuvr.commons.network.netty.handler.AbstractChannelHandler;
import com.pkuvr.commons.network.netty.handler.AbstractPacketHandlerFactory;
import com.pkuvr.commons.rc4.PacketCrypt;
import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;


public class GameServerChannelHandler extends AbstractChannelHandler {
    private static final Logger log = Logger.getLogger(GameServerChannelHandler.class);
    private int roleId = -1; // 默认为-1,认证通过后赋予真正的roleId
    private PacketCrypt packetCrypt;

    public GameServerChannelHandler(AbstractPacketHandlerFactory<GameServerChannelHandler> acphf) {
        super(acphf);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
        log.info("Game Client connected Ip:" + inetAddress.getHostAddress());
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public PacketCrypt getPacketCrypt() {
        return packetCrypt;
    }

    public void setPacketCrypt(PacketCrypt packetCrypt) {
        this.packetCrypt = packetCrypt;
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {
        log.warn("roleId=" + roleId + ";Game Connection with Client [closed] IP:" + inetAddress.getHostAddress());

        removeClient();

        super.channelClosed(ctx, e);
    }

    private void removeClient() {
        try {
            log.info("removeClient roleId = " + roleId);

            addLogoutLog(roleId); //添加退出日志
            GameServerSendService.getInstance().removeClient(roleId, super.getChannel().getId()); //删除缓存的连接
        } catch (Exception e) {
            log.error("removeClient:", e);
        }
    }

    private void addLogoutLog(int roleId) {
        try {

//            RealTimePvpService realTimePvpService = GetBeanHelper.getRealTimePvpService();
//            realTimePvpService.pvpDisconnect(roleId);
//
//            MineService mineService = GetBeanHelper.getMineService();
//            mineService.playerExitMine(roleId);
        } catch (Exception e) {
            log.error("addLogoutLog:", e);
        }
    }

}
