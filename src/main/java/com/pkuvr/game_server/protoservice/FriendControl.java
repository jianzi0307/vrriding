package com.pkuvr.game_server.protoservice;

import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.exception.SeaException;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.proto.clientproto.CR_Add_Blacklist_Request.CR_Add_Blacklist_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Add_Friend_Apply_Request.CR_Add_Friend_Apply_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Black_List_Request.CR_Black_List_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Cancel_Own_Friend_Apply_Request.CR_Cancel_Own_Friend_Apply_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Delete_Blacklist_Request.CR_Delete_Blacklist_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Delete_Friend_Request.CR_Delete_Friend_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Find_Friend_Request.CR_Find_Friend_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Friend_Apply_List_Request.CR_Friend_Apply_List_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Friend_Apply_Op_Request.CR_Friend_Apply_Op_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Friend_List_Request.CR_Friend_List_Req;
import com.pkuvr.game_server.proto.clientproto.CR_Own_Friend_Apply_List_Request.CR_Own_Friend_Apply_List_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Firend_Confirm_Request.Pvp_Firend_Confirm_Req;
import com.pkuvr.game_server.proto.clientproto.Pvp_Firend_Request.Pvp_Firend_Req;
import com.pkuvr.game_server.proto.commons.BI_Friend_Info.Friend_Info_Mes;
import com.pkuvr.game_server.proto.serverproto.CR_Add_Blacklist_Response.CR_Add_Blacklist_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Add_Friend_Apply_Response.CR_Add_Friend_Apply_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Black_List_Response.CR_Black_List_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Cancel_Own_Friend_Apply_Response.CR_Cancel_Own_Friend_Apply_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Delete_Blacklist_Response.CR_Delete_Blacklist_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Delete_Friend_Response.CR_Delete_Friend_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Find_Friend_Response.CR_Find_Friend_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Friend_Apply_List_Response.CR_Friend_Apply_List_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Friend_Apply_Op_Response.CR_Friend_Apply_Op_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Friend_List_Response.CR_Friend_List_Res;
import com.pkuvr.game_server.proto.serverproto.CR_Own_Friend_Apply_List_Response.CR_Own_Friend_Apply_List_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Friend_Confirm_Response.Pvp_Friend_Confirm_Res;
import com.pkuvr.game_server.proto.serverproto.Pvp_Friend_Response.Pvp_Friend_Res;
import com.pkuvr.game_server.service.FriendService;
import com.pkuvr.game_server.vo.DeleteBlackListVO;
import com.pkuvr.game_server.vo.DeleteFriendVO;
import com.pkuvr.game_server.vo.FriendVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("FriendControl")
public class FriendControl {
    private GameServerSendService gameServerSendService = GameServerSendService.getInstance();
    @Resource
    private FriendService friendService;

