changeColor();
function changeColor() {
	var nowspan = document.getElementById("now");
	var rfspan = document.getElementById("rise_fall");
	if (rfspan.innerHTML[0] == "-") {
		nowspan.style.color = "green";
		rfspan.style.color = "green";
	} else {
		nowspan.style.color = "red";
		rfspan.style.color = "red";
	}
}

function init_Pic(isConcerned) {
	if (isConcerned == true) {
		document.getElementById("heartdiv").setAttribute("src",
				"../webImage/heart-selected.png");
	} else {
		document.getElementById("heartdiv").setAttribute("src",
				"../webImage/heart.png")
	}
}

function changePic() {
	var src = document.getElementById("heartdiv").getAttribute("src");
	if (src == "../webImage/heart.png") {
		$.ajax({
			type : "get",
			async : false, // 同步执行
			url : "../ManageMyStock",
			dataType : "json",
			success : function(result) {
				if (result[0].AddResult == 'Succeed') {
					document.getElementById("heartdiv").setAttribute("src",
							"../webImage/heart-selected.png");
				} else if (result[0].AddResult == 'Unlogin') {
					alert("请先登录再添加关注");
				} else if (result[0].AddResult == 'Fail') {
					alert("您已关注啦");
				} else {
					alert("sorry，服务器请求失败啦");
				}
			},
			error : function(errorMsg) {
				alert("不好意思，请求数据失败啦!");
			}
		})

	} else {

		$.ajax({
			type : "get",
			async : false, // 同步执行
			url : "../ManageMyStock",
			dataType : "json",
			success : function(result) {
				if (result[0].AddResult == 'Succeed') {
					document.getElementById("heartdiv").setAttribute("src",
							"../webImage/heart.png")
				} else if (result[0].AddResult == 'Unlogin') {
					alert("请先登录再管理个人关注");
				} else if (result[0].AddResult == 'Fail') {
					alert("您还没关注这支股哦");
				} else {
					alert("sorry，服务器请求失败啦");
				}
			},
			error : function(errorMsg) {
				alert("不好意思，请求数据失败啦!");
			}
		})
	}
}