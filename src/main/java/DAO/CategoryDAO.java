package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Naberezhniy Artur
 * 
 * Consists methods to manage categories in DB.
 */
public abstract class CategoryDAO extends DAO {
	
	/**
	 * @return List of categories names
	 * @throws SQLException
	 */
	public abstract List<String> getAll() throws SQLException;
	
	/**
	 * @param Category name
	 * @return If category was created.
	 * @throws SQLException
	 */
	public abstract boolean create(String name) throws SQLException;
	
	/**
	 * @param Category name
	 * @return If category was deleted.
	 * @throws SQLException
	 */
	public abstract boolean delete(String name) throws SQLException;
}
