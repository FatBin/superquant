<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="VO.StockDetailVO"
	import="PO.UpToDateStockPO"%>
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

<link href="../css/stockdetailstyle.css" rel="stylesheet"
	type="text/css">

<script src="../js/jquery.min.js"></script>
<script src="../js/echarts.min.js"></script>

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
		StockDetailVO stockDetailVO = (StockDetailVO) session.getAttribute("StockDetail");
		String[][] data = stockDetailVO.getHistoryData();
		UpToDateStockPO uptodateStock = stockDetailVO.getUpToDateMessage();
	%>

	<div class="headdiv">
		<span class="headlbl"><%=uptodateStock.getStockName()%>〔<%=uptodateStock.getStockId()%>〕</span>

		<span id="now"><%=uptodateStock.getNow()%></span> 
		<span id="rise_fall"><%=uptodateStock.getRise_fall()%></span>

		<img title="关注该股" id="heartdiv" onclick="changePic()" src="../webImage/heart.png">

		<hr class="hrstyle" />

		<span class="fontlbl">换手率</span> 
		<span class="fontcontent"><%=uptodateStock.getTurnover() %></span>
		<span class="fontlbl" style="left:110px">量比</span> 
		<span class="fontcontent" style="left:110px"><%=uptodateStock.getQuantity_relative_ratio() %></span>
		<span class="fontlbl" style="left:200px">主动率</span> 
		<span class="fontcontent" style="left:200px"><%=uptodateStock.getPositive() %></span>
		<span class="fontlbl" style="left:305px">通吃率</span> 
		<span class="fontcontent" style="left:305px"><%=uptodateStock.getTongchilv() %></span>
		<span class="fontlbl" style="left:405px">行业</span> 
		<span class="fontcontent" style="left:405px; font-size:15px;"><%=uptodateStock.getIndustry() %></span>
		
	</div>

	<!-- k线图 -->
	<div id="klinechart" class="kline_div"></div>

	<blockquote class="quotelbl">
		<span class="headtext">近期走势图</span>
	</blockquote>

	<!-- 近期走势图 -->
	<div id="stock_history_chart" class="tendcy_div"></div>

	<blockquote class="quotelbl">
		<span class="headtext">综合分析</span>

		<a class="toAnalysis" href="Analysis.jsp">更专业的分析</a>
	</blockquote>

	<!-- 综合分析 -->
	<div class="comprehensive_div">
		<div id="dashboard" class="chart_compre"></div>
	</div>

	<blockquote class="quotelbl">
		<span class="headtext">详细数据</span>
	</blockquote>

	<!-- 详细数据 -->
	<div class="table_div">

		<table id="senfe">
			<thead>
				<tr align="center" valign="middle">
					<th width="200" bgcolor="#ccc">日期</th>
					<th width="130" bgcolor="#ccc">开盘价</th>
					<th width="130" bgcolor="#ccc">收盘价</th>
					<th width="130" bgcolor="#ccc">最高价</th>
					<th width="130" bgcolor="#ccc">最低价</th>
					<th width="130" bgcolor="#ccc">后复权价</th>
					<th width="130" bgcolor="#ccc">成交量</th>
					<th width="130" bgcolor="#ccc">换手率</th>
					<th width="130" bgcolor="#ccc">市盈率</th>
					<th width="130" bgcolor="#ccc">市净率</th>
				</tr>
			</thead>

			<tbody id="group_one">

				<%
					for (int i = 0; i < data.length; i++) {
				%>

				<tr align="center" valign="middle"
					onmouseover="mouseIn(<%=i + 1%>);"
					onmouseout="mouseOut(<%=i + 1%>);">
					<%
						for (int j = 0; j < data[0].length; j++) {
					%>
					<td height=35px;><%=data[i][j]%></td>
					<%
						}
					%>
				</tr>

				<%
					}
				%>
			</tbody>
		</table>

		<div style="margin-top: 20px; margin-left: 26%">
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

	<script src="../jschart/kLineChart.js"></script>
	<script type="text/javascript">
		getKLine("stock");
	</script>
	<script src="../jschart/StockHistoryChart.js"></script>
	<script src="../jschart/Dashboard.js"></script>
	<script src="../js/bootstrapSwitch.js"></script>
	<script src="../js/table_pages.js"></script>
	<script type="text/javascript" src="../js/searchHint.js"></script>

	<script>
		var nowspan = document.getElementById("now");
		var rfspan = document.getElementById("rise_fall");
		if(rfspan.innerHTML[0] == "-"){
			nowspan.style.color = "green";
			rfspan.style.color = "green";
		}else{
			nowspan.style.color = "red";
			rfspan.style.color = "red";
		}
		
		function changePic(){
			var src = document.getElementById("heartdiv").getAttribute("src");
			if(src == "../webImage/heart.png"){
				 document.getElementById("heartdiv").setAttribute("src", "../webImage/heart-selected.png");
			}else{
				document.getElementById("heartdiv").setAttribute("src", "../webImage/heart.png")
			}
		}
	</script>
</body>
</html>