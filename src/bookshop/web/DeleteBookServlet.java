package bookshop.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookshop.biz.service.ShoppingCart;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

			shoppingCart.removeItem(isbnCode);

			response.sendRedirect(request.getContextPath() + "/viewcart");

		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		}
	}
}
