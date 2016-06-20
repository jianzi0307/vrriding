package com.pkuvr.game_server.domain;

import java.util.ArrayList;
import java.util.List;

public class DicresourceminetypeExample {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    protected String orderByClause;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    protected boolean distinct;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public DicresourceminetypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
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

        protected void addCriterion(String condition, Object value,
                                    String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property
                        + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1,
                                    Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property
                        + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andResminetypeidIsNull() {
            addCriterion("resMineTypeID is null");
            return (Criteria) this;
        }

        public Criteria andResminetypeidIsNotNull() {
            addCriterion("resMineTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andResminetypeidEqualTo(Integer value) {
            addCriterion("resMineTypeID =", value, "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidNotEqualTo(Integer value) {
            addCriterion("resMineTypeID <>", value, "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidGreaterThan(Integer value) {
            addCriterion("resMineTypeID >", value, "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("resMineTypeID >=", value, "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidLessThan(Integer value) {
            addCriterion("resMineTypeID <", value, "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidLessThanOrEqualTo(Integer value) {
            addCriterion("resMineTypeID <=", value, "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidIn(List<Integer> values) {
            addCriterion("resMineTypeID in", values, "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidNotIn(List<Integer> values) {
            addCriterion("resMineTypeID not in", values, "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidBetween(Integer value1, Integer value2) {
            addCriterion("resMineTypeID between", value1, value2,
                    "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andResminetypeidNotBetween(Integer value1,
                                                   Integer value2) {
            addCriterion("resMineTypeID not between", value1, value2,
                    "resminetypeid");
            return (Criteria) this;
        }

        public Criteria andRestypeIsNull() {
            addCriterion("resType is null");
            return (Criteria) this;
        }

        public Criteria andRestypeIsNotNull() {
            addCriterion("resType is not null");
            return (Criteria) this;
        }

        public Criteria andRestypeEqualTo(Short value) {
            addCriterion("resType =", value, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeNotEqualTo(Short value) {
            addCriterion("resType <>", value, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeGreaterThan(Short value) {
            addCriterion("resType >", value, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeGreaterThanOrEqualTo(Short value) {
            addCriterion("resType >=", value, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeLessThan(Short value) {
            addCriterion("resType <", value, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeLessThanOrEqualTo(Short value) {
            addCriterion("resType <=", value, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeIn(List<Short> values) {
            addCriterion("resType in", values, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeNotIn(List<Short> values) {
            addCriterion("resType not in", values, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeBetween(Short value1, Short value2) {
            addCriterion("resType between", value1, value2, "restype");
            return (Criteria) this;
        }

        public Criteria andRestypeNotBetween(Short value1, Short value2) {
            addCriterion("resType not between", value1, value2, "restype");
            return (Criteria) this;
        }

        public Criteria andStartaiIsNull() {
            addCriterion("startAI is null");
            return (Criteria) this;
        }

        public Criteria andStartaiIsNotNull() {
            addCriterion("startAI is not null");
            return (Criteria) this;
        }

        public Criteria andStartaiEqualTo(Integer value) {
            addCriterion("startAI =", value, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiNotEqualTo(Integer value) {
            addCriterion("startAI <>", value, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiGreaterThan(Integer value) {
            addCriterion("startAI >", value, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiGreaterThanOrEqualTo(Integer value) {
            addCriterion("startAI >=", value, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiLessThan(Integer value) {
            addCriterion("startAI <", value, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiLessThanOrEqualTo(Integer value) {
            addCriterion("startAI <=", value, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiIn(List<Integer> values) {
            addCriterion("startAI in", values, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiNotIn(List<Integer> values) {
            addCriterion("startAI not in", values, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiBetween(Integer value1, Integer value2) {
            addCriterion("startAI between", value1, value2, "startai");
            return (Criteria) this;
        }

        public Criteria andStartaiNotBetween(Integer value1, Integer value2) {
            addCriterion("startAI not between", value1, value2, "startai");
            return (Criteria) this;
        }

        public Criteria andChangeaiIsNull() {
            addCriterion("changeAI is null");
            return (Criteria) this;
        }

        public Criteria andChangeaiIsNotNull() {
            addCriterion("changeAI is not null");
            return (Criteria) this;
        }

        public Criteria andChangeaiEqualTo(Integer value) {
            addCriterion("changeAI =", value, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiNotEqualTo(Integer value) {
            addCriterion("changeAI <>", value, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiGreaterThan(Integer value) {
            addCriterion("changeAI >", value, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiGreaterThanOrEqualTo(Integer value) {
            addCriterion("changeAI >=", value, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiLessThan(Integer value) {
            addCriterion("changeAI <", value, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiLessThanOrEqualTo(Integer value) {
            addCriterion("changeAI <=", value, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiIn(List<Integer> values) {
            addCriterion("changeAI in", values, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiNotIn(List<Integer> values) {
            addCriterion("changeAI not in", values, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiBetween(Integer value1, Integer value2) {
            addCriterion("changeAI between", value1, value2, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaiNotBetween(Integer value1, Integer value2) {
            addCriterion("changeAI not between", value1, value2, "changeai");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeIsNull() {
            addCriterion("changeAITime is null");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeIsNotNull() {
            addCriterion("changeAITime is not null");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeEqualTo(Integer value) {
            addCriterion("changeAITime =", value, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeNotEqualTo(Integer value) {
            addCriterion("changeAITime <>", value, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeGreaterThan(Integer value) {
            addCriterion("changeAITime >", value, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("changeAITime >=", value, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeLessThan(Integer value) {
            addCriterion("changeAITime <", value, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeLessThanOrEqualTo(Integer value) {
            addCriterion("changeAITime <=", value, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeIn(List<Integer> values) {
            addCriterion("changeAITime in", values, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeNotIn(List<Integer> values) {
            addCriterion("changeAITime not in", values, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeBetween(Integer value1, Integer value2) {
            addCriterion("changeAITime between", value1, value2, "changeaitime");
            return (Criteria) this;
        }

        public Criteria andChangeaitimeNotBetween(Integer value1, Integer value2) {
            addCriterion("changeAITime not between", value1, value2,
                    "changeaitime");
            return (Criteria) this;
        }

        public Criteria andMineinfoIsNull() {
            addCriterion("mineInfo is null");
            return (Criteria) this;
        }

        public Criteria andMineinfoIsNotNull() {
            addCriterion("mineInfo is not null");
            return (Criteria) this;
        }

        public Criteria andMineinfoEqualTo(String value) {
            addCriterion("mineInfo =", value, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoNotEqualTo(String value) {
            addCriterion("mineInfo <>", value, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoGreaterThan(String value) {
            addCriterion("mineInfo >", value, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoGreaterThanOrEqualTo(String value) {
            addCriterion("mineInfo >=", value, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoLessThan(String value) {
            addCriterion("mineInfo <", value, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoLessThanOrEqualTo(String value) {
            addCriterion("mineInfo <=", value, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoLike(String value) {
            addCriterion("mineInfo like", value, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoNotLike(String value) {
            addCriterion("mineInfo not like", value, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoIn(List<String> values) {
            addCriterion("mineInfo in", values, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoNotIn(List<String> values) {
            addCriterion("mineInfo not in", values, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoBetween(String value1, String value2) {
            addCriterion("mineInfo between", value1, value2, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMineinfoNotBetween(String value1, String value2) {
            addCriterion("mineInfo not between", value1, value2, "mineinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoIsNull() {
            addCriterion("mineNPCInfo is null");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoIsNotNull() {
            addCriterion("mineNPCInfo is not null");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoEqualTo(String value) {
            addCriterion("mineNPCInfo =", value, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoNotEqualTo(String value) {
            addCriterion("mineNPCInfo <>", value, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoGreaterThan(String value) {
            addCriterion("mineNPCInfo >", value, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoGreaterThanOrEqualTo(String value) {
            addCriterion("mineNPCInfo >=", value, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoLessThan(String value) {
            addCriterion("mineNPCInfo <", value, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoLessThanOrEqualTo(String value) {
            addCriterion("mineNPCInfo <=", value, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoLike(String value) {
            addCriterion("mineNPCInfo like", value, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoNotLike(String value) {
            addCriterion("mineNPCInfo not like", value, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoIn(List<String> values) {
            addCriterion("mineNPCInfo in", values, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoNotIn(List<String> values) {
            addCriterion("mineNPCInfo not in", values, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoBetween(String value1, String value2) {
            addCriterion("mineNPCInfo between", value1, value2, "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andMinenpcinfoNotBetween(String value1, String value2) {
            addCriterion("mineNPCInfo not between", value1, value2,
                    "minenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoIsNull() {
            addCriterion("blockHouseNPCInfo is null");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoIsNotNull() {
            addCriterion("blockHouseNPCInfo is not null");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoEqualTo(String value) {
            addCriterion("blockHouseNPCInfo =", value, "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoNotEqualTo(String value) {
            addCriterion("blockHouseNPCInfo <>", value, "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoGreaterThan(String value) {
            addCriterion("blockHouseNPCInfo >", value, "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoGreaterThanOrEqualTo(String value) {
            addCriterion("blockHouseNPCInfo >=", value, "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoLessThan(String value) {
            addCriterion("blockHouseNPCInfo <", value, "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoLessThanOrEqualTo(String value) {
            addCriterion("blockHouseNPCInfo <=", value, "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoLike(String value) {
            addCriterion("blockHouseNPCInfo like", value, "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoNotLike(String value) {
            addCriterion("blockHouseNPCInfo not like", value,
                    "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoIn(List<String> values) {
            addCriterion("blockHouseNPCInfo in", values, "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoNotIn(List<String> values) {
            addCriterion("blockHouseNPCInfo not in", values,
                    "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoBetween(String value1, String value2) {
            addCriterion("blockHouseNPCInfo between", value1, value2,
                    "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andBlockhousenpcinfoNotBetween(String value1,
                                                       String value2) {
            addCriterion("blockHouseNPCInfo not between", value1, value2,
                    "blockhousenpcinfo");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsIsNull() {
            addCriterion("availableTactics is null");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsIsNotNull() {
            addCriterion("availableTactics is not null");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsEqualTo(String value) {
            addCriterion("availableTactics =", value, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsNotEqualTo(String value) {
            addCriterion("availableTactics <>", value, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsGreaterThan(String value) {
            addCriterion("availableTactics >", value, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsGreaterThanOrEqualTo(String value) {
            addCriterion("availableTactics >=", value, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsLessThan(String value) {
            addCriterion("availableTactics <", value, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsLessThanOrEqualTo(String value) {
            addCriterion("availableTactics <=", value, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsLike(String value) {
            addCriterion("availableTactics like", value, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsNotLike(String value) {
            addCriterion("availableTactics not like", value, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsIn(List<String> values) {
            addCriterion("availableTactics in", values, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsNotIn(List<String> values) {
            addCriterion("availableTactics not in", values, "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsBetween(String value1, String value2) {
            addCriterion("availableTactics between", value1, value2,
                    "availabletactics");
            return (Criteria) this;
        }

        public Criteria andAvailabletacticsNotBetween(String value1,
                                                      String value2) {
            addCriterion("availableTactics not between", value1, value2,
                    "availabletactics");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table DicResourceMineType
     *
     * @mbggenerated Mon Jan 05 11:16:44 CST 2015
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

        protected Criterion(String condition, Object value, Object secondValue,
                            String typeHandler) {
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
     * This class corresponds to the database table DicResourceMineType
     *
     * @mbggenerated do_not_delete_during_merge Wed Dec 31 11:41:02 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}