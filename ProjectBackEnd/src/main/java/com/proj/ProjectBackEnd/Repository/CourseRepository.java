package com.proj.ProjectBackEnd.Repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proj.ProjectBackEnd.Model.Course;

@Repository
public class CourseRepository implements ICourseRepository {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	CourseRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Course c) {
		String sql = "INSERT INTO coursetable (name, description, skills, trainerid) VALUES (?,?,?,?)";
		return jdbcTemplate.update(sql, c.getName(), c.getDescription(), c.getSkills(), c.getTrainerid());
	}

	public List<Course> findAll() {
		String sql = "SELECT * FROM coursetable";
		return jdbcTemplate.query(sql, (rs,rowNum) -> new Course(rs.getInt("id"),rs.getString("name"), 
				rs.getString("description"),rs.getString("skills"),rs.getInt("trainerid")));
	}

	public int deleteCourse(int courseid) {
    	int id = jdbcTemplate.update("DELETE FROM coursetable WHERE id = ?", courseid);
		return jdbcTemplate.update("DELETE FROM coursetable WHERE id = ?", courseid);
		
	}

	public Course getCourseById(int id) {
		String sql = "SELECT * FROM coursetable WHERE id = ?";
		return jdbcTemplate.queryForObject(sql,(rs,rowNum) -> new Course(rs.getInt("id"),rs.getString("name"),
				rs.getString("description"),rs.getString("skills"),rs.getInt("trainerid")),id);
	}

	public int updateCourse(Course c) {
		String sql = "UPDATE coursetable SET name = ?, description = ?, skills = ? WHERE id = ?";
		return jdbcTemplate.update(sql, new Object[] {c.getName(),c.getDescription(),c.getSkills(),c.getId()});
	}

	public List<Course> searchCourseByName(String term) {
		term = "%"+term+"%";
		String sql = "SELECT * FROM coursetable WHERE name LIKE ?";
		return jdbcTemplate.query(sql, (rs,rowNum) -> new Course(rs.getInt("id"),rs.getString("name"), 
				rs.getString("description"),rs.getString("skills"),rs.getInt("trainerid")), term);
		
	}

	public int updateTimestamp(int cid, Timestamp time) {
		String sql = "UPDATE coursetable SET updatedtimestamp = ? WHERE id = ?";
		return jdbcTemplate.update(sql, new Object[] {time, cid});
	}

	public Timestamp getTimestamp(int cid) {
		String sql = "SELECT updatedtimestamp FROM coursetable WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, Timestamp.class, cid);
	}

	public List<String> getCourseNames() {
		String sql = "SELECT DISTINCT name FROM coursetable";
		return jdbcTemplate.queryForList(sql, String.class);
	}

	public List<Integer> getUserdata() {
		String sql = "select COUNT(uc.userid)  FROM coursetable c LEFT JOIN usercoursetable uc ON c.id = uc.courseid GROUP BY c.name";
		return jdbcTemplate.queryForList(sql, Integer.class);
	}

}
