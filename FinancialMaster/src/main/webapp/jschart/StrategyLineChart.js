/**
 * 
 */



function getLinechart(url,id) {
	
	var StrategyLineChart = echarts.init(document
			.getElementById(id));

	var dates = [];

	var profits = [];

	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : url,
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
			alert("不好意思，策略结果请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
		backgroundColor: "transparent",
		tooltip : {
			trigger : 'axis',
			position : function(pt) {
				return [ pt[0], '10%' ];
			}
		},
		title : {
			left : 'center',
			text : '盈亏情况',
			textStyle : {
				fontSize : 16, 
				fontWeight : 400
			},
		},
		legend : {
			top : 'bottom',
			data : [ '意向' ]
		},
		grid : [ {
			top: "25"
		}],
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
			end : 100
		}, {
			start : 0,
			end : 100
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
						color : 'rgb(200,0,0)'
					}, {
						offset : 1,
						color : 'rgb(0,200,0)'
					} ])
				}
			},
			data : profits
		} ]
	};
	StrategyLineChart.setOption(option);
}