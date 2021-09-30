package controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.sql.SQLException;

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

public class ProductControllerTest {
	
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
	
	@BeforeClass
	public static void beforeClass() {
		daoFactory = Mockito.mockStatic(DAOFactory.class);
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		daoFactory.when(DAOFactory::getProductDAO).thenReturn(productDAO);
	}

	@Test
	public void buildTest() throws SQLException, ServletException, IOException {
		String aim = "/view/jsp/product.jsp";
		
		Mockito.when(request.getParameter("id")).thenReturn("1");
		Mockito.when(servletContext.getRequestDispatcher(aim)).thenReturn(requestDispatcher);
		
		Product prod = new Product();
		prod.setId(1);
		prod.setName("test");
		prod.setCategory("test");
		prod.setDescription(null);
		prod.setGender("test");
		prod.setNumber(13);
		prod.setPhoto("test");
		prod.setPrice(13);
		prod.setProducer("test");
		Mockito.when(productDAO.get(1)).thenReturn(prod);
		
		ProductController.build(request, response, servletContext);
		
		Mockito.verify(request, Mockito.times(1)).setAttribute("product", prod);
		Mockito.verify(servletContext, Mockito.times(1)).getRequestDispatcher(aim);
	}
}
