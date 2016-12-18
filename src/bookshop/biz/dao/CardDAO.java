package bookshop.biz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bookshop.biz.entity.Card;
import bookshop.biz.entity.Customer;

public class CardDAO {
	public void update(Connection conn, Card card, String customerName) throws SQLException {
		PreparedStatement ps = null;

		try {
			// UPDATE
			String sql = "update card set card_company=?,card_number=?,card_month=?,card_year=?,card_name=?,card_security=? where customer_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, card.getCard_company());
			ps.setString(2, card.getCard_number());
			ps.setString(3, card.getCard_month());
			ps.setString(4, card.getCard_year());
			ps.setString(5, card.getCard_name());
			ps.setString(6, card.getCard_security());
			ps.setString(7, customerName);

			// SQL実行
			int count = ps.executeUpdate();
			if (count == 0) {
				SQLException e = new SQLException();
				throw e;
			}

		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	public void insert(Connection conn, Card card, String customerName) throws SQLException {
		PreparedStatement ps = null;

		try {
			// INSERT
			String sql = "insert into card (card_company,card_number,card_month,card_year,card_name,card_security,customer_name) values (?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, card.getCard_company());
			ps.setString(2, card.getCard_number());
			ps.setString(3, card.getCard_month());
			ps.setString(4, card.getCard_year());
			ps.setString(5, card.getCard_name());
			ps.setString(6, card.getCard_security());
			ps.setString(7, customerName);

			// SQL実行
			ps.executeUpdate();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	public Customer findByPrimaryKeyCard(Connection conn, String customerName) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 入力されたユーザ名をキーにしてデータを取得するSQL文
			String sql = "SELECT customer_name FROM card WHERE customer_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customerName);
			// SQL文の実行
			rs = ps.executeQuery();
			// 検索結果の取得
			if (rs.next()) {
				String customer_name = rs.getString("customer_name");

				Customer customer = new Customer();
				customer.setCustomerName(customer_name);

				return customer;
			} else {
				// 検索結果がなかった場合(ユーザ名が一致しない場合)は認証失敗。falseを返す。
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// クローズ処理
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
	}

	public Card findByName(Connection conn, String customerName) throws SQLException {
		// Cardテーブルに登録されている情報を取得。Cardオブジェクトとして返します。
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sql = "select card_company,card_number,card_month,card_year from card where customer_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customerName);
			rs = ps.executeQuery();

			if (rs.next()) {
				String card_company = rs.getString("card_company");
				String card_number = rs.getString("card_number");
				String card_month = rs.getString("card_month");
				String card_year = rs.getString("card_year");

				Card card = new Card();
				card.setCard_company(card_company);
				card.setCard_number(card_number);
				card.setCard_month(card_month);
				card.setCard_year(card_year);

				return card;
			} else {
				// 検索結果がなかった場合(ユーザ名が一致しない場合)は認証失敗。falseを返す。
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
}