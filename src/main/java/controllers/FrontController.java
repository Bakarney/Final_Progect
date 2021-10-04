package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Naberezhniy Artur
 * 
 * This is front controller.
 * It collects all requests to server and delegates them to other controllers.
 * FrontController doesn't have any logic in it, and only redirects requests
 * to pages and calling other controllers.
 */

@WebServlet("/server/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50) // 50MB
public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static final Logger logger = LogManager.getLogger(FrontController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		url = url.replaceAll(".*/server", "");
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = null;
		try {
			switch(url) {
			case "/home":
				requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/home.jsp");
				requestDispatcher.forward(request, response);
				break;
			case "/catalog":
				CatalogController.build(request, response, servletContext);
				break;
			case "/delivery":
				requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/delivery.jsp");
				requestDispatcher.forward(request, response);
				break;
			case "/about":
				requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/about.jsp");
				requestDispatcher.forward(request, response);
				break;
			case "/contacts":
				requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/contacts.jsp");
				requestDispatcher.forward(request, response);
				break;
			case "/sign_in":
				requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/sign_in.jsp");
				requestDispatcher.forward(request, response);
				break;
			case "/sign_up":
				if (request.getParameter("email") != null) {
					response.sendRedirect("http://localhost:8080/final/server/sign_up");
					return;
				}
				requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/sign_up.jsp");
				requestDispatcher.forward(request, response);
				break;
			case "/profile":
				if (request.getSession().getAttribute("user") != null) {
					requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/profile.jsp");
					requestDispatcher.forward(request, response);
				} else {
					response.sendRedirect("http://localhost:8080/final/server/sign_in");
				}
				break;
			case "/product":
				ProductController.build(request, response, servletContext);
				break;
			case "/admin_catalog":
				AdminController.buildCatalog(request, response, servletContext);
				break;
			case "/admin_users":
				AdminController.buildUsers(request, response, servletContext);
				break;
			case "/admin_product":
				AdminController.buildProduct(request, response, servletContext);
				break;
			case "/create_product":
				requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/admin_create_product.jsp");
				requestDispatcher.forward(request, response);
				break;
			case "/cart":
				OrderController.build(request, response, servletContext);
				break;
			case "/order_conflict":
				OrderController.orderConflict(request, response, servletContext);
				break;
			case "/admin_orders":
				AdminController.buildOrders(request, response, servletContext);
				break;
			default:
				response.sendError(404);
				break;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e.getMessage(), e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		url = url.replaceAll(".*/server", "");
		try {
			switch (url) {
			case "/sign_in":
				UserController.signIn(request, response);
				break;
			case "/sign_up":
				UserController.signUp(request, response);
				break;
			case "/sign_out":
				UserController.signOut(request, response);
				break;
			case "/create_product":
				AdminController.createProduct(request, response);
				break;
			case "/update_product":
				AdminController.updateProduct(request, response);
				break;
			case "/delete_product":
				AdminController.deleteProduct(request, response);
				break;
			case "/block_user":
				AdminController.blockUser(request, response);
				break;
			case "/admin_user":
				AdminController.adminUser(request, response);
				break;
			case "/delete_user":
				AdminController.deleteUser(request, response);
				break;
			case "/add_product":
				OrderController.addProduct(request, response);
				break;
			case "/confirm_order":
				OrderController.confirmOrder(request, response);
				break;
			case "/manage_carts":
				OrderController.manageCarts(request, response);
				break;
			case "/remove_product":
				OrderController.removeProducts(request, response);
				break;
			case "/set_paid":
				AdminController.setPaid(request, response);
				break;
			case "/reject_order":
				AdminController.rejectOrder(request, response);
				break;
			default:
				response.sendError(404);
				break;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e.getMessage(), e);
		}
	}
}
