/**
 * 
 */
package com.songouhe.base.dao.service;

import com.songouhe.base.dao.entity.UserEntityForTest;

import java.util.List;



public interface UserService {

	public List<UserEntityForTest> getAllUserList();
	
	public void updateUser(String sql);
	
	public void saveUser(String sql);

	public void saveUser2();

}
