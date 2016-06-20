package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.PVP_DEPLOY_VERIFY_NOTIFY_RES)
public class SM_PVP_DEPLOY_VERIFY_NOTIFY extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_PVP_DEPLOY_VERIFY_NOTIFY(byte[] prr) {
        super(prr);
    }
}
