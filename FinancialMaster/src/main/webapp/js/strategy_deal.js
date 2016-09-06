var buytemp = [ "", "", "", "", "", "", "", "", "", "" ]; // 制定策略时的买入策略 []
var soldtemp = [ "", "", "", "", "", "", "", "", "", "" ]; // 制定策略时的卖出策略 []
var buylist = new Array(); // 表格中的所有买入策略 [][]
var soldlist = new Array(); // 表格中的所有卖出策略 [][]
var perST = new Array();
var count = 0;
var stName;
var totalcost = 0;
var tempcost = 0;
var after_mod = 0; // 未修改为0， 经过修改为1
var buytemp_mod = [ "", "", "", "", "", "", "", "", "", "" ]; // 修改的缓存
var soldtemp_mod = [ "", "", "", "", "", "", "", "", "", "" ];
var isSaved = 1; // 判断是否已保存，0未保存，1保存;

var getID = [ "stockchoose", "cost", "startdate", "enddate", "buyinst",
		"soldoutst", "otherst", "frequency" ];
var texts = [ "getprice_low", "getprice_high", "getvolume_low",
		"getvolume_high", "getturnover_low", "getturnover_high", "getpe_low",
		"getpe_high", "getpb_low", "getpb_high" ];
var comboxs = [ "price_box", "volume_box", "turnover_box", "pe_box", "pb_box" ];

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
			slidein(2, "请制订完整策略");
			return;
		}
	}

	if (getInfo[2] > getInfo[3]) {
		slidein(2, "日期设定无效");
		return;
	}

	if (document.getElementById("stname").innerHTML == "我的策略") {
		slidein(2, "请设定策略名和本金");
		return;
	}

	if (tempcost - getInfo[1] < 0) {
		slidein(2, "资金不够啦");
		return;
	} else {
		tempcost -= getInfo[1];
		document.getElementById("moneyleft").innerHTML = tempcost;
	}

	buylist[count] = new Array();
	soldlist[count] = new Array();
	perST[count] = new Array();
	for (var i = 0; i < 10; i++) {
		buylist[count][i] = buytemp[i];
		soldlist[count][i] = soldtemp[i];
	}

	if (document.getElementById("myST").style.display == "none") {
		document.getElementById("myST").style.display = "";
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
	checkbox.style.float = "left";
	checkbox.style.marginLeft = "10px";
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

	var elem_pen = document.createElement("div");
	elem_pen.setAttribute("class", "fa fa-pencil mod_del_btn");
	elem_pen.style.float = "right";
	elem_pen.style.marginRight = "7px";
	elem_pen.style.display = "none";
	boxdiv.appendChild(elem_pen);

	tr.onmouseover = function() {
		elem_pen.style.display = "";
		elem_pen.onclick = function() {
			modifyST(this);
		}
	}
	tr.onmouseout = function() {
		elem_pen.style.display = "none";
	}

	var tempcount = 0;
	for (var i = 0; i < getInfo.length; i++) {
		var td = document.createElement("td");

		if (i == 4) {
			td.innerHTML = stDeal(buytemp, 0);
		} else if (i == 5) {
			td.innerHTML = stDeal(soldtemp, 1);
		} else {
			td.innerHTML = getInfo[i];
		}

		if (i != 4 && i != 5 && i != 6) {
			perST[count][tempcount] = getInfo[i];
			tempcount++;
		}
		tr.appendChild(td);
	}

	var boxs = table.getElementsByTagName("input");
	boxs[0].checked = false;

	var rundiv = document.getElementById("add_before");
	if (rundiv.style.display != "none") {
		rundiv.style.display = "none";
		document.getElementById("add_after").style.display = "";
	}

	document.getElementById("usedmoney").innerHTML = totalcost - tempcost;

	zebra();
	count++;
	resetAll();
	runST();

	var addbtn = document.getElementById("addST");
	if ((addbtn.innerHTML).trim() == "完成修改") {
		addbtn.innerHTML = "添加股票项";
		document.getElementById("resetbtn").innerHTML = "重置";
	}

	isSaved = 0;
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

function resetAll() {

	for (var i = 0; i < getID.length; i++) {
		document.getElementById(getID[i]).value = "";
	}
	buytemp = [ "", "", "", "", "", "", "", "", "", "" ];
	soldtemp = [ "", "", "", "", "", "", "", "", "", "" ];
}

// 重置按钮，可以取消
function resetAll_btn() {
	var resetbtn = document.getElementById("resetbtn");
	if ((resetbtn.innerHTML).trim() == "取消") {
		addStrategy();
		resetbtn.innerHTML = "重置";
		return;
	}

	for (var i = 0; i < getID.length; i++) {
		document.getElementById(getID[i]).value = "";
	}
	buytemp = [ "", "", "", "", "", "", "", "", "", "" ];
	soldtemp = [ "", "", "", "", "", "", "", "", "", "" ];
}

// 修改
function modifyST(elem_i) {
	var index = $(elem_i).parents("tr").parents("#strategyTable").find("tr")
			.index($(elem_i).parents("tr"));

	var pos = document.getElementById("strategyTable").getElementsByTagName(
			"tr").length
			- 1 - index;

	for (var i = 0; i < 4; i++) {
		document.getElementById(getID[i]).value = perST[pos][i];
	}
	document.getElementById(getID[7]).value = perST[pos][4];

	after_mod = 1;
	buytemp_mod = buylist[pos];
	soldtemp_mod = soldlist[pos];

	var table = document.getElementById("strategyTable");
	var boxs = table.getElementsByTagName("input");
	boxs[index].checked = true;
	deleteST();

	$("body,html").animate({
		scrollTop : 0
	}, 500);

	document.getElementById("addST").innerHTML = "完成修改";
	document.getElementById("resetbtn").innerHTML = "取消";
}

// 取消修改
function modifyCancel() {
	var modiv = document.getElementById("stMake");
	if (modiv.style.display == "block") {
		modiv.style.display = "none";
	}
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
			document.getElementById("moneyleft").innerHTML = tempcost;
			table.deleteRow(i);
			buylist.splice(boxlen - 1 - i, 1);
			soldlist.splice(boxlen - 1 - i, 1);
			perST.splice(boxlen - 1 - i, 1)
			count--;
		}
	}

	zebra();

	var isLast = table.getElementsByTagName("tr").length;
	if (isLast <= 1) {
		document.getElementById("add_before").style.display = "";
		document.getElementById("add_after").style.display = "none";
		document.getElementById("myST").style.display = "none";
	} else {
		runST();
	}

	isSaved = 0;
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
				slidein(0, "保存成功");
			} else if (result[0].SaveResult == "Unlogin") {
				slidein(2, "您还没登录");
			} else {
				slidein(2, "该策略名已存在");
			}
		},
		error : function() {
			slidein(1, "保存失败请稍候再试");
		}
	})

	isSaved = 1;
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
		document.getElementById("moneyleft").innerHTML = stcost.value;
	} else {
		slidein(2, '请输入完整信息');
	}
}

