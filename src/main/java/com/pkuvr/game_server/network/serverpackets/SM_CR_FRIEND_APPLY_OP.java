package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_FRIEND_APPLY_OP_RES)
public class SM_CR_FRIEND_APPLY_OP extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_FRIEND_APPLY_OP(byte[] prr) {
        super(prr);
    }
}
