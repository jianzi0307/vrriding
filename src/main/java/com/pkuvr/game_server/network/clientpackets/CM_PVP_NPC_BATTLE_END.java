package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.serverpackets.SM_PVP_NPC_BATTLE_END;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.PVP_NPC_BATTLE_END_REQ, state = State.AUTHED)
public class CM_PVP_NPC_BATTLE_END extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

        byte[] prr = GetBeanHelper.getRealTimePvpControl().pvpNpcBattleEnd(roleId, byteData);
        super.sendPacket(new SM_PVP_NPC_BATTLE_END(prr));
    }
}
