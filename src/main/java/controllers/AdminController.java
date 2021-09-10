package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.*;
import entities.Product;
import entities.User;

public class AdminController {
	
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
	
	public static void buildProduct(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		ProductDAO dao = DAOFactory.getProductDAO();
		String name = request.getParameter("name");
		Product prod = dao.get(name);
		request.setAttribute("product", prod);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/admin_product.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public static void buildUsers(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException, ServletException, IOException {
		UserDAO userDao = DAOFactory.getUserDAO();
		List<User> users = userDao.getAll();
		request.setAttribute("users", users);
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/admin_users.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public static void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Product prod = new Product();
		prod.setId(Integer.valueOf(request.getParameter("id")));
		prod.setName(request.getParameter("name"));
		prod.setCategory(request.getParameter("category"));
		prod.setGender(request.getParameter("gender"));
		prod.setProducer(request.getParameter("producer"));
		prod.setNumber(Integer.valueOf(request.getParameter("number")));
		prod.setPrice(Float.valueOf(request.getParameter("price")));
		prod.setPhoto(saveFile(request, response, prod.getCategory()));
		ProductDAO dao = DAOFactory.getProductDAO();
		dao.update(prod);
		
		response.sendRedirect("http://localhost:8080/final/server/admin_catalog");
	}
	
	
    public static String saveFile(HttpServletRequest request, HttpServletResponse response, String category) throws ServletException, IOException {
        try {
        	String saveDir = "view/media/" + category;
        	
            // Gets absolute path to root directory of web app.
            String appPath = request.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');
 
            // The directory to save uploaded file
            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + saveDir;
            } else {
                fullSavePath = appPath + "/" + saveDir;
            }
 
            // Creates the save directory if it does not exists
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
 
            String fileName = null;
            // Part list (multi files).
            for (Part part : request.getParts()) {
                fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    String filePath = fullSavePath + File.separator + fileName;
                    // Write to file
                    part.write(filePath);
                }
            }
            // Upload successfully!.
            return fileName;
        } catch (Exception e) {
        	// TODO: manage Error
            throw new IOException("Can not saveFile", e);
        }
    }
 
    private static String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
    
    public static void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	Product prod = new Product();
		prod.setName(request.getParameter("name"));
		prod.setCategory(request.getParameter("category"));
		prod.setGender(request.getParameter("gender"));
		prod.setProducer(request.getParameter("producer"));
		prod.setNumber(Integer.valueOf(request.getParameter("number")));
		prod.setPrice(Float.valueOf(request.getParameter("price")));
		prod.setPhoto(saveFile(request, response, prod.getCategory()));
		ProductDAO dao = DAOFactory.getProductDAO();
		dao.create(prod);
		
		response.sendRedirect("http://localhost:8080/final/server/admin_catalog");
    }
    
    public static void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	ProductDAO dao = DAOFactory.getProductDAO();
    	dao.delete(Integer.valueOf(request.getParameter("id")));
    	response.sendRedirect("http://localhost:8080/final/server/admin_catalog");
    }
    
    public static void blockUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	UserDAO dao = DAOFactory.getUserDAO();
    	int id = Integer.valueOf(request.getParameter("id"));
    	dao.setStatus(id, !dao.isActive(id));
    	response.sendRedirect("http://localhost:8080/final/server/admin_users");
    }
    
    public static void adminUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	UserDAO dao = DAOFactory.getUserDAO();
    	int id = Integer.valueOf(request.getParameter("id"));
    	dao.setAdmin(id, !dao.isAdmin(id));
    	response.sendRedirect("http://localhost:8080/final/server/admin_users");
    }
    
    public static void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	UserDAO dao = DAOFactory.getUserDAO();
    	dao.delete(Integer.valueOf(request.getParameter("id")));
    	response.sendRedirect("http://localhost:8080/final/server/admin_users");
    }
}