function showSTmake(field) {
	modifyCancel();
	var stdiv = document.getElementById("stMake");
	if (stdiv.style.display == "none") {
		stdiv.style.display = "block";
	}
	stdiv.style.position = "absolute";
	stdiv.style.top = (field.offsetTop + 371) + "px";
	stdiv.style.marginLeft = field.getBoundingClientRect().left + "px";

	for (var i = 0; i < comboxs.length; i++) {
		var combox = document.getElementById(comboxs[i]);
		combox.setAttribute("onclick", "setLimit(" + i + ")")
	}

	// 点击制订后，向temp中存数据
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

	if (after_mod == 1) {
		buytemp = buytemp_mod;
		soldtemp = soldtemp_mod;

		after_mod = 0;
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
			slidein(1, "策略模拟失败");
		}
	})
}

// 关闭新建策略块
function closeST() {

	document.getElementById("launchST_before").style.display = "";

	// 判断策略是否已保存
	if (isSaved == 0) {
		slidein(2, "再次点击放弃未保存更改");
		return;
	}

	var elem = document.getElementById("makest");
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

// 打开新建策略块
function launchST() {

	document.getElementById("launchST_before").style.display = "none";

	var elem = document.getElementById("makest");
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

var iBase = {
	SetOpacity : function(ev, v) {
		ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')'
				: ev.style.opacity = v / 100;
	}
};

function initSTlist() {

	var userId = document.getElementById("storage").innerHTML.trim();
	if (userId != null) {
		var divs = document.getElementsByClassName("myst_copy");

		for (var i = 0; i < divs.length; i++) {

			var table = divs[i].getElementsByTagName("table")[0];
			var stname = divs[i].getElementsByTagName("blockquote")[0].innerHTML.trim();
			
			var totalcost;
			var buylist;
			var soldlist;
			var stlist;

			$.ajax({
				type : "post",
				async : false, // 同步执行
				url : "../ToStrategyPageServlet",
				data : {
					'StrategyName' : stname
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
			}
		}
	}
}
