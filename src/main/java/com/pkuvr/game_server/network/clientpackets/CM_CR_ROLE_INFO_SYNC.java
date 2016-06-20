package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.serverpackets.SM_ROLE_INFO_SYNC;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.CR_ROLE_INFO_SYNC_REQ, state = State.AUTHED)
public class CM_CR_ROLE_INFO_SYNC extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

        byte[] prr = GetBeanHelper.getCrossServerControl().crossSyncRoleInfo(roleId, byteData);
        super.sendPacket(new SM_ROLE_INFO_SYNC(prr));
    }
}
