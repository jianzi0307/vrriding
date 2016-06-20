package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.constant.SeaErrorCode;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.serverpackets.SM_CR_PING;
import com.pkuvr.game_server.proto.serverproto.CR_Ping_Response.CR_Ping_Res;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.CR_PING_REQ, state = State.AUTHED)
public class CM_CR_PING extends AbstractCM {
    @Override
    protected void runImpl() {
        CR_Ping_Res.Builder resBuilder = CR_Ping_Res.newBuilder();
        resBuilder.setErrorCode(SeaErrorCode.OK.getErrorCode());
        super.sendPacket(new SM_CR_PING(resBuilder.build().toByteArray()));
    }
}
