package com.pkuvr.game_server.network.serverpackets;

import com.pkuvr.commons.network.netty.packet.AbstractServerPacket;
import com.pkuvr.game_server.network.GameServerChannelHandler;

/**
 * <p>类说明:</p>
 * <p>文件名： AbstractSM.java</p>
 * <p>创建人及时间：	宋士龙 2012-5-4</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>private  returnByte;
 **/
public abstract class AbstractSM extends AbstractServerPacket<GameServerChannelHandler> {
    private static final long serialVersionUID = 1L;
    protected byte[] protoResultReturn; // 返回的,要写入chanel的二进制串

    public AbstractSM(byte[] prr) {
        protoResultReturn = prr;
    }

    public AbstractSM(byte[] prr, boolean isBigSize) {
        super(isBigSize);
        protoResultReturn = prr;
    }

    @Override
    protected void writeImpl(GameServerChannelHandler channelhandler) {
        writeB(protoResultReturn);
    }
}
