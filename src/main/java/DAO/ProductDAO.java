package DAO;

import java.sql.SQLException;
import java.util.List;

import entities.Product;

/**
 * @author Naberezhniy Artur
 * 
 * Consists methods to manage products in DB.
 */
public abstract class ProductDAO extends DAO {
	
	/**
	 * @param Product id
	 * @return Product
	 * @throws SQLException
	 */
	public abstract Product get(int id) throws SQLException;
	
	/**
	 * @return List of all products
	 * @throws SQLException
	 */
	public abstract List<Product> getAll() throws SQLException;
	
	/**
	 * @param Order id
	 * @return List of orders products
	 * @throws SQLException
	 */
	public abstract List<Product> getOrders(int id) throws SQLException;
	
	/**
	 * @param categories
	 * @param producers
	 * @param gender
	 * @param bot
	 * @param top
	 * @param order
	 * @param start
	 * @param number
	 * @return List of filtered and limited products
	 * @throws SQLException
	 */
	public abstract List<Product> getFiltered(String[] categories, String[] producers, String gender, String bot, String top, String order, String start, String number) throws SQLException;
	
	/**
	 * @param categories
	 * @param producers
	 * @param gender
	 * @param bot
	 * @param top
	 * @return Number of filtered and limited products
	 * @throws SQLException
	 */
	public abstract int count(String[] categories, String[] producers, String gender, String bot, String top) throws SQLException;
	
	/**
	 * @param Product
	 * @return If product was created
	 * @throws SQLException
	 */
	public abstract boolean create(Product product) throws SQLException;
	
	/**
	 * @param Product
	 * @return If product was updated
	 * @throws SQLException
	 */
	public abstract boolean update(Product product) throws SQLException;
	
	/**
	 * @param Product
	 * @return If product was deleted
	 * @throws SQLException
	 */
	public abstract boolean delete(int id) throws SQLException;
}
