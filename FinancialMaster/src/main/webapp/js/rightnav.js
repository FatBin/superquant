var hrefid = [ "#comprehensive", "#technical", "#market", "#business",
		"#basic", "#moneyflow" ];
var hrefname = [ "综合分析", "技术分析", "大盘分析", "行业分析", "基本项", "资金流向" ];

function navskip() {
	var nav = document.getElementById("rightnav");

	for (var i = 0; i < 6; i++) {
		var div = document.createElement("div");
		div.style.width = "63px";
		div.style.height = "30px";
		div.style.backgroundColor = "rgb(235,235,235)";
		div.style.marginTop = "2px";
		div.style.borderRadius = "2px";
//		div.setAttribute("onclick", "mouseClick(" + i + ")");
//		div.setAttribute("");
		var span = document.createElement("span");
		span.innerHTML = hrefname[i];
		div.appendChild(span);
		nav.appendChild(div);
	}
}

function mouseClick(pos){
	
	window.location.hash = hrefid[pos];
}