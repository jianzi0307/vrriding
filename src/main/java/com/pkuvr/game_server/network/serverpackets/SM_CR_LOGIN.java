package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_LOGIN_RES)
public class SM_CR_LOGIN extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_LOGIN(byte[] prr) {
        super(prr);
    }
}
