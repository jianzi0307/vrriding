package com.pkuvr.game_server.cache;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class PlayerMineAttackedCacheManager implements ICacheManager<Integer, Long> {
    @Resource
    private JCSCache<Integer, Long> playerMineAttackedCache;

    @Override
    public Long get(Integer key) {
        Long value = playerMineAttackedCache.get(key);

        if (value != null) {
            return value;
        }

        return value;
    }

    public Set<Integer> getKeySet() {
        return playerMineAttackedCache.getKeySet();
    }

    public Long getNotFromDB(Integer key) {
        return playerMineAttackedCache.get(key);
    }

    public void put(Integer key, Long value) {
        playerMineAttackedCache.put(key, value);
    }

    public void remove(Integer key) {
        playerMineAttackedCache.remove(key);
    }

    public String getRegionName() {
        return playerMineAttackedCache.getRegionName();
    }
}