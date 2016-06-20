package com.pkuvr.login_server.dao;

import com.pkuvr.login_server.domain.PlayerFeedback;
import com.pkuvr.login_server.domain.PlayerFeedbackExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerFeedbackMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int countByExample(PlayerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int deleteByExample(PlayerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int insert(PlayerFeedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int insertSelective(PlayerFeedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    List<PlayerFeedback> selectByExample(PlayerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    PlayerFeedback selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int updateByExampleSelective(@Param("record") PlayerFeedback record, @Param("example") PlayerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int updateByExample(@Param("record") PlayerFeedback record, @Param("example") PlayerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int updateByPrimaryKeySelective(PlayerFeedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Player_Feedback
     *
     * @mbggenerated Wed Sep 12 17:45:32 CST 2012
     */
    int updateByPrimaryKey(PlayerFeedback record);
}