package com.pkuvr.game_server.service;

import com.pkuvr.game_server.cache.PlayerBufferCacheMananger;
import com.pkuvr.game_server.cache.PlayerMineAttackedCacheManager;
import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerBufferVO;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.AppConfig;
import com.pkuvr.game_server.dao.PlayermineleftresourceMapper;
import com.pkuvr.game_server.dao.PlayerresourcemineMapper;
import com.pkuvr.game_server.dao.PlayerroleMapper;
import com.pkuvr.game_server.domain.*;
import com.pkuvr.game_server.enumerate.ConsumptionTypeEnum;
import com.pkuvr.game_server.enumerate.ResourcesEnum;
import com.pkuvr.game_server.exception.CanNotCollectResourceException;
import com.pkuvr.game_server.exception.MineUnderAttackedException;
import com.pkuvr.game_server.exception.NoSuchRoleException;
import com.pkuvr.game_server.exception.TaskDataException;
import com.pkuvr.game_server.vo.HavestVO;
import com.pkuvr.game_server.vo.MineHarvestResourceVO;
import com.pkuvr.game_server.vo.MineProductivityVO;
import com.pkuvr.game_server.vo.PlayerMineResourceVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("PlayerMineResourceService")
public class MineResourceService {

    private static final Logger logger = Logger.getLogger(MineResourceService.class);

    @Resource
    private PlayerWealthDegreeService playerWealthDegreeService;
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;
    @Resource
    private PlayerBufferCacheMananger playerBufferCacheMananger;
    @Resource
    private PlayerMineAttackedCacheManager playerMineAttackedCacheManager;
    @Resource
    private PlayermineleftresourceMapper playermineleftresourceMapper;
    @Resource
    private PlayerresourcemineMapper playerresourcemineMapper;
    @Resource
    private PlayerroleMapper playerRoleMapper;
    @Resource
    private DicResMineRelaService dicResMineRelaService;
    @Resource
    private SendService sendService;

