package com.pkuvr.game_server.cache;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class OwnPlayerMineAttackedCacheManager implements ICacheManager<Integer, Integer> {
    @Resource
    private JCSCache<Integer, Integer> ownPlayerMineAttackedCache;

    @Override
    public Integer get(Integer key) {
        Integer value = ownPlayerMineAttackedCache.get(key);

        if (value != null) {
            return value;
        }

        return value;
    }

    public Set<Integer> getKeySet() {
        return ownPlayerMineAttackedCache.getKeySet();
    }

    public Integer getNotFromDB(Integer key) {
        return ownPlayerMineAttackedCache.get(key);
    }

    public void put(Integer key, Integer value) {
        ownPlayerMineAttackedCache.put(key, value);
    }

    public void remove(Integer key) {
        ownPlayerMineAttackedCache.remove(key);
    }

    public String getRegionName() {
        return ownPlayerMineAttackedCache.getRegionName();
    }
}