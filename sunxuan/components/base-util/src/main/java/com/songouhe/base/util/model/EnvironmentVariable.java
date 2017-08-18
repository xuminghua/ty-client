package com.songouhe.base.util.model;

import com.songouhe.base.util.model.enume.EnvironmentVariableType;

import java.util.Date;

/**
 * 环境变量类
 * 对应SCP_ENVIRONMENT表
 * Created by sunxuan on 16-8-14.
 */

    public class EnvironmentVariable {
        /** 环境变量名称 */
        private String name;

        /** 使用该环境变量的应用名称 */
        private String appName;

        /** 值 */
        private String value;

        /** 环境变量类型 */
        private EnvironmentVariableType type;

        /** 取值范围 */
        private String valueRange;

        /** 描述 */
        private String description;

        /** 最后修改时间 */
        private Date lastModify;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public EnvironmentVariableType getType() {
            return type;
        }

        public void setType(EnvironmentVariableType type) {
            this.type = type;
        }

        public String getValueRange() {
            return valueRange;
        }

        public void setValueRange(String valueRange) {
            this.valueRange = valueRange;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getLastModify() {
            return lastModify;
        }

        public void setLastModify(Date lastModify) {
            this.lastModify = lastModify;
        }

    }

