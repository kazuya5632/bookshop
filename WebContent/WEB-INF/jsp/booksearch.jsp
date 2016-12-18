<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>書籍検索</title>
</head>
<body style="font-size: 12px;">
	ようこそ！ ${customer.customerName} さん！
	<div align="center">

		<h1>BookShop Online</h1>
		<br> <br> <br> <br> 書籍検索 <br> <br>
		<div style="color: red; font-weight: bold;">${message}</div>
		<form method="post"
			action="http://localhost:8080/bookshop/searchBooks">
			<table style="font-size: 12px; border: 0px;">
				<tbody>
					<tr>
						<th>検索モード</th>
						<td><input type="radio" name="searchMode" value="all"
							checked="checked">全件検索<br> <input type="radio"
							name="searchMode" value="bookName">書籍名で検索<br> <input
							type="radio" name="searchMode" value="author">著者名で検索<br>
							<input type="radio" name="searchMode" value="publisher">出版社名で検索<br>
							<input type="radio" name="searchMode" value="isbnCode">ISBNコードで検索<br>
						</td>
					</tr>
					<tr>
						<th>検索条件</th>
						<td><input type="text" name="condition" size="40"></td>
					</tr>
					<tr>
						<td><input type="submit" value="検索"></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</form>

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