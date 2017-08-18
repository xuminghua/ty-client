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

	public void addBySql(String sql, final Object[] params);

	public void updateBySql(String sql, final Object[] params);

	public void deleteBySql(String sql, final Object[] params);

	public List getQueryByObj(Object obj) throws DaoSysException ;
	public List getQueryByCondition(Object obj, Map<Object, Object> paraMap, String start, String limit,
												String order, boolean isFuzzy) throws DaoSysException ;

	public RecordWithTotalCount getQueryAndCountByCondition(Object obj, Map<Object, Object> condition, String start, String limit,
													String order, boolean isFuzzy) throws DaoSysException ;
	
	public boolean createByObj(Object[] obj_array) throws DaoSysException;

	public boolean update(Object obj,Map<Object,Object> map,Map<Object, Object>condition) throws Exception ;
	public boolean delete(Object obj,Map<Object,Object> condition) throws Exception;
	public javax.sql.DataSource getDatasource();

//	public void closeDatasource(String[] inDatabaseTypes) throws DaoSysException;
}
