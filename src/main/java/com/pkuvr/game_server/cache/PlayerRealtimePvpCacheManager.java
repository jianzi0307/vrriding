package com.pkuvr.game_server.cache;

import com.pkuvr.game_server.cachevo.PlayerRealtimePvpVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class PlayerRealtimePvpCacheManager implements ICacheManager<Integer, PlayerRealtimePvpVO> {
    @Resource
    private JCSCache<Integer, PlayerRealtimePvpVO> playerRealtimePvpCache;

    @Override
    public PlayerRealtimePvpVO get(Integer key) {
        PlayerRealtimePvpVO value = playerRealtimePvpCache.get(key);

        if (value != null) {
            return value;
        }

        return value;
    }

    public Set<Integer> getKeySet() {
        return playerRealtimePvpCache.getKeySet();
    }

    public PlayerRealtimePvpVO getNotFromDB(Integer key) {
        return playerRealtimePvpCache.get(key);
    }

    public void put(Integer key, PlayerRealtimePvpVO value) {
        playerRealtimePvpCache.put(key, value);
    }

    public void remove(Integer key) {
        playerRealtimePvpCache.remove(key);
    }

    public String getRegionName() {
        return playerRealtimePvpCache.getRegionName();
    }
}
