<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- saved from url=(0042)http://localhost:8080/bookshop/searchBooks -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
</head>
<body style="font-size: 12px;">
	ようこそ！ ${customer.customerName} さん！
	<div align="center">

		<h1>BookShop Online</h1>
		<br> <br> <br> <br> 検索結果 <br> <br>
		<div style="color: red; font-weight: bold;"></div>
		<table border="1" style="font-size: 12px;">
			<tbody>
				<tr>
					<th>ISBNコード</th>
					<th>書籍名</th>
					<th>出版社名</th>
					<th>著者名</th>
					<th>価格</th>
					<th>ショッピングカートに入れる</th>
				</tr>
				<c:forEach var="book" items="${bookList}">
					<tr>
						<td>${book.isbnCode}</td>
						<td>${book.bookName}</td>
						<td>${book.publisher}</td>
						<td>${book.author}</td>
						<td>￥${book.price}</td>
						<td><a
							href="http://localhost:8080/bookshop/addCart?isbnCode=${book.isbnCode}">カートに追加</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br> <br> <a
			href="http://localhost:8080/bookshop/searchBooksInit">検索ページ</a>
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