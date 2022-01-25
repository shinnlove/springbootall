package com.shinnlove.springbootall.dao.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProcessBlockingPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ProcessBlockingPoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoIsNull() {
            addCriterion("main_process_no is null");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoIsNotNull() {
            addCriterion("main_process_no is not null");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoEqualTo(Long value) {
            addCriterion("main_process_no =", value, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoNotEqualTo(Long value) {
            addCriterion("main_process_no <>", value, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoGreaterThan(Long value) {
            addCriterion("main_process_no >", value, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoGreaterThanOrEqualTo(Long value) {
            addCriterion("main_process_no >=", value, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoLessThan(Long value) {
            addCriterion("main_process_no <", value, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoLessThanOrEqualTo(Long value) {
            addCriterion("main_process_no <=", value, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoIn(List<Long> values) {
            addCriterion("main_process_no in", values, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoNotIn(List<Long> values) {
            addCriterion("main_process_no not in", values, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoBetween(Long value1, Long value2) {
            addCriterion("main_process_no between", value1, value2, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andMainProcessNoNotBetween(Long value1, Long value2) {
            addCriterion("main_process_no not between", value1, value2, "mainProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoIsNull() {
            addCriterion("obstacle_by_process_no is null");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoIsNotNull() {
            addCriterion("obstacle_by_process_no is not null");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoEqualTo(Long value) {
            addCriterion("obstacle_by_process_no =", value, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoNotEqualTo(Long value) {
            addCriterion("obstacle_by_process_no <>", value, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoGreaterThan(Long value) {
            addCriterion("obstacle_by_process_no >", value, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoGreaterThanOrEqualTo(Long value) {
            addCriterion("obstacle_by_process_no >=", value, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoLessThan(Long value) {
            addCriterion("obstacle_by_process_no <", value, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoLessThanOrEqualTo(Long value) {
            addCriterion("obstacle_by_process_no <=", value, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoIn(List<Long> values) {
            addCriterion("obstacle_by_process_no in", values, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoNotIn(List<Long> values) {
            addCriterion("obstacle_by_process_no not in", values, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoBetween(Long value1, Long value2) {
            addCriterion("obstacle_by_process_no between", value1, value2, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andObstacleByProcessNoNotBetween(Long value1, Long value2) {
            addCriterion("obstacle_by_process_no not between", value1, value2, "obstacleByProcessNo");
            return (Criteria) this;
        }

        public Criteria andRefStatusIsNull() {
            addCriterion("ref_status is null");
            return (Criteria) this;
        }

        public Criteria andRefStatusIsNotNull() {
            addCriterion("ref_status is not null");
            return (Criteria) this;
        }

        public Criteria andRefStatusEqualTo(Integer value) {
            addCriterion("ref_status =", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusNotEqualTo(Integer value) {
            addCriterion("ref_status <>", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusGreaterThan(Integer value) {
            addCriterion("ref_status >", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ref_status >=", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusLessThan(Integer value) {
            addCriterion("ref_status <", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusLessThanOrEqualTo(Integer value) {
            addCriterion("ref_status <=", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusIn(List<Integer> values) {
            addCriterion("ref_status in", values, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusNotIn(List<Integer> values) {
            addCriterion("ref_status not in", values, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusBetween(Integer value1, Integer value2) {
            addCriterion("ref_status between", value1, value2, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("ref_status not between", value1, value2, "refStatus");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageIsNull() {
            addCriterion("blocking_message is null");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageIsNotNull() {
            addCriterion("blocking_message is not null");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageEqualTo(String value) {
            addCriterion("blocking_message =", value, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageNotEqualTo(String value) {
            addCriterion("blocking_message <>", value, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageGreaterThan(String value) {
            addCriterion("blocking_message >", value, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageGreaterThanOrEqualTo(String value) {
            addCriterion("blocking_message >=", value, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageLessThan(String value) {
            addCriterion("blocking_message <", value, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageLessThanOrEqualTo(String value) {
            addCriterion("blocking_message <=", value, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageLike(String value) {
            addCriterion("blocking_message like", value, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageNotLike(String value) {
            addCriterion("blocking_message not like", value, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageIn(List<String> values) {
            addCriterion("blocking_message in", values, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageNotIn(List<String> values) {
            addCriterion("blocking_message not in", values, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageBetween(String value1, String value2) {
            addCriterion("blocking_message between", value1, value2, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andBlockingMessageNotBetween(String value1, String value2) {
            addCriterion("blocking_message not between", value1, value2, "blockingMessage");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(Timestamp value) {
            addCriterion("ctime =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(Timestamp value) {
            addCriterion("ctime <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(Timestamp value) {
            addCriterion("ctime >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ctime >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(Timestamp value) {
            addCriterion("ctime <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ctime <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<Timestamp> values) {
            addCriterion("ctime in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<Timestamp> values) {
            addCriterion("ctime not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ctime between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ctime not between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andMtimeIsNull() {
            addCriterion("mtime is null");
            return (Criteria) this;
        }

        public Criteria andMtimeIsNotNull() {
            addCriterion("mtime is not null");
            return (Criteria) this;
        }

        public Criteria andMtimeEqualTo(Timestamp value) {
            addCriterion("mtime =", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeNotEqualTo(Timestamp value) {
            addCriterion("mtime <>", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeGreaterThan(Timestamp value) {
            addCriterion("mtime >", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("mtime >=", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeLessThan(Timestamp value) {
            addCriterion("mtime <", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("mtime <=", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeIn(List<Timestamp> values) {
            addCriterion("mtime in", values, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeNotIn(List<Timestamp> values) {
            addCriterion("mtime not in", values, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("mtime between", value1, value2, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("mtime not between", value1, value2, "mtime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

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
    }
}