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
<link href="../css/LoginAndReg.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link href="../css/headNav.css" rel="stylesheet" type="text/css">

<link href="../css/strategystyle.css" rel="stylesheet" type="text/css">

<link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	media="screen">

<link rel="stylesheet"
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">

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

				<li><a class="page-scroll" href="HomePage.jsp">首页</a></li>
				<li><a class="page-scroll" href="../ToMarketPageServlet">大盘</a></li>
				<li><a class="page-scroll" href="../ToStockPageServlet">个股</a></li>
				<li><a class="page-scroll" href="../ToBusinessPageServlet">行业</a></li>
				<li><a class="page-scroll" style="color: rgb(253, 208, 72);" href="StrategyPage.jsp">策略</a></li>
				<li><a class="page-scroll" href="SimulatePage.jsp">模拟投资</a></li>
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
		<div id="searchHint"
			style="position: absolute; background-color: rgb(235, 235, 235); width: 150px; margin-left: 935px; margin-top: -10px;"></div>

		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- 新建策略 -->
	<div class="makest_div" id="makest" style="display: block;">

		<i class="fa fa-times close_st" onclick="closeST()"></i>
	
		<!-- left part -->
		<div style="display: inline-block;">
			<div class="div_title">制定策略</div>

			<div
				style="margin-left: 45px; margin-top: 15px; display: inline-block;">
				<input class="st_textfileds" id="strategyname" placeholder="策略名称">
			</div>

			<div
				style="margin-left: 10px; margin-top: 15px; display: inline-block;">
				<input class="st_textfileds" id="totalcost" placeholder="本金（元）"
					onBlur="setName()">
			</div>

			<hr class="hr_style">

			<div>
				<div style="margin-left: 45px; display: inline-block;">
					<input class="st_textfileds" id="stockchoose" placeholder="输入股票"
						onkeyup="showHint_st(this.value)">
				</div>

				<div id="stHint"></div>

				<div style="margin-left: 10px; display: inline-block;">
					<input class="st_textfileds" id="cost" placeholder="投入资金数">
				</div>
			</div>

			<div>
				<div class="selectStyle date_divs">
					<input class="st_textfileds" id="startdate" placeholder="开始时间"
						readonly>
				</div>

				<div class="selectStyle date_divs" style="margin-left: 10px;">
					<input class="st_textfileds" id="enddate" placeholder="结束时间"
						readonly>
				</div>
			</div>

			<div>
				<div class="selectStyle date_divs">
					<input id="buyinst" class="st_textfileds" placeholder="买入策略"
						readonly onclick="showSTmake(this)">
				</div>

				<div class="selectStyle date_divs" style="margin-left: 10px;">
					<input id="soldoutst" class="st_textfileds" placeholder="卖出策略"
						readonly onclick="showSTmake(this)">
				</div>
			</div>

			<div>
				<div class="selectStyle date_divs">
					<input id="otherst" class="st_textfileds_readonly"
						placeholder="其他策略" readonly>
				</div>

				<div class="date_divs" style="margin-left: 10px;">
					<input id="frequency" class="st_textfileds" placeholder="买卖频率（天）">
				</div>
			</div>

			<div class="money_left">
				剩余本金:&nbsp;&nbsp;<span id="moneyleft"></span>
			</div>

			<div id="addST" class="add_st_btn" onclick="addStrategy()">添加股票项</div>

			<div id="resetbtn" class="reset_st_btn" onclick="resetAll_btn()">重置</div>
		</div>

		<div id="add_before" class="st_run_div">
			<img style="width: 100%;" src="../webImage/strategy_tip.png">
		</div>

		<div id="add_after" class="st_run_div" style="display: none">

			<div class="used_money">
				已用本金：<span id="usedmoney"></span>
			</div>

			<div class="add_st_btn save_btn" onclick="saveST();">保存策略</div>

			<div class="run_pic">
				<div id="strategyLineChart"
					style="width: 580px; height: 254px; display: none;"></div>
			</div>

			<div class="benifit_compare">
				策略收益：<span id="real_benefit">500元</span>&nbsp;/ <span
					id="max_benefit">10万</span>（完美利益）
			</div>

			<div class="note_div">完美利益为最高点卖最低点买所获收益</div>

		</div>

		<!-- 股票策略列表 -->
		<div id="myST" style="display: none;">
			<!-- 我的策略  -->
			<div
				style="font-size: 20px; line-height: 35px; margin-top: 40px; margin-left: 45px;">
				<span id="stname">我的策略</span><span id="stcost"
					style="font-size: 13px;"></span>
			</div>

			<hr
				style="width: 210px; border: solid 1px rgb(235, 235, 235); margin-left: 45px; margin-top: 0px;" />

			<!-- 删除、保存 -->
			<div class="reset_st_btn"
				style="margin-top: -10px; margin-bottom: 10px; margin-left: 45px; width: 140px;"
				onclick="deleteST();">删除选中股票</div>

			<div
				style="margin-left: 45px; width: 1000px; border: dashed 1px rgb(200, 200, 200);">

				<table id="strategyTable" rules="rows">
					<thead>
						<tr align="center" valign="middle"
							style="background-color: rgb(230, 230, 230); font-size: 16px;">
							<td width="60">
								<div style="margin-left: -20px;">
									<input type="checkbox" onchange="selectAll();" />
								</div>
							</td>

							<td width="105" height="40">股票名称</td>
							<td width="85">投资成本</td>
							<td width="105">开始日期</td>
							<td width="105">结束日期</td>
							<td width="192">买入策略</td>
							<td width="192">卖出策略</td>
							<td width="90">其他策略</td>
							<td width="80">买卖频率</td>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>

	<%-- 无阻塞提示框 --%>
	<div id="toaster_close">
		<div id="toaster">
			<div id="pic_div" class="green_pic"></div>
			<div id="remind" class="green_word">提示消息</div>
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
	<div style="margin-top: 165px;">
		<div style="background-color: #766F67; height: 200px;"></div>

		<div style="background-color: #645D55; height: 50px;">
			<p style="color: white; text-align: center; line-height: 50px;">@Copyright
				SuperQuant</p>
		</div>
	</div>

	<!-- 策略制定 添加策略时显示 -->
	<div id="stMake" class="content"
		style="display: none; width: 290px; height: 240px;">
		<span_d class="out"></span_d>
		<span_d class="iner"></span_d>

		<!-- 价格 -->
		<div class="combox">
			<input id="price_box" type="checkbox" /> <span>价格区间</span> <input
				id="getprice_low" class="form-control text_low" type="text" readonly>
			<input id="getprice_high" class="form-control text_high" type="text"
				readonly>
		</div>

		<!-- 成交量 -->
		<div class="combox">
			<input id="volume_box" type="checkbox" /> <span>成交量区间</span> <input
				id="getvolume_low" class="form-control text_low" type="text"
				readonly style="top: 55px;"> <input id="getvolume_high"
				class="form-control text_high" type="text" readonly
				style="top: 55px;">
		</div>

		<!-- 换手率 -->
		<div class="combox">
			<input id="turnover_box" type="checkbox" /> <span>换手率区间</span> <input
				id="getturnover_low" class="form-control text_low" type="text"
				readonly style="top: 90px;"> <input id="getturnover_high"
				class="form-control text_high" type="text" readonly
				style="top: 90px;">
		</div>

		<!-- pe -->
		<div class="combox">
			<input id="pe_box" type="checkbox" /> <span>pe区间</span> <input
				id="getpe_low" class="form-control text_low" type="text" readonly
				style="top: 125px;"> <input id="getpe_high"
				class="form-control text_high" type="text" readonly
				style="top: 125px;">
		</div>

		<!-- pb -->
		<div class="combox">
			<input id="pb_box" type="checkbox" /> <span>pb区间</span> <input
				id="getpb_low" class="form-control text_low" type="text" readonly
				style="top: 160px;"> <input id="getpb_high"
				class="form-control text_high" type="text" readonly
				style="top: 160px;">
		</div>

		<div>
			<input type="button" class="btn add_cancel_btn make_btn" value="制订"
				id="stmake_btn" /> <input type="button"
				class="btn add_cancel_btn cancel_btn" value="取消"
				onclick="modifyCancel()" />
		</div>
	</div>

	<!-- Plugin JavaScript -->
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>
	<script src="../jschart/StrategyLineChart.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/searchHint.js"></script>
	<script type="text/javascript" src="../js/strategyhint.js"></script>
	<script type="text/javascript" src="../js/strategy_deal.js"></script>
	<script src="../js/common.min.js"></script>
	<script src="../js/toaster.js"></script>
	<script type="text/javascript" src="../js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$('#startdate').datetimepicker({
			lang : 'ch',
			timepicker : false,
			format : 'Y-m-d'
		});

		$('#enddate').datetimepicker({
			lang : 'ch',
			timepicker : false,
			format : 'Y-m-d'
		});
	</script>
</body>
</html>