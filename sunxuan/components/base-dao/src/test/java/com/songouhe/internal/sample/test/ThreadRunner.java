package com.songouhe.internal.sample.test;

/**
 * @author sunxuan
 * @version 1.0 17-2-24
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.entity.UserEntityForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository("thR")
public class ThreadRunner implements Runnable{
	@Autowired
	private BaseDao baseDao;

	@Autowired
	private BaseDao baseDao2;



	private static AtomicInteger count = new AtomicInteger(0);

//	@Override
	public void run(){
		try{
			DataSource ds = baseDao.getDatasource();
			Connection conn = baseDao.getDatasource().getConnection();
			DruidDataSource pds = (DruidDataSource) ds;
//			String handle = "zhangsan" + count.addAndGet(1);
//			String[] params = {handle,"133","部门1","testactor1","测试职位1"};
//			baseDao2.addBySql("insert into UPM_USER (HANDLE,DEPARTMENTID,DEPARTMENTNAME,ACTORID,ACTORNAME)values('" +handle+"','133','部门1','testactor1','测试职位1')",params);
//								List<UserEntityForTest> list = baseDao.getQueryBySql("select handle from UPM_USER");
//			for(UserEntityForTest entity:list){
//				System.out.println("*****************************");
//				System.out.println("当前线程为：" + Thread.currentThread().getName() + "count:" + count.addAndGet(1));
////				System.out.println(handle);
//				System.out.println(entity.getHandle());
//				System.out.println("connection:" + conn.hashCode());
//				System.out.println("空闲的连接: " + pds.getPoolingCount());
//				System.out.println("使用中的连接: " + pds.getActiveCount());
//
//			}
			conn.close();


		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}



}
