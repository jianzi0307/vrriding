package com.pkuvr.game_server.cache;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class PlayerMineInstanceAttackedCacheManager implements ICacheManager<Long, Integer> {
    @Resource
    private JCSCache<Long, Integer> playerMineInstanceAttackedCache;

    @Override
    public Integer get(Long key) {
        Integer value = playerMineInstanceAttackedCache.get(key);

        if (value != null) {
            return value;
        }

        return value;
    }

    public Set<Long> getKeySet() {
        return playerMineInstanceAttackedCache.getKeySet();
    }

    public Integer getNotFromDB(Long key) {
        return playerMineInstanceAttackedCache.get(key);
    }

    public void put(Long key, Integer value) {
        playerMineInstanceAttackedCache.put(key, value);
    }

    public void remove(Long key) {
        playerMineInstanceAttackedCache.remove(key);
    }

    public String getRegionName() {
        return playerMineInstanceAttackedCache.getRegionName();
    }
}