package com.pkuvr.game_server.network.clientpackets;

import com.pkuvr.commons.network.netty.packet.AbstractClientPacket;
import com.pkuvr.game_server.constant.AppConfig;
import com.pkuvr.game_server.main.GetBeanHelper;
import com.pkuvr.game_server.network.GameServerChannelHandler;
import com.pkuvr.game_server.utils.DESUtil;

/**
 * <p>类说明:</p>
 * <p>文件名： AbstractCM.java</p>
 * <p>创建人及时间：	宋士龙 2012-5-4</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 **/
public abstract class AbstractCM extends AbstractClientPacket<GameServerChannelHandler> {
    protected byte[] byteData;

    public AbstractCM() {
        super(-1);
    }

    public int getRoleId() {
        return channelHandler.getRoleId();
    }

    @Override
    protected void readImpl() {
        // 大于 0x5000 为管理员控制用
        if (opCode >= 0x5001) {
            byte[] enCodeData = readB();
            String secretKey = GetBeanHelper.getServerConfig().getProperty(AppConfig.GM_SECRET_KEY);
            DESUtil desUtil = new DESUtil(secretKey);
            try {
                byteData = desUtil.getDesCode(enCodeData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            byteData = readB();
        }
    }
}
