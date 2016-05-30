var rowpos = 0;

function showHint(str) {

	// alert(event.keyCode); // 38上 40下 13回车

	if (str.length == 0) {
		document.getElementById("searchHint").innerHTML = '';
		rowpos = -1;
		return;
	} else {

		var div = document.getElementById("searchHint");

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
				li.setAttribute("onmouseover", "search_mouseIn(" + i + ")");
				li.setAttribute("onmouseout", "search_mouseOut(" + i + ")");
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

function search_mouseOut(pos) {
	var uls = document.getElementById("searchHint").getElementsByTagName("li");
	uls[pos].style.backgroundColor = "rgb(235, 235, 235)";
	rowpos = -1;
}
