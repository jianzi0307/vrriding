package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.PVP_MATCH_RES)
public class SM_PVP_MATCH extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_PVP_MATCH(byte[] prr) {
        super(prr);
    }
}
