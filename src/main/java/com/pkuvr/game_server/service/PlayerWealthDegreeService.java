package com.pkuvr.game_server.service;

import com.pkuvr.game_server.cache.PlayerRoleCacheManager;
import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.dao.PlayerroleMapper;
import com.pkuvr.game_server.domain.Playerrole;
import com.pkuvr.game_server.enumerate.ConsumptionTypeEnum;
import com.pkuvr.game_server.exception.*;
import com.pkuvr.game_server.vo.ChangePlayerWealthVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 玩家角色价值相关类
 *
 * @author ZY
 */
@Service("PlayerWealthService")
public class PlayerWealthDegreeService {
    @Resource
    private PlayerroleMapper playerRoleMapper;
    @Resource
    private SendService sendService;
    @Resource
    private PlayerRoleCacheManager playerRoleCacheManager;


    /**
     * 指定id的玩家获取指定的钻石、黄金、油料、钢铁等(同时下发信息)
     *
     * @param changePlayerWealthVO
     * @throws TaskDataException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addByPrimaryKey(ChangePlayerWealthVO changePlayerWealthVO, boolean intoTask) {
        addByPrimaryKey(changePlayerWealthVO.getRoleId(), changePlayerWealthVO.getReasonCode(), changePlayerWealthVO.getConsumptionType(), changePlayerWealthVO.getConsumptionInfo(),
                changePlayerWealthVO.getDiamond(), changePlayerWealthVO.getGold(), changePlayerWealthVO.getIron(), changePlayerWealthVO.getOil(),
                changePlayerWealthVO.getStone(), changePlayerWealthVO.getTon(), changePlayerWealthVO.getPower());


    }

    /**
     * 指定id的玩家消耗钻石、黄金、油料、钢铁等(同时下发金钱信息)
     *
     * @param changePlayerWealthVO
     * @throws NotEnoughWealthException
     * @throws TaskDataException
     * @throws NotEnoughGoldException
     * @throws NotEnoughIronException
     * @throws NotEnoughOilException
     * @throws NotEnoughStoneException
     * @throws NotEnoughDiamondException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void subByPrimaryKey(ChangePlayerWealthVO changePlayerWealthVO) throws NotEnoughWealthException, TaskDataException, NotEnoughDiamondException, NotEnoughStoneException, NotEnoughOilException, NotEnoughIronException, NotEnoughGoldException {
        subByPrimaryKey(changePlayerWealthVO.getRoleId(), changePlayerWealthVO.getReasonCode(), changePlayerWealthVO.getConsumptionType(), changePlayerWealthVO.getConsumptionInfo(),
                changePlayerWealthVO.getDiamond(), changePlayerWealthVO.getGold(), changePlayerWealthVO.getIron(), changePlayerWealthVO.getOil(),
                changePlayerWealthVO.getStone(), changePlayerWealthVO.getTon(), changePlayerWealthVO.getPower());
    }


    /******************************** 私有方法 *******************************************************************************/

