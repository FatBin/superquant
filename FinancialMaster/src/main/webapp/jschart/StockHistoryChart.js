/**
 *  个股历史数据图表
 */

var myChart = echarts.init(document.getElementById('stock_history_chart'));

var dates=[];
var rise_falls=[];//涨跌率
var turnovers=[];//换手率
var pes=[];//市盈率
var pbs=[];//市净率


$.ajax({
	type : "get",
	async : false, //同步执行
	url : '../GetStockKLine',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				dates.push(result[i].date);
				rise_falls.push(result[i].rise_fall);
				turnovers.push(result[i].turnover);
				pes.push(result[i].pe);	
				pbs.push(result[i].pb);	
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
	        text: '近一年波动情况'
	    },
	tooltip : {
		trigger : 'axis'
	},
	 toolbox: {
	        show: true,
	        feature: {
	            dataView: {readOnly: false},
	            magicType: {type: ['line', 'bar']},
	        }
	    },
	legend : {
		data : [ '涨跌率','换手率','市盈率','市净率'],
	    selectedMode: 'single'
	},
	xAxis : {
	    type : 'category',
		data :dates,
	} ,
	yAxis :  {
		type : 'value',

        scale : true,

	},
    dataZoom: [
               {
                   type: 'inside',
                   start: 75,
                   end: 100
               },
               {
                   show: true,
                   type: 'slider',
                   y: '90%',
                   start: 75,
                   end: 100
               }
           ],
	series : [
	          {
	              name:'涨跌率',
	              type:'line',
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
	              name:'换手率',
	              type:'line',
	              data:turnovers
	          },
	          {
	              name:'市盈率',
	              type:'line',
	              data:pes
	          },
	          {
	              name:'市净率',
	              type:'line',
	              data:pbs,
	   
	          }
	      ]
};

myChart.setOption(option);