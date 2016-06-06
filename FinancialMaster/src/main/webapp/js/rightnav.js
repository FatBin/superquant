var hrefid = [ "comprehensive", "technical", "market", "business", "basic",
		"moneyflow" ];
var hrefname = [ "综合分析", "技术分析", "大盘分析", "行业分析", "基本项", "资金流向", "返回顶部" ];

function navskip() {
	var nav = document.getElementById("rightnav");

	for (var i = 0; i < 7; i++) {
		var div = document.createElement("div");
		div.style.width = "63px";
		div.style.height = "30px";
		div.style.backgroundColor = "rgb(235,235,235)";
		div.style.marginTop = "2px";
		div.style.borderRadius = "2px";
		div.style.cursor = "pointer";
		div.setAttribute("onclick", "mouseClick(" + i + ")");
		div.setAttribute("onmouseover", "mouseIn(" + i + ")");
		div.setAttribute("onmouseout", "mouseOut(" + i + ")")
		var span = document.createElement("span");
		span.innerHTML = hrefname[i];
		span.style.lineHeight = "30px";
		div.appendChild(span);
		nav.appendChild(div);
	}
}

function mouseClick(pos) {

	if (pos < 6) {
		var div = document.getElementById(hrefid[pos]);
		var move = div.offsetTop - 150;
		$("body,html").animate({
			scrollTop : move
		}, 500);
	} else {
		$("body,html").animate({
			scrollTop : 0
		}, 500)
	}
}

function mouseIn(pos) {
	mouseOut(pos);
	var rightnav = document.getElementById("rightnav");
	var divs = rightnav.getElementsByTagName("div");
	divs[pos].style.backgroundColor = "gray";
}

function mouseOut(pos){
	var rightnav = document.getElementById("rightnav");
	var divs = rightnav.getElementsByTagName("div");
	for (var i = 0; i < 7; i++) {
		divs[i].style.backgroundColor = "rgb(235,235,235)";
	}
}