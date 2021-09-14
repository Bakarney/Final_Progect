package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class ProductDAOImpl extends ProductDAO {
	
	public static final String GET = 
			"SELECT products.id AS id,products.name,description,producer.name AS producer,categories.name AS category,gender,number,price,photo "
			+ "FROM products "
			+ "INNER JOIN categories ON categories.id=products.category_id "
			+ "INNER JOIN producer ON products.producer_id=producer.id "
			+ "WHERE products.id=?";
	
	public static final String GET_ALL = 
			"SELECT products.name,categories.name AS category,number,price,photo "
			+ "FROM products,categories "
			+ "WHERE category_id=categories.id";
	
	public static final String GET_FILTERED = 
			"SELECT products.id,products.name,categories.name AS category,number,price,photo,producer.name AS producer,gender "
			+ "FROM products "
			+ "INNER JOIN categories ON products.category_id=categories.id "
			+ "INNER JOIN producer ON products.producer_id=producer.id \n";
	
	public static final String GET_ORDERS = 
			"SELECT name,price "
			+ "FROM products,orders,orders_products "
			+ "WHERE products.id=product_id AND orders.id=order_id AND orders.id=?";
	
	public static final String CREATE =
			"INSERT INTO products (name,category_id,description,number,price,photo,producer_id,gender) "
			+ "VALUES (?,(SELECT id FROM categories WHERE categories.name=?),?,?,?,?,(SELECT id FROM producer WHERE producer.name=?),?)";
	
	public static final String UPDATE =
			"UPDATE products "
			+ "SET name=?, "
			+ "category_id=(SELECT id FROM categories WHERE name=?), "
			+ "description=?, "
			+ "number=?, "
			+ "price=?, "
			+ "photo=?, "
			+ "producer_id=(SELECT id FROM producer WHERE name=?), "
			+ "gender=? "
			+ "WHERE id=?";
	
	public static final String COUNT =
			"SELECT COUNT(products.id) "
			+ "FROM products "
			+ "INNER JOIN categories ON products.category_id=categories.id "
			+ "INNER JOIN producer ON products.producer_id=producer.id \n";
	
	public static final String DELETE =
			"DELETE FROM products "
			+ "WHERE id=?";
	
	protected ProductDAOImpl() {
	}

	@Override
	public Product get(int id) throws SQLException {
		Product prod = new Product();
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(GET);
			stat.setInt(1, id);
			res = stat.executeQuery();
			if (res.next()) {
				prod.setId(res.getInt("id"));
				prod.setName(res.getString("name"));
				prod.setPrice(res.getFloat("price"));
				prod.setCategory(res.getString("category"));
				prod.setProducer(res.getString("producer"));
				prod.setGender(res.getString("gender"));
				prod.setNumber(res.getInt("number"));
				prod.setPhoto(res.getString("photo"));
				prod.setDescription(res.getString("description"));
			}
			return prod;
		} catch (Exception e) {
			throw new SQLException("Can not getProducts", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public List<Product> getAll() throws SQLException {
		List<Product> list = new ArrayList<>();
		Connection con = null;
		Statement statment = null;
		ResultSet res = null;
		try {
			con = getConnection();
			statment = con.createStatement();
			res = statment.executeQuery(GET_ALL);
			while(res.next()) {
				Product prod = new Product();
				prod.setName(res.getString("name"));
				prod.setPrice(res.getFloat("price"));
				prod.setCategory(res.getString("category"));
				prod.setNumber(res.getInt("number"));
				prod.setPhoto(res.getString("photo"));
				list.add(prod);
			}
			return list;
		} catch (Exception e) {
			throw new SQLException("Can not getAllProducts", e);
		} finally {
			close(res);
			close(statment);
			close(con);
		}
	}

	@Override
	public List<Product> getOrders(int id) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		List<Product> list = new ArrayList<>();
		try {
			con = getConnection();
			stat = con.prepareStatement(GET_ORDERS);
			stat.setInt(1, id);
			res = stat.executeQuery();
			while(res.next()) {
				Product prod = new Product();
				prod.setName(res.getString("name"));
				prod.setPrice(res.getFloat("price"));
				prod.setPhoto(res.getString("photo"));
				list.add(prod);
			}
			return list;
		} catch (Exception e) {
			throw new SQLException("Can not getOrdersProducts", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	@Override
	public List<Product> getFiltered(String[] categories, String[] producers, String gender, String bot, String top, String order, String start, String number) throws SQLException {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;
		List<Product> list = new ArrayList<>();
		
		try {
			con = getConnection();
			stat = con.createStatement();
			res = stat.executeQuery(buildQuery(categories, producers, gender, bot, top, order, start, number));
			while (res.next()) {
				Product prod = new Product();
				prod.setId(res.getInt("products.id"));
				prod.setName(res.getString("name"));
				prod.setPrice(res.getFloat("price"));
				prod.setCategory(res.getString("category"));
				prod.setGender(res.getString("gender"));
				prod.setProducer(res.getString("producer"));
				prod.setNumber(res.getInt("number"));
				prod.setPhoto(res.getString("photo"));
				list.add(prod);
			}
			return list;
		} catch (Exception e) {
			throw new SQLException("Can not getFilteredProducts", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}
	
	@Override
	public int count(String[] categories, String[] producers, String gender, String bot, String top) throws SQLException {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;
		
		try {
			con = getConnection();
			stat = con.createStatement();
			res = stat.executeQuery(COUNT + buildWhere(categories, producers, gender, bot, top));
			if (res.next())
				return res.getInt(1);
			return 0;
		} catch (Exception e) {
			throw new SQLException("Can not getFilteredProducts", e);
		} finally {
			close(res);
			close(stat);
			close(con);
		}
	}

	private static String buildQuery(String[] categories, String[] producers, String gender, String bot, String top, String order, String start, String number) {
        return GET_FILTERED + buildWhere(categories, producers, gender, bot, top) + buildOrder(order) + buildLimit(start, number);
    }

    private static String buildWhere(String[] categories, String[] producers, String gender, String bot, String top) {
        StringBuilder query = new StringBuilder();
        boolean category = categories != null && categories.length != 0;
        boolean producer = producers != null && producers.length != 0;
        boolean sex = gender != null && !gender.isEmpty() && !gender.equals("unisex");
        boolean price = bot != null && top != null;

        if (!category && !producer && !sex && !price)
            return "";

        query.append("WHERE ");

        if (category) {
            query.append("(");
            for (int i = 0; i < categories.length; i++) {
                if (i != 0)
                    query.append(" OR ");
                query.append("categories.name=").append("'").append(categories[i]).append("'");
            }
            query.append(")");
        }

        if (producer) {
            if (category)
                query.append(" AND ");
            query.append("(");
            for (int i = 0; i < producers.length; i++) {
                if (i != 0)
                    query.append(" OR ");
                query.append("producer.name='").append(producers[i]).append("'");
            }
            query.append(")");
        }

        if (sex) {
            if (category || producer)
                query.append(" AND ");
            switch (gender) {
                case "male":
                    query.append("(gender='male' OR gender='unisex')");
                    break;
                case "female":
                    query.append("(gender='female' OR gender='unisex')");
                    break;
                default:
                    break;
            }
        }

        if (price) {
            if (category || producer || sex)
                query.append(" AND ");
            query.append("price>=").append(bot);
            query.append(" AND price<=").append(top);
        }

        query.append(" \n");
        return query.toString();
    }

    private static String buildOrder(String order) {
        StringBuilder query = new StringBuilder();

        if (order != null && !order.isEmpty()) {
            query.append("ORDER BY ");
            switch (order) {
                case "a-z":
                    query.append("name ASC");
                    break;
                case "z-a":
                    query.append("name DESC");
                    break;
                case "l-h":
                    query.append("price ASC");
                    break;
                case "h-l":
                    query.append("price DESC");
                    break;
                case "new":
                    query.append("id ASC");
                    break;
                case "old":
                    query.append("id DESC");
                    break;
                default:
                    break;
            }
            query.append("\n");
        }
        return query.toString();
    }
    
    private static String buildLimit(String start, String number) {
    	if (start == null || number == null)
    		return "";
    	return "LIMIT " + start + "," + number;
    }

	@Override
	public boolean create(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(CREATE);
			int k = 0;
			stat.setString(++k, product.getName());
			stat.setString(++k, product.getCategory());
			stat.setString(++k, product.getDescription());
			stat.setInt(++k, product.getNumber());
			stat.setFloat(++k, product.getPrice());
			stat.setString(++k, product.getPhoto());
			stat.setString(++k, product.getProducer());
			stat.setString(++k, product.getGender());
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(UPDATE);
			int k = 0;
			stat.setString(++k, product.getName());
			stat.setString(++k, product.getCategory());
			stat.setString(++k, product.getDescription());
			stat.setInt(++k, product.getNumber());
			stat.setFloat(++k, product.getPrice());
			stat.setString(++k, product.getPhoto());
			stat.setString(++k, product.getProducer());
			stat.setString(++k, product.getGender());
			stat.setInt(++k, product.getId());
			stat.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new SQLException("Can not updateProduct", e);
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
			throw new SQLException("Can not deleteProduct", e);
		} finally {
			close(stat);
			close(con);
		}
	}
	
}
