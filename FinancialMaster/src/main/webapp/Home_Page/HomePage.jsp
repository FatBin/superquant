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

<style>
#logo {
	width: 172px;
	height: 32px;
	position: relative;
	left: 19px;
	top: 10px;
}

.navbar-left {
	float: left !important;
	margin-left: 35px;
}

#searchbtn {
	display: in-line;
	width: 20px;
	height: 20px;
	float: right;
	margin-top: -35px;
	margin-right: 50px;
}
</style>
</head>

<body id="page-top" class="index" style="background-color: #4A433B;">

	<nav class="navbar navbar-default navbar-fixed-top">

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

				<li><a class="page-scroll" href="#services">首页</a></li>
				<li><a class="page-scroll" href="#portfolio">大盘</a></li>


				<li><a class="page-scroll" href="#team">个股</a></li>
				<li><a class="page-scroll" href="#about">行业</a></li>
				<li><a class="page-scroll" href="#contact">策略</a></li>
			</ul>
		</div>

		<div id="searchbtn">
			<img src="../webImage/search.png" title="搜索">
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header>
	<div class="container" style="height: 479px;">
		<div>
			<img src="../webImage/decorate.png"
				style="width: 570px; height: 187px; float: left; margin-top: 150px;">
		</div>

		<div class="intro-text"
			style="margin-left: 566px; margin-top: -145px;">
			<div class="intro-lead-in">
				Super<a style="color: orange;">Quant</a>
			</div>
			<div class="intro-heading">您的第一手证券信息</div>
			<a href="#services" class="page-scroll btn btn-xl">了解更多</a>
		</div>
	</div>
	</header>


	<!-- Services Section -->
	<section id="services" style="background-color:#FFFCF6;">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">我们的服务</h2>
				<h3 class="section-subheading text-muted">孵化、加速、上市 -
					企业生命周期的咨询及金融服务！</h3>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-cog fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading">创业及孵化</h4>
				<p class="text-muted" align="left">
					<strong>让创业更简单</strong> -
					我们为创客提供精装修的创业空间，创客可拎包入住。我们还提供全方位的孵化与辅导服务，帮助创业企业从一个
					idea，到建立团队、注册公司、项目开发并完善，从0-1。
				</p>
				<p class="text-muted" align="left">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted">人力：公司注册、社保、招聘等。</li>
					<li align="left" class="text-muted">财务：会计、帐务、报税、发票等。</li>
					<li align="left" class="text-muted">法务：股权架构设计、法律文书等。</li>
				</ul>


				</p>
			</div>
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-bar-chart fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading">加速与成长</h4>
				<p class="text-muted" align="left">
					<strong>让成长更快速</strong> -
					针对成熟的项目及团队，我们提供全方位的加速与辅导服务，通过各种活动与导师授课，让项目团队对行业及自己项目有更清晰的认识，同时，提供中期的资金与资源的支持，从1-100。
				</p>
				<p class="text-muted" align="left">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted">提供免费或低价的办公场所。</li>
					<li align="left" class="text-muted">对项目进行辅导，让项目更加成熟。</li>
					<li align="left" class="text-muted">为项目注入资源，帮助其加速成长。</li>
				</ul>


			</div>
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-magic fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading" align="left">挂牌、上市、并购</h4>
				<p class="text-muted" align="left">
					<strong>让企业更加成功</strong> -
					对于拟上市企业或上市公司，我们将提供更加丰富的资金与资源支持，并配合进行并购、重组、管理升级等，有了资金、资源、人才更加全面的支持，相信企业能够获得更大的成功。
				</p>
				<p class="text-muted" align="left">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted">为项目提供高层次的资本与资源支持！</li>
					<li align="left" class="text-muted">协助项目完成并购、重组，提升竞争力！</li>
					<li align="left" class="text-muted">全面提升企业资本市场运作能力！</li>
				</ul>

			</div>
		</div>
	</div>
	</section>

	<!-- bottom section -->
	<section style="background-color:#766F67;">
	<div style="height: 203px;"></div>
	</section>

	<section style="background-color:#645D55;">
	<div style="height: 79x;"></div>
	</section>


	<!-- jQuery -->
	<script src="../js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="../js/jqBootstrapValidation.js"></script>
	<script src="../js/contact_me.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../js/agency.js"></script>

</body>
</html>