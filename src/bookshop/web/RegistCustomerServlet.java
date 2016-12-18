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

import bookshop.biz.entity.Customer;
import bookshop.biz.exception.DuplicateCustomerException;
import bookshop.biz.service.CustomerService;

/**
 * Servlet implementation class RegistCustomerServlet
 */
@WebServlet("/registcustomer")
public class RegistCustomerServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			String customerName = request.getParameter("customerName");
			String password = request.getParameter("password");
			String passwordConfirm = request.getParameter("passwordConfirm");

			//例外処理
			if (customerName == null || "".equals(customerName)) {
				request.setAttribute("message", "登録ユーザ名を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/registcustomer.jsp");
				rd.forward(request, response);
				return;
			}
			if (password == null || "".equals(password)) {
				request.setAttribute("message", "登録パスワードを入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/registcustomer.jsp");
				rd.forward(request, response);
				return;
			}
			if (passwordConfirm == null || "".equals(passwordConfirm)) {
				request.setAttribute("message", "登録パスワード（確認用）を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/registcustomer.jsp");
				rd.forward(request, response);
				return;
			}
			if (customerName.getBytes("Windows-31J").length > 64) {
				request.setAttribute("message", "登録ユーザ名は半角64文字以内で入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/registcustomer.jsp");
				rd.forward(request, response);
				return;
			}
			if (password.getBytes("Windows-31J").length > 8) {
				request.setAttribute("message", "登録パスワードは半角8文字以内で入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/registcustomer.jsp");
				rd.forward(request, response);
				return;
			}
			if (!password.equals(passwordConfirm)) {
				request.setAttribute("message", "パスワードと確認用パスワードが一致しません");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/registcustomer.jsp");
				rd.forward(request, response);
				return;
			}
			Customer customer = new Customer();
			customer.setCustomerName(customerName);
			customer.setPassword(password);
			CustomerService service = new CustomerService();
			service.registCustomer(customer);
			response.sendRedirect(request.getContextPath() + "/index.jsp");

		} catch (DuplicateCustomerException e) {
			request.setAttribute("message", "指定されたユーザ名はすでに存在します");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/registcustomer.jsp");
			rd.forward(request, response);
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
