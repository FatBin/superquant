<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SuperQuant--您的第一手证券信息</title>

<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">

<!-- Custom CSS -->
<link href="../css/agency.css" rel="stylesheet" type="text/css">

<!-- Custom Fonts -->
<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link href="../css/headNav.css" rel="stylesheet" type="text/css">

<style>
.combox {
	font-size: 40px;
}
</style>

</head>

<body id="page-top" class="index">

	<nav class="navbar navbar-default navbar-fixed-top"
		style="background-color: #4A433B;">

	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!--  <a class="navbar-brand page-scroll" href="#page-top">Super Quant</a>  -->
			<img src="../webImage/logo.png" title="返回顶部" id="logo">
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1" style="height: 216px;">
			<ul class="nav navbar-nav navbar-left">
				<li class="hidden"><a href="#page-top"></a></li>

				<li><a class="page-scroll" href="HomePage.jsp">首页</a></li>
				<li><a class="page-scroll" href="MarketPage.jsp">大盘</a></li>
				<li><a class="page-scroll" href="StockPage.jsp">个股</a></li>
				<li><a class="page-scroll" href="BusinessPage.jsp">行业</a></li>
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
			</ul>
		</div>

		<div class="style_5">
			<form method="get" id="searchform" action="">
				<fieldset>
					<input id="s" name="s" type="text" placeholder="搜索"
						class="text_input" onblur="this.placeholder='搜索';"
						onfocus="this.placeholder='输入股票代码搜索';"
						onmouseover="this.placeholder='输入股票代码搜索';"
						onmouseout="this.placeholder='搜索';" /> <input name="submit"
						type="submit" value='' />
				</fieldset>
			</form>
		</div>

		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div style="margin-top: 140px; margin-left: 280px;">
		<form name="stockName">
			<select class="combox">
				<option value="000001">上证指数(000001)</option>
				<option value="000300">沪深300(000300)</option>
			</select>
		</form>
	</div>
	
	<div style="height:50px; width:800px; background-color:rgb(249,248,243); margin-left:225px;margin-top:20px;"></div>

	<div id="klinechart"
		style="width: 850px; height: 400px; margin-left: 200px; margin-top: 20px;"></div>
		
	<div id="barchart" style="width: 850px; height:300px; margin-left:200px; margin-top:20px;"></div>

	<div>
		<h1 style="text-align: center; margin-top: 200px; color: black;">大盘</h1>
	</div>

	<!-- Plugin JavaScript -->
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>

	<script src="../js/echarts.min.js"></script>
	<script src="../js/jquery.min.js"></script>
	<script src="../jschart/kLineChart.js"></script>
	<script src="../jschart/barChart.js"></script>

</body>
</html>