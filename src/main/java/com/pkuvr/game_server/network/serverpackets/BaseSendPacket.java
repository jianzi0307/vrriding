package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.game_server.constant.SmOpCode;

public class BaseSendPacket extends AbstractSM {
    private static final long serialVersionUID = 1L;

    public BaseSendPacket(SmOpCode smOpcode, byte[] prr) {
        super(prr);
        super.setOpCode(smOpcode.getOpCode());
    }

    public BaseSendPacket(SmOpCode smOpcode, byte[] prr, boolean isBigSize) {
        super(prr, isBigSize);
        super.setOpCode(smOpcode.getOpCode());
    }
}
