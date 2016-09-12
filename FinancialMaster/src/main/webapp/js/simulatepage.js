var iBase = {
	SetOpacity : function(ev, v) {
		ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')'
				: ev.style.opacity = v / 100;
	}
};

// 关闭块
function closeST(elem_id) {

	var elem = document.getElementById(elem_id);
	var speed = 15;
	var opacity = 0;

	var val = 100;
	(function() {
		iBase.SetOpacity(elem, val);
		val -= 5;
		if (val >= opacity) {
			setTimeout(arguments.callee, speed);
		} else if (val < 0) {
			elem.style.display = 'none';
		}
	})();
}

// 打开块
function launchST(elem_id) {

	var elem = document.getElementById(elem_id);
	var speed = 20;
	var opacity = 100;

	elem.style.display = "block";
	iBase.SetOpacity(elem, 0);
	var val = 0;
	(function() {
		iBase.SetOpacity(elem, val);
		val += 5;
		if (val <= opacity) {
			setTimeout(arguments.callee, speed)
		}
	})();
}

function addStore() {

	var userId = document.getElementById("storage").innerHTML.trim();
	if (userId == "null") {
		slidein(2, "您还没登录");
		return;
	}

	document.getElementById("intro_img").style.display = "none";
	launchST("new_buy");
}

function closeStore() {

	document.getElementById("stockchoose").value = "";
	document.getElementById("stocknums").value = "";
	document.getElementById("new_buy").style.display = "none";
	launchST("intro_img");

	useST(false);
	var table = document.getElementById("tableST");
	var trs = table.getElementsByTagName("tr");
	for (var i = trs.length - 1; i > 0; i--) {
		table.deleteRow(i);
	}

	document.getElementById("useST_box").checked = false;
}

function setTimechart(stockname) {
	setId(stockname);
	getTimeSharingDiagram("stock");
}

function useST(checked) {

	var notST_left = document.getElementById("notST_left");
	var notST_right = document.getElementById("notST_right");
	var tablediv = document.getElementById("table_div");
	var table = document.getElementById("tableST");

	if (checked == true) {
		document.getElementsByClassName("usest_tip")[0].style.color = "#e9bd8d";

		notST_left.style.display = "none";
		notST_right.style.display = "none";

		var stnames = document.getElementById("storageST").innerHTML.trim();
		stnames = stnames.slice(1, stnames.length - 1).split(",");

		if (stnames.length == 0) {
			document.getElementById("noSTtip").style.display = "";
		} else {

			tablediv.style.display = "";
			for (var i = 0; i < stnames.length; i++) {

				var buylist = "";
				var soldlist = "";
				var stlist = "";
				var stname = stnames[i].trim();

				$.ajax({
					type : "post",
					async : false, // 同步执行
					url : "../ToStrategyPageServlet",
					data : {
						'StrategyName' : stname
					},
					dataType : "json",
					success : function(result) {
						buylist = result[0].BuyList,
								soldlist = result[0].SoldList,
								stlist = result[0].perST
					},
					error : function(errorMsg) {
						alert("不好意思，请求数据失败啦!");
					}
				});

				var buyList = buylist.toString().split(";");
				var stList = stlist.toString().split(";");
				var soldList = soldlist.toString().split(";");

				for (var k = 0; k < stList.length - 1; k++) {

					var stl = stList[k].toString().split(",");
					var buy = buyList[k].toString().split(",");
					var sold = soldList[k].toString().split(",");

					var tr = table.insertRow(1);
					tr.style.height = "35px";
					tr.align = "center";
					tr.valign = "middle";
					tr.style.fontSize = "16px";

					var checkbox = document.createElement("input");
					checkbox.type = "checkbox";
					var td = document.createElement("td");
					td.appendChild(checkbox);
					tr.appendChild(td);
					checkbox.onclick = function() {
						var boxs = table.getElementsByTagName("input");
						if (this.checked == false && boxs[0].checked == true) {
							boxs[0].checked = false;
						} else if (this.checked == true
								&& boxs[0].checked == false) {
							var selectall = 1;
							for (var i = 1; i < boxs.length; i++) {
								if (boxs[i].checked == false) {
									selectall = 0;
									break;
								}
							}
							if (selectall == 1) {
								boxs[0].checked = true;
							}
						}
					}

					for (var j = 0; j < stl.length; j++) {
						var td = document.createElement("td");
						if (j == stl.length - 1) {
							td.innerHTML = "每" + stl[j] + "天";
						} else {
							td.innerHTML = stl[j];
						}
						tr.appendChild(td);
					}

					// 买入策略
					var td = document.createElement("td");
					td.style.fontSize = "13px";
					td.innerHTML = stDeal(buy, 0);
					tr.appendChild(td);

					// 卖出策略
					var td = document.createElement("td");
					td.style.fontSize = "13px";
					td.innerHTML = stDeal(sold, 1);
					tr.appendChild(td);

					// 其他策略
					var td = document.createElement("td");
					td.style.fontSize = "13px";
					td.innerHTML = "敬请期待";
					tr.appendChild(td);
				}
			}
		}

	} else {
		document.getElementsByClassName("usest_tip")[0].style.color = "#90949b";

		document.getElementById("noSTtip").style.display = "none";
		tablediv.style.display = "none";
		notST_left.style.display = "";
		notST_right.style.display = "";

		var trs = table.getElementsByTagName("tr");
		for (var i = trs.length - 1; i > 0; i--) {
			table.deleteRow(i);
		}
	}
}

