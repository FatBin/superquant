function addStrategy() {

	var getID = [ "stockchoose", "cost", "startdate", "enddate", "buyinst",
			"soldoutst", "otherst", "frequency" ];
	var getInfo = [];

	for (var i = 0; i < getID.length; i++) {
		getInfo[i] = document.getElementById(getID[i]).value;

		// if (getInfo[i] == '') {
		// alert("请制订完整策略");
		// return;
		// }
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

	for (var i = 0; i < getInfo.length; i++) {
		var td = document.createElement("td");
		td.innerHTML = getInfo[i];
		td.style.cursor = "pointer";
		tr.appendChild(td);
	}

	var boxs = table.getElementsByTagName("input");
	boxs[0].checked = false;
	
	zebra();
}

function resetAll(){
	var getID = [ "stockchoose", "cost", "startdate", "enddate", "buyinst",
	  			"soldoutst", "otherst", "frequency" ];
	
	for (var i = 0; i < getID.length; i++) {
		document.getElementById(getID[i]).value = "";
	}
}

// 修改
function modifyST(info) {
	alert(info);

	var getID = [ "stockchoose", "startdate", "buyinst", "otherst", "cost",
			"enddate", "soldoutst", "frequency" ];

	for (var i = 0; i < getID.length; i++) {
		document.getElementById(getID[i]).value = info[i];
	}
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
function saveST(){
	
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
		} else{
			tr[i].style.backgroundColor = "";
		}
	}
}