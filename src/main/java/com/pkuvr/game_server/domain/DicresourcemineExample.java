package com.pkuvr.game_server.domain;

import java.util.ArrayList;
import java.util.List;

public class DicresourcemineExample {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    protected String orderByClause;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    protected boolean distinct;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public DicresourcemineExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
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

        public Criteria andResmineidIsNull() {
            addCriterion("resMineID is null");
            return (Criteria) this;
        }

        public Criteria andResmineidIsNotNull() {
            addCriterion("resMineID is not null");
            return (Criteria) this;
        }

        public Criteria andResmineidEqualTo(Integer value) {
            addCriterion("resMineID =", value, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidNotEqualTo(Integer value) {
            addCriterion("resMineID <>", value, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidGreaterThan(Integer value) {
            addCriterion("resMineID >", value, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidGreaterThanOrEqualTo(Integer value) {
            addCriterion("resMineID >=", value, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidLessThan(Integer value) {
            addCriterion("resMineID <", value, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidLessThanOrEqualTo(Integer value) {
            addCriterion("resMineID <=", value, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidIn(List<Integer> values) {
            addCriterion("resMineID in", values, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidNotIn(List<Integer> values) {
            addCriterion("resMineID not in", values, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidBetween(Integer value1, Integer value2) {
            addCriterion("resMineID between", value1, value2, "resmineid");
            return (Criteria) this;
        }

        public Criteria andResmineidNotBetween(Integer value1, Integer value2) {
            addCriterion("resMineID not between", value1, value2, "resmineid");
            return (Criteria) this;
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

        public Criteria andMinebuildingidIsNull() {
            addCriterion("mineBuildingID is null");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidIsNotNull() {
            addCriterion("mineBuildingID is not null");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidEqualTo(Integer value) {
            addCriterion("mineBuildingID =", value, "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidNotEqualTo(Integer value) {
            addCriterion("mineBuildingID <>", value, "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidGreaterThan(Integer value) {
            addCriterion("mineBuildingID >", value, "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mineBuildingID >=", value, "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidLessThan(Integer value) {
            addCriterion("mineBuildingID <", value, "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidLessThanOrEqualTo(Integer value) {
            addCriterion("mineBuildingID <=", value, "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidIn(List<Integer> values) {
            addCriterion("mineBuildingID in", values, "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidNotIn(List<Integer> values) {
            addCriterion("mineBuildingID not in", values, "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidBetween(Integer value1, Integer value2) {
            addCriterion("mineBuildingID between", value1, value2,
                    "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildingidNotBetween(Integer value1,
                                                    Integer value2) {
            addCriterion("mineBuildingID not between", value1, value2,
                    "minebuildingid");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelIsNull() {
            addCriterion("mineBuildingLevel is null");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelIsNotNull() {
            addCriterion("mineBuildingLevel is not null");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelEqualTo(Short value) {
            addCriterion("mineBuildingLevel =", value, "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelNotEqualTo(Short value) {
            addCriterion("mineBuildingLevel <>", value, "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelGreaterThan(Short value) {
            addCriterion("mineBuildingLevel >", value, "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelGreaterThanOrEqualTo(Short value) {
            addCriterion("mineBuildingLevel >=", value, "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelLessThan(Short value) {
            addCriterion("mineBuildingLevel <", value, "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelLessThanOrEqualTo(Short value) {
            addCriterion("mineBuildingLevel <=", value, "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelIn(List<Short> values) {
            addCriterion("mineBuildingLevel in", values, "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelNotIn(List<Short> values) {
            addCriterion("mineBuildingLevel not in", values,
                    "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelBetween(Short value1, Short value2) {
            addCriterion("mineBuildingLevel between", value1, value2,
                    "minebuildinglevel");
            return (Criteria) this;
        }

        public Criteria andMinebuildinglevelNotBetween(Short value1,
                                                       Short value2) {
            addCriterion("mineBuildingLevel not between", value1, value2,
                    "minebuildinglevel");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table DicResourceMine
     *
     * @mbggenerated Wed Dec 31 15:53:56 CST 2014
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
     * This class corresponds to the database table DicResourceMine
     *
     * @mbggenerated do_not_delete_during_merge Tue Dec 30 20:14:27 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}