package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.*;
import entities.*;

/**
 * @author Naberezhniy Artur
 * 
 * AdminController consists only static methods. They must implement all
 * needed logic for admin panel of web app.
 */
public class AdminController {
	
	/**
	 * Creating lists of all products, categories and producers from DB and puts
	 * in request attributes with corresponding names.
	 * Making request to DAO with unnecessary request parameters "sort", "gender", 
	 * "producer", "category", "bot", "top".
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ServletContext
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void buildCatalog(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		String sort = request.getParameter("sort");
		String gender = request.getParameter("gender");
		String[] producersData = request.getParameterValues("producer");
		String[] categoriesData = request.getParameterValues("category");
		String bot = request.getParameter("bot");
		String top = request.getParameter("top");
		
		ProductDAO productDao = DAOFactory.getProductDAO();
		List<Product> products = productDao.getFiltered(categoriesData, producersData, gender, bot, top, sort, null, null);
		request.setAttribute("products", products);
		
		CategoryDAO categoryDao = DAOFactory.getCategoryDAO();
		List<String> categories = categoryDao.getAll();
		request.setAttribute("categories", categories);
		
		ProducerDAO producerDao = DAOFactory.getProducerDAO();
		List<String> producers = producerDao.getAll();
		request.setAttribute("producers", producers);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/admin_catalog.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Making request to DAO with request parameter "id", and puts found Product
	 * to request attributes with name "product".
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ServletContext
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void buildProduct(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		ProductDAO dao = DAOFactory.getProductDAO();
		int id = Integer.valueOf(request.getParameter("id"));
		Product prod = dao.get(id);
		request.setAttribute("product", prod);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/admin_product.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Making List of all Users from DAO and puts it in request attribute "users".
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ServletContext
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void buildUsers(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		UserDAO userDao = DAOFactory.getUserDAO();
		List<User> users = userDao.getAll();
		request.setAttribute("users", users);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/admin_users.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Making List of all Orders from DAO and puts it in request attribute "orders".
	 * In orders list after each order goes empty orders containing only info
	 * about one product of this order.
	 * Also making Map of all user id to their names and all products id and
	 * their names.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ServletContext
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void buildOrders(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		ProductDAO productDAO = DAOFactory.getProductDAO();
		UserDAO userDAO = DAOFactory.getUserDAO();
		OrderDAO orderDAO = DAOFactory.getOrderDAO();
		Map<Integer, String> products = new HashMap<>();
		Map<Integer, String> users = new HashMap<>();
		List<Order> orders = orderDAO.getAll();
		List<Order> preparedOrders = new ArrayList<>();
		
		for (Order order : orders) {
			boolean firstIter = true;
			for (Integer i : order.getCart()) {
				users.put(order.getUserId(), userDAO.get(order.getUserId()).getName());
				products.put(i, productDAO.get(i).getName());
				Order tmpOrder = new Order();
				if (firstIter) {
					firstIter = false;
					tmpOrder.setId(order.getId());
					tmpOrder.setUserId(order.getUserId());
					tmpOrder.setState(order.getState());
					tmpOrder.setCart(order.getCart());
				} else {
					tmpOrder.setCart(new ArrayList<>());
					tmpOrder.getCart().add(i);
				}
				preparedOrders.add(tmpOrder);
			}
		}
		
		request.setAttribute("orders", preparedOrders);
		request.setAttribute("products", products);
		request.setAttribute("users", users);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/admin_orders.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * Taking request parameters "id", "name", "category", "gender", "producer",
	 * "number", "price", "photo". Changes all information about product with 
	 * taken id. 
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Product prod = new Product();
		prod.setId(Integer.valueOf(request.getParameter("id")));
		prod.setName(request.getParameter("name"));
		prod.setCategory(request.getParameter("category"));
		prod.setGender(request.getParameter("gender"));
		prod.setProducer(request.getParameter("producer"));
		prod.setNumber(Integer.valueOf(request.getParameter("number")));
		prod.setPrice(Float.valueOf(request.getParameter("price")));
		if (request.getParameter("photo") != null)
			prod.setPhoto(saveFile(request, response, prod.getCategory()));
		ProductDAO dao = DAOFactory.getProductDAO();
		dao.update(prod);
		
		response.sendRedirect("http://localhost:8080/final/server/admin_catalog");
	}
	
    private static String saveFile(HttpServletRequest request, HttpServletResponse response, String category) throws ServletException, IOException {
        try {
        	String saveDir = "view/media/" + category;
            String appPath = request.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');
            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + saveDir;
            } else {
                fullSavePath = appPath + "/" + saveDir;
            }
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            String fileName = null;
            for (Part part : request.getParts()) {
                fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    String filePath = fullSavePath + File.separator + fileName;
                    part.write(filePath);
                }
            }
            return fileName;
        } catch (Exception e) {
            throw new IOException("Can not saveFile", e);
        }
    }
 
    private static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
    
    /**
	 * Taking request parameters "name", "category", "gender", "producer",
	 * "number", "price", "photo" and creation new Product in DB.
     * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	Product prod = new Product();
		prod.setName(request.getParameter("name"));
		prod.setCategory(request.getParameter("category"));
		prod.setGender(request.getParameter("gender"));
		prod.setProducer(request.getParameter("producer"));
		prod.setNumber(Integer.valueOf(request.getParameter("number")));
		prod.setPrice(Float.valueOf(request.getParameter("price")));
		if (request.getParameter("photo") != null)
			prod.setPhoto(saveFile(request, response, prod.getCategory()));
		ProductDAO dao = DAOFactory.getProductDAO();
		dao.create(prod);
		
		response.sendRedirect("http://localhost:8080/final/server/admin_catalog");
    }
    
    /**
	 * Taking request parameter "id", and deletes product with this id from DB.
     * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	ProductDAO dao = DAOFactory.getProductDAO();
    	dao.delete(Integer.valueOf(request.getParameter("id")));
    	response.sendRedirect("http://localhost:8080/final/server/admin_catalog");
    }
    
    /**
     * Taking request parameter "id", and changes status of user with this id.
     * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static void blockUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	UserDAO dao = DAOFactory.getUserDAO();
    	int id = Integer.valueOf(request.getParameter("id"));
    	dao.setStatus(id, !dao.isActive(id));
    	response.sendRedirect("http://localhost:8080/final/server/admin_users");
    }
    
    /**
     * Taking request parameter "id", and changes admin of user with this id.
     * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static void adminUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	UserDAO dao = DAOFactory.getUserDAO();
    	int id = Integer.valueOf(request.getParameter("id"));
    	dao.setAdmin(id, !dao.isAdmin(id));
    	response.sendRedirect("http://localhost:8080/final/server/admin_users");
    }
    
    /**
	 * Taking request parameter "id", and deletes user with this id from DB.
     * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	UserDAO dao = DAOFactory.getUserDAO();
    	dao.delete(Integer.valueOf(request.getParameter("id")));
    	response.sendRedirect("http://localhost:8080/final/server/admin_users");
    }
    
    /**
     * Taking request parameter "id", and changes status of order with this id
     * to "paid".
     * 
 	 * @param HttpServletRequest
	 * @param HttpServletResponse
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static void setPaid(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    	OrderDAO orderDAO = DAOFactory.getOrderDAO();
    	int id = Integer.valueOf(request.getParameter("id"));
    	orderDAO.setState(id, "paid");
    	
    	response.sendRedirect("http://localhost:8080/final/server/admin_orders");
    }
    
    /**
     * Taking request parameter "id", and changes status of order with this id
     * to "rejected".
     * 
 	 * @param HttpServletRequest
	 * @param HttpServletResponse
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    public static void rejectOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    	OrderDAO orderDAO = DAOFactory.getOrderDAO();
    	int id = Integer.valueOf(request.getParameter("id"));
    	orderDAO.setState(id, "rejected");
    	
    	response.sendRedirect("http://localhost:8080/final/server/admin_orders");
    }
}
