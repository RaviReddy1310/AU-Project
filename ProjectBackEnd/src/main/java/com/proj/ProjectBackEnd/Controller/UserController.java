package com.proj.ProjectBackEnd.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.ProjectBackEnd.Model.Course;
import com.proj.ProjectBackEnd.Model.User;
import com.proj.ProjectBackEnd.Service.UserService;

@RestController
@RequestMapping("app/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	public UserService service;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/all-users")
	public List<User> getAllUsers() {
		logger.info("Request to getAll Users is made and is done");
		return (List<User>) service.getAllUsers();
	}
	
	@GetMapping("/save-user/{name}&{email}")
	public int saveUser(@PathVariable("name") String name, @PathVariable("email") String email) {
		logger.info("Request to save user is made and is done");
		User user = new User();
		user.setEmail(email);
		user.setUsername(name);
		user.setRole("USER");
		return service.saveUser(user);
	}
	
	@GetMapping("/validate-user/{name}&{email}")
	public User validateUser (
	@PathVariable("name") String name, @PathVariable("email") String email) {
		logger.info("Request to validate user is made");
		if((int)service.isUser(email) == 0) {
			logger.info("Since the user is new, the user has to be saved");
			User user = new User();
			user.setEmail(email);
			user.setUsername(name);
			user.setRole("USER");
			int id1 = service.saveUser(user);
		}
		logger.info("Validation completed");
		return service.findByemail(email);
	}
	
	@GetMapping("/add-course/{userid}&{courseid}")
	public String addCourseToStudy (
	@PathVariable("userid") int userid, @PathVariable("courseid") int courseid) {
		logger.info("Request to be enrolled in a course ID:"+courseid+" by user ID:"+userid+" is made");
		return service.addCourseToStudy(userid, courseid);
	}
	
	@GetMapping("/istrainer/{userid}")
	public String istrainer(@PathVariable("userid") int id) {
		logger.info("Request to check if a user ID:"+id+" is a trainer is made and is done");
		return service.istrainer(id);
	}
	
	@GetMapping("/my-courses/{userid}")
	public List<Course> myCourses(@PathVariable("userid") int id) {
		logger.info("Request to getAllCourses a user ID:"+id+" enrolled in is made");
		List<Course> res = service.myCourses(id);
		if(res.isEmpty()) {
			logger.info("User ID:"+id+" is not enrolled in any courses");
			return null;
		}
		logger.info("Enrolled courses are retrieved");
		return res;
	}
	
	@GetMapping("/drop-course/{userid}&{courseid}")
	public String dropCourse(@PathVariable("userid") int userid, @PathVariable("courseid") int courseid) {
		logger.info("Request to be dropped out of course ID:"+courseid+" is made by user ID:"+userid+" and is done");
		return service.dropCourse(userid,courseid);
	}
	
	@GetMapping("/trainer-courses/{userid}")
	public List<Course> trainerCourses(@PathVariable("userid") int id) {
		logger.info("Request to getAll Courses currently training by user ID:"+id+" is made");
		List<Course> res = service.trainerCourses(id);
		if(res.isEmpty()) {
			logger.info("User ID: "+id+" is currently not training any courses");
			return null;
		}
		logger.info("Training Courses are retrieved");
		return res;
	}
	
	@GetMapping("/isUser/{email}")
	public Integer isUser(String email) {
		logger.info("Request to check if the email:"+email+" is signed in with and is done");
		return service.isUser(email);
	}
}
