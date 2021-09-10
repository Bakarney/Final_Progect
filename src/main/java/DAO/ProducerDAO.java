package DAO;

import java.sql.SQLException;
import java.util.List;

public abstract class ProducerDAO extends DAO {

	public abstract List<String> getAll() throws SQLException;
	
	public abstract boolean create(String name) throws SQLException;
	
	public abstract boolean delete(String name) throws SQLException;
}
