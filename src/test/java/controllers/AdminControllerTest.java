package controllers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAO.*;
import entities.*;

public class AdminControllerTest {
	
	@Mock
	private static HttpServletRequest request;
	@Mock
	private static HttpSession session;
	@Mock
	private static HttpServletResponse response;
	@Mock
	private static ServletContext servletContext;
	@Mock
	private static RequestDispatcher requestDispatcher;
	private static MockedStatic<DAOFactory> daoFactory;
	@Mock
	private static CategoryDAO categoryDAO;
	@Mock
	private static OrderDAO orderDAO;
	@Mock
	private static ProducerDAO producerDAO;
	@Mock
	private static ProductDAO productDAO;
	@Mock
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void beforeClass() {
		daoFactory = Mockito.mockStatic(DAOFactory.class);
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		Mockito.when(request.getSession()).thenReturn(session);
		daoFactory.when(DAOFactory::getCategoryDAO).thenReturn(categoryDAO);
		daoFactory.when(DAOFactory::getOrderDAO).thenReturn(orderDAO);
		daoFactory.when(DAOFactory::getProducerDAO).thenReturn(producerDAO);
		daoFactory.when(DAOFactory::getProductDAO).thenReturn(productDAO);
		daoFactory.when(DAOFactory::getUserDAO).thenReturn(userDAO);
	}

	@Test
	public void fullBuildCatalogTest() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException, SQLException {
		String aim = "/view/jsp/admin_catalog.jsp";
		String sort = "testSort";
		String gender = "testGender";
		String[] producer = new String[] {"testProducer"};
		String[] category = new String[] {"testCategory"};
		String bot = "1";
		String top = "13";
		String page = "5";
		List<Product> products = new ArrayList<>();
		List<String> categories = new ArrayList<>();
		List<String> producers = new ArrayList<>();
		
		Mockito.when(request.getParameter("sort")).thenReturn(sort);
		Mockito.when(request.getParameter("gender")).thenReturn(gender);
		Mockito.when(request.getParameterValues("producer")).thenReturn(producer);
		Mockito.when(request.getParameterValues("category")).thenReturn(category);
		Mockito.when(request.getParameter("bot")).thenReturn(bot);
		Mockito.when(request.getParameter("top")).thenReturn(top);
		Mockito.when(request.getParameter("page")).thenReturn(page);
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		Mockito.when(productDAO.getFiltered(category, producer, gender, bot, top, sort, "20", "24")).thenReturn(products);
		Mockito.when(productDAO.count(category, producer, gender, bot, top)).thenReturn(7);
		
		Mockito.when(categoryDAO.getAll()).thenReturn(categories);
		Mockito.when(producerDAO.getAll()).thenReturn(producers);
		
		AdminController.buildCatalog(request, response, servletContext);
		
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("products", products);
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("categories", categories);
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("producers", producers);
		Mockito.verify(servletContext, Mockito.atLeastOnce()).getRequestDispatcher(aim);
	}
	
	@Test
	public void emptyBuildCatalogTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String aim = "/view/jsp/admin_catalog.jsp";
		String sort = null;
		String gender = null;
		String[] producer = null;
		String[] category = null;
		String bot = null;
		String top = null;
		String page = null;
		List<Product> products = new ArrayList<>();
		List<String> categories = new ArrayList<>();
		List<String> producers = new ArrayList<>();
		
		Mockito.when(request.getParameter("sort")).thenReturn(sort);
		Mockito.when(request.getParameter("gender")).thenReturn(gender);
		Mockito.when(request.getParameterValues("producer")).thenReturn(producer);
		Mockito.when(request.getParameterValues("category")).thenReturn(category);
		Mockito.when(request.getParameter("bot")).thenReturn(bot);
		Mockito.when(request.getParameter("top")).thenReturn(top);
		Mockito.when(request.getParameter("page")).thenReturn(page);
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		Mockito.when(productDAO.getFiltered(category, producer, gender, bot, top, sort, "20", "24")).thenReturn(products);
		Mockito.when(productDAO.count(category, producer, gender, bot, top)).thenReturn(7);
		
		Mockito.when(categoryDAO.getAll()).thenReturn(categories);
		Mockito.when(producerDAO.getAll()).thenReturn(producers);
		
