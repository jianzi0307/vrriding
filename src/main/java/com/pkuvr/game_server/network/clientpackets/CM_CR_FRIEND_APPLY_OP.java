package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.serverpackets.SM_CR_FRIEND_APPLY_OP;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.CR_FRIEND_APPLY_OP_REQ, state = State.AUTHED)
public class CM_CR_FRIEND_APPLY_OP extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

        byte[] prr = GetBeanHelper.getFriendControl().friendApplyOp(roleId, byteData);
        super.sendPacket(new SM_CR_FRIEND_APPLY_OP(prr));
    }
}
