package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.*;

public class OrderDAOImpl extends OrderDAO {
	
	private static final String GET =
			"SELECT id "
			+ "FROM orders "
			+ "WHERE user_id=? AND orders.state=\"preparing\"";
	
	private static final String GET_BY_ID =
			"SELECT orders.id,orders.state,user_id,product_id "
			+ "FROM orders_products "
			+ "INNER JOIN orders ON orders.id=orders_products.order_id "
			+ "WHERE orders.id=?";
	
	private static final String GET_BY_USER =
			"SELECT orders.id,product_id "
			+ "FROM orders_products "
			+ "INNER JOIN orders ON orders.id=orders_products.order_id "
			+ "WHERE user_id=? AND orders.state=\"preparing\"";
	
	private static final String GET_ALL =
			"SELECT orders.id AS id,orders.state AS state,user_id,product_id "
			+ "FROM orders_products "
			+ "INNER JOIN orders ON orders.id=orders_products.order_id "
			+ "WHERE state!='preparing'";
	
	private static final String CREATE =
			"INSERT INTO orders (user_id) "
			+ "VALUES (?)";
	
	private static final String ADD =
			"INSERT INTO orders_products (order_id,product_id) "
			+ "VALUES (?,?)";
	
	private static final String REMOVE =
			"DELETE FROM orders_products "
			+ "WHERE order_id=? AND product_id=?";
	
	private static final String STATE =
			"UPDATE orders "
			+ "SET state=?"
			+ "WHERE id=?";
	
	private static final String DELETE =
			"DELETE FROM orders "
			+ "WHERE id=?";
	
	private static OrderDAOImpl instance;
	
	private OrderDAOImpl() { }
	
	public static synchronized OrderDAO getInstance() {
		if (instance == null)
			instance = new OrderDAOImpl();
		return instance;
	}

	@Override
	public Order get(int id) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		Order order = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(GET_BY_ID);
			stat.setInt(1, id);
			res = stat.executeQuery();
			if (res.next()) {
				order = new Order();
				order.setId(Integer.valueOf(res.getString("orders.id")));
				order.setUserId(Integer.valueOf(res.getString("user_id")));
				order.setState(res.getString("orders.state"));
				List<Integer> cart = new ArrayList<>();
				do {
					cart.add(Integer.valueOf(res.getString("product_id")));
				} while (res.next());
				order.setCart(cart);
			}
			return order;
		} catch (Exception e) {
			throw new SQLException("Con not getOrderById", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public Order get(User user) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		Order order = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(GET_BY_USER);
			stat.setInt(1, user.getId());
			res = stat.executeQuery();
			if (res.next()) {
				order = new Order();
				order.setId(Integer.valueOf(res.getString("orders.id")));
				List<Integer> cart = new ArrayList<>();
				do {
					cart.add(Integer.valueOf(res.getString("product_id")));
				} while (res.next());
				order.setCart(cart);
			}
			return order;
		} catch (Exception e) {
			throw new SQLException("Con not getOrderByUser", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public List<Order> getAll() throws SQLException {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;
		List<Order> orders = new ArrayList<>();
		try {
			con = getConnection();
			stat = con.createStatement();
			res = stat.executeQuery(GET_ALL);
			boolean flag = res.next();
			while (flag) {
				Order order = new Order();
				order.setId(Integer.valueOf(res.getString("id")));
				order.setUserId(Integer.valueOf(res.getString("user_id")));
				int id = order.getId();
				order.setState(res.getString("state"));
				List<Integer> cart = new ArrayList<>();
				do {
					cart.add(Integer.valueOf(res.getString("product_id")));
					flag = res.next();
				} while (flag && id == Integer.valueOf(res.getString("id")));
				order.setCart(cart);
				orders.add(order);
			}
			return orders;
		} catch (Exception e) {
			throw new SQLException("Can not getAllOrders", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public Order create(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		Order order = new Order();
		try {
			con = getConnection();
			stat = con.prepareStatement(CREATE);
			stat.setInt(1, userId);
			stat.executeUpdate();
			close(stat);
			stat = con.prepareStatement(GET);
			stat.setInt(1, userId);
			res = stat.executeQuery();
			if (res.next())
				order.setId(res.getInt("id"));
			order.setUserId(userId);
			order.setState("preparing");
			return order;
		} catch (Exception e) {
			throw new SQLException("Can not createOrder", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public boolean addProduct(int id, int product_id) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(ADD);
			stat.setInt(1, id);
			stat.setInt(2, product_id);
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new SQLException("Can not addOrder", e);
		} finally {
			close(stat);
			close(con);
		}
	}

	@Override
	public boolean removeProduct(int id, int product_id) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(REMOVE);
			stat.setInt(1, id);
			stat.setInt(2, product_id);
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new SQLException("Can not removeOrder", e);
		} finally {
			close(stat);
			close(con);
		}
	}

	@Override
	public boolean setState(int id, String state) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(STATE);
			stat.setString(1, state);
			stat.setInt(2, id);
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new SQLException("Can not setOrderState", e);
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
			throw new SQLException("Can not deleteOrder", e);
		} finally {
			close(stat);
			close(con);
		}
	}
}
