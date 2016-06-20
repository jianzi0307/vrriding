package com.pkuvr.game_server.service;

import com.pkuvr.game_server.cache.PlayerRealtimePvpCacheManager;
import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerRealtimePvpVO;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.dao.PlayerblacklistMapper;
import com.pkuvr.game_server.dao.PlayerfriendsMapper;
import com.pkuvr.game_server.dao.PlayerfriendsapplyMapper;
import com.pkuvr.game_server.domain.*;
import com.pkuvr.game_server.enumerate.TaskTypeCode;
import com.pkuvr.game_server.exception.*;
import com.pkuvr.game_server.network.GameServerSendService;
import com.pkuvr.game_server.proto.clientproto.CR_Friend_Apply_Op_Request.FriendApplyOpEnum;
import com.pkuvr.game_server.proto.clientproto.Pvp_Firend_Confirm_Request.PvpFriendOpcodeEnum;
import com.pkuvr.game_server.proto.serverproto.Pvp_Friend_Notify_Response.PvpMessageEnum;
import com.pkuvr.game_server.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("FriendService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FriendService {
    @Resource
    private PlayerfriendsMapper friendsMapper;
    @Resource
    private PlayerblacklistMapper playerblacklistMapper;
    @Resource
    private PlayerfriendsapplyMapper playerfriendsapplyMapper;
    @Resource
    private SendService sendService;
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private PlayerRealtimePvpCacheManager playerRealtimePvpCacheManager;

    private GameServerSendService gameServerSendService = GameServerSendService.getInstance();

    public int addFriendApply(int roleId, int toAddFriendRoleId) throws SeaException {
        if (toAddFriendRoleId == 0) {
            throw new SeaException(SeaErrorCode.NO_SUCH_ROLE);
        }

        int addFriendApplyResult = 0;

        PlayerfriendsapplyExample playerfriendsapplyExample = new PlayerfriendsapplyExample();
        playerfriendsapplyExample.createCriteria().andRoleidEqualTo(toAddFriendRoleId).andApplyroleidEqualTo(roleId);
        List<PlayerfriendsapplyKey> resultList = playerfriendsapplyMapper.selectByExample(playerfriendsapplyExample);
        if (resultList != null && resultList.size() > 0) {
            throw new SeaException(SeaErrorCode.FRIEND_APPLY_ALREADY_EXISTS);
        }

        PlayerfriendsapplyKey record = new PlayerfriendsapplyKey();
        record.setRoleid(toAddFriendRoleId);
        record.setApplyroleid(roleId);
        addFriendApplyResult = playerfriendsapplyMapper.insertSelective(record);
        return addFriendApplyResult;
    }

    public int friendApplyOp(int roleId, int applyRoleId, FriendApplyOpEnum friendApplyOpEnum) throws SeaException, FriendAlreadyExistsException, TooManyFriendsException {
        if (applyRoleId != 0) {
            if (friendApplyOpEnum == FriendApplyOpEnum.AGREE) {
                List<FriendVO> friendsList = getFriendsListByRoleId(roleId);
                if (friendsList.size() > 100) {
                    throw new TooManyFriendsException("roleId = " + roleId);
                }

                PlayerfriendsExample playerfriendsExample = new PlayerfriendsExample();
                playerfriendsExample.createCriteria().andFriendidEqualTo(applyRoleId).andRoleidEqualTo(roleId);
                List<PlayerfriendsKey> playerFriendsList = friendsMapper.selectByExample(playerfriendsExample);
                if (playerFriendsList.size() > 0) {
                    throw new FriendAlreadyExistsException("roleId = " + roleId);
                }

                PlayerfriendsKey record = new PlayerfriendsKey();
                record.setRoleid(roleId);
                record.setFriendid(applyRoleId);
                int addResult = friendsMapper.insert(record);

                // 判断对方是否已经添加自己为好友
                PlayerfriendsExample oExample = new PlayerfriendsExample();
                oExample.createCriteria().andRoleidEqualTo(applyRoleId).andFriendidEqualTo(roleId);
                List<PlayerfriendsKey> results = friendsMapper.selectByExample(oExample);
                if (results == null || results.size() == 0) {
                    PlayerfriendsKey otherRecord = new PlayerfriendsKey();
                    otherRecord.setRoleid(applyRoleId);
                    otherRecord.setFriendid(roleId);
                    friendsMapper.insertSelective(otherRecord);
                }

                return addResult;
            } else if (friendApplyOpEnum == FriendApplyOpEnum.REFUSE) {
                PlayerfriendsapplyKey key = new PlayerfriendsapplyKey();
                key.setRoleid(roleId);
                key.setApplyroleid(applyRoleId);
                int deleteResult = playerfriendsapplyMapper.deleteByPrimaryKey(key);
                return deleteResult;
            }
            return 0;
        } else {
            throw new SeaException(SeaErrorCode.NO_SUCH_ROLE);
        }
    }

    public List<FriendVO> getApplyFriendsListByRoleId(int roleId) {
        PlayerfriendsapplyExample playerfriendsapplyExample = new PlayerfriendsapplyExample();
        playerfriendsapplyExample.createCriteria().andRoleidEqualTo(roleId);
        List<PlayerfriendsapplyKey> friendsList = playerfriendsapplyMapper.selectByExample(playerfriendsapplyExample);
        if (friendsList.size() == 0)
            return new ArrayList<FriendVO>();

        List<FriendVO> rtnOnline = new ArrayList<FriendVO>();
        List<FriendVO> offOnline = new ArrayList<FriendVO>();
        for (PlayerfriendsapplyKey friend : friendsList) {
            PlayerRoleVO playerRoleFriend = playerRoleCacheManager.get(friend.getApplyroleid());
            FriendVO friendVO = new FriendVO();
            friendVO.setRoleId(playerRoleFriend.getRoleId());
            friendVO.setRoleName(playerRoleFriend.getRoleName());
            friendVO.setCampId(playerRoleFriend.getCampId());
            friendVO.setGeneralDegree(playerRoleFriend.getRoleLevel());
            friendVO.setAvatar(playerRoleFriend.getRoleAvatar());
            if (gameServerSendService.isOnline(playerRoleFriend.getRoleId())) {
                friendVO.setIsOnline(1);
                rtnOnline.add(friendVO);
            } else {
                friendVO.setIsOnline(0);
                offOnline.add(friendVO);
            }
        }

        List<FriendVO> rtn = new ArrayList<FriendVO>();
        rtn.addAll(rtnOnline);
        rtn.addAll(offOnline);
        return rtn;
    }

    public List<FriendVO> getOwnFriendApplyListByRoleId(int roleId) {
        PlayerfriendsapplyExample playerfriendsapplyExample = new PlayerfriendsapplyExample();
        playerfriendsapplyExample.createCriteria().andApplyroleidEqualTo(roleId);
        List<PlayerfriendsapplyKey> friendsList = playerfriendsapplyMapper.selectByExample(playerfriendsapplyExample);
        if (friendsList.size() == 0)
            return new ArrayList<FriendVO>();

        List<FriendVO> rtnOnline = new ArrayList<FriendVO>();
        List<FriendVO> offOnline = new ArrayList<FriendVO>();
        for (PlayerfriendsapplyKey friend : friendsList) {
            PlayerRoleVO playerRoleFriend = playerRoleCacheManager.get(friend.getRoleid());
            FriendVO friendVO = new FriendVO();
            friendVO.setRoleId(playerRoleFriend.getRoleId());
            friendVO.setRoleName(playerRoleFriend.getRoleName());
            friendVO.setCampId(playerRoleFriend.getCampId());
            friendVO.setGeneralDegree(playerRoleFriend.getRoleLevel());
            friendVO.setAvatar(playerRoleFriend.getRoleAvatar());
            if (gameServerSendService.isOnline(playerRoleFriend.getRoleId())) {
                friendVO.setIsOnline(1);
                rtnOnline.add(friendVO);
            } else {
                friendVO.setIsOnline(0);
                offOnline.add(friendVO);
            }
        }

        List<FriendVO> rtn = new ArrayList<FriendVO>();
        rtn.addAll(rtnOnline);
        rtn.addAll(offOnline);
        return rtn;
    }

    public int cancelOwnFriendApply(int roleId, int toAddFriendApplyRoleId) throws NoSuchRoleException {
        if (roleId != 0) {
            int cancelResult = 0;
            PlayerfriendsapplyExample playerfriendsapplyExample = new PlayerfriendsapplyExample();
            playerfriendsapplyExample.createCriteria().andApplyroleidEqualTo(roleId).andRoleidEqualTo(toAddFriendApplyRoleId);
            cancelResult = playerfriendsapplyMapper.deleteByExample(playerfriendsapplyExample);
            return cancelResult;
        } else {
            throw new NoSuchRoleException("roleId=" + roleId);
        }
    }

    public List<FriendVO> getFriendsListByRoleId(int roleId) {
        PlayerfriendsExample friendsExample = new PlayerfriendsExample();
        friendsExample.createCriteria().andRoleidEqualTo(roleId);
        List<PlayerfriendsKey> friendsList = friendsMapper.selectByExample(friendsExample);
        if (friendsList.size() == 0)
            return new ArrayList<FriendVO>();

        List<FriendVO> rtnOnline = new ArrayList<FriendVO>();
        List<FriendVO> offOnline = new ArrayList<FriendVO>();
        for (PlayerfriendsKey friend : friendsList) {
            PlayerRoleVO playerRoleFriend = playerRoleCacheManager.get(friend.getFriendid());
            FriendVO friendVO = new FriendVO();
            friendVO.setRoleId(playerRoleFriend.getRoleId());
            friendVO.setRoleName(playerRoleFriend.getRoleName());
            friendVO.setCampId(playerRoleFriend.getCampId());
            friendVO.setGeneralDegree(playerRoleFriend.getRoleLevel());
            friendVO.setAvatar(playerRoleFriend.getRoleAvatar());
            if (gameServerSendService.isOnline(playerRoleFriend.getRoleId())) {
                friendVO.setIsOnline(1);
                rtnOnline.add(friendVO);
            } else {
                friendVO.setIsOnline(0);
                offOnline.add(friendVO);
            }
        }

        List<FriendVO> rtn = new ArrayList<FriendVO>();
        rtn.addAll(rtnOnline);
        rtn.addAll(offOnline);
        return rtn;
    }

    public DeleteFriendVO deleteFriend(int roleId, int friendRoleId) throws NoSuchRoleException {
        if (friendRoleId == 0) {
            throw new NoSuchRoleException("friendRoleId=" + friendRoleId);
        }

        if (friendRoleId != 0) {
            PlayerfriendsKey key = new PlayerfriendsKey();
            key.setRoleid(roleId);
            key.setFriendid(friendRoleId);
            int delResult = friendsMapper.deleteByPrimaryKey(key);
            DeleteFriendVO deleteFriendVO = new DeleteFriendVO();
            deleteFriendVO.setDelResult(delResult);
            deleteFriendVO.setRoleId(friendRoleId);
            return deleteFriendVO;
        } else {
            throw new NoSuchRoleException("friendRoleId=" + friendRoleId);
        }
    }

    public FriendVO findFriend(int friendRoleId) throws NoSuchRoleException {
        if (friendRoleId == 0) {
            throw new NoSuchRoleException("friendRoleId = " + friendRoleId);
        }

        FriendVO friendVO = new FriendVO();
        PlayerRoleVO playerRoleFriend = playerRoleCacheManager.get(friendRoleId);
        if (playerRoleFriend != null) {
            friendVO.setRoleId(playerRoleFriend.getRoleId());
            friendVO.setRoleName(playerRoleFriend.getRoleName());
            friendVO.setCampId(playerRoleFriend.getCampId());
            friendVO.setGeneralDegree(playerRoleFriend.getRoleLevel());
            friendVO.setAvatar(playerRoleFriend.getRoleAvatar());
            if (gameServerSendService.isOnline(playerRoleFriend.getRoleId())) {
                friendVO.setIsOnline(1);
            } else {
                friendVO.setIsOnline(0);
            }
        }

        return friendVO;
    }

    public void friendPvp(int roleId, int friendRoleId) throws NoSuchRoleException, FriendNotOnlineException, FriendPvpNotFriendException {
        if (roleId < 0) {
            throw new NoSuchRoleException();
        }

        int isFriendRet = isFriend(roleId, friendRoleId);
        if (isFriendRet != 1) {
            throw new FriendPvpNotFriendException();
        }

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        PvpRankInfoVO ownRankInfoVO = new PvpRankInfoVO();
        ownRankInfoVO.setRoleId(roleId);
        ownRankInfoVO.setRoleName(playerRoleVO.getRoleName());
        ownRankInfoVO.setCampId(playerRoleVO.getCampId());
        ownRankInfoVO.setGeneralDegree(playerRoleVO.getRankLevel());
        ownRankInfoVO.setAvatar(playerRoleVO.getRoleAvatar());
        ownRankInfoVO.setHonor(playerRoleVO.getRoleHonor());
        sendService.sendFriendPvpInfo(friendRoleId, ownRankInfoVO, PvpMessageEnum.RECEIVE, "");
    }

    public void friendPvpConfirm(int roleId, int friendRoleId, PvpFriendOpcodeEnum pvpFriendOpcodeEnum, String pvpMap, String reasonStr) throws NoSuchRoleException, TaskDataException {
        if (pvpFriendOpcodeEnum == PvpFriendOpcodeEnum.REFUSE) {
            PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
            PvpRankInfoVO ownRankInfoVO = new PvpRankInfoVO();
            ownRankInfoVO.setRoleId(roleId);
            ownRankInfoVO.setRoleName(playerRoleVO.getRoleName());
            ownRankInfoVO.setCampId(playerRoleVO.getCampId());
            ownRankInfoVO.setGeneralDegree(playerRoleVO.getRankLevel());
            ownRankInfoVO.setAvatar(playerRoleVO.getRoleAvatar());
            ownRankInfoVO.setHonor(playerRoleVO.getRoleHonor());
            sendService.sendFriendPvpInfo(friendRoleId, ownRankInfoVO, PvpMessageEnum.REFUSE, reasonStr);
        } else if (pvpFriendOpcodeEnum == PvpFriendOpcodeEnum.CANCLE) {
            PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
            PvpRankInfoVO ownRankInfoVO = new PvpRankInfoVO();
            ownRankInfoVO.setRoleId(roleId);
            ownRankInfoVO.setRoleName(playerRoleVO.getRoleName());
            ownRankInfoVO.setCampId(playerRoleVO.getCampId());
            ownRankInfoVO.setGeneralDegree(playerRoleVO.getRankLevel());
            ownRankInfoVO.setAvatar(playerRoleVO.getRoleAvatar());
            ownRankInfoVO.setHonor(playerRoleVO.getRoleHonor());
            sendService.sendFriendPvpInfo(friendRoleId, ownRankInfoVO, PvpMessageEnum.CANCLE, reasonStr);
        } else {
            PvpMatchVO pvpMatchVO = new PvpMatchVO();

            PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
            PvpRankInfoVO ownRankInfoVO = new PvpRankInfoVO();
            ownRankInfoVO.setRoleId(roleId);
            ownRankInfoVO.setRoleName(playerRoleVO.getRoleName());
            ownRankInfoVO.setCampId(playerRoleVO.getCampId());
            ownRankInfoVO.setGeneralDegree(playerRoleVO.getRankLevel());
            ownRankInfoVO.setAvatar(playerRoleVO.getRoleAvatar());
            ownRankInfoVO.setHonor(playerRoleVO.getRoleHonor());
            pvpMatchVO.setOwnPlayerRank(ownRankInfoVO);

            PlayerRoleVO matchRoleVO = playerRoleCacheManager.get(friendRoleId);
            PvpRankInfoVO matchPvpRankInfoVO = new PvpRankInfoVO();
            matchPvpRankInfoVO.setRoleId(friendRoleId);
            matchPvpRankInfoVO.setRoleName(matchRoleVO.getRoleName());
            matchPvpRankInfoVO.setCampId(matchRoleVO.getCampId());
            matchPvpRankInfoVO.setGeneralDegree(matchRoleVO.getRankLevel());
            matchPvpRankInfoVO.setAvatar(matchRoleVO.getRoleAvatar());
            matchPvpRankInfoVO.setHonor(matchRoleVO.getRoleHonor());
            pvpMatchVO.setOtherPlayerRank(matchPvpRankInfoVO);

            pvpMatchVO.setOwnRewardHonor(0);
            pvpMatchVO.setOwnSubHonor(0);
            pvpMatchVO.setOtherRewardHonor(0);
            pvpMatchVO.setOtherSubHonor(0);

            // 匹配成功后将匹配成功的roleid写入本地缓存
            PlayerRealtimePvpVO ownRealtimePvpVO = new PlayerRealtimePvpVO();
            ownRealtimePvpVO.setOtherRoleId(friendRoleId);
            ownRealtimePvpVO.setRewardHonor(pvpMatchVO.getOwnRewardHonor());
            ownRealtimePvpVO.setSubHonor(pvpMatchVO.getOwnSubHonor());
            ownRealtimePvpVO.setPvpMap(pvpMap);
            ownRealtimePvpVO.setBattleEnd(false);
            ownRealtimePvpVO.setRewardHonor(false);
            playerRealtimePvpCacheManager.put(roleId, ownRealtimePvpVO);

            PlayerRealtimePvpVO playerRealtimePvpVO = new PlayerRealtimePvpVO();
            playerRealtimePvpVO.setOtherRoleId(roleId);
            playerRealtimePvpVO.setRewardHonor(pvpMatchVO.getOtherRewardHonor());
            playerRealtimePvpVO.setSubHonor(pvpMatchVO.getOtherSubHonor());
            playerRealtimePvpVO.setPvpMap(pvpMap);
            playerRealtimePvpVO.setBattleEnd(false);
            playerRealtimePvpVO.setRewardHonor(false);
            playerRealtimePvpCacheManager.put(friendRoleId, playerRealtimePvpVO);

            long deployEndTime = System.currentTimeMillis() + 45000L;

            sendService.sendPvpMatchSuccess(roleId, ownRankInfoVO, matchPvpRankInfoVO,
                    pvpMatchVO.getOwnRewardHonor(), pvpMatchVO.getOwnSubHonor(), false, deployEndTime, pvpMap, true);

            sendService.sendPvpMatchSuccess(friendRoleId, matchPvpRankInfoVO, ownRankInfoVO,
                    pvpMatchVO.getOtherRewardHonor(), pvpMatchVO.getOtherSubHonor(), true, deployEndTime, pvpMap, true);

            sendService.sendCrossAddTaskFinish(roleId, TaskTypeCode.BITE.getType());
            sendService.sendCrossAddTaskFinish(friendRoleId, TaskTypeCode.BITE.getType());
        }
    }

    public int isFriend(int roleId, int friendRoleId) {
        int isFriendRet = 0;

        PlayerfriendsExample playerfriendsExample = new PlayerfriendsExample();
        playerfriendsExample.createCriteria().andFriendidEqualTo(friendRoleId).andRoleidEqualTo(roleId);
        List<PlayerfriendsKey> playerFriendsList = friendsMapper.selectByExample(playerfriendsExample);
        if (playerFriendsList.size() > 0) {
            isFriendRet = 1;
        }

        return isFriendRet;
    }

    public int addBlackList(int roleId, int blackListRoleId) throws SeaException {
        if (blackListRoleId > 0) {
            PlayerblacklistExample playerblacklistExample = new PlayerblacklistExample();
            playerblacklistExample.createCriteria().andRoleidEqualTo(roleId).andBlacklistroleidEqualTo(blackListRoleId);
            List<PlayerblacklistKey> blackList = playerblacklistMapper.selectByExample(playerblacklistExample);
            if (blackList != null && blackList.size() > 0) {
                throw new SeaException(SeaErrorCode.BLACKLIST_ALREADY_EXISTS);
            }

            PlayerblacklistKey playerblacklistKey = new PlayerblacklistKey();
            playerblacklistKey.setRoleid(roleId);
            playerblacklistKey.setBlacklistroleid(blackListRoleId);
            int addResult = playerblacklistMapper.insertSelective(playerblacklistKey);
            return addResult;
        } else {
            throw new SeaException(SeaErrorCode.NO_SUCH_ROLE);
        }
    }

    public DeleteBlackListVO deleteBlackList(int roleId, int blackListRoleId) throws SeaException {
        if (blackListRoleId > 0) {
            PlayerblacklistExample playerblacklistExample = new PlayerblacklistExample();
            playerblacklistExample.createCriteria().andRoleidEqualTo(roleId).andBlacklistroleidEqualTo(blackListRoleId);
            List<PlayerblacklistKey> blackList = playerblacklistMapper.selectByExample(playerblacklistExample);
            if (blackList == null || blackList.size() == 0) {
                throw new SeaException(SeaErrorCode.BLACKLIST_NOT_EXISTS);
            }

            PlayerblacklistKey playerblacklistKey = new PlayerblacklistKey();
            playerblacklistKey.setRoleid(roleId);
            playerblacklistKey.setBlacklistroleid(blackListRoleId);
            int deleteResult = playerblacklistMapper.deleteByPrimaryKey(playerblacklistKey);
            DeleteBlackListVO deleteBlackListVO = new DeleteBlackListVO();
            deleteBlackListVO.setDelResult(deleteResult);
            deleteBlackListVO.setRoleId(blackListRoleId);
            return deleteBlackListVO;
        } else {
            throw new SeaException(SeaErrorCode.NO_SUCH_ROLE);
        }
    }

    public List<FriendVO> getBlackListByRoleId(int roleId) {
        PlayerblacklistExample playerblacklistExample = new PlayerblacklistExample();
        playerblacklistExample.createCriteria().andRoleidEqualTo(roleId);
        List<PlayerblacklistKey> blackList = playerblacklistMapper.selectByExample(playerblacklistExample);
        if (blackList.size() == 0)
            return new ArrayList<FriendVO>();

        List<FriendVO> rtnOnline = new ArrayList<FriendVO>();
        for (PlayerblacklistKey friend : blackList) {
            PlayerRoleVO playerRoleBlackList = playerRoleCacheManager.get(friend.getBlacklistroleid());
            FriendVO friendVO = new FriendVO();
            friendVO.setRoleId(playerRoleBlackList.getRoleId());
            friendVO.setRoleName(playerRoleBlackList.getRoleName());
            friendVO.setCampId(playerRoleBlackList.getCampId());
            friendVO.setGeneralDegree(playerRoleBlackList.getRoleLevel());
            friendVO.setAvatar(playerRoleBlackList.getRoleAvatar());
            rtnOnline.add(friendVO);
        }

        List<FriendVO> rtn = new ArrayList<FriendVO>();
        rtn.addAll(rtnOnline);
        return rtn;
    }

}
