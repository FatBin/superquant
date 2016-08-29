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
<link href="../css/LoginAndReg.css" rel="stylesheet">

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
		<a class="profile floatRight" href="../Web_Pages/PersonalPage.jsp">
			<img alt="" src="../webImage/man.svg" class="headImage">
		</a>
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

		<div class="style_5 hidden-sm hidden-xs floatRight">
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
		StockDetailVO stockDetailVO = (StockDetailVO) session.getAttribute("StockDetail");
		String[][] data = stockDetailVO.getHistoryData();
		UpToDateStockPO uptodateStock = stockDetailVO.getUpToDateMessage();
		boolean isConcerned = stockDetailVO.isConcerned();
	%>

	<div class="headdiv">
		<span class="headlbl"><%=uptodateStock.getStockName()%>〔<%=uptodateStock.getStockId()%>〕</span>
		
		<a class="toAnalysis" href="AnalysisPage.jsp" style="">更专业的分析 ›</a>

		<span id="now"><%=uptodateStock.getNow()%></span> <span id="rise_fall"><%=uptodateStock.getRise_fall()%></span>
		
		<img title="关注该股" id="heartdiv" onclick="changePic()"
			src="../webImage/heart.png"> <span id="concernsuccess">
			<span class="glyphicon glyphicon-ok"></span> 关注成功
		</span> 
		
		
		
		<hr class="hrstyle" />

		<span class="fontlbl">换手率</span> <span class="fontcontent"><%=uptodateStock.getTurnover()%></span>
		<span class="fontlbl" style="left: 110px">量比</span> <span
			class="fontcontent" style="left: 110px"><%=uptodateStock.getQuantity_relative_ratio()%></span>
		<span class="fontlbl" style="left: 200px">主动率</span> <span
			class="fontcontent" style="left: 200px"><%=uptodateStock.getPositive()%></span>
		<span class="fontlbl" style="left: 305px">通吃率</span> <span
			class="fontcontent" style="left: 305px"><%=uptodateStock.getTongchilv()%></span>
		<span class="fontlbl" style="left: 405px">行业</span> <span
			class="fontcontent" style="left: 405px; font-size: 15px;"><%=uptodateStock.getIndustry()%></span>

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
	</blockquote>

	<!-- 综合分析 -->
	<div class="comprehensive_div">
		<div id="dashboard" class="chart_compre"></div>

		<div id="barChart_score" class="text_compre"></div>
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

	<!-- Modal -->
	<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog"
		aria-labelledby="modalLogin" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">登录SuperQuant</h4>
					<p>再添人生一桶金</p>
				</div>

				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible fade in"
						role="alert" id='modalLoginErr'>
						<span id='modalLoginErrCont'></span>
					</div>
					<div class='form-group'>
						<input type="text" class="form-control" id="modal-login-name"
							placeholder='昵称'>
					</div>
					<div class='form-group'>
						<input type="password" class="form-control" id="modal-login-pwd"
							placeholder='密码'>
					</div>
				</div>
				<!-- modal-body -->

				<div class="modal-footer">
					<a role="button" href="javascript:login();" class="btn btn-login"
						data-loading-text="验证中...">登录</a>
					<div class='modal-footer-tips'>
						<label style='float: left;'><input type="checkbox"
							id="chk-rmb" value="rmb" checked='checked'> 下次自动登陆</label> <a
							href='javascript:switchToModal("reg");'>没有账号？</a>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- End Modal -->

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
	<script src="../js/common.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/searchHint.js"></script>
	<script src="../jschart/ScoreBarChart.js"></script>
	<script src="../js/stockdetail_deal.js"></script>

	<script type="text/javascript">init_Pic(<%=isConcerned%>);</script>
</body>
</html>