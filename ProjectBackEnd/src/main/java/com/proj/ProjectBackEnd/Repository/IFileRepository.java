package com.proj.ProjectBackEnd.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.proj.ProjectBackEnd.Model.TrainingMaterials;

public interface IFileRepository {
	
	int addFile(int cid, byte[] data, String name, String type) throws SerialException, SQLException;
	
	List<TrainingMaterials> allVersions(int cid);
	
	TrainingMaterials getMaterialByTS(int cid, Timestamp time);
	
	int deleteVersion(int id);
}
