import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ServletException extends HttpServlet {

	private static final long serialVersionUID = 2304076000623073897L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		String message;
		if (throwable != null)
			message = "Error";
		else
			message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		int code = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (code == 404)
			message = "Page not Found";
		request.setAttribute("code", code);
		request.setAttribute("message", message);
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/jsp/error.jsp");
		requestDispatcher.forward(request, response);
	}
}
