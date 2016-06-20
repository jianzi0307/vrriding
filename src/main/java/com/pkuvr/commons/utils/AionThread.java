/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.pkuvr.commons.utils;

import com.pkuvr.commons.utils.concurrent.RunnableStatsManager;

/**
 * @author NB4L1
 */
public abstract class AionThread extends Thread {

    private volatile boolean _isAlive = true;

    protected AionThread() {
        super();
    }

    protected AionThread(String name) {
        super(name);
    }

    public final void shutdown() throws InterruptedException {
        onShutdown();

        _isAlive = false;

        join();
    }

    protected void onShutdown() {
    }

    @Override
    public final void run() {
        try {
            while (_isAlive) {
                final long begin = System.nanoTime();

                try {
                    runTurn();
                } finally {
                    RunnableStatsManager.handleStats(getClass(), System.nanoTime() - begin);
                }

                try {
                    sleepTurn();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            onFinally();
        }
    }

    protected abstract void runTurn();

    protected abstract void sleepTurn() throws InterruptedException;

    protected void onFinally() {
    }
}
