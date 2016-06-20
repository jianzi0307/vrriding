package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.network.CmComponent;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.PVP_FRIEND_REQ, state = State.AUTHED)
public class CM_PVP_FRIEND extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

//		byte[] prr = GetBeanHelper.getFriendControl().friendPvp(roleId, byteData);
//		super.sendPacket(new SM_PVP_FRIEND(prr));
    }
}
