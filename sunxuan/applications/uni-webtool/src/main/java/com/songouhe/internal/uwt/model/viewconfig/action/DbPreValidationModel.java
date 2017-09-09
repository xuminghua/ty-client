package com.songouhe.internal.uwt.model.viewconfig.action;

import com.songouhe.internal.uwt.model.enums.DbPreValidationEnum;

/**
 * @author sunxuan
 * @version 1.0 17-8-11
 */
public class DbPreValidationModel {
    private String sql;
    private String[] values;
    private DbPreValidationEnum valid;
    private String error_msg;
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public DbPreValidationEnum getValid() {
        return valid;
    }

    public void setValid(DbPreValidationEnum valid) {
        this.valid = valid;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public DbPreValidationModel(){}
}
