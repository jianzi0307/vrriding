package com.pkuvr.game_server.vo;

import java.util.List;

public class PvpRoomVO {
    private List<PvpRankInfoVO> rankInfoList;
    private PvpRankInfoVO ownRankInfo;


    public List<PvpRankInfoVO> getRankInfoList() {
        return rankInfoList;
    }

    public void setRankInfoList(List<PvpRankInfoVO> rankInfoList) {
        this.rankInfoList = rankInfoList;
    }

    public PvpRankInfoVO getOwnRankInfo() {
        return ownRankInfo;
    }

    public void setOwnRankInfo(PvpRankInfoVO ownRankInfo) {
        this.ownRankInfo = ownRankInfo;
    }
}
