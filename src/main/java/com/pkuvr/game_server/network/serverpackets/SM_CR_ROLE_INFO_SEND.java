package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_ROLE_INFO_SEND)
public class SM_CR_ROLE_INFO_SEND extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_ROLE_INFO_SEND(byte[] prr) {
        super(prr);
    }
}
