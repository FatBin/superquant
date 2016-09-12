var nowPage = null;
var all = null;
var listLength;

$(document).ready(function() {
	listLength = document.getElementsById("listLength").innerHTML;
	nowPage = 1;
	all = document.getElementsByClassName("pagination")[0].children;
	disappear();
	appear(0);
});

function disappear() {
	for (var i = 0; i < listLength; i++) {
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

function nextPage() {
	if (nowPage < 6) {
		disappear();
		appear(nowPage);
		for (var j = 0; j < all.length; j++) {
			all[j].className = "";
		}
		all[++nowPage].className = "active";
	}
}

function previousPage() {
	if (nowPage > 1) {
		disappear();
		appear(nowPage - 2);
		for (var j = 0; j < all.length; j++) {
			all[j].className = "";
		}
		all[--nowPage].className = "active";
	}
}