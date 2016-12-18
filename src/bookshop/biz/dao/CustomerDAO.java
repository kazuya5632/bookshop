package bookshop.biz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bookshop.biz.entity.Customer;

public class CustomerDAO {
	public Customer findByPrimaryKey(Connection conn, String customerName) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 入力されたユーザ名をキーにしてデータを取得するSQL文
			String sql = "SELECT customer_name,password FROM bookshop_customer WHERE customer_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customerName);
			// SQL文の実行
			rs = ps.executeQuery();
			// 検索結果の取得
			if (rs.next()) {
				String customer_name = rs.getString("customer_name");
				String password = rs.getString("password");

				Customer customer = new Customer();
				customer.setCustomerName(customer_name);
				customer.setPassword(password);

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

	public void insert(Connection conn, Customer customer) throws SQLException {
		PreparedStatement ps = null;
		// ResultSet rs = null;
		try {
			// 入力されたユーザ名をキーにしてデータを取得するSQL文
			String sql = "insert into bookshop_customer(customer_name,password)values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getPassword());
			// SQL文の実行
			ps.executeUpdate();
		} finally {
			// クローズ処理
			if (ps != null) {
				ps.close();
			}
		}
	}


}
