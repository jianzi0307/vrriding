package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_ROLE_INFO_SYNC_RES)
public class SM_ROLE_INFO_SYNC extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_ROLE_INFO_SYNC(byte[] prr) {
        super(prr);
    }
}
