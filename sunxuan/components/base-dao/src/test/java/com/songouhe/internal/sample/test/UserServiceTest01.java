package com.songouhe.internal.sample.test;

import com.songouhe.base.dao.service.*;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * * @author zhanghao 2015/12/21
 * 多数据源测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext02.xml"})
public class UserServiceTest01 {
	@Autowired
	private UserService userService;
	

	@Test
	public void test(){
//		userService.saveUser(null);
//		DatabaseContextHolder.clearDatabaseType();
//		DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.uwtDatasource);
//		//			baseDao2.addBySql("insert into UPM_USER (HANDLE,DEPARTMENTID,DEPARTMENTNAME,ACTORID,ACTORNAME)values('" +handle+"','133','部门1','testactor1','测试职位1')",params);
//
//		userService.saveUser("insert into UPM_USER (HANDLE,DEPARTMENTID,DEPARTMENTNAME,ACTORID,ACTORNAME)values('zhangsi1','133','部门1','testactor1','测试职位1')");
		DatabaseContextHolder.clearDatabaseType();
		DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.ssoDatasource);
		userService.saveUser("update BASEUSERINFO set NAME='用户3'");
	}
	
	/**
	 * 在事务中改变数据源测试
	 * */
	@Test
	public void test02(){
		userService.saveUser2();
	}
}
