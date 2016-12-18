package bookshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookSearchInitServlet
 */
@WebServlet("/searchBooksInit")
public class BookSearchInitServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// ログインチェック
			boolean isLogin = LoginCheckUtil.loginCheck(request, response);
			if (isLogin == false) {
				return;
			}
			request.setAttribute("message", "");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/booksearch.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		}
	}
}
