<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0049)http://localhost:8080/bookshop/registcustomerinit -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ登録</title>
</head>
<body style="font-size: 12px;">
	<div align="center">

		<h1>BookShop Online</h1>
		<br> <br> <br> <br> ユーザ登録 <br> <br>

		<form method="post"
			action="http://localhost:8080/bookshop/registcustomer">
			<div style="color: red; font-weight: bold;">${message}</div>
			<table style="font-size: 12px; border: 0px;">
				<tbody>
					<tr>
						<td>登録ユーザ名</td>
						<td><input type="text" name="customerName" size="30">
						</td>
					</tr>
					<tr>
						<td>登録パスワード</td>
						<td><input type="password" name="password" size="30" value="">
						</td>
					</tr>
					<tr>
						<td>登録パスワード（確認用）</td>
						<td><input type="password" name="passwordConfirm" size="30"
							value=""></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="登録">
		</form>
		<br> <br> <a href="http://localhost:8080/bookshop/">ログイン画面に戻る</a>
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