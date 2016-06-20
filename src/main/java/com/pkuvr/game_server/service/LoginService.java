package com.pkuvr.game_server.service;

import com.pkuvr.commons.rc4.PacketCrypt;
import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.exception.NoSuchRoleException;
import com.pkuvr.game_server.exception.PleaseLoginAgainException;
import com.pkuvr.game_server.exception.TaskDataException;
import com.pkuvr.game_server.network.GameServerChannelHandler;
import com.pkuvr.game_server.redis.SessionKeyHMapCpt;
import com.pkuvr.game_server.vo.ServerInfoVO;
import com.pkuvr.login_server.dao.ServerMapper;
import com.pkuvr.login_server.domain.Server;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("LoginService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class LoginService {
    private static final Logger logger = Logger.getLogger(LoginService.class);
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private SendService sendService;
    @Resource
    private ServerMapper serverMapper;
    @Resource
    private SessionKeyHMapCpt sessionKeyHMapCpt;

    public boolean enterCrossServer(int roleId, int serverId, GameServerChannelHandler gameServerChannelHandler) throws PleaseLoginAgainException, NoSuchRoleException, TaskDataException {
        // 1.验证用户的 sessionKey
        String redisSessionKey = sessionKeyHMapCpt.getSessionKey(roleId);
        if (redisSessionKey == null)
            throw new PleaseLoginAgainException();

        gameServerChannelHandler.setPacketCrypt(new PacketCrypt(redisSessionKey));

        // 2.发送获取玩家pvp基本信息
        //sendService.sendCrossRoleInfo(roleId, roleId, serverId);

        return true;
    }

    public ServerInfoVO getServerInfo(int serverID) {
        ServerInfoVO result = new ServerInfoVO();
        Server server = serverMapper.selectByPrimaryKey(serverID);
        if (server != null) {
            result.setServerId(server.getServerId());
            result.setServerIp(server.getIp());
            result.setServerPort(server.getPortNumber());
        }

        return result;
    }

    public void reconnectCrossServer(int roleId) {
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        if (playerRoleVO != null && playerRoleVO.getServerId() > 0) {
            sendService.sendCrossRoleInfo(roleId, roleId, playerRoleVO.getServerId());
        }
    }

}
