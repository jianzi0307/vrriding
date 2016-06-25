package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.GameServerChannelHandler;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.network.serverpackets.SM_CR_LOGIN;
import com.pkuvr.game_server.proto.clientproto.CR_Login_Request.CR_Login_Req;
import com.pkuvr.game_server.proto.serverproto.CR_Login_Response.CR_Login_Res;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.CR_LOGIN_REQ, state = State.CONNECTED)
public class CM_CR_LOGIN extends AbstractCM {
    private static final Logger logger = Logger.getLogger(CM_CR_LOGIN.class);

    @Override
    protected void runImpl() {
        GameServerChannelHandler cch = getChannelHandler();
        try {
            // 请求参数
            CR_Login_Req req = CR_Login_Req.parseFrom(byteData);
            int serverId = req.getServerId();
            logger.info(serverId + "<--------------------------------------------serverId");
            // Channel 的基本属性,同时变更状态为验证过
            cch.setRoleId(serverId); // 该 channel 对应的 roleId
            cch.setState(State.AUTHED);

            // roleId 与 Channel 的对应关系
            GameServerSendService.getInstance().setPacketCrypt(this.getChannelHandler(), "encryptKey");
            GameServerSendService.getInstance().addClient(cch.getChannel().getId(), serverId, cch);

            // 返回
            CR_Login_Res.Builder resBuilder = CR_Login_Res.newBuilder();
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            cch.sendPacket(new SM_CR_LOGIN(resBuilder.build().toByteArray()));
        } catch (Exception e) {
            logger.error("游戏服务器登录失败，重新登录", e);

            // 登陆失败,要求重新登陆
            CR_Login_Res.Builder resBuilder = CR_Login_Res.newBuilder();
            resBuilder.setErrorCode(SeaErrorCode.LOGIN_FAULT.getErrorCode());
            super.sendPacket(new SM_CR_LOGIN(resBuilder.build().toByteArray()));
        }
    }
}