    /**
     * 查询玩家资源矿点产生资源情况
     *
     * @throws
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<MineHarvestResourceVO> queryHarvestResource(int roleId) {
        List<MineHarvestResourceVO> mineResList = new ArrayList<MineHarvestResourceVO>();

        Map<Short, Integer> resMap = new HashMap<Short, Integer>();
        Map<Short, Integer> resNumMap = new HashMap<Short, Integer>();
        Map<Short, Integer> resNumPerHourMap = new HashMap<Short, Integer>();
        Map<Short, Integer> storageMap = new HashMap<Short, Integer>();
        PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
        playerresourcemineExample.createCriteria().andRoleidEqualTo(roleId);
        List<Playerresourcemine> mineList = playerresourcemineMapper.selectByExample(playerresourcemineExample);
        for (Playerresourcemine playerresourcemine : mineList) {
            PlayerMineResourceVO playerMineResourceVO = getMineProductResource(roleId, playerresourcemine.getNpcresmineid());
            if (resMap.get(playerresourcemine.getProducerestype()) == null) {
                resMap.put(playerresourcemine.getProducerestype(), playerMineResourceVO.getResource());
                resNumPerHourMap.put(playerresourcemine.getProducerestype(), playerMineResourceVO.getMineProductivityVO().getProductivity());
                resNumMap.put(playerresourcemine.getProducerestype(), 1);
            } else {
                resMap.put(playerresourcemine.getProducerestype(),
                        resMap.get(playerresourcemine.getProducerestype()) + playerMineResourceVO.getResource());
                resNumPerHourMap.put(playerresourcemine.getProducerestype(),
                        resNumPerHourMap.get(playerresourcemine.getProducerestype()) + playerMineResourceVO.getMineProductivityVO().getProductivity());
                resNumMap.put(playerresourcemine.getProducerestype(),
                        resNumMap.get(playerresourcemine.getProducerestype()) + 1);
            }
            if (storageMap.get(playerresourcemine.getProducerestype()) == null) {
                storageMap.put(playerresourcemine.getProducerestype(), playerresourcemine.getStoragelimit());
            } else {
                storageMap.put(playerresourcemine.getProducerestype(),
                        storageMap.get(playerresourcemine.getProducerestype()) + playerresourcemine.getStoragelimit());
            }
        }

        PlayermineleftresourceExample playermineleftresourceExample = new PlayermineleftresourceExample();
        playermineleftresourceExample.createCriteria().andRoleidEqualTo(roleId);
        List<Playermineleftresource> leftList = playermineleftresourceMapper.selectByExample(playermineleftresourceExample);
        for (Playermineleftresource playermineleftresource : leftList) {
            if (resMap.get(playermineleftresource.getProducerestype()) == null) {
                resMap.put(playermineleftresource.getProducerestype(), playermineleftresource.getResourcestorage());
            } else {
                resMap.put(playermineleftresource.getProducerestype(),
                        resMap.get(playermineleftresource.getProducerestype()) + playermineleftresource.getResourcestorage());
            }
        }

        for (short key : resMap.keySet()) {
            MineHarvestResourceVO mineHarvestResourceVO = new MineHarvestResourceVO();
            mineHarvestResourceVO.setProduceResType(key);
            // 矿脉加成比例
            float resMuduls = 1;
            if ((resNumMap.get(key) != null) && (resNumMap.get(key) > 1)) {
                resMuduls = dicResMineRelaService.getDicResMineRela(resNumMap.get(key));
                mineHarvestResourceVO.setMineResourceNum(resNumMap.get(key));
            } else {
                mineHarvestResourceVO.setMineResourceNum(1);
            }
            mineHarvestResourceVO.setProduceMuduls((int) ((resMuduls - 1) * 100));
            mineHarvestResourceVO.setProduceNum(resMap.get(key));
            if (resNumPerHourMap.get(key) != null) {
                mineHarvestResourceVO.setProducePerHour(resNumPerHourMap.get(key));
            }
            logger.info("storageMap.get(key) == " + storageMap.get(key) + "    resMap.get(key) =====" + resMap.get(key));
            if (storageMap.get(key) == null || storageMap.get(key) == 0) {
                mineHarvestResourceVO.setMineCarDrive(true);
            } else {
                double rate = (resMap.get(key) * 0.1) / (storageMap.get(key) * 0.1);
                logger.info("------------------rate=====" + rate + "--------");
                if (rate >= 0.01) {
                    mineHarvestResourceVO.setMineCarDrive(true);
                } else {
                    mineHarvestResourceVO.setMineCarDrive(false);
                }
            }
            mineResList.add(mineHarvestResourceVO);
        }
        return mineResList;
    }

    /**
     * 玩家收获资源矿点产生资源，更新玩家资源量和该资源矿点的资源库存
     *
     * @throws CanNotCollectResourceException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MineHarvestResourceVO harvestResource(int roleId, short produceResType) throws NoSuchRoleException,
            TaskDataException, CanNotCollectResourceException, MineUnderAttackedException {
        MineHarvestResourceVO mineHarvestResourceVO = new MineHarvestResourceVO();
        if (roleId < 0)
            throw new NoSuchRoleException();

        if (playerMineAttackedCacheManager.get(roleId) != null)
            throw new MineUnderAttackedException();

        int harvestRes = 0;

        PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
        playerresourcemineExample.createCriteria().andRoleidEqualTo(roleId).andProducerestypeEqualTo(produceResType);
        List<Playerresourcemine> mineList = playerresourcemineMapper.selectByExample(playerresourcemineExample);
        for (Playerresourcemine playerresourcemine : mineList) {
            HavestVO havestVO = getMineBuildingProductivity(roleId, playerresourcemine.getNpcresmineid());
            harvestRes = harvestRes + havestVO.getHavest();
        }

        PlayermineleftresourceKey playermineleftresourceKey = new PlayermineleftresourceKey();
        playermineleftresourceKey.setRoleid(roleId);
        playermineleftresourceKey.setProducerestype(produceResType);
        Playermineleftresource playermineleftresource = playermineleftresourceMapper.selectByPrimaryKey(playermineleftresourceKey);
        if (playermineleftresource != null) {
            harvestRes = harvestRes + playermineleftresource.getResourcestorage();
            playermineleftresourceMapper.deleteByPrimaryKey(playermineleftresourceKey);
        }

        // 增加玩家对应资源并下发
        sendService.sendCrossAddResource(roleId, produceResType, harvestRes, ConsumptionTypeEnum.MINE_RESOURCE_HARVEST);

        mineHarvestResourceVO.setProduceNum(harvestRes);
        mineHarvestResourceVO.setProduceResType(produceResType);

        return mineHarvestResourceVO;
    }

    /**
     * 获取玩家资源矿点的产量
     *
     * @param roleID
     * @param npcResMineID
     * @return
     * @throws CanNotCollectResourceException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public HavestVO getMineBuildingProductivity(int roleID, long npcResMineID) throws CanNotCollectResourceException {

        HavestVO havestVO = new HavestVO();

        PlayerMineResourceVO mineProductResource = getMineProductResource(roleID, npcResMineID);

        short produceResType = mineProductResource.getMineProductivityVO().getProduceResType();
        ResourcesEnum resEnum = ResourcesEnum.getResource(produceResType);

        long time = mineProductResource.getTime();

		/* 玩家所能收集的资源数量 */
        int resource = mineProductResource.getResource();
		
