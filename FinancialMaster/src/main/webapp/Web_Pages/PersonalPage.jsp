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
			<img src="../webImage/logo.png" title="返回首页" id="logo">
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1" style="height: 216px;">
			<ul class="nav navbar-nav navbar-left">
				<li class="hidden"><a href="#page-top"></a></li>

				<li><a class="page-scroll" href="HomePage.jsp">首页</a></li>
				<li><a class="page-scroll" href="../ToMarketPageServlet">大盘</a></li>
				<li><a class="page-scroll" href="../ToStockPageServlet">个股</a></li>
				<li><a class="page-scroll" href="../ToBusinessPageServlet">行业</a></li>
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
			</ul>
		</div>

		<div class="style_5 hidden-sm hidden-xs">
			<fieldset id="searchform">
				<input type="text" placeholder="搜索" class="text_input"
					onblur="this.placeholder='搜索';"
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

	<!-- bottom section -->
	<div style="margin-top: 90px;">
		<div style="background-color: #766F67; height: 200px;"></div>

		<div style="background-color: #645D55; height: 50px;">
			<p style="color: white; text-align: center; line-height: 50px;">@Copyright
				SuperQuant</p>
		</div>
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
	<script type="text/javascript">
		/* $(".business-list").on("click", ".business-item", function(i) {
			var name = $(this).children('item-price').children('item-avg').attr('id');
			jump(name);
			alert(name);
		}); */
		
		
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