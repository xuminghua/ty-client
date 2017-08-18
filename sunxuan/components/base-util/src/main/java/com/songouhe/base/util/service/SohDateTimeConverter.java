package com.songouhe.base.util.service;


import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author sunxuan
 * @version 1.0 17-8-9
 */
public class SohDateTimeConverter extends DateTimeConverter
{
    private transient Log log;
         private static final String DATE      = "yyyy-MM-dd";
         private static final String DATETIME  = "yyyy-MM-dd HH:mm:ss";
         private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

    protected Class<?> getDefaultType() {
        return Timestamp.class;
    }
    private <T> T toDate(Class<T> type, String value) {
        if(type.equals(java.sql.Date.class)) {
            try {
                return type.cast(java.sql.Date.valueOf(value));
            } catch (IllegalArgumentException var4) {
                throw new ConversionException("String must be in JDBC format [yyyy-MM-dd] to create a java.sql.Date");
            }
        } else if(type.equals(Time.class)) {
            try {
                return type.cast(Time.valueOf(value));
            } catch (IllegalArgumentException var5) {
                throw new ConversionException("String must be in JDBC format [HH:mm:ss] to create a java.sql.Time");
            }
        } else if(type.equals(Timestamp.class)) {
            try {
                return type.cast(Timestamp.valueOf(value));
            } catch (IllegalArgumentException var6) {
                throw new ConversionException("String must be in JDBC format [yyyy-MM-dd HH:mm:ss.fffffffff] to create a java.sql.Timestamp");
            }
        } else {
            String msg = this.toString(this.getClass()) + " does not support default String to \'" + this.toString(type) + "\' conversion.";
            if(this.log().isWarnEnabled()) {
                this.log().warn("    " + msg);
                this.log().warn("    (N.B. Re-configure Converter or use alternative implementation)");
            }

            throw new ConversionException(msg);
        }
    }

    String toString(Class<?> type) {
        String typeName = null;
        if(type == null) {
            typeName = "null";
        } else if(type.isArray()) {
            Class elementType = type.getComponentType();

            int count;
            for(count = 1; elementType.isArray(); ++count) {
                elementType = elementType.getComponentType();
            }

            typeName = elementType.getName();

            for(int i = 0; i < count; ++i) {
                typeName = typeName + "[]";
            }
        } else {
            typeName = type.getName();
        }

        if(!typeName.startsWith("java.lang.") && !typeName.startsWith("java.util.") && !typeName.startsWith("java.math.")) {
            if(typeName.startsWith("org.apache.commons.beanutils.converters.")) {
                typeName = typeName.substring("org.apache.commons.beanutils.converters.".length());
            }
        } else {
            typeName = typeName.substring("java.lang.".length());
        }

        return typeName;
    }
    Log log() {
        if(this. log== null) {
            this.log = LogFactory.getLog(this.getClass());
        }

        return this.log;
    }

    }
