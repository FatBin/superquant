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