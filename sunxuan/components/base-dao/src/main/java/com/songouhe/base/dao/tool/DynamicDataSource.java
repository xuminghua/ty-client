/**
 * 
 */
package com.songouhe.base.dao.tool;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * DynamicDataSource.java Create on 2015年12月22日
 * 
 * Copyright (c) 2015年12月22日 by zhanghao
 * 
 * @author <a href="fred_zhanghao@163.com.com">fred</a>
 * @version 1.0
 * 
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.getDateBaseType();
	}


}
