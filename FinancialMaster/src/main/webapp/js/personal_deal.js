function deleteST(pos){
	
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

function checkST(pos){

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
			totalcost = result[0].totalcost,
			buylist = result[0].BuyList,
			soldlist = result[0].SoldList,
			stlist = result[0].perST
		},
		error : function(errorMsg) {
			alert("不好意思，请求数据失败啦!");
		}
	})
	
	
	var table = document.getElementById("strategyTable");

	var tr = table.insertRow(1);
	tr.style.height = "35px";
	tr.align = "center";
	tr.valign = "middle";
	for (var i = 0; i < getInfo.length; i++) {
		var td = document.createElement("td");
		td.innerHTML = getInfo[i];
		tr.appendChild(td);
	}
}