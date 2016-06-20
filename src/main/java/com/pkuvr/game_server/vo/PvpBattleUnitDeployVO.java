package com.pkuvr.game_server.vo;

import java.util.ArrayList;
import java.util.List;

public class PvpBattleUnitDeployVO {
    private int deployResult;
    private int otherRoleId;
    private List<PvpBattleUnitVO> playerBattleUnitList = new ArrayList<PvpBattleUnitVO>();
    private List<PvpBattleUnitVO> otherBattleUnitList = new ArrayList<PvpBattleUnitVO>();

    public int getDeployResult() {
        return deployResult;
    }

    public void setDeployResult(int deployResult) {
        this.deployResult = deployResult;
    }

    public int getOtherRoleId() {
        return otherRoleId;
    }

    public void setOtherRoleId(int otherRoleId) {
        this.otherRoleId = otherRoleId;
    }

    public List<PvpBattleUnitVO> getPlayerBattleUnitList() {
        return playerBattleUnitList;
    }

    public void setPlayerBattleUnitList(
            List<PvpBattleUnitVO> playerBattleUnitList) {
        this.playerBattleUnitList = playerBattleUnitList;
    }

    public List<PvpBattleUnitVO> getOtherBattleUnitList() {
        return otherBattleUnitList;
    }

    public void setOtherBattleUnitList(List<PvpBattleUnitVO> otherBattleUnitList) {
        this.otherBattleUnitList = otherBattleUnitList;
    }

}
