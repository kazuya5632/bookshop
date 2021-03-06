<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- saved from url=(0039)http://localhost:8080/bookshop/viewcart -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ショッピングカート</title>
</head>
<body style="font-size: 12px;">
	ようこそ！ ${customer.customerName} さん！
	<div align="center">

		<h1>BookShop Online</h1>
		<br> <br> <br> <br> ショッピングカート <br> <br>
		<div style="color: red; font-weight: bold;">${message}</div>


		<c:choose>
			<c:when test="${empty shoppingCart.cartItems}">
					ショッピングカートの中は空っぽです <br>
				<br>
			</c:when>
			<c:otherwise>
				<table border="1" style="font-size: 12px;">
					<tbody>
						<tr>
							<th>ISBNコード</th>
							<th>書籍名</th>
							<th>出版社名</th>
							<th>著者名</th>
							<th>価格</th>
							<th>数量</th>
							<th>小計</th>
							<th>削除</th>
						</tr>

						<c:forEach var="cart" items="${shoppingCart.cartItems}">
							<tr>
								<td>${cart.book.isbnCode}</td>
								<td>${cart.book.bookName}</td>
								<td>${cart.book.publisher}</td>
								<td>${cart.book.author}</td>
								<td>￥${cart.book.price}</td>
								<td>
									<form method="post"
										action="http://localhost:8080/bookshop/modifyCount">
										<input type="text" name="count" value="${cart.count}" size="3"> <input
											type="hidden" name="isbnCode" value="${cart.book.isbnCode}">
										<input type="submit" value="変更">
									</form>
								</td>
								<td>￥${cart.getAmount()}</td>
								<td>
									<form method="post"
										action="http://localhost:8080/bookshop/deleteBook">
										<input type="hidden" name="isbnCode"
											value="${cart.book.isbnCode}"> <input type="submit"
											value="削除">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
		  		<br> 合計金額 ￥${shoppingCart.totalAmount} <br>
				<br>
				<a href="http://localhost:8080/bookshop/selectchargeinit">精算する</a>
				<br>
				<br>

			</c:otherwise>
		</c:choose>

		<a href="http://localhost:8080/bookshop/searchBooksInit">検索ページ</a>
		&nbsp;&nbsp;&nbsp; <a href="http://localhost:8080/bookshop/viewcart">ショッピングカートの中を見る</a>
		&nbsp;&nbsp;&nbsp; <a href="http://localhost:8080/bookshop/logout">ログアウト</a>

	</div>


	<style>
.tb_button {
	padding: 1px;
	cursor: pointer;
	border-right: 1px solid #8b8b8b;
	border-left: 1px solid #FFF;
	border-bottom: 1px solid #fff;
}

.tb_button.hover {
	borer: 2px outset #def;
	background-color: #f8f8f8 !important;
}

.ws_toolbar {
	z-index: 100000
}

.ws_toolbar .ws_tb_btn {
	cursor: pointer;
	border: 1px solid #555;
	padding: 3px
}

.tb_highlight {
	background-color: yellow
}

.tb_hide {
	visibility: hidden
}

.ws_toolbar img {
	padding: 2px;
	margin: 0px
}
</style>
</body>
</html>