var myChart = echarts.init(document.getElementById('business_barchart'));

var name=[];
var value=[];

$.ajax({
	type : "post",
	async : false, //同步执行
	url : 'ToBusinessPageServlet',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				name.push('"'+result[i].name+'"');
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
	 title: {
	        text: '涨跌幅前十'
	    },
	tooltip : {
		trigger : 'axis'
	},

	legend : {
		data : [ '涨跌率' ]
	},
	xAxis : {
	type : 'category',
		data :name,
		axisLine: {show: false},
	    splitLine: {show: false},
	    lable:{
	    	
	    }
	} ,
	yAxis :  {
		type : 'value',
		name : '涨跌率',
//		min : 0,
//		max : 250,
//		interval : 50,
	},
	series : 
			{
				name : '涨跌率',
				type : 'bar',
				data : value,
				 label: {
		                normal: {
		                    show: true,
		                    formatter: '{c}%'
		                }
		            },
			}
};

myChart.setOption(option);