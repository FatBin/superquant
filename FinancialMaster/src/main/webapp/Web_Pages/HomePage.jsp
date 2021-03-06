<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="VO.UserVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SuperQuant--您的第一手证券信息</title>

<link href="../css/LoginAndReg.css" rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">

<!-- Custom CSS -->
<link href="../css/agency.css" rel="stylesheet" type="text/css">

<!-- Custom Fonts -->
<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link href="../css/headNav.css" rel="stylesheet" type="text/css">

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

				<li><a class="page-scroll active" href="HomePage.jsp"
					style="color: rgb(253,208,72);">首页</a></li>
				<li><a class="page-scroll" href="../ToMarketPageServlet">大盘</a></li>
				<li><a class="page-scroll" href="../ToStockPageServlet">个股</a></li>
				<li><a class="page-scroll" href="../ToBusinessPageServlet">行业</a></li>
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
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
			<!-- /.navbar-collapse -->
		</div>
		<div id="searchHint"
			style="position: absolute; background-color: rgb(235, 235, 235); width: 150px; margin-left: 935px; margin-top: -10px;"></div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Header -->
	<header>
	<div class="jumbotron" style="background-color: #4A433B;">
		<div class="container" id="header"
			style="background-color: #4A433B; height: 479px;">
			<div class="row" style="margin-top: 130px; width: 100%">
				<div class="col-md-7">
					<img class="img-responsive" src="../webImage/decorate.png">
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-3" style="margin-top: 45px;">
					<div>
						<span class="logoName" style="color: #FFFFFF;">Super</span> <span
							class="logoName" style="color: orange;">Quant</span>
					</div>
					<div class="slogan">您的第一手证券信息</div>

					<%
						if (session.getAttribute("User") == null) {
					%>

					<input type="button" value="立即注册" name="regbtn"
						class="bottons regbtns" data-toggle="modal"
						data-target="#modalReg" /> <input type="button" value="登录"
						name="login" class="bottons loginbtn" data-toggle="modal"
						data-target="#modalLogin" />
					<%
						}
					%>
				</div>
				<div class="col-md-1"></div>

			</div>
		</div>
	</div>
	</header>


	<!-- Services Section -->
	<section id="services"
		style="background-color:#FFFCF6;height:500px; margin-top:-10px;">
	<div class="container">
		<div class="row" style="margin-top: -120px;">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">我们的服务</h2>
				<h3 class="section-subheading text-muted">全面、丰富、多样 -
					为您提供更简单、更高效的理财服务！</h3>
			</div>
		</div>

		<div class="row text-center">
			<div class="col-md-4" style="margin-top: -140px;">

				<div
					style="width: 124px; height: 124px; border-radius: 90px; position: relative; left: 100px; margin-top: 110px; margin-bottom: 20px;">
					<img src="../webImage/dataImage.svg">
				</div>

				<p class="text-muted" align="left">
					<strong>更全面的数据</strong> - 我们提供大盘、个股、行业更新更全面的信息。
				</p>
				<p class="text-muted" align="left">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted">数据：大盘、个股、行业的最新数据及历史数据！</li>
					<li align="left" class="text-muted">分析：技术、大盘、行业、资金等多方面分析！</li>
				</ul>

				</p>
			</div>

			<div class="col-md-4" style="margin-top: -140px;">

				<div
					style="width: 124px; height: 124px; border-radius: 90px; position: relative; left: 100px; margin-top: 110px; margin-bottom: 20px;">
					<img src="../webImage/graphImage.svg">
				</div>

				<p class="text-muted" align="left">
					<strong>更丰富的图表</strong> - 将枯燥的数据可视化，给您更直观的对比和分析展示。
				</p>
				<p class="text-muted" align="left">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted">使用k线图、柱状图、折线图、饼状图等多种图表！</li>
					<li align="left" class="text-muted">根据不同内容使用不同颜色展示，更直观！</li>
					<li align="left" class="text-muted">不同图表添加到同一种表，对比更简单！</li>
				</ul>
			</div>

			<div class="col-md-4" style="margin-top: -140px;">

				<div
					style="width: 124px; height: 124px; border-radius: 90px; position: relative; left: 100px; margin-top: 110px; margin-bottom: 20px;">
					<img src="../webImage/functionImage.svg">
				</div>

				<p class="text-muted" align="left">
					<strong>更多样的功能</strong> - 针对不同用户提供更加多样的功能，让您的理财过程更简洁高效。
				</p>
				<p class="text-muted" align="left">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted">提供登录功能，让收藏关注更简单！</li>
					<li align="left" class="text-muted">提供策略模拟，给您展示能力的舞台！</li>
					<li align="left" class="text-muted">支持名称和编码搜索，让查找更高效！</li>
				</ul>

			</div>

		</div>
	</div>
	</section>

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