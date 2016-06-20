package com.pkuvr.login_server.dao;

import com.pkuvr.login_server.domain.CityLocation;
import com.pkuvr.login_server.domain.CityLocationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityLocationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int countByExample(CityLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int deleteByExample(CityLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int deleteByPrimaryKey(Integer locid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int insert(CityLocation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int insertSelective(CityLocation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    List<CityLocation> selectByExample(CityLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    CityLocation selectByPrimaryKey(Integer locid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int updateByExampleSelective(@Param("record") CityLocation record, @Param("example") CityLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int updateByExample(@Param("record") CityLocation record, @Param("example") CityLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int updateByPrimaryKeySelective(CityLocation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table City_Location
     *
     * @mbggenerated Mon Oct 29 19:02:56 CST 2012
     */
    int updateByPrimaryKey(CityLocation record);
}