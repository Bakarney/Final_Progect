package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.User;

public class UserDAOImpl extends UserDAO {
	
	public static final String GET = 
			"SELECT id,name,email,phone,addres,card "
			+ "FROM users "
			+ "WHERE email=? AND users.password=?";
	
	public static final String GET_BY_EMAIL = 
			"SELECT id,name,email,phone,addres,card,admin,status "
			+ "FROM users "
			+ "WHERE email=?";
	
	public static final String GET_ALL =
			"SELECT id,name,email,phone,addres,card,admin,status "
			+ "FROM users";
	
	public static final String IS_ADMIN =
			"SELECT admin "
			+ "FROM users "
			+ "WHERE id=?";
	
	public static final String ADMIN = 
			"UPDATE users "
			+ "SET admin=? "
			+ "WHERE id=?";
	
	public static final String IS_ACTIVE =
			"SELECT status "
			+ "FROM users "
			+ "WHERE id=?";
	
	public static final String ACTIVE = 
			"UPDATE users "
			+ "SET status=? "
			+ "WHERE id=?";
	
	public static final String CREATE =
			"INSERT INTO users (name,users.password,email,phone,addres,card) "
			+ "VALUES (?,?,?,?,?,?)";
	
	public static final String DELETE =
			"DELETE FROM users "
			+ "WHERE id=?";
	
	protected UserDAOImpl() { }

	@Override
	public User get(String email, String password) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		User user = null;
		try {
			user = new User();
			con = getConnection();
			stat = con.prepareStatement(GET);
			stat.setString(1, email);
			stat.setString(2, password);
			res = stat.executeQuery();
			if (res.next()) {
				user.setId(res.getInt("id"));
				user.setName(res.getString("name"));
				user.setEmail(res.getString("email"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("addres"));
				user.setCard(res.getString("card"));
			}
			return user;
		} catch (Exception e) {
			throw new SQLException("Can not getUser", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public User get(String email) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		User user = null;
		try {
			user = new User();
			con = getConnection();
			stat = con.prepareStatement(GET_BY_EMAIL);
			stat.setString(1, email);
			res = stat.executeQuery();
			if (res.next()) {
				user.setName(res.getString("name"));
				user.setEmail(res.getString("email"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("addres"));
				user.setCard(res.getString("card"));
				user.setAdmin(res.getBoolean("admin"));
				user.setActive(res.getBoolean("status"));
			}
			return user;
		} catch (Exception e) {
			throw new SQLException("Can not getUserByEmail", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public List<User> getAll() throws SQLException {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;
		try {
			List<User> users = new ArrayList<>();
			con = getConnection();
			stat = con.createStatement();
			res = stat.executeQuery(GET_ALL);
			User user;
			while (res.next()) {
				user = new User();
				user.setId(res.getInt("id"));
				user.setName(res.getString("name"));
				user.setEmail(res.getString("email"));
				user.setPhone(res.getString("phone"));
				user.setAddress(res.getString("addres"));
				user.setCard(res.getString("card"));
				user.setAdmin(res.getBoolean("admin"));
				user.setActive(res.getBoolean("status"));
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			throw new SQLException("Can not getAllUsers", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public boolean isAdmin(int id) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(IS_ADMIN);
			stat.setInt(1, id);
			res = stat.executeQuery();
			if (res.next())
				return res.getBoolean(1);
		} catch (Exception e) {
			throw new SQLException("Can not IsUserAdmin", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
		return false;
	}

	@Override
	public boolean isActive(int id) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(IS_ACTIVE);
			stat.setInt(1, id);
			res = stat.executeQuery();
			if (res.next())
				return res.getBoolean(1);
		} catch (Exception e) {
			throw new SQLException("Can not IsUserActive", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
		return false;
	}

	@Override
	public boolean setStatus(int id, boolean status) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(ACTIVE);
			stat.setBoolean(1, status);
			stat.setInt(2, id);
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new SQLException("Con not setUserStatus", e);
		} finally {
			close(stat);
			close(con);
		}
	}

	@Override
	public boolean setAdmin(int id, boolean admin) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(ADMIN);
			stat.setBoolean(1, admin);
			stat.setInt(2, id);
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new SQLException("Con not setUserAdmin", e);
		} finally {
			close(stat);
			close(con);
		}
	}

	@Override
	public boolean create(User user) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(CREATE);
			int k = 0;
			stat.setString(++k, user.getName());
			stat.setString(++k, user.getPassword());
			stat.setString(++k, user.getEmail());
			stat.setString(++k, user.getPhone());
			stat.setString(++k, user.getAddress());
			stat.setString(++k, user.getCard());
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
	public boolean delete(int id) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(DELETE);
			stat.setInt(1, id);
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new SQLException("Can not deleteUser", e);
		} finally {
			close(stat);
			close(con);
		}
	}
}
