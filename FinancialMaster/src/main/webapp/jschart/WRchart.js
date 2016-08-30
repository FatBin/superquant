/**
 * 
 */

/**
 * 个股技术分析————WR
 */
function initWR(){

var WRchart = echarts.init(document.getElementById('WRchart'));


	// 日期
	var dates = [];
	var WR9s = [];
	var WR14s = [];
	var WR20s=[];
	
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../GetWRChart",
		// data : {id:code},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					WR9s.push(result[i].WR9);
					WR14s.push(result[i].WR14);
					WR20s.push(result[i].WR20);;
				}
			}

		},
		error : function(errorMsg) {
			alert("不好意思，WR图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
//		title : {
//			text : 'WR分析图表',
//			left : 40
//		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend :  {
			data : [ 'WR9', 'WR14','WR20'],
			left:'right'
		},
		grid :  {
			left : '8%',
			right : '5%',
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
					name : 'WR9',
					type : 'line',
					data : WR9s,
					markLine: {
		                silent: true,
		                data: [{
		                    yAxis: 20
		                }, {
		                    yAxis: 50
		                }, {
		                    yAxis: 80
		                }]
		            }
				},{
					name : 'WR14',
					type : 'line',
					data : WR14s,
					markLine: {
		                silent: true,
		                data: [{
		                    yAxis: 20
		                }, {
		                    yAxis: 50
		                }, {
		                    yAxis: 80
		                }]
		            }
				},{
					name : 'WR20',
					type : 'line',
					data : WR20s,
					markLine: {
		                silent: true,
		                data: [{
		                    yAxis: 20
		                }, {
		                    yAxis: 50
		                }, {
		                    yAxis: 80
		                }]
		            }
				}]
	};

	WRchart.setOption(option);
}