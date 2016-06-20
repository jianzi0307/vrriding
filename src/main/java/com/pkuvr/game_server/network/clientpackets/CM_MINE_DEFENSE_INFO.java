package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.webcore.State;
import com.pkuvr.game_server.constant.CmOpCode;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.CmComponent;
import com.pkuvr.game_server.network.serverpackets.SM_MINE_DEFENSE_INFO;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@CmComponent(opCode = CmOpCode.MINE_DEFEND_INFO_REQ, state = State.AUTHED)
public class CM_MINE_DEFENSE_INFO extends AbstractCM {
    @Override
    protected void runImpl() {
        int roleId = getRoleId();

        byte[] prr = GetBeanHelper.getMineControl().getMineDefenseInfo(roleId, byteData);
        super.sendPacket(new SM_MINE_DEFENSE_INFO(prr));
    }
}
