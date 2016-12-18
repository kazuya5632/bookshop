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

import bookshop.biz.entity.Card;
import bookshop.biz.entity.Customer;
import bookshop.biz.exception.AuthException;
import bookshop.biz.exception.DuplicateCustomerException;
import bookshop.biz.service.CardService;
import bookshop.biz.service.CustomerService;
import bookshop.biz.service.ShoppingCart;

@SuppressWarnings("serial")
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			// クライアントからの入力値を取得
			String customerName = request.getParameter("customerName");
			String password = request.getParameter("password");
			// ログイン情報未入力チェック
			if (customerName == null || "".equals(customerName)) {
				request.setAttribute("message", "ログインユーザ名を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
				rd.forward(request, response);
				return;
			}
			if (password == null || "".equals(password)) {
				request.setAttribute("message", "ログインパスワードを入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
				rd.forward(request, response);
				return;
			}
			// DataAccessBeanのインスタンスを作成
			CustomerService service = new CustomerService();
			Customer customer = service.authentication(customerName, password);

			if (customerName.equals(customer.getCustomerName())) {
				// 認証成功の場合
				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);

				ShoppingCart shoppingCart = new ShoppingCart();
				session.setAttribute("shoppingCart", shoppingCart);

				session.setAttribute("message", "");

				// クレジットカードテーブル作成
				customer.setCreditCard();
				CardService cardService = new CardService();
				Card card = new Card();
				Customer checkcustomer = cardService.check(customerName);

				if (checkcustomer == null) {
					cardService.registCardtable(customerName, card);
					RequestDispatcher rd = request.getRequestDispatcher("/searchBooksInit");
					rd.forward(request, response);
					// 次の画面で表示するために、入力されたユーザ名をrequestスコープに入れる
					request.setAttribute("customerName", customerName);
				} else {
					// カード情報登録済みの場合
					RequestDispatcher rd = request.getRequestDispatcher("/searchBooksInit");
					rd.forward(request, response);

					// 次の画面で表示するために、入力されたユーザ名をrequestスコープに入れる
					request.setAttribute("customerName", customerName);
				}
			} else {
				// 認証失敗の場合
				AuthException e = new AuthException();
				throw e;
			}
		} catch (SQLException e) {
			request.setAttribute("message", "RDB接続処理でエラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		} catch (NamingException e) {
			request.setAttribute("message", "JNDIの設定情報が間違っている可能性があります");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		} catch (AuthException e) {
			request.setAttribute("message", "ログインユーザ名またはログインパスワードが間違っています");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			rd.forward(request, response);
		} catch (DuplicateCustomerException e) {
			e.printStackTrace();
		}
	}
}
