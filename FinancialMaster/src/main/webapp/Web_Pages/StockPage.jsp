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

</head>

<body id="page-top" class="index" style="height: 2000px;">

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

	<div>
		<p style="margin-top: 120px; font-size: 22px; margin-left: 180px;">股票列表</p>
		<div style="width: 850px; height: 489px; margin-left: 180px;">

			<!-- 股票列表 -->
			<div>
				<table id="senfe">
					<thead>
						<tr align="center" valign="middle">
							<td width="200" height="23" bgcolor="#ccc">股票代码</td>
							<td width="130" bgcolor="#ccc">股票名称</td>
							<td width="130" bgcolor="#ccc">开盘价</td>
							<td width="130" bgcolor="#ccc">最高价</td>
							<td width="130" bgcolor="#ccc">最低价</td>
							<td width="130" bgcolor="#ccc">收盘价</td>
							<td width="130" bgcolor="#ccc">交易量(百万股)</td>
							<td width="130" bgcolor="#ccc">涨跌幅</td>
						</tr>
					</thead>

					<tbody id="group_one">

						<%
							for (int i = 0; i < 50; i++) {
						%>

						<tr align="center" valign="middle" onmouseover="mouseIn(<%=i+1%>);"
							onmouseout="mouseOut(<%=i+1%>);" onclick="mouseClick(<%=i+1%>,'StockDetailPage.jsp')" >

							<%
								for (int j = 0; j < 8; j++) {
							%>

							<td height="23">(<%=i + 1%>, <%=j + 1%>)</td>

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
	</div>

	<!-- Plugin JavaScript -->
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>

	<script src="../js/table_pages.js"></script>

</body>

</html>