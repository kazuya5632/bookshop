package bookshop.biz.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import bookshop.biz.entity.Book;
import bookshop.biz.entity.CartItem;

public class ShoppingCart implements Serializable {
	private Map<String, CartItem> cartItems = null;

	public ShoppingCart() {
		cartItems = new HashMap<String, CartItem>();
	}

	// カート追加
	public void addCart(String isbnCode) throws NamingException, SQLException {
		try {
			CartItem item = cartItems.get(isbnCode);
			int count;

			if (item == null) {
				BookService service = new BookService();
				Book book = service.getBook(isbnCode);

				item = new CartItem();
				item.setBook(book);
				item.setCount(1);

				cartItems.put(isbnCode, item);
			} else if (item != null) {
				count = item.getCount();
				item.setCount(count + 1);
			}
		} catch (NamingException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
	}

	// カート数量変更
	public void modifyBookCount(String isbnCode, int count) {
		if (count == 0) {
			removeItem(isbnCode);
		} else {
			CartItem item = cartItems.get(isbnCode);
			item.setCount(count);
		}
	}

	// カート削除
	public void removeItem(String isbnCode) {
		cartItems.remove(isbnCode);
	}

	public int getTotalAmount() {
		// Iterator<Map<String, CartItem>> iter = cartItems.iterator();
		int totalAmount = 0;
		for (CartItem item : cartItems.values()) {
			totalAmount = totalAmount + item.getAmount();
		}
		return totalAmount;
	}

	public Collection<CartItem> getCartItems() {
		return cartItems.values();

	}
}
