package com.pkuvr.game_server.dao;

import com.pkuvr.game_server.domain.Dicunlockresourcemine;
import com.pkuvr.game_server.domain.DicunlockresourcemineExample;
import com.pkuvr.game_server.domain.DicunlockresourcemineKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicunlockresourcemineMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int countByExample(DicunlockresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int deleteByExample(DicunlockresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int deleteByPrimaryKey(DicunlockresourcemineKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int insert(Dicunlockresourcemine record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int insertSelective(Dicunlockresourcemine record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    List<Dicunlockresourcemine> selectByExample(
            DicunlockresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    Dicunlockresourcemine selectByPrimaryKey(DicunlockresourcemineKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int updateByExampleSelective(@Param("record") Dicunlockresourcemine record,
                                 @Param("example") DicunlockresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int updateByExample(@Param("record") Dicunlockresourcemine record,
                        @Param("example") DicunlockresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int updateByPrimaryKeySelective(Dicunlockresourcemine record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicUnlockResourceMine
     *
     * @mbggenerated Tue Jun 16 15:10:40 CST 2015
     */
    int updateByPrimaryKey(Dicunlockresourcemine record);
}