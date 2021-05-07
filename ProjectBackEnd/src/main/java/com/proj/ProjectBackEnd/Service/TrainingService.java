package com.proj.ProjectBackEnd.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.ProjectBackEnd.Model.User;
import com.proj.ProjectBackEnd.Repository.TrainingRepository;

@Service
@Transactional
public class TrainingService {
	
	@Autowired
	private final TrainingRepository trainingRepo;
	
	public TrainingService(TrainingRepository trainingRepo) {
		this.trainingRepo = trainingRepo;
	}

	public List<User> getAllStudents(int cid) {
		return trainingRepo.getAllStudents(cid);
	}
}