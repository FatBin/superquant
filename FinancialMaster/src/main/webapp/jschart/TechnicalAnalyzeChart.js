/**
 * 技术分析模块柱状图
 */

var TechnicalAnalyzeChart= echarts
		.init(document.getElementById('TechnicalAnalyzeChart'));

var price_MAs =[];
var volume_MAs=[];
var RSIs=[];


$.ajax({
	type : "post",
	async : false, //同步执行
	url : '../GetTechnicalAnalyzeChart',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				price_MAs.push(result[i].price_MA);
				volume_MAs.push(result[i].volume_MA);
				RSIs.push(result[i].RSI);
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，大爷，图表请求数据失败啦!");
		myChart.hideLoading();
	}
})


option = {

	grid : [ {
		left : '5%',
		right : '75%',
		top:'15%',
		bottom:"15%"
	}, {
		left : '42%',
		right : '38%',
		top:'15%',
		bottom:"15%"
	}, {
		left : '75%',
		right : '5%',
		top:'15%',
		bottom:"15%"
	} ],
	xAxis : [ {
		type : 'category',
		data : ['最新价','MA10','MA30'],
		axisLabel : {
			interval : 0,
//			rotate : 45,
		}
	}, {
		gridIndex : 1,
		type : 'category',
		data : ['最新成交量','MA10','MA30'],
		axisLabel : {
			interval : 0,
//			rotate : 45,
		}
	}, {
		gridIndex : 2,
		type : 'category',
		data : ['RSI1','RSI2','RSI3'],
		axisLabel : {
			interval : 0,
//			rotate : 45,
		}
	} ],
	yAxis : [ {
		name : '价格',
		scale : true,
	}, {
		name : '成交量',
		scale : true,
		gridIndex : 1,
	}, {
		name : 'RSI',
		scale : true,
		gridIndex : 2,
	} ],

	series : [ {
		type : 'bar',
		data : price_MAs,
	}, {
		type : 'bar',
		xAxisIndex : 1,
		yAxisIndex : 1,
		data : volume_MAs,
	}, {
		type : 'bar',
		xAxisIndex : 2,
		yAxisIndex : 2,
		data : RSIs,
	},

	]

}
TechnicalAnalyzeChart.setOption(option);