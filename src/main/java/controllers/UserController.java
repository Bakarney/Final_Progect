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

public class UserController {
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
		if (!dao.isActive(user.getId())) {
			response.sendRedirect("http://localhost:8080/final/server/sign_in?error=blocked");
			return;
		}
		session.setAttribute("user", user);
		if (dao.isAdmin(user.getId())) {
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
	
	public static void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setAddress(request.getParameter("address"));
		user.setCard(request.getParameter("card"));
		UserDAO dao = DAOFactory.getUserDAO();
		dao.create(user);
		request.getSession().setAttribute("user", user);
		response.sendRedirect("http://localhost:8080/final/server/profile");
	}
	
	public static void profile(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/profile.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public static void signOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("order");
		response.sendRedirect("http://localhost:8080/final/server/home");
	}
}