    /**
     * 添加好友申请
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] addFriendApply(int roleId, byte[] byteData) {
        CR_Add_Friend_Apply_Res.Builder resBuilder = CR_Add_Friend_Apply_Res.newBuilder();
        try {
            CR_Add_Friend_Apply_Req req = CR_Add_Friend_Apply_Req.parseFrom(byteData);
            friendService.addFriendApply(roleId, req.getToAddFriendRoleId());

            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取申请加好友列表
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getFriendApplyList(int roleId, byte[] byteData) {
        CR_Friend_Apply_List_Res.Builder resBuilder = CR_Friend_Apply_List_Res.newBuilder();
        try {
            CR_Friend_Apply_List_Req req = CR_Friend_Apply_List_Req.parseFrom(byteData);

            List<FriendVO> friendsList = friendService.getApplyFriendsListByRoleId(roleId);
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            for (FriendVO friendVO : friendsList) {
                Friend_Info_Mes.Builder res = Friend_Info_Mes.newBuilder();
                res.setRoleId(friendVO.getRoleId());
                res.setRoleName(friendVO.getRoleName());
                res.setAvatar(friendVO.getAvatar());
                res.setCampId(friendVO.getCampId());
                res.setGeneralDegree(friendVO.getGeneralDegree());
                res.setIsOnline(friendVO.getIsOnline());
                resBuilder.addApplyRoleInfo(res);
            }
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 对申请加好友的列表进行操作
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] friendApplyOp(int roleId, byte[] byteData) {
        CR_Friend_Apply_Op_Res.Builder resBuilder = CR_Friend_Apply_Op_Res.newBuilder();
        try {
            CR_Friend_Apply_Op_Req req = CR_Friend_Apply_Op_Req.parseFrom(byteData);

            int applyOpResult = friendService.friendApplyOp(roleId, req.getApplyRoleId(), req.getApplyOpType());
            // 构造返回值
            if (applyOpResult > 0) {
                resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            } else {
                resBuilder.setErrorCode(SeaErrorCode.FRIEND_APPLY_OP_FAIL.getErrorCode());
            }
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取个人发起的申请加好友信息列表
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getOwnFriendApplyList(int roleId, byte[] byteData) {
        CR_Own_Friend_Apply_List_Res.Builder resBuilder = CR_Own_Friend_Apply_List_Res.newBuilder();
        try {
            CR_Own_Friend_Apply_List_Req req = CR_Own_Friend_Apply_List_Req.parseFrom(byteData);

            List<FriendVO> friendsList = friendService.getOwnFriendApplyListByRoleId(roleId);
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            for (FriendVO friendVO : friendsList) {
                Friend_Info_Mes.Builder res = Friend_Info_Mes.newBuilder();
                res.setRoleId(friendVO.getRoleId());
                res.setRoleName(friendVO.getRoleName());
                res.setAvatar(friendVO.getAvatar());
                res.setCampId(friendVO.getCampId());
                res.setGeneralDegree(friendVO.getGeneralDegree());
                res.setIsOnline(friendVO.getIsOnline());
                resBuilder.addApplyRoleInfo(res);
            }
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 删除个人发起的申请加好友信息
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] cancelOwnFriendApply(int roleId, byte[] byteData) {
        CR_Cancel_Own_Friend_Apply_Res.Builder resBuilder = CR_Cancel_Own_Friend_Apply_Res.newBuilder();
        try {
            CR_Cancel_Own_Friend_Apply_Req req = CR_Cancel_Own_Friend_Apply_Req.parseFrom(byteData);

            int cacelResult = friendService.cancelOwnFriendApply(roleId, req.getToAddFriendApplyRoleId());
            // 构造返回值
            if (cacelResult > 0) {
                resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            } else {
                resBuilder.setErrorCode(SeaErrorCode.OWN_FRIEND_APPLY_OP_FAIL.getErrorCode());
            }
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 删除好友
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] deleteFriend(int roleId, byte[] byteData) {
        CR_Delete_Friend_Res.Builder resBuilder = CR_Delete_Friend_Res.newBuilder();
        try {
            CR_Delete_Friend_Req req = CR_Delete_Friend_Req.parseFrom(byteData);
            DeleteFriendVO deleteFriendVO = friendService.deleteFriend(roleId, req.getRoleId());

            // 构造返回值
            if (deleteFriendVO.getDelResult() == 1) {

//				List<FriendVO> friendsList = friendService.getFriendsListByRoleId(roleId);
//				Friend_List_Res.Builder resListBuilder = Friend_List_Res.newBuilder();
//				resListBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
//				for(FriendVO friendVO : friendsList){
//				 	Friend_Res.Builder res = Friend_Res.newBuilder();
//				 	res.setRoleId(friendVO.getRoleId());
//				 	res.setRoleName(friendVO.getRoleName());
//				 	res.setAvatar(friendVO.getAvatar());
//				 	res.setCampId(friendVO.getCampId());
//				 	res.setGeneralDegree(friendVO.getGeneralDegree());
//				 	res.setIsOnline(friendVO.getIsOnline());
//				 	res.setLastLogoutTime(friendVO.getLastLogoutTime());
//				 	res.setVipLevel(friendVO.getVipLevel());
//				 	resListBuilder.addFriendRes(res);
//				}
//				SM_FRIEND_LIST sendMessage = new SM_FRIEND_LIST(resListBuilder.build().toByteArray());
//				gameServerSendService.sendMessage(roleId, sendMessage);

                resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
                resBuilder.setRoleId(deleteFriendVO.getRoleId());

            } else {
                resBuilder.setErrorCode(SeaErrorCode.ERROR_UNKNOW.getErrorCode());
            }
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取好友列表
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getFriendList(int roleId, byte[] byteData) {
        CR_Friend_List_Res.Builder resBuilder = CR_Friend_List_Res.newBuilder();
        try {
            CR_Friend_List_Req req = CR_Friend_List_Req.parseFrom(byteData);

            List<FriendVO> friendsList = friendService.getFriendsListByRoleId(roleId);
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            for (FriendVO friendVO : friendsList) {
                Friend_Info_Mes.Builder res = Friend_Info_Mes.newBuilder();
                res.setRoleId(friendVO.getRoleId());
                res.setRoleName(friendVO.getRoleName());
                res.setAvatar(friendVO.getAvatar());
                res.setCampId(friendVO.getCampId());
                res.setGeneralDegree(friendVO.getGeneralDegree());
                res.setIsOnline(friendVO.getIsOnline());
                resBuilder.addFriendRes(res);
            }
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 查找好友
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] findFriend(int roleId, byte[] byteData) {
        CR_Find_Friend_Res.Builder resBuilder = CR_Find_Friend_Res.newBuilder();
        try {
            CR_Find_Friend_Req req = CR_Find_Friend_Req.parseFrom(byteData);
            FriendVO friendVO = friendService.findFriend(req.getFindRoleId());
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            if ((friendVO.getRoleName() != null) && (!"".equals(friendVO.getRoleName()))) {
                resBuilder.setRoleId(friendVO.getRoleId());
                resBuilder.setRoleName(friendVO.getRoleName());
                resBuilder.setAvatar(friendVO.getAvatar());
                resBuilder.setCampId(friendVO.getCampId());
                resBuilder.setGeneralDegree(friendVO.getGeneralDegree());
                resBuilder.setIsOnline(friendVO.getIsOnline());
            }

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 好友pvp请求
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] friendPvp(int roleId, byte[] byteData) {
        Pvp_Friend_Res.Builder resBuilder = Pvp_Friend_Res.newBuilder();
        try {
            Pvp_Firend_Req req = Pvp_Firend_Req.parseFrom(byteData);
            friendService.friendPvp(roleId, req.getFriendRoleId());
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            resBuilder.setClientTime(req.getClientTime());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 好友pvp请求确认
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] friendPvpConfirm(int roleId, byte[] byteData) {
        Pvp_Friend_Confirm_Res.Builder resBuilder = Pvp_Friend_Confirm_Res.newBuilder();
        try {
            Pvp_Firend_Confirm_Req req = Pvp_Firend_Confirm_Req.parseFrom(byteData);
            friendService.friendPvpConfirm(roleId, req.getFriendRoleId(), req.getPvpOpcode(), req.getPvpMap(), req.getReasonStr());
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 添加黑名单
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] addBlackList(int roleId, byte[] byteData) {
        CR_Add_Blacklist_Res.Builder resBuilder = CR_Add_Blacklist_Res.newBuilder();
        try {
            CR_Add_Blacklist_Req req = CR_Add_Blacklist_Req.parseFrom(byteData);
            friendService.addBlackList(roleId, req.getRoleId());
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());

        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 删除黑名单
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] deleteBlackList(int roleId, byte[] byteData) {
        CR_Delete_Blacklist_Res.Builder resBuilder = CR_Delete_Blacklist_Res.newBuilder();
        try {
            CR_Delete_Blacklist_Req req = CR_Delete_Blacklist_Req.parseFrom(byteData);
            DeleteBlackListVO deleteBlackListVO = friendService.deleteBlackList(roleId, req.getRoleId());

            // 构造返回值
            if (deleteBlackListVO.getDelResult() == 1) {
//				List<FriendVO> friendsList = friendService.getBlackListByRoleId(roleId);
//				Black_List_Res.Builder resListBuilder = Black_List_Res.newBuilder();
//				resListBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
//				for(FriendVO friendVO : friendsList){
//					Blacklist_Role_Res.Builder res = Blacklist_Role_Res.newBuilder();
//				 	res.setRoleId(friendVO.getRoleId());
//				 	res.setRoleName(friendVO.getRoleName());
//				 	res.setAvatar(friendVO.getAvatar());
//				 	res.setCampId(friendVO.getCampId());
//				 	res.setGeneralDegree(friendVO.getGeneralDegree());
//				 	resListBuilder.addBlackListRes(res);
//				}
//				SM_BLACK_LIST sendMessage = new SM_BLACK_LIST(resListBuilder.build().toByteArray());
//				gameServerSendService.sendMessage(roleId, sendMessage);

                resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
                resBuilder.setRoleId(deleteBlackListVO.getRoleId());
            } else {
                resBuilder.setErrorCode(SeaErrorCode.ERROR_UNKNOW.getErrorCode());
            }
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

    /**
     * 获取黑名单列表
     *
     * @param roleId
     * @param byteData
     * @return
     */
    public byte[] getBlackList(int roleId, byte[] byteData) {
        CR_Black_List_Res.Builder resBuilder = CR_Black_List_Res.newBuilder();
        try {
            CR_Black_List_Req req = CR_Black_List_Req.parseFrom(byteData);

            List<FriendVO> friendsList = friendService.getBlackListByRoleId(roleId);
            // 构造返回值
            resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
            for (FriendVO friendVO : friendsList) {
                Friend_Info_Mes.Builder res = Friend_Info_Mes.newBuilder();
                res.setRoleId(friendVO.getRoleId());
                res.setRoleName(friendVO.getRoleName());
                res.setAvatar(friendVO.getAvatar());
                res.setCampId(friendVO.getCampId());
                res.setGeneralDegree(friendVO.getGeneralDegree());
                resBuilder.addBlackListRes(res);
            }
        } catch (Exception e) {
            resBuilder.setErrorCode(SeaException.logoutException(e, roleId));
        }
        return resBuilder.build().toByteArray();
    }

}
