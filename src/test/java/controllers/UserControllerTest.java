package controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAO.*;
import entities.*;

public class UserControllerTest {
	
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
	private static UserDAO userDAO;
	@Mock
	private static OrderDAO orderDAO;
	
	@BeforeClass
	public static void beforeClass() {
		daoFactory = Mockito.mockStatic(DAOFactory.class);
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		Mockito.when(request.getSession()).thenReturn(session);
		daoFactory.when(DAOFactory::getUserDAO).thenReturn(userDAO);
		daoFactory.when(DAOFactory::getOrderDAO).thenReturn(orderDAO);
	}
	
	@Test
	public void signInUserNotFoundTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String email = "test@test";
		String password = "test";
		User user = new User();
		String aim = "http://localhost:8080/final/server/sign_in?error=wrong";
		
		Mockito.when(request.getParameter("email")).thenReturn(email);
		Mockito.when(request.getParameter("password")).thenReturn(password);
		Mockito.when(userDAO.get(email, password)).thenReturn(user);
		
		UserController.signIn(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signInUserNotActiveTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String email = "test@test";
		String password = "test";
		User user = new User();
		user.setId(1);
		user.setEmail(email);
		user.setPassword(password);
		user.setName("testName");
		user.setAddress("testAddress");
		user.setPhone("testPhone");
		user.setCard("testCard");
		user.setActive(false);
		user.setAdmin(false);
		String aim = "http://localhost:8080/final/server/sign_in?error=blocked";
		
		Mockito.when(request.getParameter("email")).thenReturn(email);
		Mockito.when(request.getParameter("password")).thenReturn(password);
		Mockito.when(userDAO.get(email, password)).thenReturn(user);
		
		UserController.signIn(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signInUserIsAdminTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String email = "test@test";
		String password = "test";
		User user = new User();
		user.setId(1);
		user.setEmail(email);
		user.setPassword(password);
		user.setName("testName");
		user.setAddress("testAddress");
		user.setPhone("testPhone");
		user.setCard("testCard");
		user.setActive(true);
		user.setAdmin(true);
		String aim = "http://localhost:8080/final/server/admin_catalog";
		
		Mockito.when(request.getParameter("email")).thenReturn(email);
		Mockito.when(request.getParameter("password")).thenReturn(password);
		Mockito.when(userDAO.get(email, password)).thenReturn(user);
		
		UserController.signIn(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signInBouthOrdersTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String email = "test@test";
		String password = "test";
		User user = new User();
		user.setId(1);
		user.setEmail(email);
		user.setPassword(password);
		user.setName("testName");
		user.setAddress("testAddress");
		user.setPhone("testPhone");
		user.setCard("testCard");
		user.setActive(true);
		user.setAdmin(false);
		Order sessionOrder = new Order();
		Order cloudOrder = new Order();
		String aim = "http://localhost:8080/final/server/order_conflict";
		
		Mockito.when(request.getParameter("email")).thenReturn(email);
		Mockito.when(request.getParameter("password")).thenReturn(password);
		Mockito.when(userDAO.get(email, password)).thenReturn(user);
		Mockito.when(session.getAttribute("order")).thenReturn(sessionOrder);
		Mockito.when(orderDAO.get(user)).thenReturn(cloudOrder);
		
		UserController.signIn(request, response);
		
		Mockito.verify(session).setAttribute("cloud_order", cloudOrder);
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signInSessionOrderTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String email = "test@test";
		String password = "test";
		User user = new User();
		user.setId(1);
		user.setEmail(email);
		user.setPassword(password);
		user.setName("testName");
		user.setAddress("testAddress");
		user.setPhone("testPhone");
		user.setCard("testCard");
		user.setActive(true);
		user.setAdmin(false);
		Order sessionOrder = new Order();
		Order newOrder = new Order();
		newOrder.setId(1);
		Order cloudOrder = null;
		String aim = "http://localhost:8080/final/server/profile";
		
		Mockito.when(request.getParameter("email")).thenReturn(email);
		Mockito.when(request.getParameter("password")).thenReturn(password);
		Mockito.when(userDAO.get(email, password)).thenReturn(user);
		Mockito.when(session.getAttribute("order")).thenReturn(sessionOrder);
		Mockito.when(orderDAO.get(user)).thenReturn(cloudOrder);
		Mockito.when(orderDAO.create(user.getId())).thenReturn(newOrder);
		
		UserController.signIn(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signInCloudOrderTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String email = "test@test";
		String password = "test";
		User user = new User();
		user.setId(1);
		user.setEmail(email);
		user.setPassword(password);
		user.setName("testName");
		user.setAddress("testAddress");
		user.setPhone("testPhone");
		user.setCard("testCard");
		user.setActive(true);
		user.setAdmin(false);
		Order sessionOrder = null;
		Order cloudOrder = new Order();
		String aim = "http://localhost:8080/final/server/profile";
		
		Mockito.when(request.getParameter("email")).thenReturn(email);
		Mockito.when(request.getParameter("password")).thenReturn(password);
		Mockito.when(userDAO.get(email, password)).thenReturn(user);
		Mockito.when(session.getAttribute("order")).thenReturn(sessionOrder);
		Mockito.when(orderDAO.get(user)).thenReturn(cloudOrder);
		
		UserController.signIn(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signInNoOrdersTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String email = "test@test";
		String password = "test";
		User user = new User();
		user.setId(1);
		user.setEmail(email);
		user.setPassword(password);
		user.setName("testName");
		user.setAddress("testAddress");
		user.setPhone("testPhone");
		user.setCard("testCard");
		user.setActive(true);
		user.setAdmin(false);
		Order sessionOrder = null;
		Order cloudOrder = null;
		String aim = "http://localhost:8080/final/server/profile";
		
		Mockito.when(request.getParameter("email")).thenReturn(email);
		Mockito.when(request.getParameter("password")).thenReturn(password);
		Mockito.when(userDAO.get(email, password)).thenReturn(user);
		Mockito.when(session.getAttribute("order")).thenReturn(sessionOrder);
		Mockito.when(orderDAO.get(user)).thenReturn(cloudOrder);
		
		UserController.signIn(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signUpTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String name = "testName";
		String password = "testPassword";
		String email = "testEmail";
		String phone = "testPhone";
		String address = "testAddress";
		String card = "testCard";
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);
		user.setCard(card);
		String aim = "http://localhost:8080/final/server/profile";
		
		Mockito.when(request.getParameter("name")).thenReturn(name);
		Mockito.when(request.getParameter("password")).thenReturn(password);
		Mockito.when(request.getParameter("email")).thenReturn(email);
		Mockito.when(request.getParameter("phone")).thenReturn(phone);
		Mockito.when(request.getParameter("address")).thenReturn(address);
		Mockito.when(request.getParameter("card")).thenReturn(card);
		Mockito.when(userDAO.create(Mockito.any(User.class))).thenReturn(true);
		
		UserController.signUp(request, response);
		
		Mockito.verify(session).setAttribute(Mockito.eq("user"), Mockito.any(User.class));
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signUpUserExsistTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String name = "testName";
		String password = "testPassword";
		String email = "testEmail";
		String phone = "testPhone";
		String address = "testAddress";
		String card = "testCard";
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);
		user.setCard(card);
		String aim = "http://localhost:8080/final/server/sign_up?error=exsist";
		
		Mockito.when(userDAO.create(user)).thenReturn(false);
		
		UserController.signUp(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void signOutTest() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException, SQLException {
		String aim = "http://localhost:8080/final/server/home";
		
		UserController.signOut(request, response);
		
		Mockito.verify(session).removeAttribute("user");
		Mockito.verify(session).removeAttribute("order");
		Mockito.verify(response).sendRedirect(aim);
	}
}
