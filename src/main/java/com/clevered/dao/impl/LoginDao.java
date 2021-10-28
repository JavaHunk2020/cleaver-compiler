package com.clevered.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
	
	//From where we will get this bean
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean validateUser(String username, String password) {
		String sql="select username from user_logins_tbl where username=? and password = ?";
		Object[] data=new Object[] {username, password};
		//data is input for the query
		List<String> users=jdbcTemplate.queryForList(sql,data,String.class);
		return users.size()>0;
	}
	

}
