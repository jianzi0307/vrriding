package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.MINE_COLLECT_LOG_RES)
public class SM_MINE_COLLECT_LOG extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_MINE_COLLECT_LOG(byte[] prr) {
        super(prr);
    }
}
