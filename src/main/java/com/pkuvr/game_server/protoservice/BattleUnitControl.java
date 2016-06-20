package com.pkuvr.game_server.protoservice;

import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Builder;
import com.pkuvr.game_server.proto.commons.BI_Battle_Unit.Battle_Unit.Skill;
import com.pkuvr.game_server.vo.BattleUnitVO;
import com.pkuvr.game_server.vo.SkillVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("BattleUnitControl")
public class BattleUnitControl {

    private static final Logger logger = Logger.getLogger(BattleUnitControl.class);

    public Builder buildBattleUnit(BattleUnitVO battleUnitVO) {
        Battle_Unit.Builder battleUnit = Battle_Unit.newBuilder();
        battleUnit.setBattleUnitID(battleUnitVO.getBattleUnitID());
        battleUnit.setUnitLevel(battleUnitVO.getUnitLevel());
        battleUnit.setStorageNum(battleUnitVO.getStorageNum());
        battleUnit.setAccurateRate(battleUnitVO.getAccurateRate());
        battleUnit.setHealth(battleUnitVO.getHealth());
        battleUnit.setAttackDamage(battleUnitVO.getAttackDamage());
        battleUnit.setFrontArmor(battleUnitVO.getFrontArmor());
        battleUnit.setSideArmor(battleUnitVO.getSideArmor());
        battleUnit.setAttackFrequency(battleUnitVO.getAttackFrequency());
        battleUnit.setAttackRange(battleUnitVO.getAttackRange());
        battleUnit.setCampID(battleUnitVO.getCampID());
        battleUnit.setCritRate(battleUnitVO.getCritRate());
        battleUnit.setDisAttackRange(battleUnitVO.getDisAttackRange());
        battleUnit.setDodgeRate(battleUnitVO.getDodgeRate());
        battleUnit.setIcon(battleUnitVO.getIcon());
        battleUnit.setInnerDamageRate(battleUnitVO.getInnerDamageRate());
        battleUnit.setInnerRange(battleUnitVO.getInnerRange());
        battleUnit.setOuterDamageRate(battleUnitVO.getOuterDamageRate());
        battleUnit.setOuterRange(battleUnitVO.getOuterRange());
        battleUnit.setSightRange(battleUnitVO.getSightRange());
        battleUnit.setType(battleUnitVO.getType());
        battleUnit.setSubType(battleUnitVO.getSubType());
        battleUnit.setCannonType(battleUnitVO.getCannonType());
        battleUnit.setModelAmount(battleUnitVO.getModelAmount());
        battleUnit.setEffectID(battleUnitVO.getEffectID());
        battleUnit.setNameInCh(battleUnitVO.getNameInCh());
        battleUnit.setNameInEn(battleUnitVO.getNameInEn());
        if (battleUnitVO.getModel() != null)
            battleUnit.setModel(battleUnitVO.getModel());
        battleUnit.setSpeedOnGrass(battleUnitVO.getSpeedOnGrass());
        battleUnit.setSpeedOnHill(battleUnitVO.getSpeedOnHill());
        battleUnit.setSpeedOnSand(battleUnitVO.getSpeedOnSand());
        battleUnit.setSpeedOnSnow(battleUnitVO.getSpeedOnSnow());
        battleUnit.setSpeedOnRoad(battleUnitVO.getSpeedOnRoad());
        battleUnit.setRotationSpeed(battleUnitVO.getRotationSpeed());
        if (battleUnitVO.getSpeedForView() != null)
            battleUnit.setSpeedForView(battleUnitVO.getSpeedForView());
        if (battleUnitVO.getEnginePowerForView() != null)
            battleUnit.setEnginePowerForView(battleUnitVO.getEnginePowerForView());
        if (battleUnitVO.getWeightForView() != null)
            battleUnit.setWeightForView(battleUnitVO.getWeightForView());
        battleUnit.setRewardPower(battleUnitVO.getRewardPower());

        if (battleUnitVO.getDeathAction() != null) {
            battleUnit.setDeathAction(battleUnitVO.getDeathAction());
        }

        if (battleUnitVO.getDeathParticle() != null) {
            battleUnit.setDeathParticle(battleUnitVO.getDeathParticle());
        }

        battleUnit.setClassLevel(battleUnitVO.getClassLevel());

        List<SkillVO> skill = battleUnitVO.getSkill();
        for (SkillVO skillVO : skill) {
            Skill.Builder skillInfo = Skill.newBuilder();
            skillInfo.setLevel(skillVO.getLevel());
            skillInfo.setSkill(skillVO.getSkill());

            battleUnit.addSkill(skillInfo);
        }

        return battleUnit;
    }


}
