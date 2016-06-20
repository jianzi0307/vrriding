package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.MINE_CHANGE_PLAYER_RES)
public class SM_MINE_CHANGE_PLAYER extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_MINE_CHANGE_PLAYER(byte[] prr) {
        super(prr);
    }
}
