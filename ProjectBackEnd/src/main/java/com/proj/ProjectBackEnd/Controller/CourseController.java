package com.proj.ProjectBackEnd.Controller;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.ProjectBackEnd.Model.Course;
import com.proj.ProjectBackEnd.Service.CourseService;

@RestController
@RequestMapping("app/course")
@CrossOrigin
public class CourseController {
	
	@Autowired
	public CourseService service;
	
	Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@PostMapping("/add-course")
	@Transactional
	public String saveCourse(@RequestBody Course c) {
		logger.info("Request to saveCourse is made and is done");
		return service.saveCourse(c);
	}
	
	@GetMapping("/all-courses")
	public List<Course> getAllCourses() {
		logger.info("Request to allCourses retrieval is made and are retrieved");
		return service.getAllCourses();
	}
	
	@GetMapping("/delete-course/{cid}")
	public String deleteCourse(@PathVariable("cid") int courseid) {
		logger.info("Request to delete course ID:"+courseid+" is made and is done");
		return service.deleteCourse(courseid);
	}
	
	@GetMapping("/get-course/{id}")
	public Course getCourseById(@PathVariable("id") int id) {
		logger.info("Request to getCourse with ID:"+id);
		return service.getCourseById(id);
	}
	
	@PostMapping("/update-course")
	public String updateCourse(@RequestBody Course c) {
		logger.info("Request to update course is done and is updated");
		return service.updateCourse(c);
	}
	
	@GetMapping("/search-course/{term}")
	public List<Course> searchCourseByName(@PathVariable("term") String term) {
		logger.info("Request to search course by name:"+term+" is made");
		List<Course> res = service.searchCourseByName(term);
		if(res.isEmpty()) {
			logger.info("Such courses are not available");
			return null;
		}
		logger.info("Courses are retrieved");
		return res;
	}
	
	@GetMapping("/name-courses")
	public List<String> getCourseNames() {
		logger.info("Request to get names of all courses is made");
		List<String> res = service.getCourseNames();
		if(res.isEmpty()) {
			logger.info("There are 0 courses available");
			return null;
		}
		logger.info("CourseNames are retrieved");
		return res;
	}
	
	@GetMapping("/user-data")
	public List<Integer> getUserdata() {
		logger.info("Request to getUserdata is made");
		List<Integer> res = service.getUserdata();
		if(res.isEmpty()) {
			logger.info("There are 0 courses available to get such data");
			return null;
		}
		logger.info("Userdata is retrieved");
		return res;
	}
	
}
