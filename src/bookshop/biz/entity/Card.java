package bookshop.biz.entity;

import java.io.Serializable;

public class Card implements Serializable {
	private String card_company;
	private String card_number;
	private String card_month;
	private String card_year;
	private String card_name;
	private String card_security;
	private String customer_name;

	public String getCard_company() {
		return card_company;
	}

	public void setCard_company(String card_company) {
		this.card_company = card_company;
	}

	public String getCard_number() {
		return card_number;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String costomer_name) {
		this.customer_name = costomer_name;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getCard_month() {
		return card_month;
	}

	public void setCard_month(String card_month) {
		this.card_month = card_month;
	}

	public String getCard_year() {
		return card_year;
	}

	public void setCard_year(String card_year) {
		this.card_year = card_year;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getCard_security() {
		return card_security;
	}

	public void setCard_security(String card_security) {
		this.card_security = card_security;
	}

}
