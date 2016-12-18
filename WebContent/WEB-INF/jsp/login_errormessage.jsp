<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0036)http://localhost:8080/bookshop/login -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
</head>
<body style="font-size: 12px;">
	<div align="center">

		<h1>BookShop Online</h1>
		<br> <br> <br> <br> ログイン <br> <br>
		<form method="post"
			action="./login_errormessage_files/login_errormessage.html">
			<div style="color: red; font-weight: bold;">ログインユーザ名を入力してください</div>
			<table style="font-size: 12px; border: 0px;">
				<tbody>
					<tr>
						<td>ログインユーザ名</td>
						<td><input type="text" name="customerName" size="30">
						</td>
					</tr>
					<tr>
						<td>ログインパスワード</td>
						<td><input type="password" name="password" size="30">
						</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="ログイン">
		</form>
		<br> <br> ユーザ登録していない場合は最初に <a
			href="http://localhost:8080/bookshop/registcustomerinit">ユーザ登録</a>
		してください。
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