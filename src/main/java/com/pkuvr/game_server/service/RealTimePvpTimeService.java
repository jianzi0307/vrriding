package com.pkuvr.game_server.service;

import com.pkuvr.game_server.network.GameServerSendService;
import org.apache.log4j.Logger;

import java.util.*;

public class RealTimePvpTimeService {
    private static final Logger logger = Logger.getLogger(RealTimePvpTimeService.class);
    private static List<Integer> pvpMatchTimeList = new ArrayList<Integer>();
    private static Map<Integer, Long> pvpMatchTimeMap = new HashMap<Integer, Long>();

    public static void addPvpMatchTime(int pvpMatchTime) {
        logger.info("pvpMatchTimeList.size() === " + pvpMatchTimeList.size());
        if (pvpMatchTimeList.size() > 100) {
            pvpMatchTimeList.remove(0);
        }
        pvpMatchTimeList.add(pvpMatchTime);
    }

    public static int getAvgPvpMatchTime() {
        int defaultAvgMatchTime = 60;
        if (pvpMatchTimeList.size() > 0) {
            int totalTime = 0;
            for (Integer time : pvpMatchTimeList) {
                totalTime = totalTime + time;
            }
            defaultAvgMatchTime = (int) (totalTime / pvpMatchTimeList.size());
            logger.info("defaultAvgMatchTime === " + defaultAvgMatchTime);
        }
        return defaultAvgMatchTime;
    }

    public static void addPvpMatchTimeToMap(int roleId, long matchTime) {
        pvpMatchTimeMap.put(roleId, matchTime);
    }

    public static void removePvpMatchTimeFromMap(int roleId) {
        pvpMatchTimeMap.remove(roleId);
    }

    public static long getPvpMatchTimeFromMap(int roleId) {
        return (Long) pvpMatchTimeMap.get(roleId);
    }

    public static int getAvgNpcMatchTime() {
        int defaultAvgNpcMatchTime = 4;
        GameServerSendService gameServerSendService = GameServerSendService.getInstance();
        Set<Integer> allOnlineUser = gameServerSendService.getAllOnlineUser();
        int onlineUserNum = allOnlineUser.size();
        defaultAvgNpcMatchTime = defaultAvgNpcMatchTime + (int) Math.pow((double) onlineUserNum, 0.4);
        return defaultAvgNpcMatchTime;
    }

}
