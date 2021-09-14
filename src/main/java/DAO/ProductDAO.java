package DAO;

import java.sql.SQLException;
import java.util.List;

import entities.Product;

public abstract class ProductDAO extends DAO {
	
	protected ProductDAO() { }
	
	public abstract Product get(int id) throws SQLException;
	
	public abstract List<Product> getAll() throws SQLException;
	
	public abstract List<Product> getOrders(int id) throws SQLException;
	
	public abstract List<Product> getFiltered(String[] categories, String[] producers, String gender, String bot, String top, String order, String start, String number) throws SQLException;
	
	public abstract int count(String[] categories, String[] producers, String gender, String bot, String top) throws SQLException;
	
	public abstract boolean create(Product product) throws SQLException;
	
	public abstract boolean update(Product product) throws SQLException;
	
	public abstract boolean delete(int id) throws SQLException;
}
