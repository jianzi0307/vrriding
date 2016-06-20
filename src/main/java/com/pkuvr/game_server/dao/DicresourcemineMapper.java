package com.pkuvr.game_server.dao;

import com.pkuvr.game_server.domain.Dicresourcemine;
import com.pkuvr.game_server.domain.DicresourcemineExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicresourcemineMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int countByExample(DicresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int deleteByExample(DicresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int deleteByPrimaryKey(Integer resmineid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int insert(Dicresourcemine record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int insertSelective(Dicresourcemine record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    List<Dicresourcemine> selectByExample(DicresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    Dicresourcemine selectByPrimaryKey(Integer resmineid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int updateByExampleSelective(@Param("record") Dicresourcemine record,
                                 @Param("example") DicresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int updateByExample(@Param("record") Dicresourcemine record,
                        @Param("example") DicresourcemineExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int updateByPrimaryKeySelective(Dicresourcemine record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    int updateByPrimaryKey(Dicresourcemine record);
}