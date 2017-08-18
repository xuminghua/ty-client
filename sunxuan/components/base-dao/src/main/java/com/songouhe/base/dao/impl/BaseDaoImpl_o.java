package com.songouhe.base.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.songouhe.base.dao.DaoSysException;
import com.songouhe.base.dao.entity.UserEntityForTest;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Enumeration;
import java.util.List;
/**
 * @author sunxuan
 * @version 1.0 17-5-31
 */
//@Repository("baseDao")
public class BaseDaoImpl_o
//        implements BaseDao
{



    	@Autowired
    	private JdbcTemplate jdbcTemplate;

    	private final static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    	/**
    	 * sql = "insert into user (name,age) values(?,?)"
    	 * */
    	public void addBySql(String sql,final Object[] params) {
    		//--------------methods01------------------------------------//
    		//jdbcTemplate.execute(sql);
    		//-----------------or methods02------------------------------//
    		jdbcTemplate.update(sql,
    				new PreparedStatementSetter() {
    					public void setValues(PreparedStatement ps)
    							throws SQLException {
    					}
    				});
    	}

    	/**
    	 * sql = “update table_name set name=?,age=?”
    	 * */
    	public void updateBySql(String sql,final Object[] params) {
    		//--------------methods01------------------------------------//
    		jdbcTemplate.update(sql,
    				new PreparedStatementSetter() {
    					public void setValues(PreparedStatement ps)
    							throws SQLException {
    						ps.setInt(1, Integer.parseInt(params[0].toString()));
    					}
    				});
    		//-----------------or methods02------------------------------//
    		//jdbcTemplate.update(sql, params);
    	}

    	/**
    	 * sql = "delete user where id =? and name = ?;"
    	 * */
    	public void deleteBySql(String sql,final Object[] params) {
    		//----------just like update---------------//
    		jdbcTemplate.update(sql, params);
    	}

    	/**
    	 * RowMapper接口封装返回集合，可自定义
    	 * */
    	public List<UserEntityForTest> getQueryBySql(String sql, String className) {
    		return (List<UserEntityForTest>) jdbcTemplate.query(sql,
    				new BeanPropertyRowMapper<UserEntityForTest>(UserEntityForTest.class));
    	}

    	public javax.sql.DataSource getDatasource(){
    		return jdbcTemplate.getDataSource();
    	}

    	public void open(String[] inDatabaseTypes) throws DaoSysException {
    		for(String databaseType: inDatabaseTypes){
    			DatabaseContextHolder.clearDatabaseType();
                DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(databaseType));
    			try{
    				DataSource ds = jdbcTemplate.getDataSource();
    				Connection conn = ds.getConnection();
    				DruidDataSource pds = (DruidDataSource) ds;
    				conn.close();
    				logger.info("Open DataSource '" + databaseType + "', current connections number is " +
    						pds.getConnectCount());
    			}catch(Exception e){
    				throw new DaoSysException("DataSource " + databaseType + " open error :",e);
    			}
    		}
    	}

    	public void closeDatasource(String[] inDatabaseTypes) throws DaoSysException{
    		for(String databaseType: inDatabaseTypes){
    			DatabaseContextHolder.clearDatabaseType();
    			DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf(databaseType));
    			try{
    				DataSource ds = jdbcTemplate.getDataSource();
    				if(ds != null ){
    					DruidDataSource pds = (DruidDataSource) ds;
    					pds.close();
    					logger.info("close DataSource '" + databaseType + "'");
    				}

    			}catch(Exception e){
    				throw new DaoSysException("DataSource " + databaseType + " close error :",e);
    			}
    		}
    		try {
    			AbandonedConnectionCleanupThread.shutdown();
    		} catch (InterruptedException e) {
    			throw new DaoSysException("AbandonedConnectionCleanupThread shutdown error :",e);
    		}

    		Enumeration<Driver> drivers = DriverManager.getDrivers();
    		while(drivers.hasMoreElements()) {
    			try {
    				Driver driver = drivers.nextElement();
    				DriverManager.deregisterDriver(driver);
    			} catch (SQLException e) {
    				throw new DaoSysException("DAO destroy driver error :",e);

    			}
    		}

    	}



}