    /**
     * 指定id的玩家获取指定的钻石、黄金、油料、钢铁等(同时下发信息)
     *
     * @param roleId          玩家id
     * @param reasonCode      金钱数改变的原因
     * @param consumptionType 消费点枚举
     * @param consumptionInfo 消费内容(物品id,科技id,联合主键下划线分割)
     * @param addDiamond      增加钻石数
     * @param addGold         增加黄金数
     * @param addIron         增加钢铁
     * @param addOil          增加石油
     * @param addStone        增加石材
     * @param addTon          增加上阵吨位
     * @param addPower        增加行动力
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void addByPrimaryKey(int roleId, SmOpCode reasonCode, ConsumptionTypeEnum consumptionType, String consumptionInfo,
                                 int addDiamond, int addGold, int addIron, int addOil, int addStone, int addTon, int addPower) {

        if (!(addDiamond >= 0 && addGold >= 0 && addIron >= 0 && addOil >= 0 && addStone >= 0 && addTon >= 0 && addPower >= 0)) {
            return;
        }

        // 1.更新数据库
        Playerrole playerrole = playerRoleMapper.selectByPrimaryKey(roleId);

        PlayerRoleVO playerRoleVO = playerRoleCacheManager.get(roleId);
        int goldStorageLimit = playerRoleVO.getGoldStorageLimit();
        int ironStorageLimit = playerRoleVO.getIronStorageLimit();
        int oilStorageLimit = playerRoleVO.getOilStorageLimit();
        int stoneStorageLimit = playerRoleVO.getStoneStorageLimit();

        playerrole.setRolediamond(playerrole.getRolediamond() + addDiamond);

        int gold = playerrole.getRolegold() + addGold;
        if (gold > goldStorageLimit) {
            gold = goldStorageLimit;
        }
        playerrole.setRolegold(gold);

        int iron = playerrole.getRoleiron() + addIron;
        if (iron > ironStorageLimit) {
            iron = ironStorageLimit;
        }
        playerrole.setRoleiron(iron);

        int oil = playerrole.getRoleoil() + addOil;
        if (oil > oilStorageLimit) {
            oil = oilStorageLimit;
        }
        playerrole.setRoleoil(oil);

        int stone = playerrole.getRolestone() + addStone;
        if (stone > stoneStorageLimit) {
            stone = stoneStorageLimit;
        }
        playerrole.setRolestone(stone);

        playerrole.setRoleton(playerrole.getRoleton() + addTon);
        playerrole.setRolepower(playerrole.getRolepower() + addPower);
        int count = playerRoleMapper.updateByPrimaryKeySelective(playerrole);
        if (count != 1)
            throw new IllegalArgumentException("增加钻石、黄金、油料、钢铁等失败,请检查指定玩家是否存在.roleId=" + roleId);

    }

    /**
     * 指定id的玩家消耗钻石、黄金、油料、钢铁等(同时下发金钱信息)
     *
     * @param roleId          玩家id
     * @param reasonCode      金钱数改变的原因
     * @param consumptionType 消费点枚举
     * @param consumptionInfo 消费内容(物品id,科技id,联合主键下划线分割)
     * @param subDiamond      减少钻石数
     * @param subGold         减少黄金数
     * @param subIron         减少钢铁
     * @param subOil          减少石油
     * @param subStone        减少石材
     * @param subTon          减少上阵吨位
     * @param subPower        减少行动力
     * @throws NotEnoughWealthException
     * @throws NotEnoughDiamondException
     * @throws NotEnoughStoneException
     * @throws NotEnoughOilException
     * @throws NotEnoughIronException
     * @throws NotEnoughGoldException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void subByPrimaryKey(int roleId, SmOpCode reasonCode, ConsumptionTypeEnum consumptionType, String consumptionInfo,
                                 int subDiamond, int subGold, int subIron, int subOil, int subStone, int subTon, int subPower) throws NotEnoughWealthException, NotEnoughDiamondException, NotEnoughStoneException, NotEnoughOilException, NotEnoughIronException, NotEnoughGoldException {
        // 2.更新数据库
        Playerrole playerrole = playerRoleMapper.selectByPrimaryKey(roleId);

        int diamond = playerrole.getRolediamond() - subDiamond;
        int gold = playerrole.getRolegold() - subGold;
        int iron = playerrole.getRoleiron() - subIron;
        int oil = playerrole.getRoleoil() - subOil;
        int stone = playerrole.getRolestone() - subStone;
        int ton = playerrole.getRoleton() - subTon;
        int power = playerrole.getRolepower() - subPower;

        if ((ton < 0) || (power < 0)) {
            throw new NotEnoughWealthException("钻石、黄金、油料、钢铁等 其中之一不足.roleId=" + roleId);
        }

        if (stone < 0) {
            throw new NotEnoughStoneException();
        }

        if (oil < 0) {
            throw new NotEnoughOilException();
        }

        if (iron < 0) {
            throw new NotEnoughIronException();
        }

        if (gold < 0) {
            throw new NotEnoughGoldException();
        }

        if (diamond < 0) {
            throw new NotEnoughDiamondException();
        }

        playerrole.setRolediamond(diamond);
        playerrole.setRolegold(gold);
        playerrole.setRoleiron(iron);
        playerrole.setRoleoil(oil);
        playerrole.setRolestone(stone);
        playerrole.setRoleton(ton);
        playerrole.setRolepower(power);
        int count = playerRoleMapper.updateByPrimaryKeySelective(playerrole);
        if (count != 1)
            throw new NotEnoughWealthException("钻石、黄金、油料、钢铁等 其中之一不足.roleId=" + roleId);

    }

}
