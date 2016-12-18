package bookshop.biz.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import bookshop.biz.dao.CustomerDAO;
import bookshop.biz.entity.Customer;
import bookshop.biz.exception.AuthException;
import bookshop.biz.exception.DuplicateCustomerException;

@SuppressWarnings("serial")
public class CustomerService implements Serializable {
	public Customer authentication(String customerName, String password)
			throws SQLException, NamingException, AuthException {
		// finallyのために初期化
		Connection conn = null;

		Customer custmer = null;

		try {
			// DataSource取得、データベース接続
			DataSource ds = DataSourceFactory.getDataSource();
			conn = ds.getConnection();

			// 顧客名をもとに、Custmerオブジェクト取得
			CustomerDAO customerDAO = new CustomerDAO();
			custmer = customerDAO.findByPrimaryKey(conn, customerName);

			// 認証に失敗した場合、AuthExceptionをスロー
			if (custmer == null || !password.equals(custmer.getPassword())) {
				AuthException e1 = new AuthException();
				throw e1;
			}
			// } catch (SQLException e) {
			// throw e;
			// } catch (NamingException e) {
			// throw e;
			// } catch (AuthException e) {
			// throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		// 認証に成功した場合、顧客情報を一件返す
		return custmer;
	}

	public void registCustomer(Customer customer) throws DuplicateCustomerException, NamingException, SQLException {
		// finallyのために初期化
		Connection conn = null;
		try {
			// DataSource取得、データベース接続
			DataSource ds = DataSourceFactory.getDataSource();
			conn = ds.getConnection();

			// トランザクション開始
			conn.setAutoCommit(false);

			// 二重登録チェック
			String customerName = customer.getCustomerName();
			CustomerDAO customerDAO = new CustomerDAO();
			Customer selectedcustomer = customerDAO.findByPrimaryKey(conn, customerName);
			if (selectedcustomer != null) {
				// 同一顧客名を持つデータが存在する場合、ロールバック＆DuplicateCustomerExceptionをスロー
				conn.rollback();
				DuplicateCustomerException e1 = new DuplicateCustomerException();
				throw e1;
			} else {
				// 同一顧客名を持つデータが無い場合、顧客情報をデータベースに登録し、コミット
				customerDAO.insert(conn, customer);
				conn.commit();
			}
			// } catch (NamingException e) {
			// throw e;
			// } catch (DuplicateCustomerException e1) {
			// throw e1;
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}


}
