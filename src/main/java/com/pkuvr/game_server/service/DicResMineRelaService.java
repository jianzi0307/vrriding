package com.pkuvr.game_server.service;

import com.pkuvr.game_server.dao.DicresminerelaMapper;
import com.pkuvr.game_server.domain.Dicresminerela;
import com.pkuvr.game_server.domain.DicresminerelaExample;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DicResMineRelaService")
public class DicResMineRelaService {
    private static Map<Integer, Float> dicresminerelaMap = new HashMap<Integer, Float>();
    @Resource
    private DicresminerelaMapper dicresminerelaMapper;

    /**
     * 初始化,构造 TreeSet , Map
     */
    @PostConstruct
    public void start() {
        List<Dicresminerela> dicResMineRelaList = dicresminerelaMapper.selectByExample(new DicresminerelaExample());
        for (Dicresminerela dicresminerela : dicResMineRelaList) {
            dicresminerelaMap.put(dicresminerela.getResminenum(), dicresminerela.getResproducemodulus());
        }
    }

    public float getDicResMineRela(int resMineNum) {
        float resMineRela = 0;
        if (dicresminerelaMap.get(resMineNum) != null) {
            resMineRela = dicresminerelaMap.get(resMineNum);
        }
        return resMineRela;
    }
}
