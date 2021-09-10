package DAO;

public class DAOFactory {
	
	private static UserDAO user;
	private static ProductDAO product;
	private static CategoryDAO category;
	private static ProducerDAO producer;
	private static OrderDAO order;
	
	public synchronized static UserDAO getUserDAO() {
		if (user == null)
			user = new UserDAOImpl();
		return user;
	}
	
	public synchronized static ProductDAO getProductDAO() {
		if (product == null)
			product = new ProductDAOImpl();
		return product;
	}
	
	public synchronized static CategoryDAO getCategoryDAO() {
		if (category == null)
			category = new CategoryDAOImpl();
		return category;
	}
	
	public synchronized static ProducerDAO getProducerDAO() {
		if (producer == null)
			producer = new ProducerDAOImpl();
		return producer;
	}
	
	public synchronized static OrderDAO getOrderDAO() {
		if (order == null)
			order = new OrderDAOImpl();
		return order;
	}
}
