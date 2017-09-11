package com.songouhe.base.dao;

import com.songouhe.base.dao.entity.RecordWithTotalCount;

import java.util.List;
import java.util.Map;

/**
 * * @author zhanghao 2015/12/21
 */
public interface BaseDao {
	//用于规定是否支持回滚;false为可回滚，需要每次运行更新后手动commit
	public void setAutoCommit(boolean autoCommit) ;
	public boolean isAutoCommit() ;
	//create
	public void addBySql(String sql, final Object[] params);
	public boolean createByObj(Object[] obj_array) throws DaoSysException;
 	//update
	public void updateBySql(String sql, final Object[] params);
	public boolean update(Object obj,Map<Object,Object> map,Map<Object, Object>condition) throws Exception ;
	//delete
	public void deleteBySql(String sql, final Object[] params);
	public boolean delete(Object obj,Map<Object,Object> condition) throws Exception;
	//find
	public List getQueryByObj(Object obj) throws DaoSysException ;
	public List getQueryByCondition(Object obj, Map<Object, Object> paraMap, String start, String limit,
									String order, boolean isFuzzy) throws DaoSysException ;

	public RecordWithTotalCount getQueryAndCountByCondition(Object obj, Map<Object, Object> condition, String start, String limit,
															String order, boolean isFuzzy) throws DaoSysException ;
	public int getRecordCount(String sql, Object[] values) throws DaoSysException;

	//get datasource
	public javax.sql.DataSource getDatasource();

//	public void closeDatasource(String[] inDatabaseTypes) throws DaoSysException;
}
