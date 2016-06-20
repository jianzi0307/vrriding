package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_CANCEL_OWN_FRIEND_APPLY_RES)
public class SM_CR_CANCEL_OWN_FRIEND_APPLY extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_CANCEL_OWN_FRIEND_APPLY(byte[] prr) {
        super(prr);
    }
}
