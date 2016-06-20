package com.pkuvr.login_server.domain;

import com.pkuvr.game_server.domain.AbstractPageExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerExample extends AbstractPageExample {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    protected String orderByClause;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    protected boolean distinct;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public PlayerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPlayerIdIsNull() {
            addCriterion("Player_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIsNotNull() {
            addCriterion("Player_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlayerIdEqualTo(Integer value) {
            addCriterion("Player_ID =", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotEqualTo(Integer value) {
            addCriterion("Player_ID <>", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThan(Integer value) {
            addCriterion("Player_ID >", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Player_ID >=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThan(Integer value) {
            addCriterion("Player_ID <", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdLessThanOrEqualTo(Integer value) {
            addCriterion("Player_ID <=", value, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdIn(List<Integer> values) {
            addCriterion("Player_ID in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotIn(List<Integer> values) {
            addCriterion("Player_ID not in", values, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdBetween(Integer value1, Integer value2) {
            addCriterion("Player_ID between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andPlayerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Player_ID not between", value1, value2, "playerId");
            return (Criteria) this;
        }

        public Criteria andServerIdIsNull() {
            addCriterion("Server_ID is null");
            return (Criteria) this;
        }

        public Criteria andServerIdIsNotNull() {
            addCriterion("Server_ID is not null");
            return (Criteria) this;
        }

        public Criteria andServerIdEqualTo(Integer value) {
            addCriterion("Server_ID =", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotEqualTo(Integer value) {
            addCriterion("Server_ID <>", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdGreaterThan(Integer value) {
            addCriterion("Server_ID >", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Server_ID >=", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdLessThan(Integer value) {
            addCriterion("Server_ID <", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdLessThanOrEqualTo(Integer value) {
            addCriterion("Server_ID <=", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdIn(List<Integer> values) {
            addCriterion("Server_ID in", values, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotIn(List<Integer> values) {
            addCriterion("Server_ID not in", values, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdBetween(Integer value1, Integer value2) {
            addCriterion("Server_ID between", value1, value2, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Server_ID not between", value1, value2, "serverId");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIsNull() {
            addCriterion("Device_Model is null");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIsNotNull() {
            addCriterion("Device_Model is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceModelEqualTo(String value) {
            addCriterion("Device_Model =", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotEqualTo(String value) {
            addCriterion("Device_Model <>", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelGreaterThan(String value) {
            addCriterion("Device_Model >", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelGreaterThanOrEqualTo(String value) {
            addCriterion("Device_Model >=", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLessThan(String value) {
            addCriterion("Device_Model <", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLessThanOrEqualTo(String value) {
            addCriterion("Device_Model <=", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLike(String value) {
            addCriterion("Device_Model like", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotLike(String value) {
            addCriterion("Device_Model not like", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIn(List<String> values) {
            addCriterion("Device_Model in", values, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotIn(List<String> values) {
            addCriterion("Device_Model not in", values, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelBetween(String value1, String value2) {
            addCriterion("Device_Model between", value1, value2, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotBetween(String value1, String value2) {
            addCriterion("Device_Model not between", value1, value2, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdIsNull() {
            addCriterion("Device_Uniquely_ID is null");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdIsNotNull() {
            addCriterion("Device_Uniquely_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdEqualTo(String value) {
            addCriterion("Device_Uniquely_ID =", value, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdNotEqualTo(String value) {
            addCriterion("Device_Uniquely_ID <>", value, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdGreaterThan(String value) {
            addCriterion("Device_Uniquely_ID >", value, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdGreaterThanOrEqualTo(String value) {
            addCriterion("Device_Uniquely_ID >=", value, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdLessThan(String value) {
            addCriterion("Device_Uniquely_ID <", value, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdLessThanOrEqualTo(String value) {
            addCriterion("Device_Uniquely_ID <=", value, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdLike(String value) {
            addCriterion("Device_Uniquely_ID like", value, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdNotLike(String value) {
            addCriterion("Device_Uniquely_ID not like", value, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdIn(List<String> values) {
            addCriterion("Device_Uniquely_ID in", values, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdNotIn(List<String> values) {
            addCriterion("Device_Uniquely_ID not in", values, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdBetween(String value1, String value2) {
            addCriterion("Device_Uniquely_ID between", value1, value2, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andDeviceUniquelyIdNotBetween(String value1, String value2) {
            addCriterion("Device_Uniquely_ID not between", value1, value2, "deviceUniquelyId");
            return (Criteria) this;
        }

        public Criteria andBindingTypeIsNull() {
            addCriterion("Binding_Type is null");
            return (Criteria) this;
        }

        public Criteria andBindingTypeIsNotNull() {
            addCriterion("Binding_Type is not null");
            return (Criteria) this;
        }

        public Criteria andBindingTypeEqualTo(String value) {
            addCriterion("Binding_Type =", value, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeNotEqualTo(String value) {
            addCriterion("Binding_Type <>", value, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeGreaterThan(String value) {
            addCriterion("Binding_Type >", value, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("Binding_Type >=", value, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeLessThan(String value) {
            addCriterion("Binding_Type <", value, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeLessThanOrEqualTo(String value) {
            addCriterion("Binding_Type <=", value, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeLike(String value) {
            addCriterion("Binding_Type like", value, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeNotLike(String value) {
            addCriterion("Binding_Type not like", value, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeIn(List<String> values) {
            addCriterion("Binding_Type in", values, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeNotIn(List<String> values) {
            addCriterion("Binding_Type not in", values, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeBetween(String value1, String value2) {
            addCriterion("Binding_Type between", value1, value2, "bindingType");
            return (Criteria) this;
        }

        public Criteria andBindingTypeNotBetween(String value1, String value2) {
            addCriterion("Binding_Type not between", value1, value2, "bindingType");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeIsNull() {
            addCriterion("First_Login_Time is null");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeIsNotNull() {
            addCriterion("First_Login_Time is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeEqualTo(Date value) {
            addCriterion("First_Login_Time =", value, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeNotEqualTo(Date value) {
            addCriterion("First_Login_Time <>", value, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeGreaterThan(Date value) {
            addCriterion("First_Login_Time >", value, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("First_Login_Time >=", value, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeLessThan(Date value) {
            addCriterion("First_Login_Time <", value, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("First_Login_Time <=", value, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeIn(List<Date> values) {
            addCriterion("First_Login_Time in", values, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeNotIn(List<Date> values) {
            addCriterion("First_Login_Time not in", values, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeBetween(Date value1, Date value2) {
            addCriterion("First_Login_Time between", value1, value2, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("First_Login_Time not between", value1, value2, "firstLoginTime");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpIsNull() {
            addCriterion("First_Login_Ip is null");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpIsNotNull() {
            addCriterion("First_Login_Ip is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpEqualTo(String value) {
            addCriterion("First_Login_Ip =", value, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpNotEqualTo(String value) {
            addCriterion("First_Login_Ip <>", value, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpGreaterThan(String value) {
            addCriterion("First_Login_Ip >", value, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpGreaterThanOrEqualTo(String value) {
            addCriterion("First_Login_Ip >=", value, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpLessThan(String value) {
            addCriterion("First_Login_Ip <", value, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpLessThanOrEqualTo(String value) {
            addCriterion("First_Login_Ip <=", value, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpLike(String value) {
            addCriterion("First_Login_Ip like", value, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpNotLike(String value) {
            addCriterion("First_Login_Ip not like", value, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpIn(List<String> values) {
            addCriterion("First_Login_Ip in", values, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpNotIn(List<String> values) {
            addCriterion("First_Login_Ip not in", values, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpBetween(String value1, String value2) {
            addCriterion("First_Login_Ip between", value1, value2, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andFirstLoginIpNotBetween(String value1, String value2) {
            addCriterion("First_Login_Ip not between", value1, value2, "firstLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNull() {
            addCriterion("Last_Login_Time is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNotNull() {
            addCriterion("Last_Login_Time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeEqualTo(Date value) {
            addCriterion("Last_Login_Time =", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotEqualTo(Date value) {
            addCriterion("Last_Login_Time <>", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThan(Date value) {
            addCriterion("Last_Login_Time >", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Last_Login_Time >=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThan(Date value) {
            addCriterion("Last_Login_Time <", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("Last_Login_Time <=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIn(List<Date> values) {
            addCriterion("Last_Login_Time in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotIn(List<Date> values) {
            addCriterion("Last_Login_Time not in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeBetween(Date value1, Date value2) {
            addCriterion("Last_Login_Time between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("Last_Login_Time not between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIsNull() {
            addCriterion("Last_Login_Ip is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIsNotNull() {
            addCriterion("Last_Login_Ip is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpEqualTo(String value) {
            addCriterion("Last_Login_Ip =", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotEqualTo(String value) {
            addCriterion("Last_Login_Ip <>", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpGreaterThan(String value) {
            addCriterion("Last_Login_Ip >", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpGreaterThanOrEqualTo(String value) {
            addCriterion("Last_Login_Ip >=", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLessThan(String value) {
            addCriterion("Last_Login_Ip <", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLessThanOrEqualTo(String value) {
            addCriterion("Last_Login_Ip <=", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLike(String value) {
            addCriterion("Last_Login_Ip like", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotLike(String value) {
            addCriterion("Last_Login_Ip not like", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIn(List<String> values) {
            addCriterion("Last_Login_Ip in", values, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotIn(List<String> values) {
            addCriterion("Last_Login_Ip not in", values, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpBetween(String value1, String value2) {
            addCriterion("Last_Login_Ip between", value1, value2, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotBetween(String value1, String value2) {
            addCriterion("Last_Login_Ip not between", value1, value2, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionIsNull() {
            addCriterion("Last_Login_Region is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionIsNotNull() {
            addCriterion("Last_Login_Region is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionEqualTo(Integer value) {
            addCriterion("Last_Login_Region =", value, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionNotEqualTo(Integer value) {
            addCriterion("Last_Login_Region <>", value, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionGreaterThan(Integer value) {
            addCriterion("Last_Login_Region >", value, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionGreaterThanOrEqualTo(Integer value) {
            addCriterion("Last_Login_Region >=", value, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionLessThan(Integer value) {
            addCriterion("Last_Login_Region <", value, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionLessThanOrEqualTo(Integer value) {
            addCriterion("Last_Login_Region <=", value, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionIn(List<Integer> values) {
            addCriterion("Last_Login_Region in", values, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionNotIn(List<Integer> values) {
            addCriterion("Last_Login_Region not in", values, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionBetween(Integer value1, Integer value2) {
            addCriterion("Last_Login_Region between", value1, value2, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andLastLoginRegionNotBetween(Integer value1, Integer value2) {
            addCriterion("Last_Login_Region not between", value1, value2, "lastLoginRegion");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistIsNull() {
            addCriterion("Is_Blacklist is null");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistIsNotNull() {
            addCriterion("Is_Blacklist is not null");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistEqualTo(Boolean value) {
            addCriterion("Is_Blacklist =", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistNotEqualTo(Boolean value) {
            addCriterion("Is_Blacklist <>", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistGreaterThan(Boolean value) {
            addCriterion("Is_Blacklist >", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistGreaterThanOrEqualTo(Boolean value) {
            addCriterion("Is_Blacklist >=", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistLessThan(Boolean value) {
            addCriterion("Is_Blacklist <", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistLessThanOrEqualTo(Boolean value) {
            addCriterion("Is_Blacklist <=", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistIn(List<Boolean> values) {
            addCriterion("Is_Blacklist in", values, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistNotIn(List<Boolean> values) {
            addCriterion("Is_Blacklist not in", values, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistBetween(Boolean value1, Boolean value2) {
            addCriterion("Is_Blacklist between", value1, value2, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistNotBetween(Boolean value1, Boolean value2) {
            addCriterion("Is_Blacklist not between", value1, value2, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeIsNull() {
            addCriterion("Add_Blacklist_Time is null");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeIsNotNull() {
            addCriterion("Add_Blacklist_Time is not null");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeEqualTo(Date value) {
            addCriterion("Add_Blacklist_Time =", value, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeNotEqualTo(Date value) {
            addCriterion("Add_Blacklist_Time <>", value, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeGreaterThan(Date value) {
            addCriterion("Add_Blacklist_Time >", value, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Add_Blacklist_Time >=", value, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeLessThan(Date value) {
            addCriterion("Add_Blacklist_Time <", value, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeLessThanOrEqualTo(Date value) {
            addCriterion("Add_Blacklist_Time <=", value, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeIn(List<Date> values) {
            addCriterion("Add_Blacklist_Time in", values, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeNotIn(List<Date> values) {
            addCriterion("Add_Blacklist_Time not in", values, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeBetween(Date value1, Date value2) {
            addCriterion("Add_Blacklist_Time between", value1, value2, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andAddBlacklistTimeNotBetween(Date value1, Date value2) {
            addCriterion("Add_Blacklist_Time not between", value1, value2, "addBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeIsNull() {
            addCriterion("End_Blacklist_Time is null");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeIsNotNull() {
            addCriterion("End_Blacklist_Time is not null");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeEqualTo(Date value) {
            addCriterion("End_Blacklist_Time =", value, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeNotEqualTo(Date value) {
            addCriterion("End_Blacklist_Time <>", value, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeGreaterThan(Date value) {
            addCriterion("End_Blacklist_Time >", value, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("End_Blacklist_Time >=", value, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeLessThan(Date value) {
            addCriterion("End_Blacklist_Time <", value, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeLessThanOrEqualTo(Date value) {
            addCriterion("End_Blacklist_Time <=", value, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeIn(List<Date> values) {
            addCriterion("End_Blacklist_Time in", values, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeNotIn(List<Date> values) {
            addCriterion("End_Blacklist_Time not in", values, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeBetween(Date value1, Date value2) {
            addCriterion("End_Blacklist_Time between", value1, value2, "endBlacklistTime");
            return (Criteria) this;
        }

        public Criteria andEndBlacklistTimeNotBetween(Date value1, Date value2) {
            addCriterion("End_Blacklist_Time not between", value1, value2, "endBlacklistTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table Player
     *
     * @mbggenerated Fri Dec 14 17:02:49 CST 2012
     */
    public static class Criterion {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private String typeHandler;

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table Player
     *
     * @mbggenerated do_not_delete_during_merge Mon Oct 29 14:11:58 CST 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}