package bookshop.biz.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceFactory {
	private DataSourceFactory() {
	}

	private static final String REF_NAME = "java:comp/env/jdbc/bookshop";
	private static DataSource ds = null;

	public static DataSource getDataSource() throws NamingException {
		/** DataSource 取得メソッド */
		try {
			if (ds == null) {
				// InitialContext オブジェクトの生成
				InitialContext ic = new InitialContext();
				// lookup() メソッドにJNDI参照名を渡して、JNDIリソースを取得
				ds = (DataSource) ic.lookup(REF_NAME);
			}
			return ds;
		} catch (NamingException e) {
			throw e;
		}
	}
}
