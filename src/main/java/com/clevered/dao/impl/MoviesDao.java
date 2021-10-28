package com.clevered.dao.impl;
/*package com.rab3tech.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rab3tech.dao.entity.MovieEntity;


@Repository
public class MoviesDao implements IMoviesDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	//DELETE, UPDATE ,INSERT ->> DML
	
	@Override
	public String save(MovieEntity movieEntity) {
		//SQL
		//LocalDate localDate=LocalDate.of(Integer.parseInt(movieEntity.getYear()),Month.JANUARY, 1);
		String sql="insert into movies_tbl(name,year,director,poster,story) values(?,?,?,?,?)";
		//This is data for the Query
		Object[] data=new Object[] {movieEntity.getName(),LocalDate.now(),movieEntity.getDirector(),
				movieEntity.getPoster(),movieEntity.getStory()};
		//JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		//to execute insert query
		jdbcTemplate.update(sql,data);	
		return "success";
	}
	
	
	@Override
	public MovieEntity findByMid(int mid){
		 String sql="select mid,name,year,director,poster,story from movies_tbl where mid = ?";
		 List<MovieEntity> movieDTOs=jdbcTemplate.query(sql,new Object[]{mid}, new BeanPropertyRowMapper(MovieEntity.class));
		 return movieDTOs.get(0);
	}
	
	@Override
	public void updateByMid(MovieEntity movieEntity) {
		 String sql="update movies_tbl set name=? ,director=?,poster=? where mid = ?";
		 Object data[]=new Object[]{movieEntity.getName(), movieEntity.getDirector(),movieEntity.getPoster(),movieEntity.getMid()};
		 jdbcTemplate.update(sql,data);
	}
	

	@Override
	public List<MovieEntity>  findAll(){
		 String sql="select mid,name,year,director,poster,story from movies_tbl";
		 List<MovieEntity> movieDTOs=jdbcTemplate.query(sql, new BeanPropertyRowMapper(MovieEntity.class));
		 return movieDTOs;
	}
	
	@Override
	public void  deleteByMid(int mid) {
		 String sql="delete from movies_tbl where mid = "+mid;
		 jdbcTemplate.update(sql);
	}

}
*/
