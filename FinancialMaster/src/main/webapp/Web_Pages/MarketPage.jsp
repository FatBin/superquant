<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="VO.StockMarketVO"%>
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

<script src="../js/echarts.min.js"></script>
<script src="../js/jquery.min.js"></script>

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

		<div class="style_5">
			<form method="get" id="searchform" action="">
				<fieldset>
					<input id="s" name="s" type="text" placeholder="搜索"
						class="text_input" onblur="this.placeholder='搜索'"
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

	<!-- 
	<div style="margin-top: 140px; margin-left: 140px;">
		<form name="stockName">
			<select class="combox">
				<option value="000001">上证指数(000001)</option>
				<option value="000300">沪深300(000300)</option>
			</select>
		</form>
	</div>
 -->

	<div class="dropdown" style="margin-top: 140px; margin-left: 140px;">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu1" data-toggle="dropdown">
			上证指数(000001) <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="#" onclick="changeToSZ()">上证指数(000001)</a></li>
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="#" onclick="changeToHS()">沪深300 (000300)</a></li>
		</ul>
	</div>

	<div>
		<!-- left part -->
		<div style="float: left;">
			<blockquote
				style="height: 50px; width: 760px; background-color: rgb(249, 248, 243); margin-left: 95px; margin-top: 20px;">
				<p style="line-height: 50px;">最新数据</p>
			</blockquote>

			<div id="klinechart"
				style="width: 850px; height: 910px; margin-left: 60px; margin-top: 20px;"></div>

		</div>

		<!-- right part -->
		<div
			style="float: right; background-color: rgb(239, 239, 239); height: 900px; width: 300px; margin-right: 40px; margin-top: 20px;">
			<p>分析模块</p>
		</div>

	</div>

	<!-- history data -->
	<div
		style="float: left; height: 300px; width: 1000px; margin-left: 185px; margin-top: 20px;">

		<div>

			<%!StockMarketVO sv;
			   String history_data[][];%>
			<%
				sv = (StockMarketVO) session.getAttribute("BenchMarket");
				history_data = sv.getData();
			%>

			<table id="senfe" style="">
				<thead>
					<tr align="center" valign="middle">
						<td width="200" height="23" bgcolor="#ccc">日期</td>
						<td width="130" bgcolor="#ccc">开盘价</td>
						<td width="130" bgcolor="#ccc">最高价</td>
						<td width="130" bgcolor="#ccc">最低价</td>
						<td width="130" bgcolor="#ccc">收盘价</td>
						<td width="130" bgcolor="#ccc">成交量(百万股)</td>
					</tr>
				</thead>

				<tbody id="group_one">
					<%
						for (int i = 0; i < history_data.length; i++) {
					%>
					<tr align="center" valign="middle">

						<%
							for (int j = 0; j < history_data[0].length; j++) {
						%>

						<td height="23"><%=history_data[i][j]%></td>

						<%
							}
						%>
					</tr>
					<%
						}
					%>

				</tbody>
			</table>

		</div>
		<div style="margin-left: 240px;">
			<a onclick="page.firstPage();">首 页</a>/<a onclick="page.nextPage();">下一页</a>/<a
				onclick="page.prePage();">上一页</a>/<a onclick="page.lastPage();">末
				页</a><i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i><span
				id="divFood"> </span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第 <input id="pageno"
				value="1" style="width: 30px" />页<a>&nbsp;&nbsp;</a><a
				onclick="page.aimPage();">跳转</a>
		</div>
	</div>

	<!-- bottom section -->
	<div style="margin-top: 1380px;">
		<div style="background-color: #766F67; height: 200px;"></div>

		<div style="background-color: #645D55; height: 50px;">
			<p style="color: white; text-align: center; line-height: 50px;">@Copyright
				SuperQuant</p>
		</div>
	</div>


	<!-- Plugin JavaScript -->
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>

	<script src="../js/table_pages.js"></script>

	<script src="../jschart/kLineChart.js"></script>

	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>

	<script>
		function changeToSZ() {
			//	document.getElementById("dropdownMenu1").innerHTML = "上证指数(000001) ▾";
			$(".dropdown-toggle").html("上证指数(000001) ▾");
		}

		function changeToHS() {
			//  document.getElementById("dropdownMenu1").innerHTML = "沪深300 (000300) ▾";
			$(".dropdown-toggle").html("沪深300 (000300) ▾");
		}
	</script>

</body>
</html>