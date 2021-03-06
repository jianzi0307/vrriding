package com.pkuvr.game_server.dao;

import com.pkuvr.game_server.domain.Playerzoneplunder;
import com.pkuvr.game_server.domain.PlayerzoneplunderExample;
import com.pkuvr.game_server.domain.PlayerzoneplunderKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerzoneplunderMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int countByExample(PlayerzoneplunderExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int deleteByExample(PlayerzoneplunderExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int deleteByPrimaryKey(PlayerzoneplunderKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int insert(Playerzoneplunder record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int insertSelective(Playerzoneplunder record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    List<Playerzoneplunder> selectByExample(PlayerzoneplunderExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    Playerzoneplunder selectByPrimaryKey(PlayerzoneplunderKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int updateByExampleSelective(@Param("record") Playerzoneplunder record,
                                 @Param("example") PlayerzoneplunderExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int updateByExample(@Param("record") Playerzoneplunder record,
                        @Param("example") PlayerzoneplunderExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int updateByPrimaryKeySelective(Playerzoneplunder record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerZonePlunder
     *
     * @mbggenerated Tue Nov 24 19:24:49 CST 2015
     */
    int updateByPrimaryKey(Playerzoneplunder record);
}