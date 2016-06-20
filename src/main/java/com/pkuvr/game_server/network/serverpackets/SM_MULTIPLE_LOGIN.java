package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.MULTIPLE_LOGIN)
public class SM_MULTIPLE_LOGIN extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_MULTIPLE_LOGIN(byte[] prr) {
        super(prr);
    }
}