<%@page import="PO.UpToDateStockPO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="VO.StockDetailVO"
	import="VO.Analyze_ResultVO" import="PO.UpToDateStockPO"%>
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

<link href="../css/analysistyle.css" rel="stylesheet" type="text/css">

<script src="../js/echarts.min.js"></script>
<script src="../js/jquery.min.js"></script>

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
			<!-- /.navbar-collapse -->
		</div>
		<div id="searchHint"></div>
	</div>
	<!-- /.container-fluid --> </nav>

	<%
		StockDetailVO stockDetailVO = (StockDetailVO) session.getAttribute("StockDetail");
		Analyze_ResultVO analyzeVO = stockDetailVO.getAnalyze_ResultVO();
		UpToDateStockPO uptodatePO = stockDetailVO.getUpToDateMessage();
	%>

	<div class="titlediv">
		<span class="titlelbl">个股分析：<%=uptodatePO.getStockName()%>〔<%=uptodatePO.getStockId()%>〕
		</span>
	</div>

	<!-- 综合分析 -->
	<div id="comprehensive" class="headdiv">
		<span class="headtext">综合分析</span>
	</div>

	<div class="bodydiv">
		<div id="dashboard" class="chart_compre"></div>

		<div id="barChart_score" class="text_compre"></div>
	</div>

	<!-- 技术分析 -->
	<div id="technical" class="headdiv">
		<span class="headtext">技术分析</span>
	</div>

	<div class="bodydiv">
		<div class="text_tech">
			<p><%=analyzeVO.getResult_of_technical_analyze()%></p>
		</div>

		<div id="TechnicalAnalyzeChart" class="chart_tech"></div>
	</div>

	<!-- 大盘分析 -->
	<div id="market" class="headdiv">
		<span class="headtext">大盘分析</span>
	</div>

	<div class="bodydiv">
		<div id="benchContrastChart" class="chart_market"></div>

		<div class="text_market">
			<p><%=analyzeVO.getResult_of_bench_analyze()%></p>
		</div>
	</div>

	<!-- 行业分析 -->
	<div id="business" class="headdiv">
		<span class="headtext">行业分析</span>
	</div>

	<div class="bodydiv">
		<div id="businessContrastChart" class="chart_business"></div>

		<div id="stockContrastChart" class="table_business"></div>

		<div class="text_business">
			<p><%=analyzeVO.getResult_of_business_analyze()%></p>
		</div>
	</div>

	<!-- 基本项 -->
	<div id="basic" class="headdiv">
		<span class="headtext">基本分析</span>
	</div>

	<div class="bodydiv">
		<div id="radarChart" class="chart_market" style="width: 500px;"></div>

		<div class="text_market">
			<p><%=analyzeVO.getResult_of_basic_analyze()%></p>
		</div>
	</div>

	<!-- 资金流向 -->
	<div id="moneyflow" class="headdiv">
		<span class="headtext">资金流向</span>
	</div>

	<div class="bodydiv">
		<div id="pieChart_inflows" class="chart_flow"></div>

		<div id="barChart_inflows" class="chart_2_flow"></div>

		<div id="barChart_dd" class="table_flow"></div>

		<div class="text_flow">
			<p><%=analyzeVO.getResult_of_inflows_analyze()%></p>
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
	<div style="margin-top: 50px;">
		<div style="background-color: #766F67; height: 200px;"></div>

		<div style="background-color: #645D55; height: 50px;">
			<p style="color: white; text-align: center; line-height: 50px;">@Copyright
				SuperQuant</p>
		</div>
	</div>

	<!-- 导航条 -->
	<div id="rightnav" class="right_nav"></div>

	<!-- Plugin JavaScript -->
	<script src="../js/rightnav.js"></script>
	<script>
		navskip();
	</script>

	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>

	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="../js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>

	<script type="text/javascript" src="../js/searchHint.js"></script>
	<script type="text/javascript" src="../js/rightnav.js"></script>
	<script src="../jschart/RadarChart.js"></script>
	<script src="../jschart/PieChart_inflows.js"></script>
	<script src="../jschart/barChart_inflows.js"></script>
	<script src="../jschart/Dashboard.js"></script>
	<script src="../jschart/DD_barChart.js"></script>
	<script src="../jschart/BenchContrastChart.js"></script>
	<script src="../jschart/BusinessContrastChart.js"></script>
	<script src="../jschart/StockContrastChart.js"></script>
	<script src="../jschart/TechnicalAnalyzeChart.js"></script>
	<script src="../jschart/ScoreBarChart.js"></script>

</body>
</html>