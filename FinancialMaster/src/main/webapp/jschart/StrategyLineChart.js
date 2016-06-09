/**
 * 
 */

var StrategyLineChart = echarts.init(document
		.getElementById('strategyLineChart'));

function getLinechart() {

	var dates = [];

	var profits = [];

	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : '../RunStrategy',
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					profits.push(result[i].profit);
				}
			}
		},
		error : function(errorMsg) {
			alert("不好意思，大爷，图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
		tooltip : {
			trigger : 'axis',
			position : function(pt) {
				return [ pt[0], '10%' ];
			}
		},
		title : {
			left : 'center',
			text : '盈亏情况',
		},
		legend : {
			top : 'bottom',
			data : [ '意向' ]
		},
		toolbox : {
			show : true,
			feature : {
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},

			}
		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			data : dates
		},
		yAxis : {
			type : 'value',
			boundaryGap : [ 0, '100%' ]
		},
		dataZoom : [ {
			type : 'inside',
			start : 0,
			end : 10
		}, {
			start : 0,
			end : 10
		} ],
		series : [ {
			name : '模拟数据',
			type : 'line',
			smooth : true,
			symbol : 'none',
			sampling : 'average',
			itemStyle : {
				normal : {
					color : 'rgb(255, 70, 131)'
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgb(255, 0, 0)'
					}, {
						offset : 1,
						color : 'rgb(0, 255, 0)'
					} ])
				}
			},
			data : profits
		} ]
	};
	StrategyLineChart.setOption(option);
}