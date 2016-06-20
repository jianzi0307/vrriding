package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.RECONNECTION_RES)
public class SM_RECONNECTION extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_RECONNECTION(byte[] prr) {
        super(prr);
    }
}
