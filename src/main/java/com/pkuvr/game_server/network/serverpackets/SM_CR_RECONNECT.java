package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_RECONNECT_RES)
public class SM_CR_RECONNECT extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_RECONNECT(byte[] prr) {
        super(prr);
    }
}