		/* 玩家资源建筑库存上限 */
        int buildStorageLimit = mineProductResource.getStorageLimit();
		
		/* 玩家资源建筑库存 */
        int storage = mineProductResource.getStorage();
		
		/* 玩家资源上限 */
        int playerStorageLimit;
        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleID);
		
		/* 玩家资源数量 */
        int playerResource;
        Playerrole playerrole = playerRoleMapper.selectByPrimaryKey(roleID);

        switch (resEnum) {
            case GOLD: // 金矿

                playerResource = playerrole.getRolegold();
                playerStorageLimit = playerRoleVO.getGoldStorageLimit();
                break;
            case IRON: // 炼钢场

                playerResource = playerrole.getRoleiron();
                playerStorageLimit = playerRoleVO.getIronStorageLimit();
                break;
            case OIL: // 炼油场

                playerResource = playerrole.getRoleoil();
                playerStorageLimit = playerRoleVO.getOilStorageLimit();
                break;
            case STONE: // 采石场

                playerResource = playerrole.getRolestone();
                playerStorageLimit = playerRoleVO.getStoneStorageLimit();
                break;

            default:

                playerResource = 0;
                playerStorageLimit = 0;
                break;
        }
		
		/* 清空库存 */
        PlayerresourcemineWithBLOBs playerresourcemine = mineProductResource.getPlayerresourcemine();
		
		
		/* 资源计算时间 */
        Long storagecaculatetime = playerresourcemine.getStoragecaculatetime();
        if (storagecaculatetime == 0) {
            throw new CanNotCollectResourceException();
        }
		/* 玩家收集资源 */
        long now = System.currentTimeMillis();

        int allResource = playerResource + resource;
        if (allResource > playerStorageLimit) {
            int over = allResource - playerStorageLimit;
            resource = playerStorageLimit - playerResource;

            if (over > buildStorageLimit) {
                over = buildStorageLimit;
            }

            storage = over;
        } else {
            storage = 0;
        }

        time = now;


        playerresourcemine.setStoragecaculatetime(time);
        playerresourcemine.setResourcestorage(storage);
        playerresourcemineMapper.updateByPrimaryKeySelective(playerresourcemine);

        havestVO.setHavest(resource);
        havestVO.setStorage(storage);

        return havestVO;
    }

    /**
     * 获取玩家资源矿点资源产出效率
     *
     * @param roleID
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<MineProductivityVO> getPlayerMineProductivity(int roleID) {
        List<MineProductivityVO> result = new ArrayList<MineProductivityVO>();
        PlayerBufferVO playerBufferVO = playerBufferCacheMananger.get(roleID);
        if (playerBufferVO != null && playerBufferVO.getMineProductivityList() != null
                && playerBufferVO.getMineProductivityList().size() > 0) {
            return playerBufferVO.getMineProductivityList();
        } else {
            return result;
        }
    }

    /**
     * 获取玩家资源矿点生产的资源数量(即库存)
     *
     * @param roleID
     * @param buildingID
     * @return
     * @throws CanNotCollectResourceException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PlayerMineResourceVO getMineProductResource(int roleID, long npcResMineID) {

        PlayerMineResourceVO result = new PlayerMineResourceVO();

        MineProductivityVO mineProductivity = null;

        List<MineProductivityVO> playerProductivity = getPlayerMineProductivity(roleID);
        for (MineProductivityVO productivityVO : playerProductivity) {
            if (productivityVO.getNpcResMineId() == npcResMineID) {
                mineProductivity = productivityVO;
                break;
            }
        }

        long now = System.currentTimeMillis();

        PlayerresourcemineKey key = new PlayerresourcemineKey();
        key.setNpcresmineid(npcResMineID);
        key.setRoleid(roleID);
        PlayerresourcemineWithBLOBs playerresourcemine = playerresourcemineMapper.selectByPrimaryKey(key);
		
		/* 资源生产上限 */
        Integer storageLimit = playerresourcemine.getStoragelimit();
		
		/* 资源计算时间 */
        Long storagecaculatetime = playerresourcemine.getStoragecaculatetime();

        long time = now - storagecaculatetime;
		
		/* 资源产量 */
        int resource = (int) ((mineProductivity.getProductivity() * time) / (60 * 60 * 1000));

        resource += playerresourcemine.getResourcestorage();

        if (resource > storageLimit) {
            resource = storageLimit;
        }

        result.setResource(resource);
        result.setStorageLimit(storageLimit);
        result.setMineProductivityVO(mineProductivity);
        result.setPlayerresourcemine(playerresourcemine);
        result.setStorage(playerresourcemine.getResourcestorage());
        result.setTime(now);

        return result;
    }

    /**
     * 按照资源生产速度计算所能掠夺的资源数，按照上次最晚搜集资源点距离当前时间来计算（最大4小时）
     *
     * @param roleID
     * @param
     * @return
     * @throws CanNotCollectResourceException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<Short, Double> getMinePlunderResourceNum(int roleID) {
        Map<Short, Double> result = new HashMap<Short, Double>();

        long caculateTime = 0L;
        PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
        playerresourcemineExample.createCriteria().andRoleidEqualTo(roleID);
        playerresourcemineExample.setOrderByClause(" storageCaculateTime desc ");
        List<Playerresourcemine> mineList = playerresourcemineMapper.selectByExample(playerresourcemineExample);
        if (mineList != null && mineList.size() > 0) {
            Playerresourcemine playerresourcemine = mineList.get(0);
            long storageCaculateTime = playerresourcemine.getStoragecaculatetime();
            long passedTime = System.currentTimeMillis() - storageCaculateTime;
            if (passedTime >= AppConfig.MINE_PLUNDER_CACULATE_TIME) {
                caculateTime = AppConfig.MINE_PLUNDER_CACULATE_TIME;
            } else {
                caculateTime = passedTime;
            }
        }

        List<MineProductivityVO> productList = getPlayerMineProductivity(roleID);

        for (MineProductivityVO productivityVO : productList) {
            short produceResType = productivityVO.getProduceResType();
            double product = (productivityVO.getProductivity() * caculateTime) / (3600 * 1000);
            // logger.info("produceResType ==== " + produceResType+ "     base product === " + product);
            if (result.get(produceResType) == null) {
                result.put(produceResType, product);
            } else {
                result.put(produceResType, result.get(produceResType) + product);
            }
        }

        return result;
    }

    /**
     * 按照掠夺资源扣除资源点对应资源
     *
     * @param roleID
     * @param
     * @return
     * @throws CanNotCollectResourceException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void reduceMinePlunderResource(int roleID) {
        long caculateTime = 0L;
        long now = System.currentTimeMillis();
        PlayerresourcemineExample playerresourcemineExample = new PlayerresourcemineExample();
        playerresourcemineExample.createCriteria().andRoleidEqualTo(roleID);
        playerresourcemineExample.setOrderByClause(" storageCaculateTime desc ");
        List<PlayerresourcemineWithBLOBs> mineList = playerresourcemineMapper.selectByExampleWithBLOBs(playerresourcemineExample);
        if (mineList != null && mineList.size() > 0) {
            PlayerresourcemineWithBLOBs playerresourcemine = mineList.get(0);
            long storageCaculateTime = playerresourcemine.getStoragecaculatetime();
            long passedTime = now - storageCaculateTime;
            if (passedTime >= AppConfig.MINE_PLUNDER_CACULATE_TIME) {
                caculateTime = AppConfig.MINE_PLUNDER_CACULATE_TIME;
            } else {
                caculateTime = passedTime;
            }
        }

        if (caculateTime > 0) {
            for (PlayerresourcemineWithBLOBs playerresourcemine : mineList) {
                long newStorageCaculateTime = playerresourcemine.getStoragecaculatetime() + caculateTime;
                if (newStorageCaculateTime > now) {
                    newStorageCaculateTime = now;
                }
                playerresourcemine.setStoragecaculatetime(newStorageCaculateTime);
                playerresourcemineMapper.updateByPrimaryKeySelective(playerresourcemine);
            }
        }

    }

}
