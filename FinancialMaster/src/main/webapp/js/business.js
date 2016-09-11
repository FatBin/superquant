var nowPage = null;
var all = null;

$(document).ready(function() {
	nowPage = 1;
	all = document.getElementsByClassName("pagination")[0].childNodes;
	disappear();
	appear(0);
});

function disappear() {
	for (var i = 0; i < 66; i++) {
		document.getElementsByClassName("business-list")[i].style.display = "none";
	}
}

function appear(num) {
	for (var i = num * 11; i < (num + 1) * 11; i++) {
		document.getElementsByClassName("business-list")[i].style.display = "block";
	}
}

function switchPage(li) {
	for (var j = 0; j < all.length; j++) {
		all[j].className = "";
	}
	li.parentNode.className = "active";
	var i = Number(li.innerHTML);
	nowPage = i;
	disappear();
	appear(i - 1);
}

nextPage()
{
	if (nowPage < 6) {
		disappear();
		appear(nowPage);
		for (var j = 0; j < all.length; j++) {
			all[j].className = "";
		}
		all[nowPage].className = "active";
		nowPage++;
	}
}

previousPage()
{
	if (nowPage > 1) {
		disappear();
		appear(nowPage - 1);
		for (var j = 0; j < all.length; j++) {
			all[j].className = "";
		}
		all[nowPage - 2].className = "active";
		nowPage--;
	}
}