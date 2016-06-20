package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.PVP_TOKEN_INFO_RES)
public class SM_PVP_TOKEN_INFO extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_PVP_TOKEN_INFO(byte[] prr) {
        super(prr);
    }
}
