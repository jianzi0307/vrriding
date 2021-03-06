package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.serverpackets.SM_ZONE_BATTLE_END;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.ZONE_BATTLE_END_REQ, state = State.AUTHED)
public class CM_ZONE_BATTLE_END extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

        byte[] prr = GetBeanHelper.getZoneControl().zoneBattleEnd(roleId, byteData);
        super.sendPacket(new SM_ZONE_BATTLE_END(prr));
    }
}
