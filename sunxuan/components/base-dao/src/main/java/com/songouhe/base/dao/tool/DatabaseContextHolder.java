/**
 * 
 */
package com.songouhe.base.dao.tool;

import org.springframework.util.Assert;

public class DatabaseContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDateBaseType(DataBaseTypeEnum dataBaseTypeEnum) {
		Assert.notNull(dataBaseTypeEnum, "DatabaseType cannot be null");
		contextHolder.set(dataBaseTypeEnum.toString());
	}

	public static String getDateBaseType() {
		return contextHolder.get();
	}

	public static void clearDatabaseType() {
		contextHolder.remove();
	}
}
