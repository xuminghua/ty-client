package com.songouhe.internal.sample.test;

import com.songouhe.base.dao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * * @author zhanghao 2015/12/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserServiceTest {
	@Autowired
	private UserService userService;
	

	@Test
	public void rollBackTest(){
		userService.saveUser("insert into user (name,age)values('张三',13)");
	}
}
