package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.MINE_ENTER_RES)
public class SM_MINE_ENTER extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_MINE_ENTER(byte[] prr) {
        super(prr);
    }
}
