/**
 *   大盘和个股分时图
 */
var stockId = "null";

function setId(stockid) {
	stockId = stockid;
}

function getTimeSharingDiagram(kind) {
	var TimeSharingDiagram = echarts.init(document
			.getElementById('timeSharingDiagram'));

	var servlet_url;
	var addData_url;
	var timeInterval;

	if (kind == "market") {
		servlet_url = "../GetBenchTimeSharingDiagram";
		addData_url = "../UpdateBenchVO";
		timeInterval = 10000;
	} else if (kind == "stock") {
		servlet_url = "../GetStockTimeSharingDiagram";
		addData_url = "../UpdateStockDetailVO";
		timeInterval = 300000;
	} else {
		alert("不好意思，分时图类型未匹配到！！！");
	}

	var dates = [];
	var datas = [];

	$.ajax({
		type : "get",
		async : false, //同步执行
		url : servlet_url,
		dataType : "json", //返回数据形式为json
		data: {"stockId": stockId},
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					datas.push(result[i].data);
				}
			}
		},
		error : function(errorMsg) {
			alert("不好意思，分时图数据加载失败啦!");
			myChart.hideLoading();
		}
	})

	function addNewData(){
		
		$.ajax({
			type : "post",
			async : false, //同步执行
			url : addData_url,
			dataType : "json", //返回数据形式为json
			success : function(result) {
				if (result) {
					if(result[0].status!='已收盘'){
						dates.push(result[0].time);
						datas.push(result[0].now);
					}
				}
			},
			error : function(errorMsg) {
				alert("不好意思，分时图数据加载失败啦!");
				myChart.hideLoading();
			}
		})
		
		
	}
	
	option = {
		//    title: {
		//        text: '大盘分时图'
		//    },
		backgroundColor: "rgb(246,246,246)",
		tooltip : {
			trigger : 'axis',
			// formatter: function (params) {
			//     params = params[0];
			//     return  params.value[1];
			// },
			axisPointer : {
				animation : false
			}
		},
		xAxis : {
			type : 'category',
			data : dates,
			splitLine : {
				show : false
			}
		},
		yAxis : {
			type : 'value',
			scale : true,
			splitLine : {
				show : false
			}
		},
		dataZoom : [ {
			type : 'slider', //支持鼠标滚轮缩放
			start : 0, //默认数据初始缩放范围为0%到100%
			end : 100
		}, {
			type : 'inside', //支持单独的滑动条缩放
			start : 0, //默认数据初始缩放范围为0%到100%
			end : 100
		} ],
		series : [ {
			name : '成交价',
			type : 'line',
			// showSymbol: false,
			hoverAnimation : false,
			data : datas
		} ]
	};
	setInterval(function() {

		addNewData();
		TimeSharingDiagram.setOption({
			xAxis : {
				data : dates
			//填入X轴数据
			},
			series : [ {
				data : datas
			} ]
		});
	}, timeInterval);
	TimeSharingDiagram.setOption(option);
}