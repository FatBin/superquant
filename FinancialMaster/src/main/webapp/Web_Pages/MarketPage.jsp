<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="VO.BenchVO" import=" VO.BenchListVO"%>
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

<link href="../css/marketstyle.css" rel="stylesheet" type="text/css">

<script src="../js/echarts.min.js"></script>
<script src="../js/jquery.min.js"></script>

<script>getKLine("market");</script>

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
				<li><a class="page-scroll a-active"
					href="../ToMarketPageServlet">大盘</a></li>
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
		BenchListVO benchListVO = (BenchListVO) session.getAttribute("BenchList");
		String[] benlist = benchListVO.getBenchList();

		BenchVO benchVO = (BenchVO) session.getAttribute("BenchMarket");
	%>

	<div class="combox">
		<select id="stockSelect" class="form-control" style="width: 120px;"
			onchange="changeStock(this.value);">

			<%
				for (int i = 0; i < benlist.length; i++) {
			%>

			<option>
				<%=benlist[i]%>
			</option>

			<%
				}
			%>
		</select>

	</div>

	<div class="headdiv">
		<span id="now"><%=benchVO.getNow()%></span> <span id="rise_fall"><%=benchVO.getRise_fall_price()%></span>
		<span id="rise_fall_percent"><%=benchVO.getRise_fall_percent()%>%</span>

		<span id="status" class="statelbl"><%=benchVO.getStatus()%></span> <span
			id="time" class="statelbl" style="left: 350px;"><%=benchVO.getTime()%></span>

		<hr class="hrstyle" />

		<span class="fontlbl">最高价</span> <span id="highprice"
			class="fontcontent" style="color: red"><%=benchVO.getHigh()%></span>
		<span class="fontlbl" style="left: 110px">最低价</span> <span
			id="lowprice" class="fontcontent" style="left: 110px; color: green;"><%=benchVO.getLow()%></span>
		<span class="fontlbl" style="left: 200px">今开</span> <span id="open"
			class="fontcontent" style="left: 200px"><%=benchVO.getOpen()%></span>
		<span class="fontlbl" style="left: 290px">昨收</span> <span id="close"
			class="fontcontent" style="left: 290px"><%=benchVO.getYesterday_close()%></span>
		<span class="fontlbl" style="left: 385px;">成交额</span> <span id="price"
			class="fontcontent" style="left: 385px; top: 102px; font-size: 15px;"><%=benchVO.getPrice()%></span>
		<span class="fontlbl" style="left: 475px">成交量</span> <span id="volume"
			class="fontcontent" style="left: 475px; top: 102px; font-size: 15px;"><%=benchVO.getVolume()%></span>
		<span class="fontlbl" style="left: 570px">涨家数</span> <span
			id="risecom" class="fontcontent" style="left: 570px"><%=benchVO.getRise_company()%></span>
		<span class="fontlbl" style="left: 640px">跌家数</span> <span
			id="fallcom" class="fontcontent" style="left: 640px"><%=benchVO.getFall_company()%></span>
		<span class="fontlbl" style="left: 710px">平家数</span> <span id="com"
			class="fontcontent" style="left: 710px"><%=benchVO.getCompany()%></span>
	</div>

	<div id="klinechart" class="kline_div"></div>

	<blockquote class="quotediv">
		<span class="headtext">历史数据</span>
	</blockquote>

	<!-- history data -->


	<%!BenchVO sv;
	String history_data[][];%>
	<%
		sv = (BenchVO) session.getAttribute("BenchMarket");
		history_data = sv.getData();
	%>

	<table id="senfe" class="table_div">
		<thead>
			<tr align="center" valign="middle">
				<td width="120" height="23" bgcolor="#ccc">日期</td>
				<td width="106" bgcolor="#ccc">开盘价</td>
				<td width="106" bgcolor="#ccc">最高价</td>
				<td width="106" bgcolor="#ccc">最低价</td>
				<td width="106" bgcolor="#ccc">收盘价</td>
				<td width="107" bgcolor="#ccc">成交量(百万股)</td>
				<td width="107" bgcolor="#ccc">成交额(亿元)</td>
			</tr>
		</thead>

		<tbody id="group_one">
			<%
				for (int i = 0; i < history_data.length; i++) {
			%>
			<tr align="center" valign="middle" onmouseover="mouseIn(<%=i + 1%>);"
				onmouseout="mouseOut(<%=i + 1%>);">

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

	<div class="pagelbl">
		<a onclick="page.firstPage();">首 页</a>/<a onclick="page.nextPage();">下一页</a>/<a
			onclick="page.prePage();">上一页</a>/<a onclick="page.lastPage();">末
			页</a><i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i><span
			id="divFood"> </span>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第 <input id="pageno"
			value="1" style="width: 30px" />页<a>&nbsp;&nbsp;</a><a
			onclick="page.aimPage();">跳转</a>
	</div>

	<!-- bottom section -->
	<div style="margin-top: 50px;">
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
	<script>getKLine("market");</script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/searchHint.js"></script>
	<script type="text/javascript" src="../js/market_deal.js"></script>
</body>
</html>