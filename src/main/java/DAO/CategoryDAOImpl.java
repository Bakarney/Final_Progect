package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends CategoryDAO {
	
	public static final String GET_ALL =
			"SELECT name "
			+ "FROM categories";
	
	public static final String CREATE =
			"INSERT INTO categories (name) "
			+ "VALUES (?)";
	
	public static final String DELETE =
			"DELETE FROM producer "
			+ "WHERE name=?";
	
	private static CategoryDAOImpl instance;
	
	private CategoryDAOImpl() { }
	
	public static synchronized CategoryDAO getInstance() {
		if (instance == null)
			instance = new CategoryDAOImpl();
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
			throw new SQLException("Con not getAllCategoryies", e);
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
		// TODO Auto-generated method stub
		return false;
	}
}
