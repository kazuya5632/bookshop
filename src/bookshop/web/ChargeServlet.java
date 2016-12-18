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
import bookshop.biz.exception.DuplicateCustomerException;
import bookshop.biz.service.CardService;
import bookshop.biz.service.ShoppingCart;

/**
 * Servlet implementation class ChargeServlet
 */
@WebServlet("/charge")
public class ChargeServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// ログインチェック
			boolean isLogin = LoginCheckUtil.loginCheck(request, response);
			if (isLogin == false) {
				return;
			}

			request.setCharacterEncoding("utf-8");

			String p = request.getParameter("reg");
			String p1 = request.getParameter("reg2");
			HttpSession session = request.getSession();
			Card selectedcard = (Card) session.getAttribute("card");
			String selectedcard_company = selectedcard.getCard_company();

			if (p.equals("1")) {
				// 「代金引換」ラジオボタン選択時
			} else if (p1.equals("1")) {
				// 「クレジットカード決済」ラジオボタン選択時
				// 「前回のカードを利用」ラジオボタン選択時

				// 前回のカード情報が無い場合
				if (selectedcard_company == null || "".equals(selectedcard_company)) {
					request.setAttribute("message", "カード情報を正しく入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/selectcharge.jsp");
					rd.forward(request, response);
					return;
				}
			} else if (p1.equals("2")) {
				// 「クレジットカード決済」ラジオボタン選択時
				// 「新規カードを利用」ラジオボタン選択時

				// カード情報入力フォームから値取得
				String card_company = request.getParameter("card_company");
				String card_number = request.getParameter("card_number");
				String card_month = request.getParameter("card_month");
				String card_year = request.getParameter("card_year");
				String card_name = request.getParameter("card_name");
				String card_security = request.getParameter("card_security");

				// 入力チェック
				if (card_company == null || "".equals(card_company)) {
					request.setAttribute("message", "カード情報を正しく入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/selectcharge.jsp");
					rd.forward(request, response);
					return;
				}
				if (card_number == null || "".equals(card_number)) {
					request.setAttribute("message", "カード情報を正しく入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/selectcharge.jsp");
					rd.forward(request, response);
					return;
				}
				if (card_month == null || "".equals(card_month)) {
					request.setAttribute("message", "カード情報を正しく入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/selectcharge.jsp");
					rd.forward(request, response);
					return;
				}
				if (card_year == null || "".equals(card_year)) {
					request.setAttribute("message", "カード情報を正しく入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/selectcharge.jsp");
					rd.forward(request, response);
					return;
				}
				if (card_name == null || "".equals(card_name)) {
					request.setAttribute("message", "カード情報を正しく入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/selectcharge.jsp");
					rd.forward(request, response);
					return;
				}
				if (card_security == null || "".equals(card_security)) {
					request.setAttribute("message", "カード情報を正しく入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/selectcharge.jsp");
					rd.forward(request, response);
					return;
				}

				// カード情報追加処理
				Card card = new Card();
				card.setCard_company(card_company);
				card.setCard_number(card_number);
				card.setCard_month(card_month);
				card.setCard_year(card_year);
				card.setCard_name(card_name);
				card.setCard_security(card_security);
				CardService cartService = new CardService();
				Object object = session.getAttribute("customer");
				Customer customer = (Customer) object;
				String customerName = customer.getCustomerName();
				cartService.registCard(customerName, card);

			}

			// 精算処理
			ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			request.setAttribute("shoppingCart", shoppingCart);
			ShoppingCart newShoppingCart = new ShoppingCart();
			session.setAttribute("shoppingCart", newShoppingCart);
			request.setAttribute("message", "");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/charge.jsp");
			rd.forward(request, response);

		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		} catch (DuplicateCustomerException e) {
			ServletException e1 = new ServletException();
			throw e1;
		} catch (NamingException e) {
			ServletException e1 = new ServletException();
			throw e1;
		} catch (SQLException e) {
			ServletException e1 = new ServletException();
			throw e1;
		}
	}
}