package com.songouhe.internal.uwt.model.viewconfig.action;

/**
 * @author sunxuan
 * @version 1.0 17-8-11
 */
public class UniqueValidationModel {
    private String dbField;
    private String valueField;

    public String getDbField() {
        return dbField;
    }

    public void setDbField(String dbField) {
        this.dbField = dbField;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }
    public UniqueValidationModel(){}
}
