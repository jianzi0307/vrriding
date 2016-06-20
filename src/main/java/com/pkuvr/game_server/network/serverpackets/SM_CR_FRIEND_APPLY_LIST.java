package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_FRIEND_APPLY_LIST_RES)
public class SM_CR_FRIEND_APPLY_LIST extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_FRIEND_APPLY_LIST(byte[] prr) {
        super(prr);
    }
}
