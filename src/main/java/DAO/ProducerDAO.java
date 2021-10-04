package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Naberezhniy Artur
 * 
 * Consists methods to manage producers in DB.
 */
public abstract class ProducerDAO extends DAO {

	/**
	 * @return List of producers names
	 * @throws SQLException
	 */
	public abstract List<String> getAll() throws SQLException;
	
	/**
	 * @param Producer name
	 * @return If producer was created.
	 * @throws SQLException
	 */
	public abstract boolean create(String name) throws SQLException;
	
	/**
	 * @param Producer name
	 * @return If deleted was created.
	 * @throws SQLException
	 */
	public abstract boolean delete(String name) throws SQLException;
}
