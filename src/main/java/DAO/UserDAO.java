package DAO;

import java.sql.SQLException;
import java.util.List;

import entities.User;

public abstract class UserDAO extends DAO {
	
	protected UserDAO() { }
	
	public abstract User get(String email, String password) throws SQLException;
	
	public abstract User get(String email) throws SQLException;
	
	public abstract User get(int id) throws SQLException;
	
	public abstract List<User> getAll() throws SQLException;
	
	public abstract boolean isAdmin(int id) throws SQLException;
	
	public abstract boolean isActive(int id) throws SQLException;
	
	public abstract boolean create(User user) throws SQLException;
	
	public abstract boolean setStatus(int id, boolean status) throws SQLException;
	
	public abstract boolean setAdmin(int id, boolean admin) throws SQLException;
	
	public abstract boolean delete(int id) throws SQLException;
}
