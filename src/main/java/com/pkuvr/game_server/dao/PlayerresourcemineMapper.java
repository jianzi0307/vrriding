package com.pkuvr.game_server.dao;

import com.pkuvr.game_server.domain.Playerresourcemine;
import com.pkuvr.game_server.domain.PlayerresourcemineExample;
import com.pkuvr.game_server.domain.PlayerresourcemineKey;
import com.pkuvr.game_server.domain.PlayerresourcemineWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerresourcemineMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int countByExample(PlayerresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int deleteByExample(PlayerresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int deleteByPrimaryKey(PlayerresourcemineKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int insert(PlayerresourcemineWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int insertSelective(PlayerresourcemineWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    List<PlayerresourcemineWithBLOBs> selectByExampleWithBLOBs(
            PlayerresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    List<Playerresourcemine> selectByExample(PlayerresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    PlayerresourcemineWithBLOBs selectByPrimaryKey(PlayerresourcemineKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int updateByExampleSelective(
            @Param("record") PlayerresourcemineWithBLOBs record,
            @Param("example") PlayerresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int updateByExampleWithBLOBs(
            @Param("record") PlayerresourcemineWithBLOBs record,
            @Param("example") PlayerresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int updateByExample(@Param("record") Playerresourcemine record,
                        @Param("example") PlayerresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int updateByPrimaryKeySelective(PlayerresourcemineWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(PlayerresourcemineWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerResourceMine
     *
     * @mbggenerated Wed Jan 28 10:55:47 CST 2015
     */
    int updateByPrimaryKey(Playerresourcemine record);
}