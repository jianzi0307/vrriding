package com.pkuvr.game_server.cache;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class PlayerZoneAttackedCacheManager implements ICacheManager<Integer, Integer> {
    @Resource
    private JCSCache<Integer, Integer> playerZoneAttackedCache;

    @Override
    public Integer get(Integer key) {
        Integer value = playerZoneAttackedCache.get(key);

        if (value != null) {
            return value;
        }

        return value;
    }

    public Set<Integer> getKeySet() {
        return playerZoneAttackedCache.getKeySet();
    }

    public Integer getNotFromDB(Integer key) {
        return playerZoneAttackedCache.get(key);
    }

    public void put(Integer key, Integer value) {
        playerZoneAttackedCache.put(key, value);
    }

    public void remove(Integer key) {
        playerZoneAttackedCache.remove(key);
    }

    public String getRegionName() {
        return playerZoneAttackedCache.getRegionName();
    }
}