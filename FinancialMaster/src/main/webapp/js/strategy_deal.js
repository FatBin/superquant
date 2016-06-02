function addStrategy() {

	var getID = [ "stockchoose", "startdate", "buyinst", "otherst", "cost",
			"enddate", "soldoutst", "frequency" ];
	var getInfo = [];

	for (var i = 0; i < getID.length; i++) {
		getInfo[i] = document.getElementById(getID[i]).value;

		// if (getInfo[i] == '') {
		// alert("请制订完整策略");
		// return;
		// }
	}

	var div = document.getElementById("strategyDetail");
	div.style.float = "left";

	var names = [ "选择股票", "开始日期", "买入策略", "其他策略", "投资成本", "结束日期", "卖出策略",
			"买卖频率" ];

	for (var i = 0; i < names.length / 2; i++) {

		var indiv = document.createElement("div");
		indiv.style.float = "left";
		indiv.style.marginBottom = "20px";
		indiv.style.marginRight = "40px";
		indiv.style.marginLeft = "5px";

		// 列的上半块
		var indiv1 = document.createElement("div");

		var above = document.createElement("i");
		above.innerHTML = names[i];
		above.setAttribute("class", "detailname");
		indiv1.appendChild(above);

		var field1 = document.createElement("input");
		field1.setAttribute("type", "text");
		field1.setAttribute("class", "form-control");
		field1.setAttribute("size", "16");
		field1.setAttribute("readonly", "true");
		field1.style.width = "100px";
		field1.value = getInfo[i];
		indiv1.appendChild(field1);

		// 列的下半块
		var indiv2 = document.createElement("div");
		indiv2.style.float = "left";
		indiv2.style.marginTop = "10px";

		var below = document.createElement("i");
		below.innerHTML = names[i + 4];
		below.setAttribute("class", "detailname");
		indiv2.appendChild(below);

		var field2 = document.createElement("input");
		field2.setAttribute("type", "text");
		field2.setAttribute("class", "form-control");
		field2.setAttribute("size", "16");
		field2.setAttribute("readonly", "true");
		field2.style.width = "100px";
		field2.value = getInfo[i + 4];
		indiv2.appendChild(field2);

		indiv.appendChild(indiv1);
		indiv.appendChild(indiv2);
		div.appendChild(indiv);
	}

	// 修改、删除
	var btndiv = document.createElement("div");
	btndiv.style.float = "left";
	btndiv.style.marginLeft = "20px";

	var modiv = document.createElement("div");
	modiv.style.marginTop = "5px";
	modiv.style.marginBottom = "10px";
	var mobtn = document.createElement("input");
	mobtn.value = "修改";
	mobtn.setAttribute("type", "button");
	mobtn.setAttribute("class", "btn add_cancel_btn");
	mobtn.setAttribute("onclick", "javascript:modifyST(" + getInfo + ")");
	modiv.appendChild(mobtn);
	btndiv.appendChild(modiv);

	var dediv = document.createElement("div");
	var debtn = document.createElement("input");
	debtn.value = "删除";
	debtn.setAttribute("type", "button");
	debtn.setAttribute("class", "btn add_cancel_btn");
	dediv.appendChild(debtn);
	btndiv.appendChild(debtn);

	div.appendChild(btndiv);

	var hr = document.createElement("hr");
	hr.style.width = "990px";
	div.appendChild(hr);
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