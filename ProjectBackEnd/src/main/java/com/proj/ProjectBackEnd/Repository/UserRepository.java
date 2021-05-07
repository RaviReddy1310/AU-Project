package com.proj.ProjectBackEnd.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proj.ProjectBackEnd.Model.Course;
import com.proj.ProjectBackEnd.Model.User;

@Repository
public class UserRepository implements IUserRepository {

	 @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Override
	public int save(User user) {
		return jdbcTemplate.update("INSERT INTO usertable (username,email,role) VALUES (?,?,?)",
                user.getUsername(),user.getEmail(),user.getRole());
	}

	@Override
	public Iterable<User> findAll() {
		return jdbcTemplate.query("select * from usertable", (rs, rowNum) -> 
		new User(rs.getInt("id"),rs.getString("username"), rs.getString("email"),rs.getString("role")));
	}
	
	@Override
	public User findByemail(String email) {
		String sql = "SELECT * FROM usertable WHERE email = ?";
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(rs.getInt("id"), 
				rs.getString("username"), rs.getString("email"),rs.getString("role")),email);
	}
	
	@Override
	public int addCourseToStudy(int uid, int cid) {
		String sql = "SELECT COUNT(*) FROM usercoursetable WHERE userid = ? AND courseid = ?";
		if(jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{uid,cid}) == 0) {
			return jdbcTemplate.update("INSERT INTO usercoursetable (userid, courseid) VALUES(?,?)", uid, cid);
		} else {
			return 1;
		}
	}

	public int istrainer(int userid) {
		String sql = "SELECT COUNT(*) FROM coursetable WHERE trainerid = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, userid);
	}

	public List<Course> myCourses(int id) {
		String sql = "SELECT c.* FROM coursetable c JOIN usercoursetable uc ON uc.courseid = c.id AND uc.userid = ?";
		return (List<Course>) jdbcTemplate.query(sql, (rs, rowNum) -> 
		new Course(rs.getInt("id"),rs.getString("name"), rs.getString("description"),rs.getString("skills"),rs.getInt("trainerid")),id);
	}

	public int dropCourse(int userid, int courseid) {
		String sql = "DELETE FROM usercoursetable WHERE userid = ? AND courseid = ?";
		return jdbcTemplate.update(sql, new Object[] {userid,courseid});
	}

	public List<Course> trainerCourses(int id) {
		String sql = "SELECT * FROM coursetable WHERE trainerid = ?";
		return (List<Course>) jdbcTemplate.query(sql, (rs, rowNum) -> 
		new Course(rs.getInt("id"),rs.getString("name"), rs.getString("description"),rs.getString("skills"),rs.getInt("trainerid")),id);
	}

	public String getEmailById(int uid) {
		String sql = "SELECT email FROM usertable WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, String.class, uid);
	}

	public Integer isUser(String mail) {
		String sql = "SELECT COUNT(*) FROM usertable WHERE email = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, mail);
	}

}
