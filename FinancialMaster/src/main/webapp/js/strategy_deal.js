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
		tr.appendChild(td);
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

}