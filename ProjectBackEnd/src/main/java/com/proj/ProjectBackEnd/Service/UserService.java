package com.proj.ProjectBackEnd.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.ProjectBackEnd.Model.Course;
import com.proj.ProjectBackEnd.Model.User;
import com.proj.ProjectBackEnd.Repository.UserRepository;

@Service
@Transactional
public class UserService{
	
	@Autowired
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public int saveUser(User user) {
		return userRepo.save(user);
	}
	
	public Iterable<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public User findByemail(String email) {
		return userRepo.findByemail(email);
	}
	
	public String addCourseToStudy(int uid, int cid) {
		int id = userRepo.addCourseToStudy(uid, cid);
		return "Course with ID:"+cid+" is successfully enrolled";
	}

	public String istrainer(int userid) {
		int id = userRepo.istrainer(userid);
		if(id > 0) {
			return "Y";
		} else {
		return "N";
		}
	}

	public List<Course> myCourses(int id) {
		return userRepo.myCourses(id);
	}

	public String dropCourse(int userid, int courseid) {
		int id = userRepo.dropCourse(userid,courseid);
		return "Course with ID:"+courseid+" dropped successfully";
	}

	public List<Course> trainerCourses(int id) {
		return userRepo.trainerCourses(id);
	}

	public String getEmailById(int uid) {
		return userRepo.getEmailById(uid);
	}

	public Integer isUser(String email) {
		return userRepo.isUser(email);
	}
}
