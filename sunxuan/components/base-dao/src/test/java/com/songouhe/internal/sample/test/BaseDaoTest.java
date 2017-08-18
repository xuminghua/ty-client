package com.songouhe.internal.sample.test;


import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.DaoSysException;
import com.songouhe.base.dao.entity.Product_Info;
import com.songouhe.base.dao.entity.UserEntityForTest;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * * @author zhanghao 2015/12/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext02.xml"})
public class BaseDaoTest {
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private BaseDao baseDao2;
	
	@Test
	public void addBySql(){
		String[] params = {"张三","12"};
		baseDao.addBySql("insert into user (name,age)values('张三',13)",params);
	}
	
	@Test
	public void addBySql2(){
		String[] params = {"张三","133"};
		baseDao2.addBySql("insert into user (name,age)values('张三',13)",params);
	}
	
	@Test
	public void updateBySql(){
		String marital_Status = "离异";
		Integer id = 18;
		Object[] params = {marital_Status,id};
		baseDao.updateBySql("update User_Base_Info set marital_Status=? where id=?", params);
	}

	@Test
	public void deleteBySql(){
		String[] params = {"1"};
		baseDao.deleteBySql("delete from user where id = ?", params);
	}
	
	@Test
	public void getQueryList() throws DaoSysException {
		List<UserEntityForTest> list = baseDao.getQueryByObj(new UserEntityForTest());
		for (UserEntityForTest userEntityForTest : list) {
			System.out.println(userEntityForTest.getHandle());
		}
	}
	@Test
	public void getQuerybySQL() throws DaoSysException {
		DatabaseContextHolder.clearDatabaseType();
  DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.valueOf("tyDatasource"));
		List<Product_Info> list = baseDao.getQueryByObj(new Product_Info());
		for (Product_Info userEntityForTest : list) {
			System.out.println(userEntityForTest.getId());
		}
	}
	@Test
	public void test() throws DaoSysException {
		DatabaseContextHolder.clearDatabaseType();
				DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.uwtDatasource);
				baseDao.addBySql("update User_Base_Info set marital_Status='未婚' where id=13", null);
		DatabaseContextHolder.clearDatabaseType();
		DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.ssoDatasource);
		baseDao.addBySql("update class_department_level set name='项目组' where class_no=3", null);

		DatabaseContextHolder.clearDatabaseType();
				DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.tyDatasource);
				baseDao.addBySql("insert into user_friend (user_id,friend_id,create_time)values('1002','1003','2017-11-12')", null);
	}

}