		AdminController.buildCatalog(request, response, servletContext);
		
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("products", products);
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("categories", categories);
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("producers", producers);
		Mockito.verify(servletContext, Mockito.atLeastOnce()).getRequestDispatcher(aim);
	}
	
	@Test
	public void buildProductTest() throws SQLException, ServletException, IOException {
		String aim = "/view/jsp/admin_product.jsp";
		
		Mockito.when(request.getParameter("id")).thenReturn("1");
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		
		Product prod = new Product();
		Mockito.when(productDAO.get(1)).thenReturn(prod);
		
		AdminController.buildProduct(request, response, servletContext);
		
		Mockito.verify(request, Mockito.times(1)).setAttribute("product", prod);
		Mockito.verify(servletContext, Mockito.times(1)).getRequestDispatcher(aim);
	}
	
	@Test
	public void buildUsersTest() throws SQLException, ServletException, IOException {
		List<User> users = new ArrayList<>();
		String aim = "/view/jsp/admin_users.jsp";
		
		Mockito.when(userDAO.getAll()).thenReturn(users);
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		
		AdminController.buildUsers(request, response, servletContext);
		
		Mockito.verify(requestDispatcher).forward(request, response);
	}
	
	@Test
	public void buildOrdersTest() throws SQLException, ServletException, IOException {
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setCart(new ArrayList<>());
		order.getCart().add(1);
		order.setUserId(1);
		orders.add(order);
		String aim = "/view/jsp/admin_orders.jsp";
		
		Mockito.when(orderDAO.getAll()).thenReturn(orders);
		Mockito.when(productDAO.get(1)).thenReturn(new Product());
		Mockito.when(userDAO.get(1)).thenReturn(new User());
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		
		AdminController.buildOrders(request, response, servletContext);
		
		Mockito.verify(requestDispatcher).forward(request, response);
	}
	
	@Test
	public void updateProductTest() throws SQLException, ServletException, IOException {
		String aim = "http://localhost:8080/final/server/admin_catalog";
		
		Mockito.when(request.getParameter(Mockito.matches("[^photo]"))).thenReturn("3");
		
		AdminController.updateProduct(request, response);
		
		Mockito.verify(productDAO).update(Mockito.any(Product.class));
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void createProductTest() throws SQLException, ServletException, IOException {
		String aim = "http://localhost:8080/final/server/admin_catalog";
		
		Mockito.when(request.getParameter(Mockito.matches("[^photo]"))).thenReturn("3");
		
		AdminController.createProduct(request, response);
		
		Mockito.verify(productDAO).create(Mockito.any(Product.class));
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void deleteProductTest() throws SQLException, ServletException, IOException {
		String aim = "http://localhost:8080/final/server/admin_catalog";
		
		Mockito.when(request.getParameter("id")).thenReturn("3");
		
		AdminController.deleteProduct(request, response);
		
		Mockito.verify(productDAO).delete(3);
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void blockUserTest() throws SQLException, ServletException, IOException {
		String aim = "http://localhost:8080/final/server/admin_users";
		
		Mockito.when(request.getParameter("id")).thenReturn("3");
		Mockito.when(userDAO.isActive(3)).thenReturn(true);
		
		AdminController.blockUser(request, response);
		
		Mockito.verify(userDAO).setStatus(3, false);
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void adminUserTest() throws SQLException, ServletException, IOException {
		String aim = "http://localhost:8080/final/server/admin_users";
		
		Mockito.when(request.getParameter("id")).thenReturn("3");
		Mockito.when(userDAO.isAdmin(3)).thenReturn(false);
		
		AdminController.adminUser(request, response);
		
		Mockito.verify(userDAO).setAdmin(3, true);
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void deleteUserTest() throws SQLException, ServletException, IOException {
		String aim = "http://localhost:8080/final/server/admin_users";
		
		Mockito.when(request.getParameter("id")).thenReturn("3");
		
		AdminController.deleteUser(request, response);
		
		Mockito.verify(userDAO).delete(3);
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void setPaidTest() throws SQLException, ServletException, IOException {
		String aim = "http://localhost:8080/final/server/admin_orders";
		
		Mockito.when(request.getParameter("id")).thenReturn("3");
		
		AdminController.setPaid(request, response);
		
		Mockito.verify(orderDAO).setState(3, "paid");
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void rejectOrderTest() throws SQLException, ServletException, IOException {
		String aim = "http://localhost:8080/final/server/admin_orders";
		
		Mockito.when(request.getParameter("id")).thenReturn("3");
		
		AdminController.rejectOrder(request, response);
		
		Mockito.verify(orderDAO).setState(3, "rejected");
		Mockito.verify(response).sendRedirect(aim);
	}
}
