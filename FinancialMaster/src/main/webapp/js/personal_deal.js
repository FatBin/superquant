function deleteST(pos) {

	var nameId = "name" + (pos + "");
	var stName = document.getElementById(nameId).innerHTML;
	var divId = "div" + (pos + "");

	$.ajax({
		type : "post",
		async : false, // 同步执行
		url : "../ManageMyStrategy",
		data : {
			'stName' : stName
		},
		dataType : "json",
		success : function(result) {
			if (result[0].DeleteResult == "Succeed") {
				document.getElementById(divId).innerHTML = "";
			}
		},
		error : function(errorMsg) {
			alert("不好意思，请求数据失败啦!");
		}
	})
}

function checkST(pos) {

	var nameId = "name" + (pos + "");
	var stName = document.getElementById(nameId).innerHTML;

	var totalcost;
	var buylist;
	var soldlist;
	var stlist;

	$.ajax({
		type : "post",
		async : false, // 同步执行
		url : "../ToStrategyPageServlet",
		data : {
			'StrategyName' : stName
		},
		dataType : "json",
		success : function(result) {
			totalcost = result[0].totalcost, buylist = result[0].BuyList,
					soldlist = result[0].SoldList, stlist = result[0].perST
		},
		error : function(errorMsg) {
			alert("不好意思，请求数据失败啦!");
		}
	})

	var stDetail = document.getElementById("stDetail");
	if (stDetail.style.display == "none") {
		stDetail.style.display = "block";
		if (document.getElementById("stRun").style.display == "block")
			document.getElementById("stRun").style.display = "none";
	}
	stDetail.style.position = "absolute";
	stDetail.style.top = (document.getElementById(nameId).offsetTop + 30)
			+ "px";
	stDetail.style.left = (document.getElementById(nameId).offsetLeft - 190)
			+ "px";

	document.getElementById("backbtn").onclick = function() {
		stDetail.style.display = "none";
	}

	var table = document.getElementById("strategyTable");
	while (table.rows.length > 1) {
		table.deleteRow(1);
	}
	document.getElementById("stDetail").style.height = "110px";

	document.getElementById("costspan").innerHTML = "总成本：" + totalcost;

	var buyList = buylist.toString().split(";");
	var stList = stlist.toString().split(";");
	var soldList = soldlist.toString().split(";");

	for (var i = 0; i < stList.length - 1; i++) {

		document.getElementById("stDetail").style.height = (document
				.getElementById("stDetail").offsetHeight + 35)
				+ "px";

		var stl = stList[i].toString().split(",");
		var buy = buyList[i].toString().split(",");
		var sold = soldList[i].toString().split(",");

		var tr = table.insertRow(1);
		tr.style.height = "35px";
		tr.align = "center";
		tr.valign = "middle";
		tr.style.fontSize = "16px";
		for (var j = 0; j < stl.length; j++) {
			var td = document.createElement("td");
			td.innerHTML = stl[j];
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
	}
}

function runST(pos) {
	var nameId = "name" + (pos + "");
	var stName = document.getElementById(nameId).innerHTML;

	$
			.ajax({
				type : "post",
				async : false, // 同步执行
				url : "../RunMyStrategy",
				data : {
					'StrategyName' : stName
				},
				dataType : "json",
				success : function(result) {
					var div = document.getElementById("stRun");
					if (div.style.display == "none") {
						div.style.display = "block";
						if (document.getElementById("stDetail").style.display == "block")
							document.getElementById("stDetail").style.display = "none";
					}
					div.style.position = "absolute";
					div.style.top = (document.getElementById(nameId).offsetTop + 30)
							+ "px";
					div.style.left = (document.getElementById(nameId).offsetLeft - 150)
							+ "px";

					document.getElementById("backbtn_2").onclick = function() {
						div.style.display = "none";
					}

					getLinechart("../RunMyStrategy");
				},
				error : function(errorMsg) {
					alert("不好意思，请求数据失败啦!");
				}
			})
}

// 0买入，1卖出
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

function Logout(){
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../Log",
		dataType : "json",
		success : function(result) {
			if(result[0].UnLoginResult == "Succeed"){
				window.location.href = "../Web_Pages/HomePage.jsp";
			}
		}
	})
}