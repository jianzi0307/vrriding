package com.pkuvr.manage_server.dao;

import com.pkuvr.manage_server.domain.SystemMailToServerExample;
import com.pkuvr.manage_server.domain.SystemMailToServerKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemMailToServerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table System_Mail_To_Server
     *
     * @mbggenerated Thu May 15 16:52:10 CST 2014
     */
    int countByExample(SystemMailToServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table System_Mail_To_Server
     *
     * @mbggenerated Thu May 15 16:52:10 CST 2014
     */
    int deleteByExample(SystemMailToServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table System_Mail_To_Server
     *
     * @mbggenerated Thu May 15 16:52:10 CST 2014
     */
    int deleteByPrimaryKey(SystemMailToServerKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table System_Mail_To_Server
     *
     * @mbggenerated Thu May 15 16:52:10 CST 2014
     */
    int insert(SystemMailToServerKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table System_Mail_To_Server
     *
     * @mbggenerated Thu May 15 16:52:10 CST 2014
     */
    int insertSelective(SystemMailToServerKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table System_Mail_To_Server
     *
     * @mbggenerated Thu May 15 16:52:10 CST 2014
     */
    List<SystemMailToServerKey> selectByExample(SystemMailToServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table System_Mail_To_Server
     *
     * @mbggenerated Thu May 15 16:52:10 CST 2014
     */
    int updateByExampleSelective(@Param("record") SystemMailToServerKey record, @Param("example") SystemMailToServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table System_Mail_To_Server
     *
     * @mbggenerated Thu May 15 16:52:10 CST 2014
     */
    int updateByExample(@Param("record") SystemMailToServerKey record, @Param("example") SystemMailToServerExample example);
}