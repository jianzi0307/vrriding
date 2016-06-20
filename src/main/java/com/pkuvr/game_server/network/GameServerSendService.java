package com.pkuvr.game_server.network;

import com.pkuvr.commons.network.netty.handler.AbstractChannelHandler;
import com.pkuvr.commons.network.netty.packet.AbstractServerPacket;
import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.commons.rc4.PacketCrypt;
import com.pkuvr.game_server.constant.SmOpCode;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GameServerSendService {
    private static final Logger logger = Logger.getLogger(GameServerSendService.class);
    private Map<Integer, GameServerChannelHandler> roleIdToChannelMap = new ConcurrentHashMap<Integer, GameServerChannelHandler>(); // 多线程安全的Map
    private Map<Integer, Integer> clientIdToRoleIdMap = new ConcurrentHashMap<Integer, Integer>();  // 多线程安全的Map

    private GameServerSendService() {
    }

    public static final GameServerSendService getInstance() {
        return SingletonHolder.instance;
    }

    public synchronized void addClient(Integer clientId, Integer roleId, GameServerChannelHandler cch) {
        GameServerChannelHandler cchOld = roleIdToChannelMap.get(roleId);

        // 多重登陆,后登陆的把先登录的踢掉了
        if (cchOld != null) {
            try {
//				Multiple_Login_Res.Builder resBuilder = Multiple_Login_Res.newBuilder();
//				resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
//				cchOld.close(new SM_MULTIPLE_LOGIN(resBuilder.build().toByteArray())); // 下发最后一个包之后关闭连接
                roleIdToChannelMap.remove(roleId);
                clientIdToRoleIdMap.remove(cchOld.getChannel().getId());
            } catch (Exception e) {
            }
        }

        clientIdToRoleIdMap.put(clientId, roleId);
        roleIdToChannelMap.put(roleId, cch);

    }

    public synchronized void reconnectClient(Integer clientId, Integer roleId, GameServerChannelHandler cch) {
        GameServerChannelHandler cchOld = roleIdToChannelMap.get(roleId);

        if (cchOld != null) {
            try {
                cchOld.close();
                roleIdToChannelMap.remove(roleId);
                clientIdToRoleIdMap.remove(cchOld.getChannel().getId());
            } catch (Exception e) {
            }
        }

        clientIdToRoleIdMap.put(clientId, roleId);
        roleIdToChannelMap.put(roleId, cch);

    }

    public void removeClient(Integer roleId, Integer channelId) {
        // 这样写是因为:重复登录时,关闭前一个的时候会错误的关闭新的
        try {
            if (roleId == null)
                return;

            // roleId 相同但是 channelId 不同,是不同的人登陆同一个账户,但是是不同的sokect
            GameServerChannelHandler cch = roleIdToChannelMap.get(roleId);
            if (null != cch) {
                if (cch.getChannel().getId().equals(channelId)) {
                    roleIdToChannelMap.remove(roleId);
                    clientIdToRoleIdMap.remove(channelId);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 下发消息报文
     *
     * @param roleId
     * @param abstractserverpacket
     */
    public void sendMessage(Integer roleId, AbstractServerPacket<? extends AbstractChannelHandler> abstractserverpacket) {
        GameServerChannelHandler cch = roleIdToChannelMap.get(roleId);
        if (cch == null)
            return;

        try {
            cch.sendPacket(abstractserverpacket);
        } catch (Exception exc) {
        }
    }

    /**
     * 下发消息并关闭链接(这个方法是老的可能有问题,可以用 cch.sendPacketSetOpCodeAndClose 方法)
     *
     * @param roleId
     * @param abstractserverpacket
     */
    public void sendMessageAndClose(Integer roleId, AbstractServerPacket<? extends AbstractChannelHandler> abstractserverpacket) {
        GameServerChannelHandler cch = roleIdToChannelMap.get(roleId);
        if (cch == null)
            return;
        try {
            cch.close(abstractserverpacket);
        } catch (Exception exc) {
        }
    }

    /**
     * 为广播提供的方法,暂时未经测试
     * 向所有在线用户下发消息(不知道多线程的时候 ChannelBuffer 是否允许多个信道同时使用(是否多线程安全))
     *
     * @param abstractserverpacket
     */
    public void sendMessageToAllUser(AbstractServerPacket<? extends AbstractChannelHandler> abstractserverpacket) {
        Collection<GameServerChannelHandler> values = roleIdToChannelMap.values();
        for (GameServerChannelHandler cch : values) {
            try {
                cch.sendPacket(abstractserverpacket);
            } catch (Exception e) {
                logger.error("向所有在线用户下发消息时,出错,roleId=" + cch.getRoleId(), e);
            }
        }
    }

    /**
     * 进行加解密逻辑
     */
    public void setPacketCrypt(int roleId, String sessionKey) {
        GameServerChannelHandler cch = roleIdToChannelMap.get(roleId);
        if (cch == null)
            return;

        cch.setPacketCrypt(new PacketCrypt(sessionKey));
    }

    /**
     * 进行加解密逻辑
     */
    public void setPacketCrypt(GameServerChannelHandler cch, String sessionKey) {
        if (cch == null)
            return;

        cch.setPacketCrypt(new PacketCrypt(sessionKey));
    }


    /**
     * 获取所有在线用户的id
     *
     * @return
     */
    public Set<Integer> getAllOnlineUser() {
        return roleIdToChannelMap.keySet();
    }

    public boolean isOnline(Integer roleId) {
        GameServerChannelHandler gameServerChannelHandler = roleIdToChannelMap.get(roleId);
        if (gameServerChannelHandler == null)
            return false;
        return true;
    }

    public int getClientSize() {
        return roleIdToChannelMap.size();
    }

    public void encryptIfNeed(int clientId, ChannelBuffer buf) {
        Integer roleId = clientIdToRoleIdMap.get(clientId);
        if (roleId == null)
            return;
        GameServerChannelHandler cch = roleIdToChannelMap.get(roleId);
        if (cch == null)
            return;

        if (cch.getState() != State.AUTHED)
            return;

        if (buf.getShort(2) == SmOpCode.ENTER_CROSS_SERVER_RES.getOpCode())
            return;

        if (buf.getShort(2) == SmOpCode.RECONNECTION_RES.getOpCode())
            return;

        if (buf.getShort(2) == SmOpCode.CR_LOGIN_RES.getOpCode())
            return;

        if (buf.getShort(2) == SmOpCode.CR_RECONNECT_RES.getOpCode())
            return;


        PacketCrypt packetCrypt = cch.getPacketCrypt();
        if (packetCrypt == null) {
            return;
        }

        int len = buf.getShort(0);

        byte[] dataHead = new byte[4];
        buf.getBytes(0, dataHead);
        packetCrypt.encrypt(dataHead);
        buf.setBytes(0, dataHead);

        if (len > 4) {
            byte[] data = new byte[len - 4];
            buf.getBytes(4, data);
            packetCrypt.encrypt(data);
            buf.setBytes(4, data);
        }
    }

    public void decryptIfNeed(int clientId, ChannelBuffer buf) {
        if (clientIdToRoleIdMap.get(clientId) == null)
            return;

        Integer roleId = clientIdToRoleIdMap.get(clientId);

        GameServerChannelHandler cch = roleIdToChannelMap.get(roleId);
        if (cch == null)
            return;

        if (cch.getState() != State.AUTHED)
            return;

        if (buf == null)
            return;

        byte[] dataHead = new byte[4];
        buf.getBytes(buf.readerIndex(), dataHead);
        cch.getPacketCrypt().decrypt(dataHead);
        buf.setBytes(buf.readerIndex(), dataHead);


        if (buf.getShort(buf.readerIndex()) > 4) {
            byte[] data = new byte[buf.getShort(buf.readerIndex()) - 4];
            buf.getBytes(buf.readerIndex() + 4, data);
            cch.getPacketCrypt().decrypt(data);
            buf.setBytes(buf.readerIndex() + 4, data);
        }
    }

    public void decryptBytesIfNeed(int clientId, byte[] bytes) {
        if (clientIdToRoleIdMap.get(clientId) == null)
            return;

        Integer roleId = clientIdToRoleIdMap.get(clientId);
        GameServerChannelHandler cch = roleIdToChannelMap.get(roleId);
        if (cch == null)
            return;

        if (cch.getState() != State.AUTHED)
            return;

        if (bytes == null)
            return;

        cch.getPacketCrypt().decrypt(bytes);
    }

    private static class SingletonHolder {
        protected static final GameServerSendService instance = new GameServerSendService();
    }
}
