package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.MINE_HARVEST_QUERY_RES)
public class SM_MINE_HARVEST_QUERY extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_MINE_HARVEST_QUERY(byte[] prr) {
        super(prr);
    }
}
