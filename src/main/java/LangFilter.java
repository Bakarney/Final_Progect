import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Naberezhniy Artur
 * 
 * Checks if someone trying to change language.
 */
@WebFilter("/server/*")
public class LangFilter implements Filter {
	
	private static final String PROPERTY_FILE_NAME = "resources";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String lang = req.getParameter("lang");
		
		if (lang != null)
			req.getSession().setAttribute("lang", lang);
		
		chain.doFilter(request, response);
	}
}
