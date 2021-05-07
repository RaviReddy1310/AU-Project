package com.proj.ProjectBackEnd.Repository;

import java.util.List;

import com.proj.ProjectBackEnd.Model.Course;
import com.proj.ProjectBackEnd.Model.User;

public interface IUserRepository {
	
	int save(User user);

	Iterable<User> findAll();
	
	User findByemail(String email);
	
	int addCourseToStudy(int uid, int cid);
	
	int istrainer(int userid);
	
	List<Course> myCourses(int id);
	
	int dropCourse(int userid, int courseid);
	
	List<Course> trainerCourses(int id);
	
	String getEmailById(int uid);

}
