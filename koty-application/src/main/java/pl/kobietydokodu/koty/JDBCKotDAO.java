package pl.kobietydokodu.koty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.koty.domain.Kot;

@Repository
public class JDBCKotDAO {

	@Autowired
	private DataSource dataSource;

	public void dodajKota(Kot kot) {
		String sql = "INSERT INTO koty VALUES(?,?,?,?,?)";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, kot.getId());
			ps.setString(2, kot.getImie());
			java.util.Date utilDataUrodzenia = kot.getDataUrodzenia();
			ps.setDate(3, new java.sql.Date(utilDataUrodzenia.getTime()));
			ps.setFloat(4, kot.getWaga());
			ps.setString(5, kot.getImieOpiekuna()); 

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

	public List<Kot> getKoty() {
		List<Kot> koty = new ArrayList<Kot>();

		String sql = "SELECT * FROM koty";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			Kot kot = null;
			while (rs.next()) {
				kot = new Kot();
				kot.setId(rs.getInt("id"));
				kot.setImie(rs.getString("imie"));
				kot.setImieOpiekuna(rs.getString("imieOpiekuna"));
				kot.setWaga(rs.getFloat("waga"));
				kot.setDataUrodzenia(rs.getDate("dataUrodzenia"));
				koty.add(kot);
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

		return koty;
	}

	public Kot getKotById(Integer id) {

		String sql = "SELECT * FROM koty WHERE id = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			Kot kot = null;
			if (rs.next()) {
				kot = new Kot();
				kot.setId(rs.getInt("id"));
				kot.setImie(rs.getString("imie"));
				kot.setImieOpiekuna(rs.getString("imieOpiekuna"));
				kot.setWaga(rs.getFloat("waga"));
				kot.setDataUrodzenia(rs.getDate("dataUrodzenia"));
			}
			rs.close();
			ps.close();
			
			return kot;

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
