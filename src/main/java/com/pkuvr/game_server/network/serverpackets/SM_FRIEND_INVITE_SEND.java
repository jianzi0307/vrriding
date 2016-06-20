package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;
import com.pkuvr.game_server.network.SmVO;

@SmVO(opCode = SmOpCode.FRIEND_INVITE_SEND)
public class SM_FRIEND_INVITE_SEND extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public SM_FRIEND_INVITE_SEND(byte[] prr) {
        super(prr);
    }
}
