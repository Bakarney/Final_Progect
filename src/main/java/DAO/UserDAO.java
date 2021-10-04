package DAO;

import java.sql.SQLException;
import java.util.List;

import entities.User;

/**
 * @author Naberezhniy Artur
 * 
 * Consists methods to manage users in DB.
 */
public abstract class UserDAO extends DAO {
	
	/**
	 * @param email
	 * @param password
	 * @return User with this email and password or null if it's not found
	 * @throws SQLException
	 */
	public abstract User get(String email, String password) throws SQLException;
	
	/**
	 * @param email
	 * @return User with this email or null if it's not found
	 * @throws SQLException
	 */
	public abstract User get(String email) throws SQLException;
	
	/**
	 * @param id
	 * @return User with this id or null if it's not found
	 * @throws SQLException
	 */
	public abstract User get(int id) throws SQLException;
	
	/**
	 * @return List of all users
	 * @throws SQLException
	 */
	public abstract List<User> getAll() throws SQLException;
	
	/**
	 * @param User id
	 * @return If user admin
	 * @throws SQLException
	 */
	public abstract boolean isAdmin(int id) throws SQLException;
	
	/**
	 * @param User id
	 * @return If user active
	 * @throws SQLException
	 */
	public abstract boolean isActive(int id) throws SQLException;
	
	/**
	 * @param User
	 * @return If user was created
	 * @throws SQLException
	 */
	public abstract boolean create(User user) throws SQLException;
	
	/**
	 * @param User id
	 * @param New status
	 * @return If status was set
	 * @throws SQLException
	 */
	public abstract boolean setStatus(int id, boolean status) throws SQLException;
	
	/**
	 * @param User
	 * @param New admin
	 * @return If admin was set
	 * @throws SQLException
	 */
	public abstract boolean setAdmin(int id, boolean admin) throws SQLException;
	
	/**
	 * @param User id
	 * @return If user was deleted
	 * @throws SQLException
	 */
	public abstract boolean delete(int id) throws SQLException;
}
