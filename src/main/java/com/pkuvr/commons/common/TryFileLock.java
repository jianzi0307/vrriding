package com.pkuvr.commons.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * 判断程序是否正在运行(文件锁)
 *
 * @author hrbscs
 */
public class TryFileLock {
    private static final Log log = LogFactory.getLog(TryFileLock.class);
    private static final String FILE_PATH = "_running.txt";

    private File file; // 锁文件
    private RandomAccessFile randomAccessFile;
    private FileChannel fileChannel;
    private FileLock fileLock; // 锁文件的文件锁

    public TryFileLock(String lockKey) {
        super();
        file = new File(lockKey + FILE_PATH);
    }

    /**
     * 判断程序是否正在运行
     *
     * @return
     */
    public boolean isRunning() {
        try {
            // 自己获取文件锁,可以操作
            if (fileLock != null && fileLock.isValid())
                return false;

            // 不存在则创建文件
            if (!file.exists())
                file.createNewFile();

            // 不是文件,则认为程序正在运行
            if (!file.isFile())
                return true;

            // 试图获取文件锁
            randomAccessFile = new RandomAccessFile(file, "rw");
            fileChannel = randomAccessFile.getChannel();
            // 试图获取对此通道的文件的独占锁定。(tryLock 非阻塞,lock 阻塞)
            // 一个锁定对象，表示新获取的锁定，如果由于另一个程序保持着一个重叠锁定而无法获取锁定，则返回 null
            fileLock = fileChannel.tryLock();

            // 如果获取的锁为空或锁无效,则认为有程序在运行
            if (fileLock == null || !fileLock.isValid())
                return true;
        } catch (ClosedChannelException e) {
            return true; // 如果此通道已关闭
        } catch (OverlappingFileLockException e) {
            return true; // 另一个线程已阻塞于此方法
        } catch (IOException e) {
            log.error("创建锁文件时出错:" + file.getName(), e);
            return true;
        } finally {
            // 如果获取的锁为空或锁无效,则释放获取的文件渠道
            if (fileLock == null || !fileLock.isValid()) {
                try {
                    fileChannel.close();
                    randomAccessFile.close();
                } catch (IOException e) {
                }
            }
        }
        return false;
    }

    /**
     * 程序运行完成,删除锁文件,多线程并发的情况下,有可能文件无法正确删除,其实只要锁正确释放就可以了
     *
     * @param path
     */
    public void finished() {
        try {
            // 锁不为空且有效,则释放锁,并删除锁文件
            if (fileLock != null && fileLock.isValid()) {
                fileLock.release();
                fileChannel.close();
                randomAccessFile.close();
                file.delete();
            }
        } catch (Exception e) {
            log.error("释放文件锁时出错:" + file.getName(), e);
        }
    }

    /**
     * 获取锁路径
     *
     * @return
     */
    public String getFilePath() {
        return file.getPath();
    }


}
