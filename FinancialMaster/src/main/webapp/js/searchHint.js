var rowpos = 0;

function showHint(str) {

	// alert(event.keyCode); // 38上 40下 13回车

	document.body.onclick = function() {
		var hint = document.getElementById("searchHint");
		if (hint.style.display == "block") {
			hint.style.display = "none";
			rowpos = -1;
			return;
		}
	}

	if (str.length == 0) {
		document.getElementById("searchHint").innerHTML = '';
		rowpos = -1;
		return;
	} else {

		var div = document.getElementById("searchHint");
		div.style.display = "block";

		if (event.keyCode != 38 && event.keyCode != 40 && event.keyCode != 13) {
			div.innerHTML = '';

			var data = [];
			$.ajax({
				type : "post",
				async : false, // 同步执行
				url : "../StockSearch",
				data : {
					'key' : str
				},
				dataType : "json",
				success : function(result) {
					if (result) {
						for (var i = 0; i < result.length; i++) {
							data.push(result[i].stockMessage);
						}
					}
				},
				error : function(errorMsg) {
					alert("不好意思，请求数据失败啦!");
				}
			})

			var datalen = data.length;
			if (datalen > 10) {
				datalen = 10;
			}
			for (var i = 0; i < datalen; i++) {
				var li = document.createElement("li");
				li.innerHTML = data[i];
				li.style.cursor = "pointer";
				li.setAttribute("onmouseover", "search_mouseIn(" + i + ")");
				li.setAttribute("onclick", "search_mouseClick(" + i + ")");
				div.appendChild(li);
			}
		}

		var uls = div.getElementsByTagName("li");
		var rowcounts = uls.length;
		if (event.keyCode == 38) {

			for (var i = 0; i < uls.length; i++) {
				uls[i].style.backgroundColor = "rgb(235, 235, 235)";
			}
			if (rowpos > 0) {
				rowpos--;
			}
			uls[rowpos].style.backgroundColor = "rgb(127, 127, 127)";
		} else if (event.keyCode == 40) {

			for (var i = 0; i < uls.length; i++) {
				uls[i].style.backgroundColor = "rgb(235, 235, 235)";
			}
			if (rowpos < rowcounts - 1) {
				rowpos++;
			}
			uls[rowpos].style.backgroundColor = "rgb(127, 127, 127)";
		} else if (event.keyCode == 13) {
			search_mouseClick(rowpos);
		} else {
			rowpos = -1;
		}
	}
}

function search_mouseIn(pos) {
	var uls = document.getElementById("searchHint").getElementsByTagName("li");
	for (var i = 0; i < uls.length; i++) {
		uls[i].style.backgroundColor = "rgb(235, 235, 235)";
	}
	uls[pos].style.backgroundColor = "rgb(127, 127, 127)";
	rowpos = pos;
}

function search_mouseClick(pos) {
	var uls = document.getElementById("searchHint").getElementsByTagName("li");
	var stockMes = uls[pos].innerHTML.split(' ');

	$.ajax({
		type : "post",
		async : false, // 同步执行
		url : '../ToStockDetailPageServlet',
		data : {
			"Stockid" : stockMes[0]
		},
		dataType : "json"
	})
	window.location.href = '../ToStockDetailPageServlet';

}
