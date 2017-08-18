/**
 * 
 */
package com.songouhe.base.dao.service.impl;


import com.songouhe.base.dao.BaseDao;
import com.songouhe.base.dao.entity.UserEntityForTest;
import com.songouhe.base.dao.service.UserService;
import com.songouhe.base.dao.tool.DataBaseTypeEnum;
import com.songouhe.base.dao.tool.DatabaseContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseDao baseDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.songouhe.internal.sample.test.service.UserService#getAllUserList()
	 */
	public List<UserEntityForTest> getAllUserList() {
		return null;
//		return baseDao.getQueryBySql("select name,age from user");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.songouhe.internal.sample.test.service.UserService#updateUser()
	 */
	public void updateUser(String sql) {
		baseDao.updateBySql(sql, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.songouhe.internal.sample.test.service.UserService#saveUser(java.lang.String)
	 */
	public void saveUser(String sql) {
		baseDao.addBySql(sql, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.songouhe.internal.sample.test.service.UserService#saveUser2()
	 */
	public void saveUser2() {
		DatabaseContextHolder.clearDatabaseType();
		DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.uwtDatasource);
		baseDao.addBySql("insert into user (name,age)values('张三',13)", null);
		DatabaseContextHolder.clearDatabaseType();
		DatabaseContextHolder.setDateBaseType(DataBaseTypeEnum.tyDatasource);
		baseDao.addBySql("insert into user_friend (user_id,friend_id,create_time)values('test1','test2','2017-11-12')", null);
	}

}
