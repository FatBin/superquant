setColor();
// 每10秒更新最新数据
setInterval(refreshData, 60000);

function refreshData() {
	// 刷新最新数据
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../UpdateBenchVO",
		dataType : "json",
		success : function(result) {
			setNewest(result);
		},
		error : function(errorMsg) {
//			alert("不好意思，最新数据刷新失败啦!");
		}
	})
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
						setNewest(result);
					},
					error : function(errorMsg) {
						alert("不好意思，最新数据请求失败啦!");
					}
				})

			});
}

function setNewest(result) {
	document.getElementById("now").innerHTML = result[0].now;
	document.getElementById("rise_fall").innerHTML = result[0].rise_fall_price;
	document.getElementById("rise_fall_percent").innerHTML = result[0].rise_fall_percent + "%";
	document.getElementById("status").innerHTML = result[0].status;
	document.getElementById("time").innerHTML = result[0].time;
	document.getElementById("highprice").innerHTML = result[0].high;
	document.getElementById("lowprice").innerHTML = result[0].low;
	document.getElementById("open").innerHTML = result[0].open;
	document.getElementById("close").innerHTML = result[0].yesterday_close;
	document.getElementById("price").innerHTML = result[0].price;
	document.getElementById("volume").innerHTML = result[0].volume;
	document.getElementById("risecom").innerHTML = result[0].rise_company;
	document.getElementById("fallcom").innerHTML = result[0].fall_company;
	document.getElementById("com").innerHTML = result[0].company;
}