package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.GameServerChannelHandler;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.network.serverpackets.SM_ENTER_CROSS_SERVER;
import com.pkuvr.game_server.proto.clientproto.Enter_Cross_Server_Request.Enter_Cross_Server_Req;
import com.pkuvr.game_server.proto.serverproto.Enter_Cross_Server_Response.Enter_Cross_Server_Res;
import com.pkuvr.game_server.protoservice.LoginControl;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.ENTER_CROSS_SERVER_REQ, state = State.CONNECTED)
public class CM_ENTER_CROSS_SERVER extends AbstractCM {
    private static final Logger logger = Logger.getLogger(CM_ENTER_CROSS_SERVER.class);

    @Override
    protected void runImpl() {
        GameServerChannelHandler cch = getChannelHandler();
        try {
            // 请求参数
            Enter_Cross_Server_Req req = Enter_Cross_Server_Req.parseFrom(byteData);
            int roleId = req.getRoleId();
            int serverId = req.getServerId();

            // 验证是否有权登陆
            LoginControl loginControl = GetBeanHelper.getLoginControl();
            logger.info("-------------------------roleId ===== " + roleId + "    serverID ====== " + serverId);
            byte[] prr = loginControl.enterCrossServer(roleId, serverId, getChannelHandler(), byteData);
            // Channel 的基本属性,同时变更状态为验证过
            cch.setRoleId(req.getRoleId()); // 该 channel 对应的 roleId
            cch.setState(State.AUTHED);

            // roleId 与 Channel 的对应关系
            GameServerSendService.getInstance().addClient(cch.getChannel().getId(), roleId, cch);

            // 返回
            cch.sendPacket(new SM_ENTER_CROSS_SERVER(prr));
        } catch (Exception e) {
            logger.error("用户登录失败，重新登录", e);

            // 登陆失败,要求重新登陆
            Enter_Cross_Server_Res.Builder resBuilder = Enter_Cross_Server_Res.newBuilder();
            resBuilder.setErrorCode(SeaErrorCode.LOGIN_FAULT.getErrorCode());
            super.sendPacket(new SM_ENTER_CROSS_SERVER(resBuilder.build().toByteArray()));
        }
    }
}








