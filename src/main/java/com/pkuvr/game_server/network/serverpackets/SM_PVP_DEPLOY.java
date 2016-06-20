package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.PVP_BATTLE_UNIT_DEPLOY_RES)
public class SM_PVP_DEPLOY extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_PVP_DEPLOY(byte[] prr) {
        super(prr);
    }
}
