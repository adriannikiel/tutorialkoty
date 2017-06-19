package pl.kobietydokodu.cats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.cats.domain.Cat;

/**
 * The use of a database in Spring directly using JDBC.
 */
@Repository
public class JDBCCatDAO {

	/**
	 * DataSource which is defined in security-context.xml
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * Add cat to table 'cats'
	 */
	public void addCat(Cat kot) {
		String sql = "INSERT INTO cats VALUES(?,?,?,?,?)";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, kot.getId());
			ps.setString(2, kot.getName());
			java.util.Date utilBirthday = kot.getBirthday();
			ps.setDate(3, new java.sql.Date(utilBirthday.getTime()));
			ps.setFloat(4, kot.getWeight());
			ps.setString(5, kot.getGuardianName()); 

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	/**
	 * @return Return list of cats from table 'cats'
	 */
	public List<Cat> getCats() {
		List<Cat> cats = new ArrayList<Cat>();

		String sql = "SELECT * FROM cats";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			Cat cat = null;
			while (rs.next()) {
				cat = new Cat();
				cat.setId(rs.getLong("id"));
				cat.setName(rs.getString("name"));
				cat.setGuardianName(rs.getString("guardian_name"));
				cat.setWeight(rs.getFloat("weight"));
				cat.setBirthday(rs.getDate("birthday"));
				cats.add(cat);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return cats;
	}

	/**
	 * @param id Unique identification number of cat
	 * @return Cat with specific id number
	 */
	public Cat getCatById(Integer id) {

		String sql = "SELECT * FROM cats WHERE id = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			Cat cat = null;
			if (rs.next()) {
				cat = new Cat();
				cat.setId(rs.getLong("id"));
				cat.setName(rs.getString("name"));
				cat.setGuardianName(rs.getString("guardian_name"));
				cat.setWeight(rs.getFloat("weight"));
				cat.setBirthday(rs.getDate("birthday"));
			}
			rs.close();
			ps.close();
			
			return cat;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

}
