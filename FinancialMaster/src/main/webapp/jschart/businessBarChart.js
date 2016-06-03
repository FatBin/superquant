/**
 * 
 */
var myChart = echarts.init(document.getElementById('business_barchart'));

var name1 = [];
var value1 = [];
$.ajax({
	type : "post",
	async : false, //同步执行
	url : '../ToBusinessPageServlet',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				name1.push(result[i].name);
				value1.push(result[i].value);
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，大爷，图表请求数据失败啦!");
		myChart.hideLoading();
	}
})
option = {
	title : {
		text : '',
		left : 'center'
	//	        subtext: 'From ExcelHome'
	},
	grid : {
		left : '3%',
		right : '4%',
		bottom : '12%',
		containLabel : true
	},
	xAxis : {
		type : 'category',
		data : name1,
		axisLine : {
			show : false
		},
		splitLine : {
			show : false
		},
		axisLabel : {
			interval : 0,
			rotate : 45,
		}
	},
	yAxis : {
		type : 'value',
		name : '涨跌率',
		splitLine : {
			lineStyle : {
				type : 'dashed'
			}
		},
	},
	series : {
		name : '涨跌率(%)',
		type : 'bar',
		data : value1,
		label : {
			normal : {
				show : true,
				formatter : '{c}',
				position : 'top'
			}
		},
		itemStyle : {
			normal : {
				color : function(params) {
					if (params.dataIndex < 10) {
						return 'rgb(191,23,34)';
					} else {
						return 'rgb(11,137,62)';
					}

				}
			}
		}
	}
};

myChart.setOption(option);