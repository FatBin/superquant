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

<link href="../css/bootstrapSwitch.css" rel="stylesheet" type="text/css">

<script src="../js/jquery.min.js"></script>
<script src="../js/echarts.min.js"></script>

<style>
li {
	list-style-type: none;
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
				<li><a class="page-scroll" href="../ToMarketPageServlet">大盘</a></li>
				<li><a class="page-scroll" href="StockPage.jsp">个股</a></li>
				<li><a class="page-scroll" href="BusinessPage.jsp">行业</a></li>
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
			</ul>
		</div>

		<div class="style_5 hidden-sm hidden-xs">
			<fieldset id="searchform">
				<input type="text" placeholder="搜索"
					class="text_input" onblur="this.placeholder='搜索';"
					onfocus="this.placeholder='输入股票代码搜索';"
					onmouseover="this.placeholder='输入股票代码搜索';"
					onmouseout="this.placeholder='搜索';" onkeyup="showHint(this.value)" />
				<input name="submit" type="submit" value='' />
			</fieldset>

			<div id="searchHint"
				style="position: absolute; background-color: rgb(235, 235, 235); width: 150px; margin-left: 945px; margin-top: -20px;">
			</div>
		</div>

		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div
		style="height: 40px; font-size: 28px; line-height: 40px; font-family: PingFangSC-Regular; margin-top: 140px; margin-left: 250px;">股票名称(编号)</div>

	<blockquote
		style="height: 50px; width: 760px; background-color: rgb(249, 248, 243); margin: 20px auto; margin-top: 20px;">
		<p style="line-height: 50px;">最新数据</p>
	</blockquote>

	<div class="switch switch-small"
		style="float: right; margin-right: 300px; margin-bottom: -30px;"
		data-on-label=" " data-off-label="对比">
		<input type="checkbox" unchecked />
	</div>

	<div id="klinechart"
		style="width: 850px; height: 910px; margin: 20px auto;"></div>

	<blockquote
		style="height: 50px; width: 760px; background-color: rgb(249, 248, 243); margin: 20px auto; margin-top: 20px;">
		<p style="line-height: 50px;">大盘分析</p>
	</blockquote>

	<div
		style="height: 143px; width: 910px; background-color: gray; margin: 20px auto;">
	</div>

	<blockquote
		style="height: 50px; width: 760px; background-color: rgb(249, 248, 243); margin: 20px auto; margin-top: 20px;">
		<p style="line-height: 50px;">行业对比</p>
	</blockquote>

	<div
		style="height: 400px; width: 910px; background-color: gray; margin: 20px auto;">
	</div>

	<blockquote
		style="height: 50px; width: 760px; background-color: rgb(249, 248, 243); margin: 20px auto; margin-top: 20px;">
		<p style="line-height: 50px;">详细数据</p>
	</blockquote>

	<div
		style="height: 400px; width: 910px; background-color: gray; margin: 20px auto;">表格
	</div>

	<!-- Plugin JavaScript -->
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>

	<script src="../jschart/kLineChart.js"></script>
	<script type="text/javascript">
		getKLine("market", "hs300");
	</script>
	<script src="../js/bootstrapSwitch.js"></script>

	<script type="text/javascript" src="../js/searchHint.js"></script>

</body>
</html>