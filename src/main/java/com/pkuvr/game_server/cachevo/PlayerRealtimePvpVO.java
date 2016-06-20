package com.pkuvr.game_server.cachevo;

import com.pkuvr.game_server.vo.PvpBattleUnitVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerRealtimePvpVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int otherRoleId;
    private int battleUnitIndex;
    private List<PvpBattleUnitVO> playerBattleUnitList1 = new ArrayList<PvpBattleUnitVO>();
    private List<PvpBattleUnitVO> playerBattleUnitList2 = new ArrayList<PvpBattleUnitVO>();
    private List<PvpBattleUnitVO> playerBattleUnitList3 = new ArrayList<PvpBattleUnitVO>();
    private List<PvpBattleUnitVO> playerBattleUnitList4 = new ArrayList<PvpBattleUnitVO>();
    private int rewardHonor;
    private int subHonor;
    private long battleUnitDeployTime;
    private String landMineInfo;
    private String pvpMap;
    private boolean isBattleStart;
    private boolean isBattleEnd;
    private boolean isRewardHonor;
    private int battleResult;
    private String deployRoomId;
    private String battleRoomId;
    private long delayTime;

    public int getOtherRoleId() {
        return otherRoleId;
    }

    public void setOtherRoleId(int otherRoleId) {
        this.otherRoleId = otherRoleId;
    }

    public int getBattleUnitIndex() {
        return battleUnitIndex;
    }

    public void setBattleUnitIndex(int battleUnitIndex) {
        this.battleUnitIndex = battleUnitIndex;
    }

    public int getRewardHonor() {
        return rewardHonor;
    }

    public void setRewardHonor(int rewardHonor) {
        this.rewardHonor = rewardHonor;
    }

    public int getSubHonor() {
        return subHonor;
    }

    public void setSubHonor(int subHonor) {
        this.subHonor = subHonor;
    }

    public long getBattleUnitDeployTime() {
        return battleUnitDeployTime;
    }

    public void setBattleUnitDeployTime(long battleUnitDeployTime) {
        this.battleUnitDeployTime = battleUnitDeployTime;
    }

    public List<PvpBattleUnitVO> getPlayerBattleUnitList1() {
        return playerBattleUnitList1;
    }

    public void setPlayerBattleUnitList1(List<PvpBattleUnitVO> playerBattleUnitList1) {
        this.playerBattleUnitList1 = playerBattleUnitList1;
    }

    public List<PvpBattleUnitVO> getPlayerBattleUnitList2() {
        return playerBattleUnitList2;
    }

    public void setPlayerBattleUnitList2(List<PvpBattleUnitVO> playerBattleUnitList2) {
        this.playerBattleUnitList2 = playerBattleUnitList2;
    }

    public List<PvpBattleUnitVO> getPlayerBattleUnitList3() {
        return playerBattleUnitList3;
    }

    public void setPlayerBattleUnitList3(List<PvpBattleUnitVO> playerBattleUnitList3) {
        this.playerBattleUnitList3 = playerBattleUnitList3;
    }

    public List<PvpBattleUnitVO> getPlayerBattleUnitList4() {
        return playerBattleUnitList4;
    }

    public void setPlayerBattleUnitList4(List<PvpBattleUnitVO> playerBattleUnitList4) {
        this.playerBattleUnitList4 = playerBattleUnitList4;
    }

    public String getLandMineInfo() {
        return landMineInfo;
    }

    public void setLandMineInfo(String landMineInfo) {
        this.landMineInfo = landMineInfo;
    }

    public boolean isBattleStart() {
        return isBattleStart;
    }

    public void setBattleStart(boolean isBattleStart) {
        this.isBattleStart = isBattleStart;
    }

    public boolean isBattleEnd() {
        return isBattleEnd;
    }

    public void setBattleEnd(boolean isBattleEnd) {
        this.isBattleEnd = isBattleEnd;
    }

    public boolean isRewardHonor() {
        return isRewardHonor;
    }

    public void setRewardHonor(boolean isRewardHonor) {
        this.isRewardHonor = isRewardHonor;
    }

    public int getBattleResult() {
        return battleResult;
    }

    public void setBattleResult(int battleResult) {
        this.battleResult = battleResult;
    }

    public String getPvpMap() {
        return pvpMap;
    }

    public void setPvpMap(String pvpMap) {
        this.pvpMap = pvpMap;
    }

    public String getDeployRoomId() {
        return deployRoomId;
    }

    public void setDeployRoomId(String deployRoomId) {
        this.deployRoomId = deployRoomId;
    }

    public String getBattleRoomId() {
        return battleRoomId;
    }

    public void setBattleRoomId(String battleRoomId) {
        this.battleRoomId = battleRoomId;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }
}
