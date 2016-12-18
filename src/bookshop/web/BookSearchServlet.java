package bookshop.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.biz.entity.Book;
import bookshop.biz.exception.NotFoundException;
import bookshop.biz.service.BookService;

/**
 * Servlet implementation class BookSearchServlet
 */
@WebServlet("/searchBooks")
public class BookSearchServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// ログインチェック
			boolean isLogin = LoginCheckUtil.loginCheck(request, response);
			if (isLogin == false) {
				return;
			}

			request.setCharacterEncoding("utf-8");

			String searchMode = request.getParameter("searchMode");
			String condition = request.getParameter("condition");

			BookService bookService = new BookService();
			Collection<Book> bookList = bookService.findBooks(searchMode, condition);

			// 検索結果0 : NotFoundException
			if (bookList.size() == 0) {
				NotFoundException e = new NotFoundException();
				throw e;
			} else {

				request.setAttribute("bookList", bookList);
				request.setAttribute("message", "");

				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/booklist.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("message", "RDB接続処理でエラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		} catch (NotFoundException e) {
			request.setAttribute("message", "検索結果はありませんでした。");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/booksearch.jsp");
			rd.forward(request, response);
		} catch (NamingException e) {
			request.setAttribute("message", "JNDIの設定情報が間違っている可能性があります");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			rd.forward(request, response);
		}
	}
}
