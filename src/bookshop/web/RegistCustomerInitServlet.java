package bookshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistCustomerInitServlet
 */
@WebServlet("/registcustomerinit")
public class RegistCustomerInitServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("message", "");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/registcustomer.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		}
	}
}
