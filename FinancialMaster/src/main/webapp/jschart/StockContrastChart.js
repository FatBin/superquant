/**
 * 同行业股票对比
 */
var stockContrastChart = echarts.init(document
		.getElementById('stockContrastChart'));

var up_and_downs = [];
var names = [];

$.ajax({
	type : "get",
	async : false, //同步执行
	url : '../GetBusinessContrast',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				names.push(result[i].name);
				up_and_downs.push(result[i].up_and_down);
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，同行业股票对比图请求数据失败啦!");
		myChart.hideLoading();
	}
})

option = {
	title : {
		text : '同行业涨跌率排名',
	},
	tooltip : {
		trigger : 'axis'
	},
	calculable : true,
	xAxis : [ {
		type : 'category',
		data : names,
		axisLabel : {
			interval : 0,
		//    			rotate : 45,
		}
	} ],
	yAxis : [ {
		type : 'value'
	} ],
	grid : {
		top : '35%',
		left : '3%',
		right : '4%',
		bottom : '3%',
		containLabel : true
	},
	series : [ {
		name : '涨跌率',
		type : 'bar',
		data : up_and_downs,
		label : {
			normal : {
				show : true,
				formatter : '{c}%',
				position : 'top'
			}
		},
		itemStyle : {
			normal : {
				color : function(params) {
					if (params.dataIndex < 3) {
						return 'rgb(191,23,34)';
					} else {
						return 'rgb(25,36,83)';
					}

				}
			}
		}
	} ]
};
stockContrastChart.setOption(option);