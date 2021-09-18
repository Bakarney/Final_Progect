package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProducerDAOImpl extends ProducerDAO {
	
	public static final String GET_ALL =
			"SELECT name "
			+ "FROM producer";
	
	public static final String CREATE =
			"INSERT INTO producer (name) "
			+ "VALUES (?)";
	
	public static final String DELETE =
			"DELETE FROM producer "
			+ "WHERE name=?";
	
	private static ProducerDAOImpl instance;
	
	private ProducerDAOImpl() { }
	
	public static synchronized ProducerDAO getInstance() {
		if (instance == null)
			instance = new ProducerDAOImpl();
		return instance;
	}

	@Override
	public List<String> getAll() throws SQLException {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;
		List<String> list = new ArrayList<>();
		try {
			con = getConnection();
			stat = con.createStatement();
			res = stat.executeQuery(GET_ALL);
			while (res.next()) {
				list.add(res.getString(1));
			}
			return list;
		} catch (Exception e) {
			throw new SQLException("Con not getAllProducers", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public boolean create(String name) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(CREATE);
			stat.setString(1, name);
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			close(stat);
			close(con);
		}
	}

	@Override
	public boolean delete(String name) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(DELETE);
			stat.setString(1, name);
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			close(stat);
			close(con);
		}
	}
}
