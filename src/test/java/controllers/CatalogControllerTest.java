package controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAO.*;
import entities.*;

public class CatalogControllerTest {
	
	@Mock
	private static HttpServletRequest request;
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
	private static ProducerDAO producerDAO;
	@Mock
	private static CategoryDAO categoryDAO;
	
	@BeforeClass
	public static void beforeClass() {
		daoFactory = Mockito.mockStatic(DAOFactory.class);
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		daoFactory.when(DAOFactory::getProductDAO).thenReturn(productDAO);
		daoFactory.when(DAOFactory::getCategoryDAO).thenReturn(categoryDAO);
		daoFactory.when(DAOFactory::getProducerDAO).thenReturn(producerDAO);
	}

	@Test
	public void fullBuildTest() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException, SQLException {
		String aim = "/view/jsp/catalog.jsp";
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
		
		CatalogController.build(request, response, servletContext);
		
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("products", products);
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("categories", categories);
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("producers", producers);
		Mockito.verify(servletContext, Mockito.atLeastOnce()).getRequestDispatcher(aim);
	}
	
	@Test
	public void emptyBuildTest() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServletException, IOException {
		String aim = "/view/jsp/catalog.jsp";
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
		
		CatalogController.build(request, response, servletContext);
		
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("products", products);
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("categories", categories);
		Mockito.verify(request, Mockito.atLeastOnce()).setAttribute("producers", producers);
		Mockito.verify(servletContext, Mockito.atLeastOnce()).getRequestDispatcher(aim);
	}
}
