package bookshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookshop.biz.service.ShoppingCart;

/**
 * Servlet implementation class ModifyCountServlet
 */
@WebServlet("/modifyCount")
public class ModifyCountServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// ログインチェック
			boolean isLogin = LoginCheckUtil.loginCheck(request, response);
			if (isLogin == false) {
				return;
			}

			request.setCharacterEncoding("utf-8");
			String isbnCode = request.getParameter("isbnCode");
			String count = request.getParameter("count");

			// カート数値変換
			if (count == null || "".equals(count)) {
				request.setAttribute("message", "数量を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/shoppingcart.jsp");
				rd.forward(request, response);
				return;
			}

			Integer countValue = Integer.parseInt(count);

			HttpSession session = request.getSession();

			Object obj = session.getAttribute("shoppingCart");
			ShoppingCart shoppingCart = (ShoppingCart) obj;

			shoppingCart.modifyBookCount(isbnCode, countValue.intValue());

			response.sendRedirect(request.getContextPath() + "/viewcart");

		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		} catch (NumberFormatException e) {
			request.setAttribute("message", "数量は数字で入力してください");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/shoppingcart.jsp");
			rd.forward(request, response);
			return;
		}
	}
}
