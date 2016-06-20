package com.pkuvr.game_server.cache;

import com.pkuvr.game_server.cachevo.PlayerBufferVO;
import com.pkuvr.game_server.dao.PlayercrossmineinfoMapper;
import com.pkuvr.game_server.domain.Playercrossmineinfo;
import com.pkuvr.game_server.vo.MineProductivityVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Component
public class PlayerBufferCacheMananger implements ICacheManager<Integer, PlayerBufferVO> {
    @Resource
    private JCSCache<Integer, PlayerBufferVO> playerBufferCache;
    @Resource
    private PlayercrossmineinfoMapper playercrossmineinfoMapper;
    @Resource
    private ObjectMapper jacksonObjectMapper;

    /**
     * 主键为:roleId , 如果不存在,返回空
     */
    public PlayerBufferVO get(Integer key) {
        PlayerBufferVO value = playerBufferCache.get(key);
        if (value != null)
            return value;

        Playercrossmineinfo playercrossmineinfo = playercrossmineinfoMapper.selectByPrimaryKey(key);
        if (playercrossmineinfo == null)
            return null;

        value = new PlayerBufferVO();
        try {
            List<MineProductivityVO> mineProductivityList = jacksonObjectMapper.readValue(playercrossmineinfo.getMineproductivitylist(),
                    new TypeReference<List<MineProductivityVO>>() {
                    });
            value.setMineProductivityList(mineProductivityList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    public Set<Integer> getKeySet() {
        return playerBufferCache.getKeySet();
    }

    public PlayerBufferVO getNotFromDB(Integer key) {
        return playerBufferCache.get(key);
    }

    public void put(Integer key, PlayerBufferVO value) {
        playerBufferCache.put(key, value);
    }

    public void remove(Integer key) {
        playerBufferCache.remove(key);
    }

    public String getRegionName() {
        return playerBufferCache.getRegionName();
    }

}
