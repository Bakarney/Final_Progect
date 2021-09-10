package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.*;
import entities.*;

public class CatalogController {
	
	private static final int PAGE_LENGTH = 4;
	
	public static void build(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		String sort = request.getParameter("sort");
		String gender = request.getParameter("gender");
		String[] producersData = request.getParameterValues("producer");
		String[] categoriesData = request.getParameterValues("category");
		String bot = request.getParameter("bot");
		String top = request.getParameter("top");
		String page = request.getParameter("page");
		page = (page == null) ? "1" : page;
		String start = String.valueOf((Integer.valueOf(page) - 1) * PAGE_LENGTH);
		
		ProductDAO productDao = DAOFactory.getProductDAO();
		List<Product> products = productDao.getFiltered(categoriesData, producersData, gender, bot, top, sort, start, String.valueOf(PAGE_LENGTH));
		int numberPages = (productDao.count(categoriesData, producersData, gender, bot, top) - 1)/PAGE_LENGTH + 1;
		request.setAttribute("products", products);
		request.setAttribute("numberPages", numberPages);
		
		CategoryDAO categoryDao = DAOFactory.getCategoryDAO();
		List<String> categories = categoryDao.getAll();
		request.setAttribute("categories", categories);
		
		ProducerDAO producerDao = DAOFactory.getProducerDAO();
		List<String> producers = producerDao.getAll();
		request.setAttribute("producers", producers);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/catalog.jsp");
		requestDispatcher.forward(request, response);
	}
}