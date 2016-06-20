package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_REDUCE_PLUNDER_RESOURCE_SEND)
public class SM_CR_REDUCE_PLUNDER_RESOURCE_SEND extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_REDUCE_PLUNDER_RESOURCE_SEND(byte[] prr) {
        super(prr);
    }
}
