package com.pkuvr.game_server.cache;

import com.pkuvr.game_server.cachevo.PlayerRoleVO;
import com.pkuvr.game_server.dao.PlayerroleMapper;
import com.pkuvr.game_server.domain.Playerrole;
import com.pkuvr.game_server.vo.BattleUnitVO;
import com.pkuvr.game_server.vo.PlayerTacticVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class PlayerRoleCacheManager implements ICacheManager<Integer, PlayerRoleVO> {
    @Resource
    private PlayerroleMapper playerRoleMapper;
    @Resource
    private ObjectMapper jacksonObjectMapper;
    @Resource
    private JCSCache<Integer, PlayerRoleVO> playerRoleCache;
    /**
     * 主键为:roleId , 如果不存在,返回空
     */
    public PlayerRoleVO get(Integer key) {
        PlayerRoleVO value = playerRoleCache.get(key);
        if (value != null)
            return value;

        Playerrole playerrole = playerRoleMapper.selectByPrimaryKey(key);
        if (playerrole == null)
            return null;

        value = new PlayerRoleVO();
        value.setRoleId(playerrole.getRoleid());
        value.setCampId(playerrole.getCampid());
        if (playerrole.getRolelevel() != null)
            value.setRoleLevel(playerrole.getRolelevel());
        if (playerrole.getRoleexp() != null)
            value.setRoleExp(playerrole.getRoleexp());
        value.setRoleName(playerrole.getRolename()); // 角色名(唯一索引)
        value.setRoleAvatar(playerrole.getRoleavatar()); // 头像
        if (playerrole.getRolegender() != null)
            value.setRoleGender(playerrole.getRolegender()); // 性别(1男2女)
        value.setRoleDiamond(playerrole.getRolediamond()); // 钻石
        value.setRoleGold(playerrole.getRolegold()); // 黄金
        value.setRoleIron(playerrole.getRoleiron()); //钢铁
        value.setRoleOil(playerrole.getRoleoil()); // 石油
        value.setRoleStone(playerrole.getRolestone()); // 石材
        value.setRoleTon(playerrole.getRoleton()); // 上阵吨位
        value.setRolePower(playerrole.getRolepower()); // 行动力
        value.setCombatValue(playerrole.getCombatvalue()); // 战斗力
        if (playerrole.getRanklevel() != null)
            value.setRankLevel(playerrole.getRanklevel());
        value.setHeaderquartersLevel(playerrole.getHeaderquarterslevel());
        value.setGoldStorageLimit(playerrole.getGoldstoragelimit());
        value.setOilStorageLimit(playerrole.getOilstoragelimit());
        value.setIronStorageLimit(playerrole.getIronstoragelimit());
        value.setStoneStorageLimit(playerrole.getStonestoragelimit());

        try {
            List<PlayerTacticVO> playerTacticsList = jacksonObjectMapper.readValue(playerrole.getTacticslist(),
                    new TypeReference<List<PlayerTacticVO>>() {
                    });
            value.setTacticsList(playerTacticsList);

            List<BattleUnitVO> battleUnitList = jacksonObjectMapper.readValue(playerrole.getBattleunitlist(),
                    new TypeReference<List<BattleUnitVO>>() {
                    });
            Map<Integer, BattleUnitVO> battleUnitMap = new HashMap<Integer, BattleUnitVO>();
            for (BattleUnitVO battleUnitVO : battleUnitList) {
                battleUnitMap.put(battleUnitVO.getBattleUnitID(), battleUnitVO);
            }
            value.setBattleUnitMap(battleUnitMap);

            Map<Integer, Integer> baseResoucePlunderMap = jacksonObjectMapper.readValue(playerrole.getBaseresourcepluderlist(),
                    new TypeReference<Map<Integer, Integer>>() {
                    });
            value.setBaseResoucePlunderMap(baseResoucePlunderMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    public Set<Integer> getKeySet() {
        return playerRoleCache.getKeySet();
    }

    public PlayerRoleVO getNotFromDB(Integer key) {
        return playerRoleCache.get(key);
    }

    public void put(Integer key, PlayerRoleVO value) {

        playerRoleCache.put(key, value);
    }

    public void remove(Integer key) {
        playerRoleCache.remove(key);
    }

    public String getRegionName() {
        return playerRoleCache.getRegionName();
    }
}
