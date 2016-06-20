package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_GUIDE_STEP_FINISH_SEND)
public class SM_CR_GUIDE_STEP_FINISH_SEND extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_GUIDE_STEP_FINISH_SEND(byte[] prr) {
        super(prr);
    }
}
