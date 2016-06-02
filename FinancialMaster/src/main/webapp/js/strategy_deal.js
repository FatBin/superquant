function addStrategy() {

	var getID = [ "stockchoose", "startdate", "buyinst", "otherst", "cost",
			"enddate", "soldoutst", "frequency" ];
	var getInfo = [];
	for (var i = 0; i < getID.length; i++) {
		getInfo[i] = document.getElementById(getID[i]).innerHTML;
	}
	
//	alert(getInfo[1]);

	var div = document.getElementById("strategyDetail");
	div.style.float = "left";

	var names = [ "选择股票", "开始日期", "买入策略", "其他策略", "投资成本", "结束日期", "卖出策略",
			"买卖频率" ];

	for (var i = 0; i < names.length / 2; i++) {

		var indiv = document.createElement("div");
		indiv.style.float = "left";
		indiv.style.marginBottom = "10px";

		// 列的上半块
		var indiv1 = document.createElement("div");
		indiv1.style.float = "left";

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
		indiv1.appendChild(field1);

		// 列的下半块
		var indiv2 = document.createElement("div");
		indiv2.style.float = "left";

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
		indiv2.appendChild(field2);

		indiv.appendChild(indiv1);
		indiv.appendChild(indiv2);
		div.appendChild(indiv);
	}

	// 修改
	var btndiv = document.createElement("div");
	var mobtn = document.createElement("input");
	mobtn.setAttribute("type", "button");
	mobtn.setAttribute("class", "btn add_cancel_btn");
	mobtn.setAttribute("onclick", "modify()");
	btndiv.appendChild(mobtn);
	div.appendChild(btndiv);

	var hr = document.createElement("hr");
	hr.style.width = "990px";
	div.appendChild(hr);

}

// 修改
function modify() {

}
