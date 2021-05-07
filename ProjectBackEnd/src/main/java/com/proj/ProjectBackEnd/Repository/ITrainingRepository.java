package com.proj.ProjectBackEnd.Repository;

import java.util.List;

import com.proj.ProjectBackEnd.Model.User;

public interface ITrainingRepository {
	
	 List<User> getAllStudents(int cid);

}
