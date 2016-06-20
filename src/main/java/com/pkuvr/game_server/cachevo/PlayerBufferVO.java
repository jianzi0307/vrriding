package com.pkuvr.game_server.cachevo;

import com.pkuvr.game_server.vo.MineProductivityVO;

import java.io.Serializable;
import java.util.List;

public class PlayerBufferVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<MineProductivityVO> mineProductivityList;

    public List<MineProductivityVO> getMineProductivityList() {
        return mineProductivityList;
    }

    public void setMineProductivityList(
            List<MineProductivityVO> mineProductivityList) {
        this.mineProductivityList = mineProductivityList;
    }

}
