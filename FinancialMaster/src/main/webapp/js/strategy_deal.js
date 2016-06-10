var buytemp = [ "", "", "", "", "", "", "", "", "", "" ]; // 制定策略时的买入策略 []
var soldtemp = [ "", "", "", "", "", "", "", "", "", "" ]; // 制定策略时的卖出策略 []
var buylist = new Array(); // 表格中的所有买入策略 [][]
var soldlist = new Array(); // 表格中的所有卖出策略 [][]
var perST = new Array();
var count = 0;
var stName;
var totalcost = 0;
var tempcost = 0;

var getID = [ "stockchoose", "cost", "startdate", "enddate", "buyinst",
		"soldoutst", "otherst", "frequency" ];
var texts = [ "getprice_low", "getprice_high", "getvolume_low",
		"getvolume_high", "getturnover_low", "getturnover_high", "getpe_low",
		"getpe_high", "getpb_low", "getpb_high" ];
var comboxs = [ "price_box", "volume_box", "turnover_box", "pe_box", "pb_box" ];

var texts_2 = [ "getprice_low_2", "getprice_high_2", "getvolume_low_2",
		"getvolume_high_2", "getturnover_low_2", "getturnover_high_2",
		"getpe_low_2", "getpe_high_2", "getpb_low_2", "getpb_high_2" ];
var comboxs_2 = [ "price_box_2", "volume_box_2", "turnover_box_2", "pe_box_2",
		"pb_box_2" ];

