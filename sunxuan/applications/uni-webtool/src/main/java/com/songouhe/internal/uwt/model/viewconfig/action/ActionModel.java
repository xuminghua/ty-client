package com.songouhe.internal.uwt.model.viewconfig.action;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author sunxuan
 * @version 1.0 17-2-12
 */
public class ActionModel implements Serializable {
    private String sql;
    private String beanStr;
    private UniqueValidationModel[] uniqueValidation;
    /*
    conditionFields
    create action:格式是Map(field,value);
    update action:格式是Map(field,mainTable_field) ,mainTable_field是主表中对应字段,
    即令field=主表字段的value.只用于subAction.
     */
    private HashMap conditionFields;
    private String sortCondition;
    private ActionModel[] subAction;

    public ActionModel() {
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public HashMap getConditionFields() {
        return conditionFields;
    }

    public void setConditionFields(HashMap conditionFields) {
        this.conditionFields = conditionFields;
    }

    public String getSortCondition() {
        return sortCondition;
    }

    public void setSortCondition(String sortCondition) {
        this.sortCondition = sortCondition;
    }

    public ActionModel[] getSubAction() {
        return subAction;
    }

    public void setSubAction(ActionModel[] subAction) {
        this.subAction = subAction;
    }

    public String getBeanStr() {
        return beanStr;
    }

    public void setBeanStr(String beanStr) {
        this.beanStr = beanStr;
    }

    public UniqueValidationModel[] getUniqueValidation() {
        return uniqueValidation;
    }

    public void setUniqueValidation(UniqueValidationModel[] uniqueValidation) {
        this.uniqueValidation = uniqueValidation;
    }
}
