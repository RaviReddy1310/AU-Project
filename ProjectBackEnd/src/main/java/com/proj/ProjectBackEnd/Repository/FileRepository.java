package com.proj.ProjectBackEnd.Repository;

import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proj.ProjectBackEnd.Model.TrainingMaterials;

@Repository
public class FileRepository implements IFileRepository {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	FileRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addFile(int cid, byte[] data, String name, String type) throws SerialException, SQLException {
		
		Blob blob = new javax.sql.rowset.serial.SerialBlob(data);	
		Timestamp time =  new Timestamp(System.currentTimeMillis());
		
		String sql = "UPDATE coursetable SET updatedtimestamp = ? WHERE id = ?";
		int id = jdbcTemplate.update(sql, time, cid);
		
		sql = "INSERT INTO trainingmaterials (courseid, filedata, filename, filetype, fileuploadtime) VALUES (?,?,?,?,?)";
		return jdbcTemplate.update(sql, cid, blob, name, type, time);
	}

	public List<TrainingMaterials> allVersions(int cid) {
		String sql = "SELECT * FROM trainingmaterials WHERE courseid = ? ORDER BY fileuploadtime ASC";
		return  jdbcTemplate.query(sql, (rs,rowNum) -> new TrainingMaterials(rs.getInt("id"),rs.getInt("courseid"), 
				rs.getBlob("filedata").getBytes(1, (int)rs.getBlob("filedata").length()),rs.getString("filename"), 
				rs.getString("filetype"), rs.getTimestamp("fileuploadtime")),cid);
	}

	public TrainingMaterials getMaterialByTS(int cid, Timestamp time) {
		String sql = "SELECT * FROM trainingmaterials WHERE courseid = ? AND fileuploadtime = ?";
		return jdbcTemplate.queryForObject(sql, (rs,rowNum) -> new TrainingMaterials(rs.getInt("id"),rs.getInt("courseid"), 
				rs.getBlob("filedata").getBytes(1, (int)rs.getBlob("filedata").length()),rs.getString("filename"), 
				rs.getString("filetype"), rs.getTimestamp("fileuploadtime")), cid, time);
	}
	
	public int deleteVersion(int id) {
		String sql = "SELECT fileuploadtime FROM trainingmaterials WHERE id = ?";
		Timestamp time = jdbcTemplate.queryForObject(sql, Timestamp.class, id);
		
		sql = "UPDATE coursetable SET updatedtimestamp = NULL WHERE updatedtimestamp = ?";
		int value = jdbcTemplate.update(sql, time);
		
		sql = "DELETE FROM trainingmaterials WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
}
