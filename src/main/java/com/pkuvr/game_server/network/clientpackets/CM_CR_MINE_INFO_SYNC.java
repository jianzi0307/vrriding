package com.pkuvr.game_server.network.clientpackets;

//import com.gfan.gameserver.main.GetBeanHelper;
//import com.gfan.gameserver.network.serverpackets.SM_MINE_INFO_SYNC;
//import org.springframework.context.annotation.Scope;
//import com.gfan.commons.network.webcore.State;
//import com.gfan.gameserver.constant.CmOpCode;
//import com.gfan.gameserver.network.CmComponent;

//@Scope("prototype")
//@CmComponent(opCode = CmOpCode.CR_MINE_INFO_SYNC_REQ, state = State.AUTHED)
//public class CM_CR_MINE_INFO_SYNC extends AbstractCM {
//	@Override
//	protected void runImpl() {
//		int roleId = getRoleId();
//		
//		byte[] prr = GetBeanHelper.getCrossServerControl().crossSyncMineInfo(roleId, byteData);
//		super.sendPacket(new SM_MINE_INFO_SYNC(prr));
//	}
//}
