package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.CR_ROLE_INFO_GET_REQ, state = State.AUTHED)
public class CM_CR_ROLE_INFO_GET extends AbstractCM {
    @Override
    protected void runImpl() {
        GetBeanHelper.getCrossServerControl().crRoleInfoGet(byteData);
    }
}
