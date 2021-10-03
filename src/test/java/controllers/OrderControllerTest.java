package controllers;

import java.io.IOException;
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
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAO.*;
import entities.*;

public class OrderControllerTest {
	
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
	private static ProductDAO productDAO;
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
		daoFactory.when(DAOFactory::getProductDAO).thenReturn(productDAO);
		daoFactory.when(DAOFactory::getOrderDAO).thenReturn(orderDAO);
	}

	@Test
	public void buildIfUserTest() throws SQLException, ServletException, IOException {
		User user = new User();
		Order order = new Order();
		order.setCart(new ArrayList<>());
		order.getCart().add(1);
		String aim = "/view/jsp/cart.jsp";
		
		Mockito.when(session.getAttribute("user")).thenReturn(user);
		Mockito.when(orderDAO.get(user)).thenReturn(order);
		for (Integer prod : order.getCart()) 
			Mockito.when(productDAO.get(prod)).thenReturn(new Product());
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		
		OrderController.build(request, response, servletContext);
		
		Mockito.verify(request).setAttribute(Mockito.eq("products"), Mockito.any(List.class));
		Mockito.verify(requestDispatcher).forward(request, response);
	}
	
	@Test
	public void buildIfNoUserButSessionIsOrderTest() throws SQLException, ServletException, IOException {
		User user = null;
		Order order = new Order();
		order.setCart(new ArrayList<>());
		order.getCart().add(1);
		String aim = "/view/jsp/cart.jsp";
		
		Mockito.when(session.getAttribute("user")).thenReturn(user);
		Mockito.when(session.getAttribute("order")).thenReturn(order);
		for (Integer prod : order.getCart()) 
			Mockito.when(productDAO.get(prod)).thenReturn(new Product());
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		
		OrderController.build(request, response, servletContext);
		
		Mockito.verify(request).setAttribute(Mockito.eq("products"), Mockito.any(List.class));
		Mockito.verify(requestDispatcher).forward(request, response);
	}
	
	@Test
	public void buildIfNoUserAndNoOrderTest() throws SQLException, ServletException, IOException {
		User user = null;
		Order order = null;
		String aim = "/view/jsp/cart.jsp";
		
		Mockito.when(session.getAttribute("user")).thenReturn(user);
		Mockito.when(session.getAttribute("order")).thenReturn(order);
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		
		OrderController.build(request, response, servletContext);
		
		Mockito.verify(request).setAttribute(Mockito.eq("products"), Mockito.any(List.class));
		Mockito.verify(requestDispatcher).forward(request, response);
	}
	
	@Test
	public void addProductWithoutUserTest() throws IOException, SQLException {
		User user = null;
		Order order = new Order();
		order.setCart(new ArrayList<>());
		String productId = "1"; 
		String aim = "http://localhost:8080/final/server/product?id=" + productId;
		
		Mockito.when(request.getParameter("product_id")).thenReturn(productId);
		Mockito.when(session.getAttribute("user")).thenReturn(user);
		Mockito.when(session.getAttribute("order")).thenReturn(order);
		
		OrderController.addProduct(request, response);
		
		Mockito.verify(session).setAttribute(Mockito.eq("order"), Mockito.any(Order.class));
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void addProductWithUserTest() throws IOException, SQLException {
		User user = new User();
		Order order = new Order();
		order.setCart(new ArrayList<>());
		String productId = "1"; 
		String aim = "http://localhost:8080/final/server/product?id=" + productId;
		
		Mockito.when(request.getParameter("product_id")).thenReturn(productId);
		Mockito.when(session.getAttribute("user")).thenReturn(user);
		Mockito.when(orderDAO.get(user)).thenReturn(order);
		
		OrderController.addProduct(request, response);
		
		Mockito.verify(session).setAttribute(Mockito.eq("order"), Mockito.any(Order.class));
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void removeProductWithoutDBTest() throws IOException, SQLException {
		Order order = new Order();
		order.setId(1);
		order.setCart(new ArrayList<>());
		order.getCart().add(3);
		String productId = "1"; 
		String aim = "http://localhost:8080/final/server/product?id=" + productId;
		
		Mockito.when(request.getParameter("product_id")).thenReturn(productId);
		Mockito.when(session.getAttribute("order")).thenReturn(order);
		Mockito.when(orderDAO.get(1)).thenReturn(order);
		
		OrderController.addProduct(request, response);
		
		Mockito.verify(session).setAttribute(Mockito.eq("order"), Mockito.any(Order.class));
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void removeProductWithDBTest() throws IOException, SQLException {
		Order order = new Order();
		order.setCart(new ArrayList<>());
		order.getCart().add(3);
		String productId = "3"; 
		String aim = "http://localhost:8080/final/server/product?id=" + productId;
		
		Mockito.when(request.getParameter("product_id")).thenReturn(productId);
		Mockito.when(session.getAttribute("order")).thenReturn(order);
		
		OrderController.addProduct(request, response);
		
		Mockito.verify(session).setAttribute(Mockito.eq("order"), Mockito.any(Order.class));
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void confirmOrderIfNoUserTest() throws IOException, SQLException {
		String orderId = "1";
		User user = null;
		String aim = "http://localhost:8080/final/server/sign_in";
		
		Mockito.when(request.getParameter("order_id")).thenReturn(orderId);
		Mockito.when(session.getAttribute("user")).thenReturn(user);
		
		OrderController.confirmOrder(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void confirmOrderIfUserTest() throws IOException, SQLException {
		String orderId = "1";
		User user = new User();
		String aim = "http://localhost:8080/final/server/home";
		
		Mockito.when(request.getParameter("order_id")).thenReturn(orderId);
		Mockito.when(session.getAttribute("user")).thenReturn(user);
		
		OrderController.confirmOrder(request, response);
		
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void orderConflictTest() throws IOException, SQLException, ServletException {
		Order order = new Order();
		order.setCart(new ArrayList<>());
		order.getCart().add(1);
		String aim = "/view/jsp/order_conflict.jsp";
		
		Mockito.when(request.getParameter("order_id")).thenReturn("1");
		Mockito.when(session.getAttribute("order")).thenReturn(order);
		Mockito.when(session.getAttribute("cloud_order")).thenReturn(order);
		Mockito.when(productDAO.get(0)).thenReturn(new Product());
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		
		OrderController.orderConflict(request, response, servletContext);
		
		Mockito.verify(request).setAttribute(Mockito.eq("local_products"), Mockito.anyList());
		Mockito.verify(request).setAttribute(Mockito.eq("cloud_products"), Mockito.anyList());
		Mockito.verify(requestDispatcher).forward(request, response);
	}
	
	@Test
	public void manageCartsLocalTest() throws IOException, SQLException {
		User user = new User();
		user.setId(1);
		Order localOrder = new Order();
		localOrder.setCart(new ArrayList<>());
		Order cloudOrder = new Order();
		cloudOrder.setId(1);
		String aim = "http://localhost:8080/final/server/profile";
		
		Mockito.when(request.getParameter("command")).thenReturn("local");
		Mockito.when(session.getAttribute("user")).thenReturn(user);
		Mockito.when(session.getAttribute("order")).thenReturn(localOrder);
		Mockito.when(session.getAttribute("cloud_order")).thenReturn(cloudOrder);
		Mockito.when(orderDAO.create(user.getId())).thenReturn(new Order());
		
		OrderController.manageCarts(request, response);
		
		Mockito.verify(orderDAO).delete(cloudOrder.getId());
		Mockito.verify(orderDAO).create(user.getId());
		Mockito.verify(session).setAttribute(Mockito.eq("order"), Mockito.any(Order.class));
		Mockito.verify(session).removeAttribute("cloud_order");
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void manageCartsCloudTest() throws IOException, SQLException {
		String aim = "http://localhost:8080/final/server/profile";
		
		Mockito.when(request.getParameter("command")).thenReturn("cloud");
		
		OrderController.manageCarts(request, response);
		
		Mockito.verify(session).removeAttribute("cloud_order");
		Mockito.verify(response).sendRedirect(aim);
	}
	
	@Test
	public void manageCartsCombineTest() throws IOException, SQLException {
		Order localOrder = new Order();
		localOrder.setCart(new ArrayList<>());
		localOrder.getCart().add(1);
		Order cloudOrder = new Order();
		cloudOrder.setCart(new ArrayList<>());
		cloudOrder.getCart().add(2);
		String aim = "http://localhost:8080/final/server/profile";
		
		Mockito.when(request.getParameter("command")).thenReturn("combine");
		Mockito.when(session.getAttribute("order")).thenReturn(localOrder);
		Mockito.when(session.getAttribute("cloud_order")).thenReturn(cloudOrder);
		
		OrderController.manageCarts(request, response);
		
		Mockito.verify(session).removeAttribute("cloud_order");
		Mockito.verify(session).setAttribute(Mockito.eq("order"), Mockito.any(Order.class));
		Mockito.verify(response).sendRedirect(aim);
	}
}
