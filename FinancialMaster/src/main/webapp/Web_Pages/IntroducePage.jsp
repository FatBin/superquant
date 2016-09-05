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

<link href="../css/introducepage.css" rel="stylesheet" type="text/css">

</head>

<body id="page-top" class="index">

	<nav class="navbar navbar-default navbar-fixed-top"
		style="background-color: #4A433B; height:60px; padding-top:7px;">

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
				<li><a class="page-scroll" href="SimulatePage.jsp">模拟投资</a></li>
			</ul>
		</div>

		<div class="style_5 hidden-sm hidden-xs floatRight"
			style="margin-right: -10px;">
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
			style="position: absolute; background-color: rgb(235, 235, 235); width: 150px; margin-left: 975px; margin-top: -14px;"></div>
		<!-- /.container-fluid -->
	</nav>

	<div class="firstpart">
		<div class="fst_title">
			<p style="font-size: 40px">
				Super<span style="color: #f8b31d">Quant</span>
			</p>
			<p style="font-size: 18px; color: #4a433b; margin-top: -15px;">一个提供实时股票信息、自主选取并智能模拟的平台</p>
		</div>
	</div>

	<!-- Services Section -->
	<section class="secondpart">

	<div class="cut_img">
		<img style="width: 100%; border-radius: 3px;"
			src="../webImage/cut-img.png">
	</div>

	<div class="container">

		<div class="row text-center" style="margin-top: 415px">
			<div class="col-md-4" style="margin-top: -140px;">

				<div
					style="width: 124px; height: 124px; border-radius: 90px; position: relative; left: 100px; margin-top: 110px; margin-bottom: 20px;">
					<img src="../webImage/dataImage.svg">
				</div>

				<p class="text-muted" align="left" style="color: #fdfbf7">
					<strong>更全面的数据</strong> - 我们提供大盘、个股、行业更新更全面的信息。
				</p>

				<p class="text-muted" align="left" style="color: #fdfbf7">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted" style="color: #fdfbf7">数据：大盘、个股、行业的最新数据及历史数据！</li>
					<li align="left" class="text-muted" style="color: #fdfbf7">分析：技术、大盘、行业、资金等多方面分析！</li>
				</ul>

			</div>

			<div class="col-md-4" style="margin-top: -140px;">

				<div
					style="width: 124px; height: 124px; border-radius: 90px; position: relative; left: 100px; margin-top: 110px; margin-bottom: 20px;">
					<img src="../webImage/graphImage.svg">
				</div>

				<p class="text-muted" align="left" style="color: #fdfbf7">
					<strong>更丰富的图表</strong> - 将枯燥的数据可视化，给您更直观的对比和分析展示。
				</p>
				<p class="text-muted" align="left" style="color: #fdfbf7">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted" style="color: #fdfbf7">使用k线图、柱状图、折线图、饼状图等多种图表！</li>
					<li align="left" class="text-muted" style="color: #fdfbf7">根据不同内容使用不同颜色展示，更直观！</li>
					<li align="left" class="text-muted" style="color: #fdfbf7">不同图表添加到同一种表，对比更简单！</li>
				</ul>
			</div>

			<div class="col-md-4" style="margin-top: -140px;">

				<div
					style="width: 124px; height: 124px; border-radius: 90px; position: relative; left: 100px; margin-top: 110px; margin-bottom: 20px;">
					<img src="../webImage/functionImage.svg">
				</div>

				<p class="text-muted" align="left" style="color: #fdfbf7">
					<strong>更多样的功能</strong> - 针对不同用户提供更加多样的功能，让您的理财过程更简洁高效。
				</p>
				<p class="text-muted" align="left" style="color: #fdfbf7">
					<strong>主要内容</strong>
				<ul align="left">
					<li align="left" class="text-muted" style="color: #fdfbf7">提供登录功能，让收藏关注更简单！</li>
					<li align="left" class="text-muted" style="color: #fdfbf7">提供策略模拟，给您展示能力的舞台！</li>
					<li align="left" class="text-muted" style="color: #fdfbf7">支持名称和编码搜索，让查找更高效！</li>
				</ul>
			</div>
		</div>
	</div>
	</section>

	<!-- bottom section -->
	<div class="lastpart">

		<div class="each_sec">

			<div style="float: left; padding-top: 50px">
				<p class="sum_title">实时信息</p>
				<p class="in_detail">
					每一支股票、每一个行业<br>实时更新
				</p>
			</div>

			<div class="insert_img">
				<img style="width: 100%; border-radius: 3px"
					src="../webImage/market.png">
			</div>

			<hr style="width: 100%; border: 1px solid #786b5d;">
		</div>

		<div class="each_sec">

			<div style="float: right; padding-top: 50px">
				<p class="sum_title">个股诊断</p>
				<p class="in_detail">
					股票整体扫描<br>给你全面技术分析
				</p>
			</div>

			<div class="insert_img_left">
				<img style="width: 100%; border-radius: 3px"
					src="../webImage/analysis.png">
			</div>

			<hr style="width: 100%; border: 1px solid #786b5d;">
		</div>

		<div class="each_sec">

			<div style="float: left; padding-top: 50px">
				<p class="sum_title">策略模拟</p>
				<p class="in_detail">
					高度自定义策略<br>股票收益全预测
				</p>
			</div>

			<div class="insert_img">
				<img style="width: 100%; border-radius: 3px"
					src="../webImage/strategy.png">
			</div>

			<hr style="width: 100%; border: 1px solid #786b5d;">
		</div>

		<div style="text-align: center; width: 640px; margin: 0 auto;">

			<p class="in_detail">POWERED BY</p>

			<div class="developer_div">
				<div class="developer_img">
					<img style="width: 100%; border-radius: 60px;"
						src="../webImage/frebin.png">
				</div>
				<div class="in_detail">FREIN</div>
			</div>

			<div class="developer_div">
				<div class="developer_img">
					<img style="width: 100%; border-radius: 60px;"
						src="../webImage/marioquer.png">
				</div>
				<div class="in_detail">MARIOQUER</div>
			</div>

			<div class="developer_div">
				<div class="developer_img">
					<img style="width: 100%; border-radius: 60px;"
						src="../webImage/chao.png">
				</div>
				<div class="in_detail">CHAO</div>
			</div>

			<div class="developer_div">
				<div class="developer_img">
					<img style="width: 100%; border-radius: 60px;"
						src="../webImage/sure.png">
				</div>

				<div class="in_detail">SURE</div>
			</div>

		</div>

	</div>


	<div style="background-color: #645D55; height: 50px;">
		<p style="color: white; text-align: center; line-height: 50px;">@Copyright
			SuperQuant</p>
	</div>

	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>
	<script src="../js/jqBootstrapValidation.js"></script>
	<script src="../js/agency.js"></script>
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