function addStrategy() {

	var getInfo = [];

	for (var i = 0; i < getID.length; i++) {
		if (i == 4 || i == 5) {
			getInfo[i] = "查看详情";
		} else if (i == 6) {
			getInfo[i] = "敬请期待";
		} else {
			getInfo[i] = document.getElementById(getID[i]).value;
		}

		if (getInfo[i] == '') {
			alert("请制订完整策略");
			return;
		}
	}

	if (getInfo[2] > getInfo[3]) {
		alert("开始日期和结束日期是不是反啦");
		return;
	}

	if (document.getElementById("stname").innerHTML == "我的策略") {
		alert("请设定策略名和本金");
		return;
	}

	if (tempcost - getInfo[1] < 0) {
		alert("资金不够啦");
		return;
	} else {
		tempcost -= getInfo[1];
		document.getElementById("cost").placeholder = tempcost + "  可用";
	}

	buylist[count] = new Array();
	soldlist[count] = new Array();
	perST[count] = new Array();
	for (var i = 0; i < 10; i++) {
		buylist[count][i] = buytemp[i];
		soldlist[count][i] = soldtemp[i];
	}

	var table = document.getElementById("strategyTable");

	var tr = table.insertRow(1);
	tr.style.height = "35px";
	tr.align = "center";
	tr.valign = "middle";

	var boxtd = document.createElement("td");
	tr.appendChild(boxtd);
	var boxdiv = document.createElement("div");
	boxtd.appendChild(boxdiv);
	var checkbox = document.createElement("input");
	checkbox.type = "checkbox";
	boxdiv.appendChild(checkbox);
	checkbox.onclick = function() {
		var boxs = table.getElementsByTagName("input");
		if (this.checked == false && boxs[0].checked == true) {
			boxs[0].checked = false;
		} else if (this.checked == true && boxs[0].checked == false) {
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

	var tempcount = 0;
	for (var i = 0; i < getInfo.length; i++) {
		var td = document.createElement("td");
		td.innerHTML = getInfo[i];
		if (i != 4 && i != 5 && i != 6) {
			perST[count][tempcount] = getInfo[i];
			tempcount++;
		}
		if (i != 6) {
			td.style.cursor = "pointer";
		}
		td.setAttribute("onclick", "modifyST(this)");
		tr.appendChild(td);
	}

	var boxs = table.getElementsByTagName("input");
	boxs[0].checked = false;

	zebra();
	count++;
	resetAll();
}

function resetAll() {

	for (var i = 0; i < getID.length; i++) {
		document.getElementById(getID[i]).value = "";
	}
	buytemp = [ "", "", "", "", "", "", "", "", "", "" ];
	soldtemp = [ "", "", "", "", "", "", "", "", "", "" ];
}

// 修改
function modifyST(td) {
	modifyCancel();

	var dialogtype = [ "textfieldMod", "textfieldMod", "timepickMod",
			"timepickMod", "stMod", "stMod", "stMod", "textfieldMod" ];
	var inputtype = [ "modtext", "modtext", "moddate", "moddate", "modselect",
			"modselect", "modselect", "modtext" ];
	var buttontype = [ "modCon_1", "modCon_1", "modCon_2", "modCon_2", "stbtn",
			"stbtn", "stbtn", "modCon_1" ];

	// td的列数
	var col = ($(td).parents("tr").find("td").index($(td))) - 1;
	var row = $(td).parents("tr").parents("table").find("tr").index(
			$(td).parents("tr")) - 1;
	var rowcount = document.getElementById("strategyTable").rows.length - 1;
	var pos = rowcount - 1 - row;

	if (col != 6) {
		var modiv = document.getElementById(dialogtype[col]);
		if (modiv.style.display == "none") {
			modiv.style.display = "block";
		}

		if (col == 4 || col == 5) {
			for (var i = 0; i < comboxs_2.length; i++) {
				var combox = document.getElementById(comboxs_2[i]);
				combox.setAttribute("onclick", "setLimit_2(" + i + ")")
			}
		}
		// 策略内容
		if (col == 4) {
			for (var i = 0; i < texts_2.length; i++) {
				document.getElementById(texts_2[i]).value = buylist[pos][i];
			}

			for (var i = 0; i < comboxs_2.length; i++) {
				if (buylist[pos][2 * i] == "" && buylist[pos][2 * i + 1] == "") {
					document.getElementById(texts_2[2 * i]).readOnly = true;
					document.getElementById(texts_2[2 * i + 1]).readOnly = true;
					document.getElementById(comboxs_2[i]).checked = false;
				} else {
					document.getElementById(texts_2[2 * i]).readOnly = false;
					document.getElementById(texts_2[2 * i + 1]).readOnly = false;
					document.getElementById(comboxs_2[i]).checked = true;
				}
			}

		} else if (col == 5) {
			for (var i = 0; i < texts_2.length; i++) {
				document.getElementById(texts_2[i]).value = soldlist[pos][i];
			}

			for (var i = 0; i < comboxs_2.length; i++) {
				if (soldlist[pos][2 * i] == ""
						&& soldlist[pos][2 * i + 1] == "") {
					document.getElementById(texts_2[2 * i]).readOnly = true;
					document.getElementById(texts_2[2 * i + 1]).readOnly = true;
					document.getElementById(comboxs_2[i]).checked = false;
				} else {
					document.getElementById(texts_2[2 * i]).readOnly = false;
					document.getElementById(texts_2[2 * i + 1]).readOnly = false;
					document.getElementById(comboxs_2[i]).checked = true;
				}
			}
		}

		var table_h = rowcount * 35;
		if (document.getElementById("strategyLineChart").style.display == "none") {
			modiv.style.marginTop = (td.offsetTop - table_h + 10 - 300) + "px";
		} else {
			modiv.style.marginTop = (td.offsetTop - table_h + 10 - 300 - 430)
					+ "px";
		}
		modiv.style.marginLeft = td.getBoundingClientRect().left + "px";

		var confirmMod = document.getElementById(buttontype[col]);
		confirmMod.onclick = function() {
			if (col == 4) {
				for (var i = 0; i < texts_2.length; i++) {
					buylist[pos][i] = document.getElementById(texts_2[i]).value;
				}
			} else if (col == 5) {
				for (var i = 0; i < texts_2.length; i++) {
					soldlist[pos][i] = document.getElementById(texts_2[i]).value;
				}
			} else if (col != 6) {
				if (col == 1) {
					var minus = parseInt(document
							.getElementById(inputtype[col]).value)
							- parseInt(td.innerHTML);
					if (tempcost - minus >= 0) {
						tempcost -= minus;
						document.getElementById("cost").placeholder = tempcost
								+ "  可用";
					} else {
						alert("资金不够啦");
						return;
					}
				}

				if (document.getElementById(inputtype[col]).value != "") {
					td.innerHTML = document.getElementById(inputtype[col]).value;
					perST[pos][i] = document.getElementById(inputtype[col]).value;
				}
			}
			modifyCancel();
		}
	}
}

// 取消修改
function modifyCancel() {
	var dialogtype = [ "textfieldMod", "timepickMod", "stMod", "stMake" ];
	var modiv;
	for (var i = 0; i < dialogtype.length; i++) {
		modiv = document.getElementById(dialogtype[i]);
		if (modiv.style.display == "block") {
			modiv.style.display = "none";
		}
	}

	document.getElementById("modtext").value = "";
	document.getElementById("moddate").value = "";
}

// 删除
function deleteST() {
	var table = document.getElementById("strategyTable");
	var boxs = table.getElementsByTagName("input");
	var boxlen = boxs.length;
	var trs = table.getElementsByTagName("tr");

	for (var i = boxlen - 1; i > 0; i--) {
		if (boxs[i].checked == true) {
			var tds = trs[i].getElementsByTagName("td");
			tempcost += parseInt(tds[2].innerHTML);
			document.getElementById("cost").placeholder = tempcost + "  可用";
			table.deleteRow(i);
			buylist.splice(boxlen - 1 - i, 1);
			soldlist.splice(boxlen - 1 - i, 1);
			perST.splice(boxlen - 1 - i, 1)
			count--;
		}
	}

	zebra();
}

// save
function saveST() {

	var buydata = new Array();
	var solddata = new Array();
	for (var i = 0; i < count; i++) {
		buydata[i] = new Array();
		solddata[i] = new Array();
		for (var j = 0; j < buylist[0].length; j++) {
			buydata[i][j] = (buylist[i][j] == "") ? 0 : buylist[i][j];
			solddata[i][j] = (soldlist[i][j] == "") ? 0 : soldlist[i][j];
		}
	}

	var per = perST.join(";")
	var buy = buydata.join(";")
	var sold = solddata.join(";")
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../ManageMyStrategy",
		data : {
			"stName" : stName,
			"totalcost" : totalcost,
			"perST" : per,
			"BuyList" : buy,
			"SoldList" : sold
		},
		dataType : "json",
		success : function(result) {
			if (result[0].SaveResult == "Succeed") {
				document.getElementById("savesuccess").style.display = "block";
				setTimeout(hide, "3000");
			}else if(result[0].SaveResult == "Unlogin"){
				alert("您还没登录呢")
			}else {
				alert("策略重名啦");
			}

			function hide() {
				document.getElementById("savesuccess").style.display = "none";
			}
		},
		error : function() {
			alert("策略保存失败");
		}
	})

}

