package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.ZONE_CHANGE_PLAYER_RES)
public class SM_ZONE_CHANGE_PLAYER extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_ZONE_CHANGE_PLAYER(byte[] prr) {
        super(prr);
    }
}
