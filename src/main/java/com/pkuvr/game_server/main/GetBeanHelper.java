package com.pkuvr.game_server.main;

import com.pkuvr.game_server.network.ScanPackage;
import com.pkuvr.game_server.protoservice.*;
import com.pkuvr.game_server.service.LoginService;
import com.pkuvr.game_server.service.MineService;
import com.pkuvr.game_server.service.RealTimePvpService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Properties;

/**
 * 从Spring中获取非单例的对象(代替new)
 * 这种方法不是很好,形成了对Spring的依赖
 * 但是 Lookup方法注入 需求修改的类太多了,
 * 而且需要仔细研究涉及到的每个类是 singleton　还是 prototype
 * 对Netty的处理机制也不熟悉,所以所有Service的引用都从这里走,这些都是单例
 *
 * @author SHACS
 */
public class GetBeanHelper {
    private static ApplicationContext applicationContext; // 保存spring工厂的引用

    public static void setApplicationContext(ApplicationContext ac) throws BeansException {
        GetBeanHelper.applicationContext = ac; // 获得该ApplicationContext引用
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static Properties getServerConfig() {
        return (Properties) applicationContext.getBean("serverConfig");
    }

    public static ScanPackage getCms() {
        return (ScanPackage) applicationContext.getBean("cms");
    }

    public static ScanPackage getSms() {
        return (ScanPackage) applicationContext.getBean("sms");
    }

    public static RealTimePvpControl getRealTimePvpControl() {
        return (RealTimePvpControl) applicationContext.getBean("RealTimePvpControl");
    }

    public static LoginService getLoginService() {
        return (LoginService) applicationContext.getBean("LoginService");
    }

    public static FriendControl getFriendControl() {
        return (FriendControl) applicationContext.getBean("FriendControl");
    }

    public static LoginControl getLoginControl() {
        return (LoginControl) applicationContext.getBean("LoginControl");
    }

    public static RealTimePvpService getRealTimePvpService() {
        return (RealTimePvpService) applicationContext.getBean("RealTimePvpService");
    }

    public static CrossServerControl getCrossServerControl() {
        return (CrossServerControl) applicationContext.getBean("CrossServerControl");
    }

    public static MineControl getMineControl() {
        return (MineControl) applicationContext.getBean("MineControl");
    }

    public static ZoneControl getZoneControl() {
        return (ZoneControl) applicationContext.getBean("ZoneControl");
    }

    public static MineService getMineService() {
        return (MineService) applicationContext.getBean("MineService");
    }
}
