/**
 *   个股分析中资金流向差柱状图
 */

var barChart_inflows = echarts
		.init(document.getElementById('barChart_inflows'));
var kinds = [];
var gaps = [];

$.ajax({
	type : "get",
	async : false, //同步执行
	url : '../GetStockInflowPieChart',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				kinds.push(result[i].name);
				gaps.push(result[i].gap);
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，大爷，图表请求数据失败啦!");
		myChart.hideLoading();
	}
})

option = {
//	title : {
//		text : '资金流入流出差额'
//	},
//	tooltip : {
//		trigger : 'axis'
//	},
    grid: {
    	top: '5%',
        left: '0%',
        right: '0%',
        bottom: '5%',
        containLabel: true
    },
	//	legend : {
	//		data : [ '差额' ]
	//	},
	xAxis : {
		type : 'category',
		data : kinds,
		axisLine : {
			show : false
		},
		splitLine : {
			show : false
		},
		axisLabel : {
			interval : 0,
//			rotate : 45,
		}
	},
	yAxis : {
		type : 'value',
		name : '差额',
	},
	series : {
		name : '涨跌率',
		type : 'bar',
		data : gaps,
		label : {
			normal : {
				show : true,
				formatter : '{c}%',
				position:'top'
			}
		},
		itemStyle : {
			normal : {
				color : function(params) {
					if (gaps[params.dataIndex] > 0) {
						return 'rgb(191,23,34)';
					} else {
						return 'rgb(11,137,62)';
					}

				}
			}
		}
	}

};

barChart_inflows.setOption(option);
