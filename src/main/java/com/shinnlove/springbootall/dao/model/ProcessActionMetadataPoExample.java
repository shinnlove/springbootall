package com.shinnlove.springbootall.dao.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProcessActionMetadataPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ProcessActionMetadataPoExample() {
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Integer value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Integer value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Integer value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Integer value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Integer> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Integer> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andActionIdIsNull() {
            addCriterion("action_id is null");
            return (Criteria) this;
        }

        public Criteria andActionIdIsNotNull() {
            addCriterion("action_id is not null");
            return (Criteria) this;
        }

        public Criteria andActionIdEqualTo(Integer value) {
            addCriterion("action_id =", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotEqualTo(Integer value) {
            addCriterion("action_id <>", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdGreaterThan(Integer value) {
            addCriterion("action_id >", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("action_id >=", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdLessThan(Integer value) {
            addCriterion("action_id <", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdLessThanOrEqualTo(Integer value) {
            addCriterion("action_id <=", value, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdIn(List<Integer> values) {
            addCriterion("action_id in", values, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotIn(List<Integer> values) {
            addCriterion("action_id not in", values, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdBetween(Integer value1, Integer value2) {
            addCriterion("action_id between", value1, value2, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("action_id not between", value1, value2, "actionId");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionIsNull() {
            addCriterion("action_description is null");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionIsNotNull() {
            addCriterion("action_description is not null");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionEqualTo(String value) {
            addCriterion("action_description =", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionNotEqualTo(String value) {
            addCriterion("action_description <>", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionGreaterThan(String value) {
            addCriterion("action_description >", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("action_description >=", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionLessThan(String value) {
            addCriterion("action_description <", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionLessThanOrEqualTo(String value) {
            addCriterion("action_description <=", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionLike(String value) {
            addCriterion("action_description like", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionNotLike(String value) {
            addCriterion("action_description not like", value, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionIn(List<String> values) {
            addCriterion("action_description in", values, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionNotIn(List<String> values) {
            addCriterion("action_description not in", values, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionBetween(String value1, String value2) {
            addCriterion("action_description between", value1, value2, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andActionDescriptionNotBetween(String value1, String value2) {
            addCriterion("action_description not between", value1, value2, "actionDescription");
            return (Criteria) this;
        }

        public Criteria andSourceStatusIsNull() {
            addCriterion("source_status is null");
            return (Criteria) this;
        }

        public Criteria andSourceStatusIsNotNull() {
            addCriterion("source_status is not null");
            return (Criteria) this;
        }

        public Criteria andSourceStatusEqualTo(Integer value) {
            addCriterion("source_status =", value, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusNotEqualTo(Integer value) {
            addCriterion("source_status <>", value, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusGreaterThan(Integer value) {
            addCriterion("source_status >", value, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_status >=", value, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusLessThan(Integer value) {
            addCriterion("source_status <", value, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("source_status <=", value, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusIn(List<Integer> values) {
            addCriterion("source_status in", values, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusNotIn(List<Integer> values) {
            addCriterion("source_status not in", values, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusBetween(Integer value1, Integer value2) {
            addCriterion("source_status between", value1, value2, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andSourceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("source_status not between", value1, value2, "sourceStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusIsNull() {
            addCriterion("destination_status is null");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusIsNotNull() {
            addCriterion("destination_status is not null");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusEqualTo(Integer value) {
            addCriterion("destination_status =", value, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusNotEqualTo(Integer value) {
            addCriterion("destination_status <>", value, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusGreaterThan(Integer value) {
            addCriterion("destination_status >", value, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("destination_status >=", value, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusLessThan(Integer value) {
            addCriterion("destination_status <", value, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusLessThanOrEqualTo(Integer value) {
            addCriterion("destination_status <=", value, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusIn(List<Integer> values) {
            addCriterion("destination_status in", values, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusNotIn(List<Integer> values) {
            addCriterion("destination_status not in", values, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusBetween(Integer value1, Integer value2) {
            addCriterion("destination_status between", value1, value2, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andDestinationStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("destination_status not between", value1, value2, "destinationStatus");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceIsNull() {
            addCriterion("process_entrance is null");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceIsNotNull() {
            addCriterion("process_entrance is not null");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceEqualTo(Integer value) {
            addCriterion("process_entrance =", value, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceNotEqualTo(Integer value) {
            addCriterion("process_entrance <>", value, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceGreaterThan(Integer value) {
            addCriterion("process_entrance >", value, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_entrance >=", value, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceLessThan(Integer value) {
            addCriterion("process_entrance <", value, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceLessThanOrEqualTo(Integer value) {
            addCriterion("process_entrance <=", value, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceIn(List<Integer> values) {
            addCriterion("process_entrance in", values, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceNotIn(List<Integer> values) {
            addCriterion("process_entrance not in", values, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceBetween(Integer value1, Integer value2) {
            addCriterion("process_entrance between", value1, value2, "processEntrance");
            return (Criteria) this;
        }

        public Criteria andProcessEntranceNotBetween(Integer value1, Integer value2) {
            addCriterion("process_entrance not between", value1, value2, "processEntrance");
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