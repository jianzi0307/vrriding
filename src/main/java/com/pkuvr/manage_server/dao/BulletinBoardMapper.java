package com.pkuvr.manage_server.dao;

import com.pkuvr.manage_server.domain.BulletinBoard;
import com.pkuvr.manage_server.domain.BulletinBoardExample;
import com.pkuvr.manage_server.domain.custom.BulletinInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BulletinBoardMapper")
public interface BulletinBoardMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int countByExample(BulletinBoardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int deleteByExample(BulletinBoardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int insert(BulletinBoard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int insertSelective(BulletinBoard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    List<BulletinBoard> selectByExample(BulletinBoardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    BulletinBoard selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int updateByExampleSelective(@Param("record") BulletinBoard record,
                                 @Param("example") BulletinBoardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int updateByExample(@Param("record") BulletinBoard record,
                        @Param("example") BulletinBoardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int updateByPrimaryKeySelective(BulletinBoard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Bulletin_Board
     *
     * @mbggenerated Mon Mar 11 14:05:27 CST 2013
     */
    int updateByPrimaryKey(BulletinBoard record);

    List<BulletinInfo> selectByTimeAndServerID(@Param("serverId") String serverId);
}