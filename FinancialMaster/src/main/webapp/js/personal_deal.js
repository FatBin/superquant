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
	if(stDetail.style.display == "none"){
		stDetail.style.display = "block";
		stDetail.style.position = "absolute";
		stDetail.style.marginTop = document.getElementById(nameId).offsetTop - 340;
	}
	
	var buyList = buylist.toString().split(";");
	var stList = stlist.toString().split(";");
	var soldList = soldlist.toString().split(";");

	var table = document.getElementById("strategyTable");

	for (var i = 0; i < stList.length - 1; i++) {
		var stl = stList.toString().split(",");

		var tr = table.insertRow(1);
		tr.style.height = "35px";
		tr.align = "center";
		tr.valign = "middle";
		tr.style.fontSize = "16px";
		for (var j = 0; j < stl.length - 1; j++) {
			var td = document.createElement("td");
			td.innerHTML = stl[j];
			tr.appendChild(td);
		}

		for (var j = 4; j < 6; j++) {
			var td = document.createElement("td");
			td.innerHTML = "查看详情";
			tr.appendChild(td);
		}
	}
}

function runST(pos){
	var nameId = "name" + (pos + "");
	var stName = document.getElementById(nameId).innerHTML;
	

	$.ajax({
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
			}
			getLinechart("../RunMyStrategy");
		},
		error : function(errorMsg) {
			alert("不好意思，请求数据失败啦!");
		}
	})
}