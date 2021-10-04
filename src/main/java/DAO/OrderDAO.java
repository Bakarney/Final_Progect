package DAO;

import java.sql.SQLException;
import java.util.List;

import entities.*;

/**
 * @author Naberezhniy Artur
 * 
 * Consists methods to manage orders in DB.
 */
public abstract class OrderDAO extends DAO {
	
	/**
	 * @param Order id
	 * @return Order
	 * @throws SQLException
	 */
	public abstract Order get(int id) throws SQLException;
	
	/**
	 * @param User (needs only id)
	 * @return Order
	 * @throws SQLException
	 */
	public abstract Order get(User user) throws SQLException;
	
	/**
	 * @return List
	 * @throws SQLException
	 */
	public abstract List<Order> getAll() throws SQLException;
	
	/**
	 * @param User id
	 * @return Order
	 * @throws SQLException
	 */
	public abstract Order create(int userId) throws SQLException;
	
	/**
	 * @param Order id
	 * @param Product id
	 * @return If product was added
	 * @throws SQLException
	 */
	public abstract boolean addProduct(int id, int product_id) throws SQLException;
	
	/**
	 * @param Order id
	 * @param Product id
	 * @return If product was removed
	 * @throws SQLException
	 */
	public abstract boolean removeProduct(int id, int product_id) throws SQLException;
	
	/**
	 * @param Order id
	 * @param State
	 * @return If state was set
	 * @throws SQLException
	 */
	public abstract boolean setState(int id, String state) throws SQLException;
	
	/**
	 * @param Order id
	 * @return If order was deleted
	 * @throws SQLException
	 */
	public abstract boolean delete(int id) throws SQLException;
}