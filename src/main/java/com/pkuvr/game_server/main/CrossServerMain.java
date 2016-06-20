package com.pkuvr.game_server.main;

import com.pkuvr.commons.common.TryFileLock;
import com.pkuvr.game_server.NettyGameServer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * 主程序,负责启动所有的监控线程
 *
 * @author hrbscs
 */
public class CrossServerMain {
    private static final Logger log = Logger.getLogger(CrossServerMain.class);

    @Resource
    private NettyGameServer nettyGameServer;

    public static CrossServerMain getFromApplicationContext(ApplicationContext ac) {
        return (CrossServerMain) ac.getBean("crossServerMain");

    }

    /**
     * 主程序入口
     *
     * @param args
     */
    public static void main(String[] args) {
        String lockKey = "CrossServerMain";
        TryFileLock tryFileLock = new TryFileLock(lockKey);

        // 0.判读是否是单例
        if (tryFileLock.isRunning()) {
            log.error("已经有一个实例启动了,请停止该进程.如果您确认该实例已停止,请手工删除程序所在目录中的文件:" + tryFileLock.getFilePath());  // 原来保证单例的 rmi 服务已删除,改为文件锁
            return;
        }

        // 2.加载spring配置
        CrossServerMain mm = null;

        try {
            ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            GetBeanHelper.setApplicationContext(ac);

            mm = CrossServerMain.getFromApplicationContext(ac);
            mm.nettyGameServer.initialize();

        } catch (Exception e) {
            log.error("启动MakeDarenMain程序时出错,请检查配置文件与程序.", e);
        } finally {
            tryFileLock.finished();// 移除文件锁
        }

//        System.exit(0);
    }

}