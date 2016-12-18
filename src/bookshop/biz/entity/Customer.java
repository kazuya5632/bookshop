package bookshop.biz.entity;

import java.io.Serializable;
import java.sql.SQLException;

import javax.naming.NamingException;

import bookshop.biz.exception.DuplicateCustomerException;

public class Customer implements Serializable {
	private String customerName;
	private String password;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setCreditCard() throws NamingException, SQLException, DuplicateCustomerException{
		Card card = new Card();
		card.setCard_company(null);
		card.setCard_number(null);
		card.setCard_month(null);
		card.setCard_year(null);
		card.setCard_name(null);
		card.setCard_security(null);
		card.setCustomer_name(customerName);
	}
	public void getCreditCard() throws NamingException, SQLException, DuplicateCustomerException{
		Card card = new Card();
		card.getCard_company();
		card.getCard_number();
		card.getCard_month();
		card.getCard_year();
		card.getCard_name();
		card.getCard_security();
		card.getCustomer_name();
	}
}
