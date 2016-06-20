package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.PVP_PLAYER_EXIT_NOTIGY_RES)
public class SM_PVP_PLAYER_EXIT_NOTIFY extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_PVP_PLAYER_EXIT_NOTIFY(byte[] prr) {
        super(prr);
    }
}
