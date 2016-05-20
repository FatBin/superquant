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

#searchform {
	float: right;
	margin-right: 20px;
	margin-top: -55px;
}

#searchform input[type="text"] {
	background: #e8e8e8;
	border: none;
	border-radius: 5px;
	float: left;
	padding: 0px 10px 0px 12px;
	margin: 0px;
	width: 50px;
	height: 25px;
	line-height: 25px;
	font-size: 12px;
	transition: all 300ms cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
	-moz-transition: all 300ms cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
	-webkit-transition: all 300ms cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
	-o-transition: all 300ms cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
	color: #585858;
}

#searchform input[type="text"]:hover, #searchform input[type="text"]:focus
	{
	width: 200px;
}

#searchform input[type="submit"] {
	background: url('../webImage/search.png') center no-repeat;
	cursor: pointer;
	margin: 0px;
	padding: 0px;
	width: 25px;
	height: 25px;
	line-height: 25px;
}

input[type="submit"] {
	padding: 4px 17px;
	color: #ffffcolor:#585858; ff;
	text-transform: uppercase;
	border: none;
	border-radius: 5px;
	font-size: 20px;
	background: url(gradient.png) bottom repeat-x;
	cursor: pointer;
	float: left;
	overflow: visible;
	transition: all .3s linear;
	-moz-transition: all .3s linear;
	-o-transition: all .3s linear;
	-webkit-transition: all .3s linear;
}

#searchform input[type="submit"]:hover {
	background-color: #333232;
}

.style_5 #searchform input[type='submit'] {
	background-color: #ff8a00;
}

.style_5 {
	margin: 10px;
	display: block;
	clear: both;
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

				<li><a class="page-scroll" href="#header">首页</a></li>
				<li><a class="page-scroll" href="#portfolio">大盘</a></li>


				<li><a class="page-scroll" href="#team">个股</a></li>
				<li><a class="page-scroll" href="#about">行业</a></li>
				<li><a class="page-scroll" href="#contact">策略</a></li>
			</ul>
		</div>

		<!--
		<div id="searchbtn">
			<img src="../webImage/search.png" title="搜索">
		</div>
  -->

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
		<h1 style="text-align: center; margin-top: 200px; color: white;">行业</h1>
	</div>

	<!-- Plugin JavaScript -->
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>

</body>
</html>