/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.enums;

/**
 * @author Tony Zhao
 * @version $Id: TemplateType.java, v 0.1 2022-02-10 11:30 AM Tony Zhao Exp $$
 */
public enum TemplateType {

                          UNKNOWN(-1, "未知模板流程", -1),

                          REVISE_PARENT(30000, "改价父流程", 1),

                          ORDER_PRICE(30001, "单个改价项订单金额子流程", 1),

                          SERVICE_FEE(30002, "单个改价项信息技术费子流程", 1),

                          ORDER_EXPENSE(30003, "单个改价项订单支出子流程", 1),

                          UPPER_PROFIT(30004, "单个改价项UP主收益子流程", 1),

                          VENDOR_PROFIT(30005, "单个改价项服务商收益子流程", 1),

    ;

    private int    templateId;
    private String desc;
    private int    defaultInitDst;

    TemplateType(int templateId, String desc, int defaultInitDst) {
        this.templateId = templateId;
        this.desc = desc;
        this.defaultInitDst = defaultInitDst;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDefaultInitDst() {
        return defaultInitDst;
    }

    public void setDefaultInitDst(int defaultInitDst) {
        this.defaultInitDst = defaultInitDst;
    }

    public static TemplateType getByTemplateId(int templateId) {
        for (TemplateType type : values()) {
            if (templateId == type.getTemplateId()) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static int getDstByTemplateId(int templateId) {
        TemplateType type = getByTemplateId(templateId);
        return type.getDefaultInitDst();
    }

}