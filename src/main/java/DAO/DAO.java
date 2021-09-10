package DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public abstract class DAO {
	
	private static String dbFullURL;
	private static String dbURL;
	private static String dbUserName;
	private static String dbPassword;
	
	public static Connection getConnection(String dbFullURL) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		DAO.dbFullURL = dbFullURL;
		return DriverManager.getConnection(dbFullURL);
	}
	
	public static Connection getConnection(String dbURL, String dbUserName, String dbPassword) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		DAO.dbURL = dbURL;
		DAO.dbUserName = dbUserName;
		DAO.dbPassword = dbPassword;
		return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	}
	
	public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		if (dbFullURL == null && dbURL == null) {
			// TODO: read property file
			dbFullURL = "jdbc:mysql://localhost:3306/finalproject?user=root&password=";
		}
		if (dbFullURL != null) {
			return DriverManager.getConnection(dbFullURL);
		}
		return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	}
	
	public static <T extends AutoCloseable> void close(T obj) {
		try {
			if (obj != null) {
				obj.close();
			}
		} catch (Exception e) {
			// nothing
		}
	}
}
