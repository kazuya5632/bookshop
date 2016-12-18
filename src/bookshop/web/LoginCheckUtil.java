package bookshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookshop.biz.entity.Customer;

public class LoginCheckUtil {
	private LoginCheckUtil() {
	}

	public static boolean loginCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("customer");
		Customer customer = (Customer) obj;

		//ログイン状態をチェック
		if (customer == null) {
			request.setAttribute("message", "ログインしてください");
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
			return false;
		} else {
			return true;
		}

	}
}
