package com.proj.ProjectBackEnd.Service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.ProjectBackEnd.Model.Course;
import com.proj.ProjectBackEnd.Repository.CourseRepository;

@Service
@Transactional
public class CourseService {
	
	@Autowired
	private final CourseRepository courseRepo;
	
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}
	
	public String saveCourse(Course c) {
		int id = courseRepo.save(c);
		return "Course saved successfully";
	}
	
	public List<Course> getAllCourses() {
		return (List<Course>) courseRepo.findAll();
	}

	public String deleteCourse(int courseid) {
		int id = courseRepo.deleteCourse(courseid);
		return "Course with ID:"+courseid+"deleted successfully";
	}

	public Course getCourseById(int id) {
		return courseRepo.getCourseById(id);
	}

	public String updateCourse(Course c) {
		int id = courseRepo.updateCourse(c);
		return "Course with ID:"+c.getId()+" updated successfully";
	}

	public List<Course> searchCourseByName(String term) {
		return courseRepo.searchCourseByName(term);
	}

	public int updateTimestamp(int cid, Timestamp time) {
		return courseRepo.updateTimestamp(cid,time);
	}

	public Timestamp getTimestamp(int cid) {
		return courseRepo.getTimestamp(cid);
	}

	public List<String> getCourseNames() {
		return courseRepo.getCourseNames();
	}

	public List<Integer> getUserdata() {
		return courseRepo.getUserdata();
	}

}
