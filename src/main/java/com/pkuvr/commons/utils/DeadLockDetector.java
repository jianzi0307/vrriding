/**
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

import org.apache.log4j.Logger;

import java.lang.management.*;

/**
 * @author -Nemesiss-
 */
public class DeadLockDetector extends Thread {
    public static final byte NOTHING = 0; // What should we do on DeadLock
    public static final byte RESTART = 1; // What should we do on DeadLock
    private static final Logger log = Logger.getLogger(DeadLockDetector.class); // Logger for this class.
    private final int sleepTime; // how often check for deadlocks
    private final ThreadMXBean tmx; // ThreadMXBean , Java 虚拟机线程系统的管理接口。Java 虚拟机具有此接口的实现类的单一实例。可以通过调用 ManagementFactory.getThreadMXBean() 方法或从平台 MBeanServer 方法获得它。
    private final byte doWhenDL; // What should we do on DeadLock

    /**
     * Create new DeadLockDetector with given values.
     *
     * @param sleepTime 间隔多少秒,检查一次死线程
     * @param doWhenDL 是什么也不做,还是重启线程
     */
    public DeadLockDetector(int sleepTime, byte doWhenDL) {
        super("DeadLockDetector");
        this.sleepTime = sleepTime * 1000;
        this.tmx = ManagementFactory.getThreadMXBean();
        this.doWhenDL = doWhenDL;
    }

    /**
     * Check if there is a DeadLock.
     */
    @Override
    public final void run() {
        boolean deadlock = false;
        while (!deadlock) {
            try {
                long[] ids = tmx.findDeadlockedThreads();

                if (ids != null) {
                    /** deadlock found :/ */
                    deadlock = true;
                    ThreadInfo[] tis = tmx.getThreadInfo(ids, true, true);
                    String info = "DeadLock Found!\n";
                    for (ThreadInfo ti : tis) {
                        info += ti.toString();
                    }

                    for (ThreadInfo ti : tis) {
                        LockInfo[] locks = ti.getLockedSynchronizers();
                        MonitorInfo[] monitors = ti.getLockedMonitors();
                        if (locks.length == 0 && monitors.length == 0) {
                            /** this thread is deadlocked but its not guilty */
                            continue;
                        }

                        ThreadInfo dl = ti;
                        info += "Java-level deadlock:\n";
                        info += "\t" + dl.getThreadName() + " is waiting to lock " + dl.getLockInfo().toString() + " which is held by " + dl.getLockOwnerName() + "\n";
                        while ((dl = tmx.getThreadInfo(new long[]{dl.getLockOwnerId()}, true, true)[0]).getThreadId() != ti.getThreadId()) {
                            info += "\t" + dl.getThreadName() + " is waiting to lock " + dl.getLockInfo().toString() + " which is held by " + dl.getLockOwnerName() + "\n";
                        }
                    }
                    log.warn(info);

                    if (doWhenDL == RESTART) {
                        System.exit(ExitCode.CODE_RESTART);
                    }
                }
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                log.warn("DeadLockDetector: " + e, e);
            }
        }
    }
}
