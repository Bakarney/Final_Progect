import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.*;
import DAO.*;


@WebFilter("/*")
public class AuthorizationCheck implements Filter {
	
	private static final String[] adminURLs = new String[] {"/admin_catalog", "/admin_users", "/admin_product", "/delete_product",
			"/block_user", "/admin_user", "/delete_user", "/set_paid", "/reject_order", "/create_product", "/admin_orders"};

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		User user = (User)req.getSession().getAttribute("user");
		String url = req.getRequestURL().toString();
		url = url.replaceAll(".*/server", "");
		UserDAO dao = DAOFactory.getUserDAO();

		try {
			if (Arrays.stream(adminURLs).anyMatch(url::equals) && (user == null || !dao.isAdmin(user.getId())))
				res.sendError(404);
		} catch (Exception e) {
			throw new ServletException("Page not found", e);
		}
		
		chain.doFilter(request, response);
	}
}
