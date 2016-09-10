/**
 * 
 */

/**
 * 个股技术分析————BIAS
 */
function initBIAS(){

var BIASchart = echarts.init(document.getElementById('BIASchart'));


	// 日期
	var dates = [];
	var BIAS6s = [];
	var BIAS12s = [];
	var BIAS24s=[];
	
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../GetBIASChart",
		// data : {id:code},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					BIAS6s.push(result[i].BIAS6);
					BIAS12s.push(result[i].BIAS12);
					BIAS24s.push(result[i].BIAS24);;
				}
			}

		},
		error : function(errorMsg) {
			alert("不好意思，BIAS图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
//		title : {
//			text : 'BIAS分析图表',
//			left : 40
//		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend :  {
			data : [ 'BIAS6', 'BIAS12','BIAS24'],
			left:'right'
		},
		grid :  {
			left : '8%',
			right : '0%',
			top:  '15%',
			height : '63%'
		},
		xAxis : {
			type : 'category',
			data : dates,
			scale : true
		},
		yAxis :  {
//			name : '',
			scale : true,
			splitArea : {
				show : true
			}
		},
		dataZoom : [ {
			type : 'inside',
			start : 60,
			end : 100,
		}, {
			show : true,
			type : 'slider',
			y : '90%',
			start : 60,
			end : 100,
		} ],
		series :[{
					name : 'BIAS6',
					type : 'line',
					data : BIAS6s,
				},{
					name : 'BIAS12',
					type : 'line',
					data : BIAS12s,
				},{
					name : 'BIAS24',
					type : 'line',
					data : BIAS24s,
				}]
	};

	BIASchart.setOption(option);
}