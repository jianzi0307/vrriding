package com.pkuvr.game_server.vo;

import com.pkuvr.game_server.domain.PlayerresourcemineWithBLOBs;

public class PlayerMineResourceVO {
    private int resource; // 玩家所能收集的资源数
    private int storageLimit; // 玩家资源建筑资源上限
    private int storage; // 玩家资源建筑库存数量
    private long time; // 所计算的时间

    private PlayerresourcemineWithBLOBs playerresourcemine;
    private MineProductivityVO mineProductivityVO;

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getStorageLimit() {
        return storageLimit;
    }

    public void setStorageLimit(int storageLimit) {
        this.storageLimit = storageLimit;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public PlayerresourcemineWithBLOBs getPlayerresourcemine() {
        return playerresourcemine;
    }

    public void setPlayerresourcemine(PlayerresourcemineWithBLOBs playerresourcemine) {
        this.playerresourcemine = playerresourcemine;
    }

    public MineProductivityVO getMineProductivityVO() {
        return mineProductivityVO;
    }

    public void setMineProductivityVO(MineProductivityVO mineProductivityVO) {
        this.mineProductivityVO = mineProductivityVO;
    }

}
