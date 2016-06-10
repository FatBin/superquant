
<%@page import="PO.industryPO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="VO.BusinessListVO"%>
<%@page import="PO.industriesPO"%>
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
<link href="../css/structure.css" rel="stylesheet" type="text/css">
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
			data-target="#modalLogin" style="margin-top: 8px; margin-right: -20px;" />
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

			<div id="searchHint"
				style="background-color: rgb(235, 235, 235); width: 150px;"></div>
		</div>





		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<%
		BusinessListVO businessListVO = (BusinessListVO) session.getAttribute("BusinessList");
		ArrayList<industriesPO> businessList = businessListVO.getBusinessList();
	%>

	<div class="container">
		<div class="compare" style="height: 450px;">
			<h3 class="title" style="margin-top: 140px; height: 45px;">行业涨跌榜</h3>
			<div id="business_barchart"
				style="width: 100%; height: 400px; margin-left: auto; margin-right: auto;"></div>
		</div>

		<div class="business-rank" style="height: 1000px;">
			<h3 class="title">行业对比</h3>
			<%
				for (int i = 0; i < businessList.size(); i++) {
			%>
			<div class="business-list">
				<div class="business-item">
					<div class="item-left item-top">
						<%
							industriesPO list = businessList.get(i);
								String name = "b" + (i + "");
						%>
						<div class="item-name" id=<%=name%>><%=list.getIndustry()%></div>
						<div class="item-num">
							<%=list.getCompany() + ""%>
							家企业
						</div>
					</div>

					<div class="item-price item-top">
						<%
							if (list.getRise_fall() > 0) {
						%>
						<div class="item-rate red">
							<%=list.getRise_fall()%>
							%
						</div>
						<div class="glyphicon glyphicon-arrow-up red"></div>
						<%
							} else {
						%>
						<div class="item-rate green">
							<%=list.getRise_fall()%>
							%
						</div>
						<div class="glyphicon glyphicon-arrow-down green"></div>
						<%
							}
						%>
						<div class="item-avg">
							<%=list.getAverage_price()%>

						</div>

					</div>

					<div class="item-right item-top">
						<div class="riser text-right">
							<a style="color: #eb8a31;">领涨股：<%=list.getLeaderstock()%></a>
						</div>
						<div class="riser-price text-right"><%=list.getPrice()%>
							元
						</div>
						<div class="glyphicon glyphicon-arrow-up red"
							style="float: right; margin-top: 3px;"></div>
						<div class="riser-rate text-right red"><%=list.getStock_rise_fall()%>%
						</div>

					</div>

				</div>
				<%
					String a = "a" + (3 * i + "");
						String b = "a" + (3 * i + 1 + "");
						String c = "a" + (3 * i + 2 + "");
				%>
				<div class="business-extend" style="display: none;">
					<div id=<%=a%> class="pie"></div>
					<div id=<%=b%> class="pie"></div>
					<div id=<%=c%> class="pie"></div>

					<div class="ToDetail" onclick="jump(<%=i%>)">查看详情</div>
				</div>
			</div>
			<%
				}
			%>
			<!-- <nav>
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">6</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
			</nav> -->
		</div>
	</div>

	<!-- bottom section -->
	<div
		style="background-color: #766F67; height: 200px; margin-top: 10px; display: block;"></div>

	<div style="background-color: #645D55; height: 50px;">
		<p style="color: white; text-align: center; line-height: 50px;">@Copyright
			SuperQuant</p>
	</div>


	<!-- Plugin JavaScript -->

	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>
	<script src="../jschart/businessBarChart.js"></script>
	<script src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/searchHint.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../jschart/PieChart_StockMessage.js"></script>
	<script type="text/javascript">
	 <%for (int i = 0; i < 66; i++) {
				industriesPO list = businessList.get(i);%>
		pieChart('成交量', <%=list.getVolume()%>, 0, 0, 18, 'a'+(3*<%=i%>+''), '#D34E4E')
		pieChart('总成交额', <%=list.getTurnover()%>, 0, 1, 18, 'a'+(3*<%=i%>+1+''), '#4A433B')
		pieChart('流入资金量', <%=list.getInflows()%>, 0, 1, 18, 'a'+(3*<%=i%>+2+''), '#4E58B5')
	 <%}%>
	</script>

	<script type="text/javascript">
	$(".business-rank").on("click", ".business-list", function() {
		$(this).children(".business-extend").slideToggle();
	});
	
	function jump(i){
		var name = "b"+(i+"");	
		$.ajax({
			type : "get",
			async : false, // 同步执行
			url : "../ToBusinessDetailPageServlet",
			data : {
				"BusinessName" : document.getElementById(name).innerHTML
			},
			dataType : "json",
			success : function() {
				window.location.href = "../Web_Pages/BusinessDetailPage.jsp";
			},
			error : function(errorMsg) {
				alert("不好意思，请求数据失败啦!");
			}
		})
	}
</script>
</body>




</html>