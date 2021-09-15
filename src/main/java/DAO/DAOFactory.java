package DAO;

public class DAOFactory {
	
	public synchronized static UserDAO getUserDAO() {
		return UserDAOImpl.getInstance();
	}
	
	public synchronized static ProductDAO getProductDAO() {
		return ProductDAOImpl.getInstance();
	}
	
	public synchronized static CategoryDAO getCategoryDAO() {
		return CategoryDAOImpl.getInstance();
	}
	
	public synchronized static ProducerDAO getProducerDAO() {
		return ProducerDAOImpl.getInstance();
	}
	
	public synchronized static OrderDAO getOrderDAO() {
		return OrderDAOImpl.getInstance();
	}
}
