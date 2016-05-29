/**
 * 
 */
var myChart = echarts.init(document.getElementById('business_barchart'));

var name = [];
var value = [];
$.ajax({
	type : "post",
	async : false, //同步执行
	url : 'ToBusinessPageServlet',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				name.push(result[i].name);
				value.push(result[i].value);
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
		text : '行业涨榜前十与跌榜前十',
		left : 'center'
	//	        subtext: 'From ExcelHome'
	},
	xAxis : {
		type : 'category',
		data : name,
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
		name : '涨跌率',
		type : 'bar',
		data : value,
		label : {
			normal : {
				show : true,
				formatter : '{c}%'
			}
		},
		itemStyle : {
			normal : {
				color : function(params) {
					if (params.dataIndex < 10) {
						return 'red';
					} else {
						return 'green';
					}

				}
			}
		}
	}
};

myChart.setOption(option);