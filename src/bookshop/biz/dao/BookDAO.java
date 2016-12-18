package bookshop.biz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import bookshop.biz.entity.Book;

public class BookDAO {
	public Book findByPrimaryKey(Connection conn, String isbnCode) throws SQLException {
		// プライマリキーであるISBNコード（isbn_code）をもとに、書籍テーブル（books）を検索し、
		// 取得した書籍情報（Bookオブジェクト）を一件返します。該当するデータがない場合はnullを返します。
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select isbn_code,book_name,author,publisher,price from books where isbn_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbnCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				String isbn_code = rs.getString("isbn_code");
				String book_name = rs.getString("book_name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");

				Book book = new Book();
				book.setIsbnCode(isbn_code);
				book.setBookName(book_name);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setPrice(price);

				return book;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
	}

	public Collection<Book> findAll(Connection conn) throws SQLException {
		// 書籍テーブル（books）に登録されている書籍を全件取得し、
		// Bookオブジェクトのコレクションとして返します。
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Book> list = new ArrayList<Book>();
		try {
			String sql = "select isbn_code,book_name,author,publisher,price from books";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String isbn_code = rs.getString("isbn_code");
				String book_name = rs.getString("book_name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");

				Book book = new Book();
				book.setIsbnCode(isbn_code);
				book.setBookName(book_name);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setPrice(price);

				list.add(book);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return list;
	}

	public Collection<Book> findByBookName(Connection conn, String bookName) throws SQLException {
		// 書籍テーブル（books）に登録されている書籍の中から、引数bookNameで指定された文字が
		// 含まれる書籍名（book_name）のデータを取得し、Bookオブジェクトのコレクションとして
		// 返します。
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Book> list = new ArrayList<Book>();

		try {
			String sql = "select isbn_code,book_name,author,publisher,price from books where book_name like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + bookName + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String isbn_code = rs.getString("isbn_code");
				String book_name = rs.getString("book_name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");

				Book book = new Book();
				book.setIsbnCode(isbn_code);
				book.setBookName(book_name);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setPrice(price);

				list.add(book);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return list;
	}

	public Collection<Book> findByPublisher(Connection conn, String publisher) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Book> list = new ArrayList<Book>();

		try {
			String sql = "select isbn_code,book_name,author,publisher,price from books where publisher like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + publisher + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String isbn_code = rs.getString("isbn_code");
				String book_name = rs.getString("book_name");
				String author = rs.getString("author");
				String publisher2 = rs.getString("publisher");
				int price = rs.getInt("price");

				Book book = new Book();
				book.setIsbnCode(isbn_code);
				book.setBookName(book_name);
				book.setAuthor(author);
				book.setPublisher(publisher2);
				book.setPrice(price);

				list.add(book);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return list;
	}

	public Collection<Book> findByAuthor(Connection conn, String author) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Book> list = new ArrayList<Book>();

		try {
			String sql = "select isbn_code,book_name,author,publisher,price from books where author like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + author + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String isbn_code = rs.getString("isbn_code");
				String book_name = rs.getString("book_name");
				String author2 = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");

				Book book = new Book();
				book.setIsbnCode(isbn_code);
				book.setBookName(book_name);
				book.setAuthor(author2);
				book.setPublisher(publisher);
				book.setPrice(price);

				list.add(book);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return list;
	}

	public Collection<Book> findByIsbnCode(Connection conn, String isbnCode) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Book> list = new ArrayList<Book>();

		try {
			String sql = "select isbn_code,book_name,author,publisher,price from books where isbn_code like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + isbnCode + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String isbn_code = rs.getString("isbn_code");
				String book_name = rs.getString("book_name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");

				Book book = new Book();
				book.setIsbnCode(isbn_code);
				book.setBookName(book_name);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setPrice(price);

				list.add(book);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return list;
	}

}
