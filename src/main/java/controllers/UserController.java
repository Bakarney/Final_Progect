package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

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
 * Contains static methods to manage users.
 */
public class UserController {
	
	/**
	 * Adding to session attribute user if it active and his email and password 
	 * is correct. If user is admin redirects him to admin page.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO dao = DAOFactory.getUserDAO();
		User user = dao.get(email, password);
		HttpSession session = request.getSession();
		if (user.getName() == null) {
			response.sendRedirect("http://localhost:8080/final/server/sign_in?error=wrong");
			return;
		}
		if (!user.isActive()) {
			response.sendRedirect("http://localhost:8080/final/server/sign_in?error=blocked");
			return;
		}
		session.setAttribute("user", user);
		if (user.isAdmin()) {
			response.sendRedirect("http://localhost:8080/final/server/admin_catalog");
		}
		else {
			Order sessionOrder = (Order)session.getAttribute("order");
			OrderDAO orderDao = DAOFactory.getOrderDAO();
			Order cloudOrder = orderDao.get(user);
			if (sessionOrder != null && cloudOrder != null) {
				session.setAttribute("cloud_order", cloudOrder);
				response.sendRedirect("http://localhost:8080/final/server/order_conflict");
			}
			else if (sessionOrder != null) {
				Order order = orderDao.create(user.getId());
				for (Integer i : sessionOrder.getCart())
					orderDao.addProduct(order.getId(), i);
				sessionOrder.setId(order.getId());
				session.setAttribute("order", sessionOrder);
				response.sendRedirect("http://localhost:8080/final/server/profile");
			} else {
				session.setAttribute("order", cloudOrder);
				response.sendRedirect("http://localhost:8080/final/server/profile");
			}
		}
	}
	
	/**
	 * Adding new user to BD if user with this email is not exists.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setAddress(request.getParameter("address"));
		user.setCard(request.getParameter("card"));
		UserDAO dao = DAOFactory.getUserDAO();
		if (dao.create(user)) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("http://localhost:8080/final/server/profile");
		} else
			response.sendRedirect("http://localhost:8080/final/server/sign_up?error=exists");
	}
	
	/**
	 * Removing parameters "user" and "order" from session.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void signOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("order");
		response.sendRedirect("http://localhost:8080/final/server/home");
	}
}
