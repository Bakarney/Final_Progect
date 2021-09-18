package DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public abstract class DAO {
	
    private static BasicDataSource ds = new BasicDataSource();
    
    static {
        ds.setUrl("jdbc:mysql://localhost:3306/finalproject");
        ds.setUsername("root");
        ds.setPassword("");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
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
