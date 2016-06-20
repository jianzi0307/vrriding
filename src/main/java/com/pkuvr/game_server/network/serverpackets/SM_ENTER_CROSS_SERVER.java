package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.ENTER_CROSS_SERVER_RES)
public class SM_ENTER_CROSS_SERVER extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_ENTER_CROSS_SERVER(byte[] prr) {
        super(prr);
    }
}
