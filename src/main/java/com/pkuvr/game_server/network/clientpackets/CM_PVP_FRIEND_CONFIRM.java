package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.network.CmComponent;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.PVP_FRIEND_CONFIRM_REQ, state = State.AUTHED)
public class CM_PVP_FRIEND_CONFIRM extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

//		byte[] prr = GetBeanHelper.getFriendControl().friendPvpConfirm(roleId, byteData);
//		super.sendPacket(new SM_PVP_FRIEND_CONFIRM(prr));
    }
}
