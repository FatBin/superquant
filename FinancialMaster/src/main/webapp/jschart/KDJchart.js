/**
 * 
 */

/**
 * 个股技术分析————KDJ
 */
function initKDJ(){

var KDJchart = echarts.init(document.getElementById('KDJchart'));


	// 日期
	var dates = [];
	var Ks = [];
	var Ds = [];
	var Js=[];
	
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../GetKDJChart",
		// data : {id:code},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					Ks.push(result[i].K);
					Ds.push(result[i].D);
					Js.push(result[i].J);;
				}
			}

		},
		error : function(errorMsg) {
			alert("不好意思，KDJ图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
//		title : {
//			text : 'KDJ分析图表',
//			left : 40
//		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend :  {
			data : [ 'K', 'D','J'],
			left:'right'
		},
		grid :  {
			left : '5%',
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
					name : 'K',
					type : 'line',
					data : Ks,
				},{
					name : 'D',
					type : 'line',
					data : Ds,
				},{
					name : 'J',
					type : 'line',
					data : Js,
				}]
	};

	KDJchart.setOption(option);
}