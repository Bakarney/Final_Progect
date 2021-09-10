package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Product;
import DAO.*;

public class ProductController {
	
	public static void build(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		String name = request.getParameter("name");
		ProductDAO dao = DAOFactory.getProductDAO();
		Product prod = dao.get(name);
		
		request.setAttribute("product", prod);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/product.jsp");
		requestDispatcher.forward(request, response);
	}
}