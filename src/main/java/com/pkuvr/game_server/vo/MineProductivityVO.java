package com.pkuvr.game_server.vo;

public class MineProductivityVO {
    private long npcResMineId;
    private int productivity;
    private short produceResType;

    public long getNpcResMineId() {
        return npcResMineId;
    }

    public void setNpcResMineId(long npcResMineId) {
        this.npcResMineId = npcResMineId;
    }

    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        this.productivity = productivity;
    }

    public short getProduceResType() {
        return produceResType;
    }

    public void setProduceResType(short produceResType) {
        this.produceResType = produceResType;
    }

}
