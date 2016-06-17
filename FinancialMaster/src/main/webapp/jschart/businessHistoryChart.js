/**
 *  某行业历史数据展示的图表
 */

var myChart = echarts.init(document.getElementById('business_history_chart'));

var dates=[];
var average_prices=[];//平均价格
var rise_falls=[];//涨跌率
var volumes=[];//成交量
var turnovers=[];//换手率
var inflowses=[];//流入资金量


$.ajax({
	type : "post",
	async : false, //同步执行
	url : '../ToBusinessDetailPageServlet',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				average_prices.push(result[i].average_price);
				dates.push(result[i].date);
				rise_falls.push(result[i].rise_fall);
				volumes.push(result[i].volume);
				turnovers.push(result[i].turnover);
				inflowses.push(result[i].inflows);				
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，行业历史数据图表请求数据失败啦!");
		myChart.hideLoading();
	}
})

option = {
	 title: {
	        text: '该行业近一月波动情况'
	    },
	tooltip : {
		trigger : 'axis'
	},
	 toolbox: {
	        show: true,
	        feature: {
	            // dataZoom: {},
	            dataView: {readOnly: false},
	            magicType: {type: ['line', 'bar']},
	        }
	    },
	legend : {
		data : [ '平均价格','涨跌率','成交量','换手率','流入资金量'],
	    selectedMode: 'single'
	},
	xAxis : {
	    type : 'category',
		data :dates
	} ,
	yAxis :  {
		type : 'value',
		 scale : true,
//		name : '涨跌率',

	},
	series : [
	          {
	              name:'平均价格',
	              type:'line',
//	              label: {
//		                normal: {
//		                    show: true,
//		                    formatter: '{c}%'
//		                }
//		            },
	              data:average_prices
	          },
	          {
	              name:'涨跌率',
	              type:'bar',
//	              label: {
//		                normal: {
//		                    show: true,
//		                    formatter: '{c}%'
//		                }
//		            },
	              data:rise_falls,
	              itemStyle : {
		      			normal : {
		      				color : function(params) {
		      					if (rise_falls[params.dataIndex] >0) {
		      						return 'rgb(191,23,34)';
		    					} else {
		    						return 'rgb(25,36,83)';
		    					}

		      				}
		      			}
		      		}
	          },
	          {
	              name:'成交量',
	              type:'bar',
	              data:volumes
	          },
	          {
	              name:'换手率',
	              type:'line',
	              data:turnovers
	          },
	          
	          {
	              name:'流入资金量',
	              type:'bar',
	              data:inflowses,
	              itemStyle : {
	      			normal : {
	      				color : function(params) {
	      					if (inflowses[params.dataIndex] >0) {
	      						return 'rgb(191,23,34)';
	    					} else {
	    						return 'rgb(25,36,83)';
	    					}

	      				}
	      			}
	      		}
	          }
	      ]
};

myChart.setOption(option);