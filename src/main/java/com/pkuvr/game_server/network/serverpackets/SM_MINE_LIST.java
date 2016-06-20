package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.MINE_LIST_RES)
public class SM_MINE_LIST extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_MINE_LIST(byte[] prr) {
        super(prr);
    }
}
