package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.serverpackets.SM_CR_OWN_FRIEND_APPLY_LIST;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.CR_OWN_FRIEND_APPLY_LIST_REQ, state = State.AUTHED)
public class CM_CR_OWN_FRIEND_APPLY_LIST extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

        byte[] prr = GetBeanHelper.getFriendControl().getOwnFriendApplyList(roleId, byteData);
        super.sendPacket(new SM_CR_OWN_FRIEND_APPLY_LIST(prr));
    }
}
