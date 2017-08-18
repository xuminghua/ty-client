package com.songouhe.base.dao.impl;

import com.songouhe.base.dao.BaseDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * * @author zhanghao 2015/12/21
 * */
@Repository("baseDao2")
public class BaseDaoImpl2 extends JdbcDaoSupport
//		implements BaseDao
{

	/**
	 * sql = "insert into user (name,age) values(?,?)"
	 * */
	public void addBySql(String sql, final Object[] params) {
		// --------------methods01------------------------------------//
		this.getJdbcTemplate().update(sql);
		// -----------------or methods02------------------------------//
		// just like updateBySql
	}

	public void updateBySql(String sql, Object[] params) {
		// TODO Auto-generated method stub

	}

	public void deleteBySql(String sql, Object[] params) {
		// TODO Auto-generated method stub

	}

	public List getQueryBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	public javax.sql.DataSource getDatasource(){
		// TODO Auto-generated method stub
		return null;
    }

}
