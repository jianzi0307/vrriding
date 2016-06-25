package com.pkuvr.game_server.network;

import com.pkuvr.commons.network.netty.handler.AbstractPacketHandlerFactory;
import com.pkuvr.commons.network.netty.packet.AbstractClientPacket;
import com.pkuvr.commons.network.netty.packet.AbstractServerPacket;
import com.pkuvr.game_server.main.GetBeanHelper;
import org.apache.log4j.Logger;

import java.util.Set;

/**
 * 注册 操作码 与 处理类 之间的映射关系
 *
 * @author SHACS
 */
public class GameServerPacketHandlerFactory extends AbstractPacketHandlerFactory<GameServerChannelHandler> {
    private static final Logger logger = Logger.getLogger(GameServerPacketHandlerFactory.class);
//	private ApplicationContext applicationContext;
//	@Resource private ScanPackage cms;
//	@Resource private ScanPackage sms;

    public GameServerPacketHandlerFactory() {
        super(null, null);

        // 1绑定请求处理类类和opCode
//		Map<String, Object> cmMap = applicationContext.getBeansWithAnnotation(CmComponent.class); // 不知道为什么,偶尔会为空
//		if(cmMap != null) {
//			Set<String> classNames = cmMap.keySet();
//			for (String className : classNames) {
//				try {
//					Object cmObject = cmMap.get(className);
//					CmComponent cmComponent = cmObject.getClass().getAnnotation(CmComponent.class);
//					if(cmComponent == null || !(cmObject instanceof AbstractClientPacket<?>))
//						continue;
//					
//					AbstractClientPacket<GameServerChannelHandler> cm = (AbstractClientPacket<GameServerChannelHandler>)cmObject;
//					cm.setOpCode(cmComponent.opCode().getOpCode());
//					
//					addPacket(cm,cmComponent.state());
//				} catch (Exception e) {
//					logger.error("处理类时出错,类:"+className , e);
//				}
//			}
//		}
        //扫描接收处理包
        ScanPackage cms = GetBeanHelper.getCms();
        Set<Class<?>> cmsClasses = cms.getClasses();
        logger.info("扫描接收处理包=====>" + cmsClasses);
        for (Class<?> cmsClass : cmsClasses) {
            try {
                CmComponent cmComponent = (CmComponent) cmsClass.getAnnotation(CmComponent.class);
                if (cmComponent == null)
                    continue;

                //绑定opcode与handler
                AbstractClientPacket<GameServerChannelHandler> cm = (AbstractClientPacket<GameServerChannelHandler>) GetBeanHelper.getBean(cmsClass);
                cm.setOpCode(cmComponent.opCode().getOpCode());

                addPacket(cm, cmComponent.state());
            } catch (Exception e) {
                logger.error("处理类时出错,类:" + cmsClass.getName(), e);
            }
        }

        //扫描发送包
        // 2.绑定响应类和opCode
        ScanPackage sms = GetBeanHelper.getSms();
        Set<Class<?>> smsClasses = sms.getClasses();
        logger.info("扫描发送包====>" + smsClasses);
        for (Class<?> smsClass : smsClasses) {
            try {
                SmVO smVO = (SmVO) smsClass.getAnnotation(SmVO.class);
                if (smVO == null)
                    continue;

                addPacket((Class<? extends AbstractServerPacket<GameServerChannelHandler>>) smsClass, smVO.opCode().getOpCode());
            } catch (Exception e) {
                logger.error("处理类时出错,类:" + smsClass.getName(), e);
            }
        }
    }
}















