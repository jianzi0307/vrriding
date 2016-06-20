package com.pkuvr.game_server.dao;

import com.pkuvr.game_server.domain.PlayerfriendsapplyExample;
import com.pkuvr.game_server.domain.PlayerfriendsapplyKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerfriendsapplyMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerFriendsApply
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    int countByExample(PlayerfriendsapplyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerFriendsApply
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    int deleteByExample(PlayerfriendsapplyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerFriendsApply
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    int deleteByPrimaryKey(PlayerfriendsapplyKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerFriendsApply
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    int insert(PlayerfriendsapplyKey record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerFriendsApply
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    int insertSelective(PlayerfriendsapplyKey record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerFriendsApply
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    List<PlayerfriendsapplyKey> selectByExample(
            PlayerfriendsapplyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerFriendsApply
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    int updateByExampleSelective(@Param("record") PlayerfriendsapplyKey record,
                                 @Param("example") PlayerfriendsapplyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table PlayerFriendsApply
     *
     * @mbggenerated Thu May 19 12:37:02 CST 2016
     */
    int updateByExample(@Param("record") PlayerfriendsapplyKey record,
                        @Param("example") PlayerfriendsapplyExample example);
}