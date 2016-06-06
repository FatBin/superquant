function addStrategy() {

	var getID = [ "stockchoose", "cost", "startdate", "enddate", "buyinst",
			"soldoutst", "otherst", "frequency" ];
	var getInfo = [];

	for (var i = 0; i < getID.length; i++) {
		getInfo[i] = document.getElementById(getID[i]).value;

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

	for (var i = 0; i < getInfo.length; i++) {
		var td = document.createElement("td");
		td.innerHTML = getInfo[i];
		td.style.cursor = "pointer";
		td.setAttribute("onclick", "modifyST(this)");
		tr.appendChild(td);
	}

	var boxs = table.getElementsByTagName("input");
	boxs[0].checked = false;

	zebra();
}

function resetAll() {
	var getID = [ "stockchoose", "cost", "startdate", "enddate", "buyinst",
			"soldoutst", "otherst", "frequency" ];

	for (var i = 0; i < getID.length; i++) {
		document.getElementById(getID[i]).value = "";
	}
}

// 修改
function modifyST(td) {
	modifyCancel();

	var dialogtype = [ "textfieldMod", "textfieldMod", "timepickMod",
			"timepickMod", "comboxMod", "comboxMod", "comboxMod",
			"textfieldMod" ];
	var inputtype = [ "modtext", "modtext", "moddate", "moddate", "modselect",
			"modselect", "modselect", "modtext" ];
	var buttontype = [ "modCon_1", "modCon_1", "modCon_2", "modCon_2",
			"modCon_3", "modCon_3", "modCon_3", "modCon_1" ];

	// td的列数
	var col = ($(td).parents("tr").find("td").index($(td))) - 1;

	var modiv = document.getElementById(dialogtype[col]);
	if (modiv.style.display == "none") {
		modiv.style.display = "block";
	}

	var table_h = (document.getElementById("strategyTable").rows.length - 1) * 35;

	modiv.style.marginTop = (td.offsetTop - table_h + 10 - 300) + "px";
	modiv.style.marginLeft = td.getBoundingClientRect().left + "px";

	var confirmMod = document.getElementById(buttontype[col]);
	confirmMod.onclick = function() {
		if (document.getElementById(inputtype[col]).value != "") {
			td.innerHTML = document.getElementById(inputtype[col]).value;
		}
		modifyCancel();
	}
}

// 取消修改
function modifyCancel() {
	var dialogtype = [ "textfieldMod", "timepickMod", "comboxMod" ];
	var modiv;
	for (var i = 0; i < 3; i++) {
		modiv = document.getElementById(dialogtype[i]);
		if (modiv.style.display == "block") {
			modiv.style.display = "none";
		}
	}

	document.getElementById("modtext").value = "";
	document.getElementById("modselect").value = "";
	document.getElementById("moddate").value = "";
}

// 删除
function deleteST() {
	var table = document.getElementById("strategyTable");
	var boxs = table.getElementsByTagName("input");
	var boxlen = boxs.length;

	for (var i = boxlen - 1; i > 0; i--) {
		if (boxs[i].checked == true) {
			table.deleteRow(i);
		}
	}

	zebra();
}

// save
function saveST() {

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
	} else {
		alert('请输入完整信息');
	}
}