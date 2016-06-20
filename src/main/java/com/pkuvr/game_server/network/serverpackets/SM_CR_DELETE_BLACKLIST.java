package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_DEIETE_BLACKLIST_RES)
public class SM_CR_DELETE_BLACKLIST extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_CR_DELETE_BLACKLIST(byte[] prr) {
        super(prr);
    }
}