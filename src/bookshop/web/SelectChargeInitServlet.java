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
import bookshop.biz.exception.NotFoundException;
import bookshop.biz.service.CardService;

/**
 * Servlet implementation class SelectChargeInitServlet
 */
@WebServlet("/selectchargeinit")
public class SelectChargeInitServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		} catch (SQLException e) {
			ServletException e1 = new ServletException();
			throw e1;
		} catch (NamingException e) {
			ServletException e1 = new ServletException();
			throw e1;
		} catch (NotFoundException e) {
			ServletException e1 = new ServletException();
			throw e1;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		} catch (SQLException e) {
			ServletException e1 = new ServletException();
			throw e1;
		} catch (NamingException e) {
			ServletException e1 = new ServletException();
			throw e1;
		} catch (NotFoundException e) {
			ServletException e1 = new ServletException();
			throw e1;
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, NamingException, NotFoundException {
		try {
			// ログインチェック
			boolean isLogin = LoginCheckUtil.loginCheck(request, response);
			if (isLogin == false) {
				return;
			}

			// cardテーブル情報をsessionスコープに格納
			HttpSession session = request.getSession();
			Object object = session.getAttribute("customer");
			Customer customer = (Customer) object;
			String customerName = customer.getCustomerName();
			CardService cardService = new CardService();
			Card card = cardService.findCard(customerName);
			session.setAttribute("card",card);

			request.setAttribute("message", "");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/selectcharge.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		}
	}
}