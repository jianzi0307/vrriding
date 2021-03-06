package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.CR_ROLE_INFO_GET_NOTIFY_RES)
public class SM_ROLE_INFO_GET_NOTIFY extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_ROLE_INFO_GET_NOTIFY(byte[] prr) {
        super(prr);
    }
}
