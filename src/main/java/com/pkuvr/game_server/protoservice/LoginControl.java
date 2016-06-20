package com.pkuvr.game_server.protoservice;

import com.google.protobuf.InvalidProtocolBufferException;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.exception.PleaseLoginAgainException;
import com.pkuvr.game_server.exception.SeaException;
import com.pkuvr.game_server.network.GameServerChannelHandler;
import com.pkuvr.game_server.proto.clientproto.Enter_Cross_Server_Request.Enter_Cross_Server_Req;
import com.pkuvr.game_server.proto.serverproto.Enter_Cross_Server_Response.Enter_Cross_Server_Res;
import com.pkuvr.game_server.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("LoginControl")
public class LoginControl {
    private static final Logger logger = Logger.getLogger(LoginControl.class);
    @Resource
    private LoginService loginService;

    /**
     * 进入跨服服务器
     *
     * @param byteData
     * @param roleId
     * @return
     * @throws PleaseLoginAgainException
     * @throws InvalidProtocolBufferException
     */
    public byte[] enterCrossServer(int roleId, int serverId, GameServerChannelHandler gameServerChannelHandler, byte[] byteData) throws PleaseLoginAgainException, InvalidProtocolBufferException {
        Enter_Cross_Server_Res.Builder resBuilder = Enter_Cross_Server_Res.newBuilder();
        try {
            // 请求参数
            Enter_Cross_Server_Req req = Enter_Cross_Server_Req.parseFrom(byteData);

            logger.info("进入跨服服务器,roleId=" + roleId);

            // 处理类
            loginService.enterCrossServer(roleId, serverId, gameServerChannelHandler);

            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }

        return resBuilder.build().toByteArray();
    }

}
