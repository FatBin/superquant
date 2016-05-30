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

<link href="../css/headNav.css" rel="stylesheet" type="text/css">

<link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	media="screen">

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

				<li><a class="page-scroll" href="HomePage.jsp">首页</a></li>
				<li><a class="page-scroll" href="../ToMarketPageServlet">大盘</a></li>
				<li><a class="page-scroll" href="StockPage.jsp">个股</a></li>
				<li><a class="page-scroll" href="BusinessPage.jsp">行业</a></li>
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
			</ul>
		</div>

		<div class="style_5 hidden-sm hidden-xs">
			<fieldset id="searchform">
				<input type="text" placeholder="搜索" class="text_input"
					onblur="this.placeholder='搜索';"
					onfocus="this.placeholder='输入股票代码搜索';"
					onmouseover="this.placeholder='输入股票代码搜索';"
					onmouseout="this.placeholder='搜索';" onkeyup="showHint(this.value)" />
				<input name="submit" type="submit" value='' />
			</fieldset>

			<div id="searchHint"
				style="position: absolute; background-color: rgb(235,235,235); width: 150px; margin-left: 945px; margin-top: -20px;">
			</div>
		</div>

		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div
		style="font-size: 28px; line-height: 35px; font-family: PingFangSC-Regular; margin-top: 150px; margin-left: 190px;">
		策略⎡ <span>sss</span> ⎦
	</div>

	<hr style="margin-top: 10px; width: 900px;" />

	<!-- 所有按钮的大块 -->
	<div>

		<!-- 第一列的块 -->
		<div id="first_col"
			style="margin-left: 190px; margin-right: 40px; margin-bottom: -15px; float: left;">

			<!-- 开始日期 -->
			<div class="form-group">
				<i
					style="font-size: 14px; line-height: 34px; font-family: PingFangSC-Regular; float: left; margin-right: 20px;">开始日期</i>
				<div class="input-group date form_date col-md-5" data-date=""
					data-date-format="dd MM yyyy" data-link-field="dtp_input2"
					data-link-format="yyyy-mm-dd">

					<input class="form-control" size="16" type="text" value="" readonly
						style="width: 100px;"> <span class="input-group-addon"
						style="height: 34px; width: 37px;"><span
						class="glyphicon glyphicon-remove"></span></span> <span
						class="input-group-addon" style="height: 34px; width: 37px;"><span
						class="glyphicon glyphicon-time" style="margin: auto;"></span></span>
				</div>
				<input type="hidden" id="dtp_input2" value="" /><br />
			</div>

			<!-- 结束日期 -->
			<div class="form-group" style="margin-top: -25px;">
				<i
					style="font-size: 14px; line-height: 34px; font-family: PingFangSC-Regular; float: left; margin-right: 20px;">结束日期</i>
				<div class="input-group date form_date col-md-5" data-date=""
					data-date-format="dd MM yyyy" data-link-field="dtp_input2"
					data-link-format="yyyy-mm-dd">

					<input class="form-control" size="16" type="text" value="" readonly
						style="width: 100px;"> <span class="input-group-addon"
						style="height: 34px; width: 37px;"><span
						class="glyphicon glyphicon-remove"></span></span> <span
						class="input-group-addon" style="height: 34px; width: 37px;"><span
						class="glyphicon glyphicon-time" style="margin: auto;"></span></span>
				</div>
				<input type="hidden" id="dtp_input2" value="" /><br />
			</div>
		</div>

		<!-- 第二列的块 -->
		<div id="second_col" style="float: left; margin-right: 40px;">
			<!-- 选择股票 -->
			<div>
				<i
					style="font-size: 14px; line-height: 34px; font-family: PingFangSC-Regular; float: left; margin-right: 20px;">选择股票</i>

				<input class="form-control" size="16" type="text" value=""
					style="width: 100px;">
			</div>

			<div style="margin-top: 10px;">
				<!-- 买卖股数 -->
				<i
					style="font-size: 14px; line-height: 34px; font-family: PingFangSC-Regular; float: left; margin-right: 20px;">买卖股数</i>

				<input class="form-control" size="16" type="text" value=""
					style="width: 100px;">
			</div>
		</div>

		<!-- 第三列的块 -->
		<div id="second_col" style="float: left;">
			<!-- 买入策略 -->
			<div>
				<i
					style="font-size: 14px; line-height: 34px; font-family: PingFangSC-Regular; float: left; margin-right: 20px;">买入策略</i>

				<div style="float: left;">

					<form name="buyin">
						<select class="form-control" style="width: 100px;">
							<option value="000001">策略一</option>
							<option value="000300">策略二</option>
							<option value="000300">策略三</option>
						</select>
					</form>

				</div>

			</div>

			<div style="margin-top: 10px;">
				<!-- 卖出策略 -->
				<i
					style="font-size: 14px; line-height: 55px; font-family: PingFangSC-Regular; float: left; margin-right: 20px;">卖出策略</i>

				<div style="float: left; margin-top: 10px;">

					<form name="soldout">
						<select class="form-control" style="width: 100px;">
							<option value="000001">策略一</option>
							<option value="000300">策略二</option>
							<option value="000300">策略三</option>
						</select>
					</form>

				</div>

			</div>
		</div>

		<!-- 修改按钮 -->
		<div id="modify-div" style="float: left; margin-left: 80px;">
			<input type="button" class="btn" name="modifybtn" value="修改"
				style="width: 50px; height: 29px; background-color: transparent; border: 1px solid rgb(204, 204, 204); margin-top: 20px;"
				onclick="showConfirmbtn()" />
		</div>

		<div id="confirm-div"
			style="float: left; margin-left: 80px; margin-top: 5px; display: none;">
			<div>
				<input type="button" class="btn" name="confirmbtn" value="确认"
					style="width: 50px; height: 29px; background-color: transparent; border: 1px solid rgb(204, 204, 204);"
					onclick="showModifybtn()" />
			</div>

			<div>
				<input type="reset" class="btn" name="resetbtn" value="取消"
					style="width: 50px; height: 29px; background-color: transparent; border: 1px solid rgb(204, 204, 204); margin-top: 10px;"
					onclick="showModifybtn()" />
			</div>
		</div>

		<hr style="margin-top: 10px; width: 900px;" />

		<div
			style="width: 900px; height: 500px; margin-left: 180px; background-color: gray;"></div>


		<!-- Plugin JavaScript -->
		<script src="../js/classie.js"></script>
		<script src="../js/cbpAnimatedHeader.js"></script>

		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../js/jquery.js"></script>

		<script type="text/javascript" src="../js/searchHint.js"></script>

		<script type="text/javascript" src="../js/bootstrap-datetimepicker.js"
			charset="UTF-8"></script>
		<script type="text/javascript">
			$('.form_date').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				minView : 2,
				forceParse : 0,
				format : 'yyyy-mm-dd'
			});

			function showModifybtn() {
				document.getElementById("confirm-div").style.display = "none";
				document.getElementById("modify-div").style.display = "block";
			}

			function showConfirmbtn() {
				document.getElementById("modify-div").style.display = "none";
				document.getElementById("confirm-div").style.display = "block";
			}
		</script>
</body>
</html>