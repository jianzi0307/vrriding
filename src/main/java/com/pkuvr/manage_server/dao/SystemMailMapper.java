package com.pkuvr.manage_server.dao;

import com.pkuvr.manage_server.domain.SystemMail;
import com.pkuvr.manage_server.domain.SystemMailExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemMailMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int countByExample(SystemMailExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int deleteByExample(SystemMailExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int deleteByPrimaryKey(Integer mailId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int insert(SystemMail record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int insertSelective(SystemMail record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    List<SystemMail> selectByExample(SystemMailExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    SystemMail selectByPrimaryKey(Integer mailId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int updateByExampleSelective(@Param("record") SystemMail record,
                                 @Param("example") SystemMailExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int updateByExample(@Param("record") SystemMail record,
                        @Param("example") SystemMailExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int updateByPrimaryKeySelective(SystemMail record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table System_Mail
     *
     * @mbggenerated Tue Jul 07 10:09:57 CST 2015
     */
    int updateByPrimaryKey(SystemMail record);

    List<SystemMail> selectByGameIDAndServerID(@Param("gameId") int gameId, @Param("serverId") String serverId);
}