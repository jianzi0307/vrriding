package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.commons.rc4.PacketCrypt;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.exception.PleaseLoginAgainException;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.GameServerChannelHandler;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.network.serverpackets.SM_RECONNECTION;
import com.pkuvr.game_server.proto.clientproto.Reconnection_Request.Reconnection_Req;
import com.pkuvr.game_server.proto.serverproto.Reconnection_Response.Reconnection_Res;
import com.pkuvr.game_server.redis.SessionKeyHMapCpt;
import com.pkuvr.game_server.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.RECONNECTION_REQ, state = State.CONNECTED)
public class CM_RECONNECTION extends AbstractCM {
    private static final Logger logger = Logger.getLogger(CM_RECONNECTION.class);
    @Resource
    private SessionKeyHMapCpt sessionKeyHMapCpt;
    @Resource
    private LoginService loginService;

    @Override
    protected void runImpl() {
        GameServerChannelHandler cch = getChannelHandler();
        try {
            // 请求参数
            Reconnection_Req req = Reconnection_Req.parseFrom(byteData);
            int roleId = req.getRoleId();

            // 验证是否有权登陆
            // 1.验证用户的 sessionKey
            String redisSessionKey = sessionKeyHMapCpt.getSessionKey(roleId);
            if (redisSessionKey == null) {
                throw new PleaseLoginAgainException();
            }

            // Channel 的基本属性,同时变更状态为验证过
            cch.setRoleId(roleId); // 该 channel 对应的 roleId
            cch.setState(State.AUTHED);
            cch.setPacketCrypt(new PacketCrypt(redisSessionKey));

            // roleId 与 Channel 的对应关系
            GameServerSendService.getInstance().reconnectClient(cch.getChannel().getId(), roleId, cch);

            // 返回
            Reconnection_Res.Builder resBuilder = Reconnection_Res.newBuilder();
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            cch.sendPacket(new SM_RECONNECTION(resBuilder.build().toByteArray()));

            //loginService.reconnectCrossServer(roleId);
        } catch (Exception e) {
            logger.error("用户登录失败，重新登录", e);

            // 登陆失败,要求重新登陆
            Reconnection_Res.Builder resBuilder = Reconnection_Res.newBuilder();
            resBuilder.setErrorCode(SeaErrorCode.LOGIN_FAULT.getErrorCode());
            super.sendPacket(new SM_RECONNECTION(resBuilder.build().toByteArray()));
        }
    }
}