// 策略文字化; 0买入，1卖出
function stDeal(st, sybol) {
	var str = [ "股价", "成交量", "换手率", "pe", "pb" ];
	var BorS = [ "时买入", "时卖出" ]
	var via = "落在";
	var result = "当";
	var other = [ "您未限定买入条件", "始终持有该股" ];

	var douhao = 0;
	for (var i = 0; i < 5; i++) {
		if (st[i * 2] != 0 || st[i * 2 + 1] != 0) {
			if (douhao == 1) {
				result += "、";
			}
			result = result + str[i] + via + st[i * 2] + "~" + st[i * 2 + 1];
			douhao = 1;
		}
	}

	if (douhao == 1) {
		return (result + BorS[sybol]);
	} else {
		return other[sybol];
	}
}

function selectAll() {
	var table = document.getElementById("tableST");
	var boxs = table.getElementsByTagName("input");

	if (boxs[0].checked == false) {
		for (var i = 0; i < boxs.length; i++) {
			if (boxs[i].type == "checkbox")
				boxs[i].checked = false;
		}
	} else {
		for (var i = 0; i < boxs.length; i++) {
			if (boxs[i].type == "checkbox")
				boxs[i].checked = true;
		}
	}
}

function buyStock() {

	var stockname = document.getElementById("stockchoose").value;
	var stocknums = document.getElementById("stocknums").value;

	if (document.getElementById("useST_box").checked == true) {
		// 使用策略

	} else {
		// 不使用策略
		$.ajax({
			type : "post",
			async : false, // 同步执行
			url : "../SimulationStock",
			data : {
				"Order" : "Buy",
				"StockID" : stockname,
				"Number" : stocknums
			},
			dataType : "json",
			success : function(result) {
				alert(result[0].BuyResult)
			},
			error : function(errorMsg) {
				alert("不好意思，请求数据失败啦!");
			}
		});
	}
}

// 历史交易记录
function initHis() {

	var userId = document.getElementById("storage").innerHTML.trim();
	if (userId != "null") {
		document.getElementsByClassName("noHis_tip")[0].style.display = "none";
		
		$.ajax({
					type : "get",
					async : false, // 同步执行
					url : "../SimulationRecord",
					dataType : "json",
					success : function(result) {

						if (result.length > 0) {
							for (var i = 0; i < result.length; i++) {

								var div = document.createElement("div");
								div.innerHTML = document
										.getElementById("his_copy").innerHTML;
								div.setAttribute("class", "his_each");

								var BorS = "-";

								if (result[i].deal == "Sell"
										|| result[i].deal == "ST_Sell") {
									div.getElementsByClassName("syb_buy")[0].innerHTML = "卖";
									BorS = "+";
								}

								if (result[i].deal == "ST_Buy"
										|| result[i].deal == "ST_Sell") {
									div.getElementsByClassName("usest_syb")[0].style.display = "";
									div.getElementsByTagName("div")[2].style.marginLeft = "105px";
									div.style.backgroundColor = "#fff8ea";
								}

								var spans = div.getElementsByTagName("span");
								spans[0].innerHTML = result[i].stockName;
								spans[1].innerHTML = result[i].stockID;
								spans[2].innerHTML = BorS + result[i].money;
								spans[3].innerHTML = result[i].time;

								document.getElementById("histrades")
										.appendChild(div);

							}
						} else {
							document.getElementsByClassName("noHis_tip")[0].style.display = "";
						}
					},
					error : function(errorMsg) {
						alert("不好意思，请求数据失败啦!");
					}
				});
	} else {
		document.getElementsByClassName("noHis_tip")[0].style.display = "";
		document.getElementsByClassName("noHis_tip")[0].innerHTML = "您还没登录";
	}
}
