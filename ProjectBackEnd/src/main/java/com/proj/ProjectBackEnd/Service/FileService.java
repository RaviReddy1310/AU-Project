package com.proj.ProjectBackEnd.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.ProjectBackEnd.Model.TrainingMaterials;
import com.proj.ProjectBackEnd.Repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	private final FileRepository fileRepo;
	
	public FileService(FileRepository fileRepo) {
		this.fileRepo = fileRepo;
	}

	public String addFile(int cid, byte[] data, String name, String type) throws SerialException, SQLException {
		int id = fileRepo.addFile(cid, data, name, type);
		return "File Saved Successfully";
	}

	public List<TrainingMaterials> allVersions(int cid) {
		return fileRepo.allVersions(cid);
	}

	public TrainingMaterials getMaterialByTS(int cid, Timestamp time) {
		return fileRepo.getMaterialByTS(cid,time);
	}

	public String deleteVersion(int id) {
		int value = fileRepo.deleteVersion(id);
		return "Version deleted successfully";
	}

}
