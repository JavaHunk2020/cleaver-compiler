package com.clevered.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
		

}
