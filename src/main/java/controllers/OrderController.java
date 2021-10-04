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

import DAO.*;
import entities.*;

/**
 * @author Naberezhniy Artur
 * 
 * Contains static methods to manage orders.
 */
public class OrderController {
	
	/**
	 * Making List of products of order from session or from DB, if session
	 * contains parameter "user". If both of them isn't in session will return
	 * empty List.
	 * 
	 * @param request
	 * @param response
	 * @param servletContext
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void build(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		OrderDAO orderDao = DAOFactory.getOrderDAO();
		ProductDAO prodDAO = DAOFactory.getProductDAO();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Order order;
		List<Product> products = new ArrayList<>();
		
		if (user != null) {
			order = orderDao.get(user);
		}
		else 
			order = (Order)session.getAttribute("order");
		
		if (order != null)
			for (int i : order.getCart())
				products.add(prodDAO.get(i));
		
		request.setAttribute("products", products);
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/cart.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Taking "product_id" from request parameters and adding it to order from 
	 * session or from DB, if session contains parameter "user".
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int id = Integer.valueOf(request.getParameter("product_id"));
		OrderDAO dao = DAOFactory.getOrderDAO();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Order order;
		
		if (user != null) {
			order = dao.get(user);
			if (order == null)
				order = dao.create(user.getId());
			dao.addProduct(order.getId(), id);
			order.getCart().add(id);
			session.setAttribute("order", order);
		}
		else {
			order = (Order)session.getAttribute("order");
			if (order == null)
				order = new Order();
			order.getCart().add(id);
			session.setAttribute("order", order);
		}
		
		response.sendRedirect("http://localhost:8080/final/server/product?id=" + id);
	}
	
	/**
	 * Taking "id" from request parameters and removing it from orders product List
	 * from session or from DB, if session contains parameter "user".
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void removeProducts(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		order.getCart().remove(id);
		session.setAttribute("order", order);
		
		OrderDAO dao = DAOFactory.getOrderDAO();
		dao.removeProduct(order.getId(), id);
		
		if (dao.get(order.getId()) == null)
			dao.delete(order.getId());
		
		response.sendRedirect("http://localhost:8080/final/server/cart");
	}
	
	/**
	 * Setting status of order to "registrated" or redirects to sign in page if
	 * user isn't authorized.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int id = Integer.valueOf(request.getParameter("order_id"));
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("http://localhost:8080/final/server/sign_in");
		} else {
			session.removeAttribute("order");
			OrderDAO dao = DAOFactory.getOrderDAO();
			dao.setState(id, "registrated");
			response.sendRedirect("http://localhost:8080/final/server/home");
		}
	}
	
	/**
	 * Preparing lists of cloud and local orders for order conflict page.
	 * 
	 * @param request
	 * @param response
	 * @param servletContext
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void orderConflict(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		ProductDAO prodDAO = DAOFactory.getProductDAO();
		HttpSession session = request.getSession();
		
		List<Product> products = new ArrayList<>();
		List<Integer> list = ((Order)session.getAttribute("order")).getCart();
		for (Integer i : list)
			products.add(prodDAO.get(i));
		request.setAttribute("local_products", products);
		
		products = new ArrayList<>();
		list = ((Order)session.getAttribute("cloud_order")).getCart();
		for (Integer i : list)
			products.add(prodDAO.get(i));
		request.setAttribute("cloud_products", products);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/order_conflict.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Saving local, cloud or combined carts due to request parameter "command".
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void manageCarts(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String command = request.getParameter("command");
		HttpSession session = request.getSession();
		OrderDAO dao = DAOFactory.getOrderDAO();
		switch (command) {
		case "local":
			User user = (User)session.getAttribute("user");
			Order order = (Order)session.getAttribute("cloud_order");
			dao.delete(order.getId());
			session.removeAttribute("cloud_order");
			order = (Order)session.getAttribute("order");
			Order newOrder = dao.create(user.getId());
			for (Integer i : order.getCart()) 
				dao.addProduct(newOrder.getId(), i);
			session.setAttribute("order", newOrder);
			break;
		case "cloud":
			session.removeAttribute("cloud_order");
			break;
		case "combine":
			Order cloudOrder = (Order)session.getAttribute("cloud_order");
			Order localOrder = (Order)session.getAttribute("order");
			
			session.removeAttribute("cloud_order");
			for (Integer i : localOrder.getCart()) {
				dao.addProduct(cloudOrder.getId(), i);
				cloudOrder.getCart().add(i);
			}
			session.setAttribute("order", cloudOrder);
			break;
		}
		response.sendRedirect("http://localhost:8080/final/server/profile");
	}
}