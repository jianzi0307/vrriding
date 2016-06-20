package com.pkuvr.game_server.dao;

import com.pkuvr.game_server.domain.Playerprotectionbuff;
import com.pkuvr.game_server.domain.PlayerprotectionbuffExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerprotectionbuffMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int countByExample(PlayerprotectionbuffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int deleteByExample(PlayerprotectionbuffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int deleteByPrimaryKey(Integer roleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int insert(Playerprotectionbuff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int insertSelective(Playerprotectionbuff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    List<Playerprotectionbuff> selectByExample(PlayerprotectionbuffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    Playerprotectionbuff selectByPrimaryKey(Integer roleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int updateByExampleSelective(@Param("record") Playerprotectionbuff record, @Param("example") PlayerprotectionbuffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int updateByExample(@Param("record") Playerprotectionbuff record, @Param("example") PlayerprotectionbuffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int updateByPrimaryKeySelective(Playerprotectionbuff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PlayerProtectionBuff
     *
     * @mbggenerated Wed Oct 28 10:50:29 CST 2015
     */
    int updateByPrimaryKey(Playerprotectionbuff record);
}