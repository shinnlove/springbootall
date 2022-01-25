package com.shinnlove.springbootall.dao.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UniversalProcessPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UniversalProcessPoExample() {
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

        public Criteria andProcessNoIsNull() {
            addCriterion("process_no is null");
            return (Criteria) this;
        }

        public Criteria andProcessNoIsNotNull() {
            addCriterion("process_no is not null");
            return (Criteria) this;
        }

        public Criteria andProcessNoEqualTo(Long value) {
            addCriterion("process_no =", value, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoNotEqualTo(Long value) {
            addCriterion("process_no <>", value, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoGreaterThan(Long value) {
            addCriterion("process_no >", value, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoGreaterThanOrEqualTo(Long value) {
            addCriterion("process_no >=", value, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoLessThan(Long value) {
            addCriterion("process_no <", value, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoLessThanOrEqualTo(Long value) {
            addCriterion("process_no <=", value, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoIn(List<Long> values) {
            addCriterion("process_no in", values, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoNotIn(List<Long> values) {
            addCriterion("process_no not in", values, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoBetween(Long value1, Long value2) {
            addCriterion("process_no between", value1, value2, "processNo");
            return (Criteria) this;
        }

        public Criteria andProcessNoNotBetween(Long value1, Long value2) {
            addCriterion("process_no not between", value1, value2, "processNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoIsNull() {
            addCriterion("parent_process_no is null");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoIsNotNull() {
            addCriterion("parent_process_no is not null");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoEqualTo(Long value) {
            addCriterion("parent_process_no =", value, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoNotEqualTo(Long value) {
            addCriterion("parent_process_no <>", value, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoGreaterThan(Long value) {
            addCriterion("parent_process_no >", value, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_process_no >=", value, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoLessThan(Long value) {
            addCriterion("parent_process_no <", value, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoLessThanOrEqualTo(Long value) {
            addCriterion("parent_process_no <=", value, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoIn(List<Long> values) {
            addCriterion("parent_process_no in", values, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoNotIn(List<Long> values) {
            addCriterion("parent_process_no not in", values, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoBetween(Long value1, Long value2) {
            addCriterion("parent_process_no between", value1, value2, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andParentProcessNoNotBetween(Long value1, Long value2) {
            addCriterion("parent_process_no not between", value1, value2, "parentProcessNo");
            return (Criteria) this;
        }

        public Criteria andProcessTypeIsNull() {
            addCriterion("process_type is null");
            return (Criteria) this;
        }

        public Criteria andProcessTypeIsNotNull() {
            addCriterion("process_type is not null");
            return (Criteria) this;
        }

        public Criteria andProcessTypeEqualTo(Integer value) {
            addCriterion("process_type =", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeNotEqualTo(Integer value) {
            addCriterion("process_type <>", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeGreaterThan(Integer value) {
            addCriterion("process_type >", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_type >=", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeLessThan(Integer value) {
            addCriterion("process_type <", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeLessThanOrEqualTo(Integer value) {
            addCriterion("process_type <=", value, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeIn(List<Integer> values) {
            addCriterion("process_type in", values, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeNotIn(List<Integer> values) {
            addCriterion("process_type not in", values, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeBetween(Integer value1, Integer value2) {
            addCriterion("process_type between", value1, value2, "processType");
            return (Criteria) this;
        }

        public Criteria andProcessTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("process_type not between", value1, value2, "processType");
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

        public Criteria andRefUniqueNoIsNull() {
            addCriterion("ref_unique_no is null");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoIsNotNull() {
            addCriterion("ref_unique_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoEqualTo(Long value) {
            addCriterion("ref_unique_no =", value, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoNotEqualTo(Long value) {
            addCriterion("ref_unique_no <>", value, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoGreaterThan(Long value) {
            addCriterion("ref_unique_no >", value, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoGreaterThanOrEqualTo(Long value) {
            addCriterion("ref_unique_no >=", value, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoLessThan(Long value) {
            addCriterion("ref_unique_no <", value, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoLessThanOrEqualTo(Long value) {
            addCriterion("ref_unique_no <=", value, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoIn(List<Long> values) {
            addCriterion("ref_unique_no in", values, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoNotIn(List<Long> values) {
            addCriterion("ref_unique_no not in", values, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoBetween(Long value1, Long value2) {
            addCriterion("ref_unique_no between", value1, value2, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andRefUniqueNoNotBetween(Long value1, Long value2) {
            addCriterion("ref_unique_no not between", value1, value2, "refUniqueNo");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusIsNull() {
            addCriterion("current_status is null");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusIsNotNull() {
            addCriterion("current_status is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusEqualTo(Integer value) {
            addCriterion("current_status =", value, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusNotEqualTo(Integer value) {
            addCriterion("current_status <>", value, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusGreaterThan(Integer value) {
            addCriterion("current_status >", value, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_status >=", value, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusLessThan(Integer value) {
            addCriterion("current_status <", value, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusLessThanOrEqualTo(Integer value) {
            addCriterion("current_status <=", value, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusIn(List<Integer> values) {
            addCriterion("current_status in", values, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusNotIn(List<Integer> values) {
            addCriterion("current_status not in", values, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusBetween(Integer value1, Integer value2) {
            addCriterion("current_status between", value1, value2, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andCurrentStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("current_status not between", value1, value2, "currentStatus");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdIsNull() {
            addCriterion("latest_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdIsNotNull() {
            addCriterion("latest_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdEqualTo(Long value) {
            addCriterion("latest_operator_id =", value, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdNotEqualTo(Long value) {
            addCriterion("latest_operator_id <>", value, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdGreaterThan(Long value) {
            addCriterion("latest_operator_id >", value, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("latest_operator_id >=", value, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdLessThan(Long value) {
            addCriterion("latest_operator_id <", value, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdLessThanOrEqualTo(Long value) {
            addCriterion("latest_operator_id <=", value, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdIn(List<Long> values) {
            addCriterion("latest_operator_id in", values, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdNotIn(List<Long> values) {
            addCriterion("latest_operator_id not in", values, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdBetween(Long value1, Long value2) {
            addCriterion("latest_operator_id between", value1, value2, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIdNotBetween(Long value1, Long value2) {
            addCriterion("latest_operator_id not between", value1, value2, "latestOperatorId");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIsNull() {
            addCriterion("latest_operator is null");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIsNotNull() {
            addCriterion("latest_operator is not null");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorEqualTo(String value) {
            addCriterion("latest_operator =", value, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorNotEqualTo(String value) {
            addCriterion("latest_operator <>", value, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorGreaterThan(String value) {
            addCriterion("latest_operator >", value, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("latest_operator >=", value, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorLessThan(String value) {
            addCriterion("latest_operator <", value, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorLessThanOrEqualTo(String value) {
            addCriterion("latest_operator <=", value, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorLike(String value) {
            addCriterion("latest_operator like", value, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorNotLike(String value) {
            addCriterion("latest_operator not like", value, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorIn(List<String> values) {
            addCriterion("latest_operator in", values, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorNotIn(List<String> values) {
            addCriterion("latest_operator not in", values, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorBetween(String value1, String value2) {
            addCriterion("latest_operator between", value1, value2, "latestOperator");
            return (Criteria) this;
        }

        public Criteria andLatestOperatorNotBetween(String value1, String value2) {
            addCriterion("latest_operator not between", value1, value2, "latestOperator");
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