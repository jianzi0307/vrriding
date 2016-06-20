package com.pkuvr.game_server.service;

import com.pkuvr.game_server.constant.AppConfig;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.dao.NpcresourcemineMapper;
import com.pkuvr.game_server.dao.PlayerresourcemineMapper;
import com.pkuvr.game_server.dao.PlayerresourceminecollectMapper;
import com.pkuvr.game_server.dao.ResourceminecollectlogMapper;
import com.pkuvr.game_server.domain.*;
import com.pkuvr.game_server.exception.SeaException;
import com.pkuvr.game_server.utils.ConvertStringAndDate;
import com.pkuvr.game_server.utils.DateUtil;
import com.pkuvr.game_server.vo.MineCollectLogVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MineCollectService {
    private static final Logger logger = Logger.getLogger(MineCollectService.class);

    @Resource
    private ResourceminecollectlogMapper resourceminecollectlogMapper;
    @Resource
    private PlayerresourceminecollectMapper playerresourceminecollectMapper;
    @Resource
    private NpcresourcemineMapper npcresourcemineMapper;
    @Resource
    private PlayerresourcemineMapper playerresourcemineMapper;

    /**
     * 资源点开始收集
     *
     * @return
     */
    public void collectStart(int roleId, long resMineID) throws SeaException, ParseException {
        Npcresourcemine npcresourcemine = npcresourcemineMapper.selectByPrimaryKey(resMineID);
        if (npcresourcemine == null) {
            throw new SeaException(SeaErrorCode.MINE_NOT_EXIST);
        }

        PlayerresourceminecollectExample playerresourceminecollectExample = new PlayerresourceminecollectExample();
        playerresourceminecollectExample.createCriteria().andRoleidEqualTo(roleId);
        List<Playerresourceminecollect> resourceMineCollectList = playerresourceminecollectMapper.selectByExample(playerresourceminecollectExample);
        if (resourceMineCollectList != null && resourceMineCollectList.size() > 0) {
            throw new SeaException(SeaErrorCode.MINE_ALREADY_IN_COLLECTED);
        }

        PlayerresourcemineKey playerresourcemineKey = new PlayerresourcemineKey();
        playerresourcemineKey.setRoleid(roleId);
        playerresourcemineKey.setNpcresmineid(resMineID);
        PlayerresourcemineWithBLOBs playerresourcemineWithBLOBs = playerresourcemineMapper.selectByPrimaryKey(playerresourcemineKey);
        if (playerresourcemineWithBLOBs == null) {
            throw new SeaException(SeaErrorCode.MINE_CAN_NOT_COLLECT);
        }

        long currentTime = System.currentTimeMillis();
        String dateCaculateStr = DateUtil.longDateToString(currentTime);
        long beginDate = ConvertStringAndDate.convertStringToDate(dateCaculateStr + " 05:00:00", "yyyy-MM-dd HH:mm:ss").getTime();
        if (beginDate < currentTime) {
            beginDate = beginDate - 86400000L;
        }

        ResourceminecollectlogExample resourceminecollectlogExample = new ResourceminecollectlogExample();
        resourceminecollectlogExample.createCriteria().andRoleidEqualTo(roleId).andCollectresultEqualTo(AppConfig.BATTLE_SUCCESS_RESULT).andCollectendtimeGreaterThanOrEqualTo(beginDate);
        resourceminecollectlogExample.setOrderByClause("collectLogID desc");
        List<Resourceminecollectlog> collectResultList = resourceminecollectlogMapper.selectByExample(resourceminecollectlogExample);
        if (collectResultList != null && collectResultList.size() >= 3) {
            throw new SeaException(SeaErrorCode.MINE_COLLECT_TOO_MANY);
        }

        Playerresourceminecollect playerresourceminecollect = new Playerresourceminecollect();
        playerresourceminecollect.setNpcresmineid(resMineID);
        playerresourceminecollect.setRoleid(roleId);
        playerresourceminecollect.setProducerestype(playerresourcemineWithBLOBs.getProducerestype());
        // 根据计算公式计算出可以采集的资源数和采集所用时间
        int collectResourceNum = (playerresourcemineWithBLOBs.getMinebuildinglevel() * playerresourcemineWithBLOBs.getMinebuildinglevel() * 100);
        int collectSecond = (playerresourcemineWithBLOBs.getMinebuildinglevel() * 30) / 600;
        long collectMilliSecond = (collectSecond + 1) * 600000L;
        playerresourceminecollect.setRewardresource(collectResourceNum);
        playerresourceminecollect.setCollectstarttime(currentTime);
        playerresourceminecollect.setCollectendtime(currentTime + collectMilliSecond);

        playerresourceminecollectMapper.insertSelective(playerresourceminecollect);
    }

    /**
     * 资源点收集日志
     *
     * @return
     */
    public List<MineCollectLogVO> getCollectLogList(int roleId) throws SeaException {
        List<MineCollectLogVO> collectLogList = new ArrayList<MineCollectLogVO>();

        // 获取一个月内的收集日志
        long beginDate = System.currentTimeMillis() - 2592000000L;
        ResourceminecollectlogExample resourceminecollectlogExample = new ResourceminecollectlogExample();
        resourceminecollectlogExample.createCriteria().andRoleidEqualTo(roleId).andCollectendtimeGreaterThanOrEqualTo(beginDate);
        resourceminecollectlogExample.setOrderByClause("collectLogID desc");
        List<Resourceminecollectlog> collectResultList = resourceminecollectlogMapper.selectByExample(resourceminecollectlogExample);
        for (Resourceminecollectlog resourceminecollectlog : collectResultList) {
            MineCollectLogVO mineCollectLogVO = new MineCollectLogVO();
            mineCollectLogVO.setRoleID(resourceminecollectlog.getRoleid());
            mineCollectLogVO.setResMineInstanceID(resourceminecollectlog.getResmineinstanceid());
            mineCollectLogVO.setCollectEndTime(resourceminecollectlog.getCollectendtime());
            mineCollectLogVO.setCollectResult(resourceminecollectlog.getCollectresult());
            if (resourceminecollectlog.getCollectresult() == AppConfig.BATTLE_SUCCESS_RESULT) {
                mineCollectLogVO.setProduceResType(resourceminecollectlog.getProducerestype());
                mineCollectLogVO.setRewardResource(resourceminecollectlog.getRewardresource());
            } else {
                mineCollectLogVO.setAttackerRoleId(resourceminecollectlog.getAttackerroleid());
                mineCollectLogVO.setAttackerRoleName(resourceminecollectlog.getAttackerrolename());
            }
            collectLogList.add(mineCollectLogVO);
        }
        return collectLogList;
    }

    /**
     * 获取玩家资源点收集次数，每日凌晨5点刷新
     *
     * @return
     */
    public int getCollectTimes(int roleId) throws ParseException {
        int collectTimes = 0;
        long currentTime = System.currentTimeMillis();
        String dateCaculateStr = DateUtil.longDateToString(currentTime);
        long beginDate = ConvertStringAndDate.convertStringToDate(dateCaculateStr + " 05:00:00", "yyyy-MM-dd HH:mm:ss").getTime();
        if (beginDate > currentTime) {
            beginDate = beginDate - 86400000L;
        }

        ResourceminecollectlogExample resourceminecollectlogExample = new ResourceminecollectlogExample();
        resourceminecollectlogExample.createCriteria().andRoleidEqualTo(roleId).andCollectresultEqualTo(AppConfig.BATTLE_SUCCESS_RESULT).andCollectendtimeGreaterThanOrEqualTo(beginDate);
        resourceminecollectlogExample.setOrderByClause("collectLogID desc");
        List<Resourceminecollectlog> collectResultList = resourceminecollectlogMapper.selectByExample(resourceminecollectlogExample);
        if (collectResultList != null) {
            if (collectResultList.size() <= 3) {
                collectTimes = collectResultList.size();
            } else {
                collectTimes = 3;
            }
        }

        return collectTimes;
    }

}
