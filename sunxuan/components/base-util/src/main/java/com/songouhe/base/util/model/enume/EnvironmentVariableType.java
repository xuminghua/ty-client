package com.songouhe.base.util.model.enume;

/**
 * 环境变量类型
 * 作为 EnvironmentVariable的一个属性存在
 * Created by sunxuan on 16-8-14.
 */

public class EnvironmentVariableType {
    private final int value;
    private final String key;
    private final String color;

    private EnvironmentVariableType(int value, String key, String color) {
        this.value = value;
        this.key = key;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public String getColor() {
        return color;
    }

    public static EnvironmentVariableType getEnvironmentVariableType(int value) {
        EnvironmentVariableType status = null;
        if (value >= 0 && value < VALUES.length)
            status = VALUES[value];
        return status;
    }

    public static final EnvironmentVariableType STRING;       // 字符串
    public static final EnvironmentVariableType BOOL;          // 布尔型
    public static final EnvironmentVariableType INT;               // 整型
    public static final EnvironmentVariableType FLOAT;           // 浮点型
    public static final EnvironmentVariableType SINGLE_SELECT;      // 单选
    public static final EnvironmentVariableType MULTI_SELECT;        // 多选
    public static final EnvironmentVariableType FOREIGN_KEY;         // 外键
    public static final EnvironmentVariableType LIMITED_WORD;        // 受控词
    public static final EnvironmentVariableType[] VALUES;

    //字符串\BOOL\整数\浮点数\单选\多选\表外键\受控词
    static {
        STRING = new EnvironmentVariableType(0, "string", null);
        BOOL = new EnvironmentVariableType(1, "bool", null);
        INT = new EnvironmentVariableType(2, "int", null);
        FLOAT = new EnvironmentVariableType(3, "float", null);
        SINGLE_SELECT = new EnvironmentVariableType(4, "single_select", null);
        MULTI_SELECT = new EnvironmentVariableType(5, "multi_select", null);
        FOREIGN_KEY = new EnvironmentVariableType(6, "foreign_key", null);
        LIMITED_WORD = new EnvironmentVariableType(7, "limited_word", null);
        VALUES = new EnvironmentVariableType[]{STRING, BOOL, INT, FLOAT,
                                               SINGLE_SELECT, MULTI_SELECT, FOREIGN_KEY, LIMITED_WORD};

    }

}
