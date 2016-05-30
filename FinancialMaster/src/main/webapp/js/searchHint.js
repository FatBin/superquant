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
				div.appendChild(li);
			}
		}

		var uls = div.getElementsByTagName("li");
		var rowcounts = uls.length;
		if (event.keyCode == 38) {
			if (rowpos > 0) {
				if (rowpos < rowcounts) {
					uls[rowpos].style.backgroundColor = "rgb(235, 235, 235)";
				}
				rowpos--;
			}
			uls[rowpos].style.backgroundColor = "rgb(127, 127, 127)";
		} else if (event.keyCode == 40) {
			if (rowpos < rowcounts - 1) {
				if (rowpos > -1) {
					uls[rowpos].style.backgroundColor = "rgb(235, 235, 235)";
				}
				rowpos++;
			}
			uls[rowpos].style.backgroundColor = "rgb(127, 127, 127)";
		} else if (event.keyCode == 13) {

		} else {
			rowpos = -1;
		}
	}
}