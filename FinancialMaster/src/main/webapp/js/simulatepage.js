
var iBase = {
	SetOpacity : function(ev, v) {
		ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')'
				: ev.style.opacity = v / 100;
	}
};

// 关闭块
function closeST(elem_id) {

	var elem = document.getElementById(elem_id);
	var speed = 15;
	var opacity = 0;

	var val = 100;
	(function() {
		iBase.SetOpacity(elem, val);
		val -= 5;
		if (val >= opacity) {
			setTimeout(arguments.callee, speed);
		} else if (val < 0) {
			elem.style.display = 'none';
		}
	})();
}

// 打开块
function launchST(elem_id) {

	var elem = document.getElementById(elem_id);
	var speed = 20;
	var opacity = 100;

	elem.style.display = "block";
	iBase.SetOpacity(elem, 0);
	var val = 0;
	(function() {
		iBase.SetOpacity(elem, val);
		val += 5;
		if (val <= opacity) {
			setTimeout(arguments.callee, speed)
		}
	})();
}

