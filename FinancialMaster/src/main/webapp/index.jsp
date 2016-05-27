<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="VO.BenchVO"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>hello</title>
	<script src="js/echarts.min.js"></script>
	<script src="js/jquery.min.js"></script>
</head>
<body>
   <h1>中文!</h1>
     <a href="ToStrategyPageServlet">  get请求tohome_page </a>

     <%!BenchVO sv; %>
     <%
       sv=(BenchVO)session.getAttribute("BenchMarket");
     %>
      <h1>成交量为：<%=sv.getVolume() %></h1>
     <div id="klinechart" style="width: 700px; height: 800px;"></div>

	<script src="jschart/kLineChart.js"></script>
</body>
</html>