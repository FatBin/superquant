<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="VO.UserVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SuperQuant--您的第一手证券信息</title>

<link href="../css/LoginAndReg.css" rel="stylesheet">

<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">

<link href="../css/agency.css" rel="stylesheet" type="text/css">

<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link href="../css/headNav.css" rel="stylesheet" type="text/css">

<link href="../css/mycheckbox.css" rel="stylesheet" type="text/css">

<link href="../css/simulatepage.css" rel="stylesheet" type="text/css">

<link href="../css/strategystyle.css" rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
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
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
				<li><a class="page-scroll" style="color: rgb(253, 208, 72);"
					href="SimulatePage.jsp">模拟投资</a></li>
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
		<div id="searchHint"
			style="position: absolute; background-color: rgb(235, 235, 235); width: 150px; margin-left: 935px; margin-top: -10px;"></div>
		<!-- /.container-fluid -->
	</nav>

	<div style="width: 80%; margin: 30px auto; margin-top: 90px;">

		<div class="my_sim_title">
			<div style="display: inline-block;">
				资产净值：<span id="totalfund">10000元</span>

				<div class="init_fund">1W原始资本</div>

			</div>

			<div style="display: inline-block; margin-left: 50px;">
				我的收益：<span id="myprofit" style="color: #e35339;">+1000元</span>
			</div>

			<div style="display: inline-block; margin-left: 50px;">
				购买力：<span id="buyability" style="color: #f8b31d;">10000元</span>
			</div>

			<div class="reset_st_btn newST_btn"
				style="width: 84px; height: 29px; display: inline-block; margin-left: 80px;"
				onclick="addStore();">增加持仓</div>

		</div>

		<!-- 引导图/持有股表格 -->
		<div id="intro_img"
			style="width: 100%; height: 316px; margin-top: 30px; margin-bottom: 90px; background-color: gray;">

			<img style="width: 60%; display: none;"
				src="../webImage/strategy_tip.png">

			<!-- 持有股表格 -->
			<table id="strategyTable" rules="rows" style="display: none;">
				<thead>
					<tr align="center" valign="middle"
						style="background-color: rgb(230, 230, 230); font-size: 16px;">

						<td width="105" height="40">股票名称</td>
						<td width="100">投资成本</td>
						<td width="105">开始日期</td>
						<td width="105">结束日期</td>
						<td width="195">买入策略</td>
						<td width="195">卖出策略</td>
						<td width="110">其他策略</td>
						<td width="110">买卖频率</td>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="new_buy" class="makest_div"
		style="display: none; margin-top: 0; width: 80%; margin-bottom: 90px;">

		<i class="fa fa-times close_st" onclick="closeStore()"></i>

		<div style="display: inline-block;">
			<div class="div_title">
				新建交易
				<div class="checkbox_div">
					<input class="mui-switch mui-switch-animbg" type="checkbox">
					<span class="usest_tip">使用策略</span>
				</div>

			</div>

			<div
				style="margin-left: 45px; margin-top: 15px; display: inline-block;">
				<input class="st_textfileds" id="stockchoose" placeholder="股票代码/名称"
					onkeyup="showHint_st(this.value)">
			</div>
			<div id="stHint"></div>

			<div
				style="margin-left: 11px; margin-top: 15px; display: inline-block;">
				<input id="stocknums" class="st_textfileds" placeholder="买入股数">
			</div>

			<div class="tips_word">tips: 模拟交易与真实交易略有不同，将会直接以最新价格成交</div>

			<div class="money_left" style="margin-top: 70px;">
				剩余本金:&nbsp;&nbsp;<span id="moneyleft">10000</span>
			</div>

			<div class="add_st_btn" onclick="">购&nbsp;&nbsp;买</div>

			<div class="reset_st_btn" onclick="">取&nbsp;&nbsp;消</div>
		</div>

		<!-- 时分图 -->
		<div class="st_run_div" style="height: 295px; display: inline-block;">

			<div class="used_money">
				已用本金：<span id="usedmoney">10000</span>
			</div>

			<div class="run_pic" style="height: 220px;">
				<div id="strategyLineChart"
					style="width: 580px; height: 200px; display: none;"></div>
			</div>

		</div>

	</div>

	<!-- 历史交易、累计盈亏 -->
	<div style="width: 80%; margin: 30px auto; margin-top: 140px; vertical-align:top">

		<div style="width: 55%; display: inline-block;">

			<blockquote class="stname_title">历史交易</blockquote>

			<div style="width: 100%; background-color: #fcfcfc;">

				<!-- 买 -->
				<div class="his_each">
					<div class="syb_buy" style="display: inline-block;">买</div>
					
					<div class="usest_syb" style="display: none;">策略</div>

					<div style="margin-left: 85px; display: inline-block;">
						<span style="font-size: 18px; color: #4a433b;">浦发银行</span><br>
						<span style="font-size: 14px; color: #9e9a95;">sh600000</span>
					</div>
					
					<div style="margin-left: 40px; display: inline-block;">
						<span style="font-size: 18px; color: #4a433b;">30元</span><br>
						<span style="font-size: 14px; color: #9e9a95;">200股</span>
					</div>
					
					<div style="margin-left: 80px; display: inline-block;">
						<span style="font-size: 18px; color: #f8b31d">-3000元</span><br>
						<span style="font-size: 14px; color: #9e9a95;">2016-9-10 14:59:00</span>
					</div>
				</div>
				
				<!-- 卖 -->
				<div class="his_each" style="background-color: #fff8ea;">
					<div class="syb_buy" style="display: inline-block;">卖</div>
					
					<div class="usest_syb">策略</div>

					<div style="margin-left: 60px; display: inline-block;">
						<span style="font-size: 18px; color: #4a433b;">浦发银行</span><br>
						<span style="font-size: 14px; color: #9e9a95;">sh600000</span>
					</div>
					
					<div style="margin-left: 40px; display: inline-block;">
						<span style="font-size: 18px; color: #4a433b;">30元</span><br>
						<span style="font-size: 14px; color: #9e9a95;">200股</span>
					</div>
					
					<div style="margin-left: 80px; display: inline-block;">
						<span style="font-size: 18px; color: #f8b31d">+3000元</span><br>
						<span style="font-size: 14px; color: #9e9a95;">2016-9-10 14:59:00</span>
					</div>
				</div>

			</div>

		</div>

		<div style="width: 40%; margin-left: 4%; display: inline-block; vertical-align:top">

			<blockquote class="stname_title">策略累计盈亏</blockquote>

			<div style="width: 100%; background-color: #fcfcfc;">
			
				<div class="used_sts">
					<span>MyStrategy</span>
					<span style="color: #c4330c; float: right;">+5000元</span>
				</div>
				
				<div class="used_sts">
					<span>technical</span>
					<span style="color: #186b03; float: right;">-1000元</span>
				</div>
			
			</div>

		</div>

	</div>

	<!-- bottom section -->
	<div style="background-color: #766F67; height: 200px;"></div>

	<div style="background-color: #645D55; height: 50px;">
		<p style="color: white; text-align: center; line-height: 50px;">@Copyright
			SuperQuant</p>
	</div>

	<!-- login and register -->
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

	<!-- Modal -->
	<div class="modal fade" id="modalReg" tabindex="-1" role="dialog"
		aria-labelledby="modalReg" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">加入SuperQuant</h4>
					<p>再添人生一桶金</p>
				</div>

				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible fade in"
						role="alert" id='modalRegErr'>
						<span id='modalRegErrCont'></span>
					</div>

					<div class='form-group'>
						<input type="text" class="form-control" id="modal-reg-name"
							placeholder='昵称'>
					</div>

					<div class='form-group'>
						<input type="password" class="form-control" id="modal-reg-pwd"
							placeholder='密码（不少于6位）'>
					</div>


					<div class='form-group'>
						<input type="password" class="form-control" id="modal-reg-pwd2"
							placeholder='请再次确认密码'>
					</div>

				</div>
				<!-- modal-body -->

				<div class="modal-footer">
					<div id='reg-succ-tips'></div>
					<a role="button" href="javascript:reg();" class="btn btn-reg"
						data-loading-text="注册中...">注册</a>
					<div class='modal-footer-tips'>
						<a href='javascript:switchToModal("login");'>已有账号？直接登陆</a>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- End Modal -->

	<%-- 用来存放userId --%>
	<a id="storage" style="display: none;"><%=session.getAttribute("User")%>
	</a>
	
	<%-- 无阻塞提示框 --%>
	<div id="toaster_close">
		<div id="toaster">
			<div id="pic_div" class="green_pic"></div>
			<div id="remind" class="green_word">提示消息</div>
		</div>
	</div>

	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>
	<script src="../js/jqBootstrapValidation.js"></script>
	<script src='http://s1.yuehetong.com/sitemedia/js/jquery-2.2.1.min.js'></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/searchHint.js"></script>
	<script src="../js/common.min.js"></script>
	<script src="../js/simulatepage.js"></script>
	<script src="../js/toaster.js"></script>
	<script type="text/javascript" src="../js/strategyhint.js"></script>
	<script>
		(function() {
			var bp = document.createElement('script');
			var curProtocol = window.location.protocol.split(':')[0];
			if (curProtocol === 'https') {
				bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
			} else {
				bp.src = 'http://push.zhanzhang.baidu.com/push.js';
			}
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(bp, s);
		})();
	</script>

</body>
</html>