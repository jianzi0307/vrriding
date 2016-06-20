package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.serverpackets.SM_ZONE_DEFENSE_DEPLOY;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.ZONE_DEFEND_DEPLOY_REQ, state = State.AUTHED)
public class CM_ZONE_DEFENSE_DEPLOY extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

        byte[] prr = GetBeanHelper.getZoneControl().zoneDefenseDeploy(roleId, byteData);
        super.sendPacket(new SM_ZONE_DEFENSE_DEPLOY(prr));
    }
}
