package com.proj.ProjectBackEnd.Repository;

import java.sql.Timestamp;
import java.util.List;

import com.proj.ProjectBackEnd.Model.Course;

public interface ICourseRepository {
	
	int save(Course c);
	
	List<Course> findAll();
	
	int deleteCourse(int courseid);
	
	Course getCourseById(int id);
	
	int updateCourse(Course c);
	
	List<Course> searchCourseByName(String term);
	
	int updateTimestamp(int cid, Timestamp time);
	
	Timestamp getTimestamp(int cid);

}
