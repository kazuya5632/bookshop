<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>お支払方法</title>
<script type="text/javascript">
	function RegistON() {
		for (i = 0; i < document.form1.elements.length; i++) {
			document.form1.elements[i].disabled = false;
		}
	}
	function RegistOFF() {
		for (i = 4; i < document.form1.elements.length; i++) {
			document.form1.elements[i].disabled = true;
		}
		document.getElementById("submit").disabled = false;
		document.getElementById("card1").style.display = "none";
	}
	function CardON() {
		document.getElementById("card1").style.display = "";
		document.getElementById("reg3").disable = false;
		document.getElementById("reg4").disable = false;
	}
	function CardRegistOFF() {
		for (i = 4; i < document.form1.elements.length; i++) {
			document.form1.elements[i].disabled = true;
		}
		document.getElementById("submit").disabled = false;
	}
</script>
</head>
<body style="font-size: 12px;" onload="RegistOFF()">
	ようこそ！ ${customer.customerName} さん！
	<div align="center">

		<h1>BookShop Online</h1>
		<br> <br> <br> <br> お支払方法 <br> <br>


		<form name="form1" action="http://localhost:8080/bookshop/charge"
			method="post">


			<table style="width: 274px;">
				<tr>
					<td><input type="radio" name="reg" id="reg1"
						onclick="RegistOFF()" value="1" checked><label for="reg1">代金引換</label></td>
				</tr>
				<tr>
					<td><input type="radio" name="reg" id="reg2"
						onclick="CardON()" value="2"><label for="reg2">クレジットカード決済</label></td>
				</tr>
			</table>
			<br>
			<table id="card1" style="width: 201px;">
				<tr>
					<td><input type="radio" name="reg2" id="reg4" value="1"
						onclick="CardRegistOFF()" checked><label for="reg4">前回のカードを利用
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${card.card_number}&nbsp;${card.card_company}&nbsp;${card.card_month}${card.card_year}
					</label></td>
				</tr>
				<tr>
					<td><input type="radio" name="reg2" id="reg3" value="2"
						onclick="RegistON()"><label for="reg3">新規カードを利用</label></td>
				</tr>
			</table>

			<br>
			<fieldset
				style="border: double blue; background-color: mistyrose; width: 500px">
				<legend style="color: red; font: 16;">カード情報入力フォーム</legend>

				● カード会社
				<div>
					<select name="card_company" size="1">
						<option value="">---------------</option>
						<option value="VISA">VISA</option>
						<option value="MASTER">MASTER</option>
						<option value="JCB">JCB</option>
						<option value="Diners">Diners</option>
						<option value="AMEX">AMEX</option>
					</select>
				</div>
				<br> ● カード番号
				<div>
					<input type="text" name="card_number" size="30">
				</div>
				<br> ● 有効期限
				<div>
					<select name="card_month" size="1">
						<option value="">-----</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select> 月 <select name="card_year" size="1">
						<option value="">-----</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
					</select> 年
				</div>
				<br> ● 名義人
				<div>
					<input type="text" name="card_name" size="30">
				</div>
				<br> ● セキュリティコード
				<div>
					<input type="text" name="card_security" size="3">
				</div>
				<br>

				<div style="color: red; font-weight: bold;">${message}</div>
			</fieldset>
			<br>
			<div>
				<input type="submit" name="submit" id="submit" value="注文を確定する">
			</div>
		</form>
	</div>
	<br>
	<br>

	<div align="center">
		<a href="http://localhost:8080/bookshop/viewcart">戻る</a> <br> <br>
	</div>
	<table align="center">
		<tr>
			<td><a href="http://localhost:8080/bookshop/searchBooksInit">検索ページ</a></td>
			<td>&nbsp;&nbsp;&nbsp; <a
				href="http://localhost:8080/bookshop/viewcart">ショッピングカートの中を見る</a></td>
			<td>&nbsp;&nbsp;&nbsp; <a
				href="http://localhost:8080/bookshop/logout">ログアウト</a></td>
		</tr>
	</table>

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