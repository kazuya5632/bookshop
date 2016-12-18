package bookshop.biz.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import bookshop.biz.dao.CardDAO;
import bookshop.biz.entity.Card;
import bookshop.biz.entity.Customer;
import bookshop.biz.exception.DuplicateCustomerException;
import bookshop.biz.exception.NotFoundException;

public class CardService implements Serializable {

	public Card findCard(String customerName) throws SQLException, NamingException, NotFoundException {
		Connection conn = null;
		Card card = null;
		try {
			// DataSource取得、データベース接続
			DataSource ds = DataSourceFactory.getDataSource();
			conn = ds.getConnection();

			CardDAO cardDAO = new CardDAO();
			card = cardDAO.findByName(conn, customerName);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		// 検索処理が正常に終了した場合、結果の書籍情報一覧を返す
		return card;
	}

	public void registCard(String customerName, Card card)
			throws DuplicateCustomerException, NamingException, SQLException {
		// finallyのために初期化
		Connection conn = null;
		try {
			// DataSource取得、データベース接続
			DataSource ds = DataSourceFactory.getDataSource();
			conn = ds.getConnection();

			// トランザクション開始
			conn.setAutoCommit(false);

			// 新規カード利用の場合、カード情報をcardテーブルに追加（Update)
			CardDAO cardDAO = new CardDAO();
			cardDAO.update(conn, card, customerName);
			conn.commit();

		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void registCardtable(String customerName, Card card)
			throws DuplicateCustomerException, NamingException, SQLException {
		// finallyのために初期化
		Connection conn = null;
		try {
			// DataSource取得、データベース接続
			DataSource ds = DataSourceFactory.getDataSource();
			conn = ds.getConnection();

			// トランザクション開始
			conn.setAutoCommit(false);

			// ログイン時、ユーザ名+カード情報(null)をcardテーブルに追加（insert)
			CardDAO cardDAO = new CardDAO();
			cardDAO.insert(conn, card, customerName);
			conn.commit();

		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Customer check(String customerName) throws SQLException, NamingException {
		Connection conn = null;
		try {
			// DataSource取得、データベース接続
			DataSource ds = DataSourceFactory.getDataSource();
			conn = ds.getConnection();

			// カードテーブルチェック（1ユーザにつき1件)
			CardDAO cardDAO = new CardDAO();
			Customer selectedcustomer = cardDAO.findByPrimaryKeyCard(conn, customerName);

			return selectedcustomer;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
