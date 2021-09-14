package DAO;

import java.sql.SQLException;
import java.util.List;

import entities.*;

public abstract class OrderDAO extends DAO {
	
	public abstract Order get(int id) throws SQLException;
	
	public abstract Order get(User user) throws SQLException;
	
	public abstract List<Order> getAll() throws SQLException;
	
	public abstract Order create(int userId) throws SQLException;
	
	public abstract boolean addProduct(int id, int product_id) throws SQLException;
	
	public abstract boolean removeProduct(int id, int product_id) throws SQLException;
	
	public abstract boolean setState(int id, String state) throws SQLException;
	
	public abstract boolean delete(int id) throws SQLException;
}