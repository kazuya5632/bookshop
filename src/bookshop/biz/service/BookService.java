package bookshop.biz.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.NamingException;
import javax.sql.DataSource;

import bookshop.biz.dao.BookDAO;
import bookshop.biz.entity.Book;
import bookshop.biz.exception.NotFoundException;

public class BookService {
	// 検索モード定義
	public static final String SEARCHMODE_ALL = "all";
	public static final String SEARCHMODE_BOOKNAME = "bookName";
	public static final String SEARCHMODE_AUTHOR = "author";
	public static final String SEARCHMODE_PUBLISHER = "publisher";
	public static final String SEARCHMODE_ISBNCODE = "isbnCode";

	public Book getBook(String isbnCode) throws NamingException, SQLException {
		Connection conn = null;
		Book book = null;
		try {
			// DataSource取得、データベース接続
			DataSource ds = DataSourceFactory.getDataSource();
			conn = ds.getConnection();

			// 引数で指定されたISBNコードに該当する書籍情報を取得
			BookDAO bookDAO = new BookDAO();
			book = bookDAO.findByPrimaryKey(conn, isbnCode);
		//	if (book == null) {
		//		return null;
		//	}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		// 取得した書籍情報を返す
		return book;
	}

	public Collection<Book> findBooks(String searchMode, String condition)
			throws SQLException, NamingException, NotFoundException {
		Connection conn = null;
		Collection<Book> books = null;
		try {
			// DataSource取得、データベース接続
			DataSource ds = DataSourceFactory.getDataSource();
			conn = ds.getConnection();

			BookDAO dao = new BookDAO();

			// 検索方法を元に書籍検索
			if (searchMode.equals(SEARCHMODE_ALL)) {
				books = dao.findAll(conn);
			} else if (searchMode.equals(SEARCHMODE_BOOKNAME)) {
				books = dao.findByBookName(conn, condition);
			} else if (searchMode.equals(SEARCHMODE_AUTHOR)) {
				books = dao.findByAuthor(conn, condition);
			} else if (searchMode.equals(SEARCHMODE_PUBLISHER)) {
				books = dao.findByPublisher(conn, condition);
			} else if (searchMode.equals(SEARCHMODE_ISBNCODE)) {
				books = dao.findByIsbnCode(conn, condition);
			} else {
				NotFoundException e = new NotFoundException();
				throw e;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		// 検索処理が正常に終了した場合、結果の書籍情報一覧を返す
		return books;

	}

}
