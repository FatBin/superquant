<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="VO.UserVO"%>
<%@page import="java.util.*"%>
<%@page import="DAO.pojo.Stock"%>
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

<link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	media="screen">

<link href="../css/personal.css" rel="stylesheet" type="text/css">

<script src="../js/echarts.min.js"></script>
<script src="../js/jquery.min.js"></script>

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
		<div class="navbar-header page-scroll floatRight">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!--  <a class="navbar-brand page-scroll" href="#page-top">Super Quant</a>  -->
		</div>


		<a href="HomePage.jsp" class="floatLeft"> <img
			src="../webImage/logo.png" title="返回首页" id="logo">
		</a>

		<%
			if (session.getAttribute("User") != null) {
		%>
		<%
			} else {
		%>
		<input type="button" value="登录" name="login"
			class="bottons loginbtn floatRight" data-toggle="modal"
			data-target="#modalLogin"
			style="margin-top: 8px; margin-right: -20px;" />
		<%
			}
		%>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse "
			id="bs-example-navbar-collapse-1" style="height: 216px;">
			<ul class="nav navbar-nav navbar-left ">
				<li class="hidden"><a href="#page-top"></a></li>

				<li><a class="page-scroll active" href="HomePage.jsp">首页</a></li>
				<li><a class="page-scroll" href="../ToMarketPageServlet">大盘</a></li>
				<li><a class="page-scroll" href="../ToStockPageServlet">个股</a></li>
				<li><a class="page-scroll" href="../ToBusinessPageServlet">行业</a></li>
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
			</ul>
		</div>

		<div class="style_5 hidden-sm hidden-xs floatRight"
			style="margin-right: -40px;">
			<fieldset id="searchform">
				<input type="text" placeholder="搜索" class="text_input"
					onblur="this.placeholder='搜索';"
					onfocus="this.placeholder='输入股票代码搜索';"
					onmouseover="this.placeholder='输入股票代码搜索';"
					onmouseout="this.placeholder='搜索';" onkeyup="showHint(this.value)" />
				<input name="submit" type="submit" value='' />
			</fieldset>
		</div>
		<div id="searchHint"></div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<%
		UserVO userVO = (UserVO) session.getAttribute("User");
		ArrayList<String> strategyList = userVO.getStrategy();
		ArrayList<Stock> stockList = userVO.getStockList();
	%>

	<div class="portrait middle">
		<div class="profile middle">
			<img src="../webImage/Portrait.png" class="img-responsive">
		</div>
		<div class="username"><%=userVO.getUsername()%></div>
	</div>

	<div class="middle liketitle">我的关注</div>
	<hr
		style="height: 2px; width: 600px; margin-left: auto; margin-right: auto;" />

	<%
		for (int i = 0; i < stockList.size(); i++) {
			Stock stock = stockList.get(i);
			String name = "b" + (i + "");
	%>
	<div class="business-list" onclick="jump(<%=i%>)">
		<div class="business-item">
			<div class="item-left item-top">
				<div class="item-name"><%=stock.getStockName()%></div>
			</div>
			<div class="item-price item-top">
				<div class="item-avg" id=<%=name%>><%=stock.getStockId()%></div>
			</div>
			<div class="item-right item-top">
				<div class="riser-price text-right">
					所属行业：<%=stock.getIndustry()%>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>

	<div class="middle liketitle" style="margin-top: 80px;">我的策略</div>
	<hr
		style="height: 2px; width: 600px; margin-left: auto; margin-right: auto;" />

	<%
		for (int i = 0; i < strategyList.size(); i++) {
			String strategy = strategyList.get(i);
			String strategyId = "c" + (i + "");
			String nameId = "name" + (i + "");
			String divId = "div" + (i + "");
	%>
	<div id=<%=divId%> class="business-list">
		<div class="business-item">
			<div class="item-left item-top">
				<div class="item-name">
					<a id=<%=nameId%> class="riser-price strategy-name"><%=strategy%></a>
				</div>
			</div>
			<div class="item-right item-top"
				style="margin-left: 120px; width: 200px;">
				<a class="riser-price text-right" style="margin-right: 20px;"
					onclick="deleteST(<%=i%>)">删除策略</a> <a
					class="riser-price text-right" onclick="runST((<%=i%>))">策略模拟</a>
			</div>
		</div>
	</div>
	<%
		}
	%>

	<!-- bottom section -->
	<div style="margin-top: 90px;">
		<div style="background-color: #766F67; height: 200px;"></div>

		<div style="background-color: #645D55; height: 50px;">
			<p style="color: white; text-align: center; line-height: 50px;">@Copyright
				SuperQuant</p>
		</div>
	</div>

	<!-- 策略详情 -->
	<div id="stDetail" class="content"
		style="display: block; width: 1000px; height: 240px;">
		<span_d class="out"></span_d>
		<span_d class="iner"></span_d>

		<table id="strategyTable" rules="rows">
			<thead>
				<tr align="center" valign="middle"
					style="background-color: rgb(230, 230, 230); font-size: 16px; display: none;">
					<td width="100" height="40">股票名称</td>
					<td width="100">投资成本</td>
					<td width="100">开始日期</td>
					<td width="100">结束日期</td>
					<td width="100">买卖频率</td>
					<td width="180">买入策略</td>
					<td width="180">卖出策略</td>
				</tr>
			</thead>
		</table>

	</div>

	<!-- 策略模拟 -->
	<div id="stRun" class="content"
		style="display: block; width: 1000px; height: 400px; display: none;">
		<span_d class="out"></span_d>
		<span_d class="iner"></span_d>
	</div>

	<!-- Plugin JavaScript -->
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>

	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="../js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>

	<script type="text/javascript" src="../js/searchHint.js"></script>

	<script src="../js/personal_deal.js"></script>
	<script type="text/javascript">

	function jump(i){
			var name = "b"+(i+"");
			$.ajax({
				type : "post",
				async : false, // 同步执行
				url : "../ToStockDetailPageServlet",
				data : {
					"Stockid" : document.getElementById(name).innerHTML
				},
				dataType : "json"
			})
			window.location.href = "../ToStockDetailPageServlet";
		}
	</script>
</body>
</html>