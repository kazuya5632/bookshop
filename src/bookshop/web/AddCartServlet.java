package bookshop.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookshop.biz.service.ShoppingCart;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			request.setCharacterEncoding("utf-8");

			String isbnCode = request.getParameter("isbnCode");
			HttpSession session = request.getSession();

			Object obj = session.getAttribute("shoppingCart");
			ShoppingCart shoppingCart = (ShoppingCart) obj;

			shoppingCart.addCart(isbnCode);

			response.sendRedirect(request.getContextPath() + "/viewcart");
		} catch (NamingException e) {
			request.setAttribute("message", "JNDIの設定情報が間違っている可能性があります");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("message", "RDB接続処理でエラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		}
	}
}
