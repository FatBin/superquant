setColor();
// 每10秒更新最新数据
setInterval(refreshData, 10000);

function refreshData() {

}

function setColor() {
	var nowspan = document.getElementById("now");
	var rfspan = document.getElementById("rise_fall");
	var rfpspan = document.getElementById("rise_fall_percent");
	if (rfspan.innerHTML[0] == "-") {
		nowspan.style.color = "green";
		rfspan.style.color = "green";
		rfpspan.style.color = "green";
	} else {
		nowspan.style.color = "red";
		rfspan.style.color = "red";
		rfpspan.style.color = "red";
	}
}
// 切换大盘
function changeStock(stockname) {

	$.post("../ToMarketPageServlet", {
		benchName : stockname
	}).success(
			function() {
				getKLine("market"); // 刷新kline
				var tablehead = [ "日期", "开盘价", "最高价", "最低价", "收盘价", "成交量(百万股)",
						"成交额(亿元)" ];
				var data = [];

				// 刷新表格
				$.ajax({
					type : "get",
					async : false, // 同步执行
					url : "../GetMarketTableDate",
					dataType : "json",
					success : function(result) {
						if (result) {
							for (var i = 0; i < result.length; i++) {
								data.push(result[i].value);
							}
						}
					},
					error : function(errorMsg) {
						alert("不好意思，请求数据失败啦!");
					}
				})
				refresh_table(tablehead, data);
				
				// 刷新最新数据
				$.ajax({
					type : "post",
					async : false, // 同步执行
					url : "../UpdateBenchVO",
					dataType : "json",
					success : function(result) {
//						setNewest(result);
						document.getElementById("now").innerHTML = result[0].now;
					},
					error : function(errorMsg) {
						alert("不好意思，最新数据请求失败啦!");
					}
				})
				
			});
}

//function setNewest(result){
//	document.getElementById("now").innerHTML = result[0].now;
//	document.getElementById("now").innerHTML = result[0].now;
//	document.getElementById("now").innerHTML = result[0].now;
//	document.getElementById("now").innerHTML = result[0].now;
//	document.getElementById("now").innerHTML = result[0].now;
//	document.getElementById("now").innerHTML = result[0].now;
//	document.getElementById("now").innerHTML = result[0].now;
//}