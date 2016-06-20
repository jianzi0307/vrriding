package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.PVP_BATTLE_END_RES)
public class SM_PVP_BATTLE_END extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_PVP_BATTLE_END(byte[] prr) {
        super(prr);
    }
}
