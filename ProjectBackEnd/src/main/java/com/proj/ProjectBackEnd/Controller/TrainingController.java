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
import com.proj.ProjectBackEnd.Service.CourseService;
import com.proj.ProjectBackEnd.Service.EmailService;
import com.proj.ProjectBackEnd.Service.TrainingService;
import com.proj.ProjectBackEnd.Service.UserService;

@RestController
@RequestMapping("app/training")
@CrossOrigin
public class TrainingController {
	
	@Autowired
	public TrainingService service;
	
	@Autowired
	public EmailService emailservice;
	
	@Autowired
	public CourseService courseService;
	
	@Autowired
	public UserService userService;
	
	Logger logger = LoggerFactory.getLogger(TrainingController.class);
	
	@GetMapping("/all-students/{id}")
	public List<User> getAllStudents(@PathVariable("id") int cid) {
		logger.info("request to get all students enrolled in a course ID:"+cid+" is made");
		List<User> res = service.getAllStudents(cid);
		if(res.isEmpty()) {
			logger.info("No students are enrolled");
			return null;
		}
		logger.info("student data is retrieved");
		return res;
	}
	
	@GetMapping("/send-email/{userid}&{courseid}")
	public String sendEmail(@PathVariable("userid") int uid, @PathVariable("courseid") int cid) {
		
		logger.info("Request to send an email to student ID:"+uid+" regarding course ID:"+" cid is made and is done");
		
		Course c = courseService.getCourseById(cid);
		String body = "Dear Student,"+ "\n\n" +
	      "You are currently enrolled in following course:" + "\n" +
	      "Course Name - " + c.getName() + "\n" +
	      "Details - " + c.getDescription() + "\n" +
	      "Skills you\'ll gain after completing the course : " + c.getSkills() + "\n" +
	      "\n\nRegards,\n AU CMS>";
		
		String toEmail = userService.getEmailById(uid);
		
		return emailservice.sendSimpleEmail(toEmail, body,"AUMS Web Course Management System" );
	}

}
