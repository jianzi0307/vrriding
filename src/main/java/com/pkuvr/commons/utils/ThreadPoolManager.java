/**
 * 执行已提交的 Runnable 任务的对象。此接口提供一种将任务提交与每个任务将如何运行的机制（包括线程使用的细节、调度等）分离开来的方法。
 * 还提供了定时方法
 * <p>
 * This file is part of aion-emu <aion-emu.com>.
 * <p>
 * aion-emu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * aion-emu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkuvr.commons.utils;

import com.pkuvr.commons.utils.concurrent.ExecuteWrapper;
import org.apache.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author -Nemesiss-
 */
public class ThreadPoolManager implements Executor {
    private static final Logger log = Logger.getLogger(ThreadPoolManager.class); // Logger for this class,(日志)
    private final ThreadPoolExecutor generalPacketsThreadPool; // TPE for execution of gameserver client packets,(执行gameserver客户包)
    private ScheduledThreadPoolExecutor scheduledThreadPool; // STPE for normal scheduled tasks,(正常，预定的任务),

    /**
     * Constructor.
     */
    private ThreadPoolManager() {
        new DeadLockDetector(60, DeadLockDetector.RESTART).start(); // 每隔60秒检查一次死锁线程,若线程死锁,重启线程
        scheduledThreadPool = new ScheduledThreadPoolExecutor(4, new PriorityThreadFactory("ScheduledThreadPool", Thread.NORM_PRIORITY)); // 它可另行安排在给定的延迟后运行命令，或者定期执行命令。4个核心线程,线程等级:普通
        generalPacketsThreadPool = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()); // 线程池,保留一个线程,其他线程超60秒则回收.一种阻塞队列，其中每个插入操作必须等待另一个线程的对应移除操作
    }

    /**
     * @return the packetsThreadPool
     */
    public ThreadPoolExecutor getPacketsThreadPool() {
        return generalPacketsThreadPool;
    }

    /**
     * Schedule
     * 创建并执行在给定延迟后启用的一次性操作。
     * @param <T>
     * @param r
     * @param delay
     * @return ScheduledFuture
     */
    @SuppressWarnings("unchecked")
    public <T extends Runnable> ScheduledFuture<T> schedule(T r, long delay) {
        try {
            if (delay < 0)
                delay = 0;
            return (ScheduledFuture<T>) scheduledThreadPool.schedule(r, delay, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            return null; /* shutdown, ignore */
        }
    }

    /**
     * Schedule at fixed rate
     * 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
     * 也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。
     * @param <T>
     * @param r
     * @param initial
     * @param delay
     * @return ScheduledFuture
     */
    @SuppressWarnings("unchecked")
    public <T extends Runnable> ScheduledFuture<T> scheduleAtFixedRate(T r, long initial, long delay) {
        try {
            if (delay < 0)
                delay = 0;
            if (initial < 0)
                initial = 0;
            return (ScheduledFuture<T>) scheduledThreadPool.scheduleAtFixedRate(r, initial, delay, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    /**
     * Executes Runnable - GameServer Client packet.
     * 在将来某个时间执行给定任务。可以在新线程中或者在现有池线程中执行该任务。
     * 如果无法将任务提交执行，或者因为此执行程序已关闭，或者因为已达到其容量，则该任务由当前 RejectedExecutionHandler 处理。
     * @param pkt
     */
    public void execute(Runnable pkt) {
        generalPacketsThreadPool.execute(new ExecuteWrapper(pkt));
    }

    /**
     * Shutdown all thread pools.
     */
    public void shutdown() {
        try {
            scheduledThreadPool.shutdown();
            generalPacketsThreadPool.shutdown();
            scheduledThreadPool.awaitTermination(2, TimeUnit.SECONDS);
            generalPacketsThreadPool.awaitTermination(2, TimeUnit.SECONDS);
            log.info("All ThreadPools are now stopped.");
        } catch (InterruptedException e) {
            log.error("Can't shutdown ThreadPoolManager", e);
        }
    }

    /**
     * PriorityThreadFactory creating new threads for ThreadPoolManager
     * 创建新的线程 for ThreadPoolManager
     * 根据需要创建新线程的对象。
     * 使用线程工厂就无需再手工编写对 new Thread 的调用了，从而允许应用程序使用特殊的线程子类、属性等等。
     */
    private class PriorityThreadFactory implements ThreadFactory {
        private int prio; // Priority of new threads,新线程的优先级
        private String name; // Thread group name
        private AtomicInteger threadNumber = new AtomicInteger(1); // Number of created threads,可以用原子方式更新的 int 值。
        private ThreadGroup group; // ThreadGroup for created threads

        /**
         * Constructor.
         *
         * @param name
         * @param prio
         */
        public PriorityThreadFactory(String name, int prio) {
            this.prio = prio;
            this.name = name;
            group = new ThreadGroup(this.name);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r);
            t.setName(name + "-" + threadNumber.getAndIncrement());
            t.setPriority(prio);
            t.setUncaughtExceptionHandler(new ThreadUncaughtExceptionHandler());
            return t;
        }
    }
}
