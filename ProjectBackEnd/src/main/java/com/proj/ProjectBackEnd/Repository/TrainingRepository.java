package com.proj.ProjectBackEnd.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proj.ProjectBackEnd.Model.User;

@Repository
public class TrainingRepository implements ITrainingRepository {
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	TrainingRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<User> getAllStudents(int cid) {
		String sql = "SELECT u.* FROM usercoursetable uc JOIN usertable u ON u.id = uc.userid AND uc.courseid = ?";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new User(rs.getInt("id"),
				rs.getString("username"), rs.getString("email"),rs.getString("role")), cid);
	}

}