function selectAll() {
	var table = document.getElementById("strategyTable");
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

// 斑马线效果
function zebra() {
	var table = document.getElementById("strategyTable");
	var tr = table.getElementsByTagName("tr");
	for (var i = 1; i < tr.length; i++) {
		if (i % 2 == 0) {
			tr[i].style.backgroundColor = "rgb(250,250,250)";
		} else {
			tr[i].style.backgroundColor = "";
		}
	}
}

// 设定本金、策略名称
function setName() {
	var stname = document.getElementById("strategyname");
	var stcost = document.getElementById("totalcost");

	if (stname.value != "" && stcost.value != "") {
		document.getElementById("stname").innerHTML = stname.value;
		document.getElementById("stcost").innerHTML = ("  本金:" + stcost.value);
		stName = stname.value;
		totalcost = stcost.value;
		tempcost = totalcost;
		document.getElementById("cost").placeholder = tempcost + "  可用";
	} else {
		alert('请输入完整信息');
	}
}

function showSTmake(field) {
	modifyCancel();
	var stdiv = document.getElementById("stMake");
	if (stdiv.style.display == "none") {
		stdiv.style.display = "block";
	}
	stdiv.style.position = "absolute";
	stdiv.style.top = (field.offsetTop + 30) + "px";
	stdiv.style.marginLeft = field.getBoundingClientRect().left + "px";

	for (var i = 0; i < comboxs.length; i++) {
		var combox = document.getElementById(comboxs[i]);
		combox.setAttribute("onclick", "setLimit(" + i + ")")
	}

	// 向temp中存数据
	document.getElementById("stmake_btn").onclick = function() {
		if (field.id == "buyinst") {
			for (var i = 0; i < texts.length; i++) {
				buytemp[i] = document.getElementById(texts[i]).value;
			}
		} else {
			for (var i = 0; i < texts.length; i++) {
				soldtemp[i] = document.getElementById(texts[i]).value;
			}
		}

		modifyCancel();
	}

	// 制定策略的点开界面

	if (field.id == "buyinst") {
		for (var i = 0; i < texts.length; i++) {
			document.getElementById(texts[i]).value = buytemp[i];
		}

		for (var i = 0; i < comboxs.length; i++) {
			if (buytemp[2 * i] == "" && buytemp[2 * i + 1] == "") {
				document.getElementById(texts[2 * i]).readOnly = true;
				document.getElementById(texts[2 * i + 1]).readOnly = true;
				document.getElementById(comboxs[i]).checked = false;
			} else {
				document.getElementById(texts[2 * i]).readOnly = false;
				document.getElementById(texts[2 * i + 1]).readOnly = false;
				document.getElementById(comboxs[i]).checked = true;
			}
		}

	} else {
		for (var i = 0; i < texts.length; i++) {
			document.getElementById(texts[i]).value = soldtemp[i];
		}

		for (var i = 0; i < comboxs.length; i++) {
			if (soldtemp[2 * i] == "" && soldtemp[2 * i + 1] == "") {
				document.getElementById(texts[2 * i]).readOnly = true;
				document.getElementById(texts[2 * i + 1]).readOnly = true;
				document.getElementById(comboxs[i]).checked = false;
			} else {
				document.getElementById(texts[2 * i]).readOnly = false;
				document.getElementById(texts[2 * i + 1]).readOnly = false;
				document.getElementById(comboxs[i]).checked = true;
			}
		}
	}
}

// 制定策略块
function setLimit(pos) {

	var combox = document.getElementById(comboxs[pos]);

	pos = pos * 2;

	if (combox.checked == true) {
		document.getElementById(texts[pos]).readOnly = false;
		document.getElementById(texts[pos + 1]).readOnly = false;
	} else {
		document.getElementById(texts[pos]).value = "";
		document.getElementById(texts[pos + 1]).value = "";
		document.getElementById(texts[pos]).readOnly = true;
		document.getElementById(texts[pos + 1]).readOnly = true;
	}
}

// 修改策略块
function setLimit_2(pos) {

	var combox = document.getElementById(comboxs_2[pos]);

	pos = pos * 2;

	if (combox.checked == true) {
		document.getElementById(texts_2[pos]).readOnly = false;
		document.getElementById(texts_2[pos + 1]).readOnly = false;
	} else {
		document.getElementById(texts_2[pos]).value = "";
		document.getElementById(texts_2[pos + 1]).value = "";
		document.getElementById(texts_2[pos]).readOnly = true;
		document.getElementById(texts_2[pos + 1]).readOnly = true;
	}
}

function runST() {

	var buydata = new Array();
	var solddata = new Array();
	for (var i = 0; i < count; i++) {
		buydata[i] = new Array();
		solddata[i] = new Array();
		for (var j = 0; j < buylist[0].length; j++) {
			buydata[i][j] = (buylist[i][j] == "") ? 0 : buylist[i][j];
			solddata[i][j] = (soldlist[i][j] == "") ? 0 : soldlist[i][j];
		}
	}

	var per = perST.join(";")
	var buy = buydata.join(";")
	var sold = solddata.join(";")
	$.ajax({
		type : "post",
		async : false, // 同步执行
		url : "../RunStrategy",
		data : {
			"stName" : stName,
			"totalcost" : totalcost,
			"perST" : per,
			"BuyList" : buy,
			"SoldList" : sold
		},
		dataType : "json",
		success : function() {
			var div = document.getElementById("strategyLineChart");
			if (div.style.display == "none") {
				div.style.display = "block";
			}
			getLinechart("../RunStrategy");
		},
		error : function() {
			alert("策略模拟失败");
		}
	})
}