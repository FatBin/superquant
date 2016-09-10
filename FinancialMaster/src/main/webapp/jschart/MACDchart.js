/**
 * 
 */

/**
 * 个股技术分析————MACD
 */


function initMACD(){
	var MACDchart = echarts.init(document.getElementById('MACDchart'));

	// 日期
	var dates = [];
	var DIFs = [];
	var DEAs = [];
	var DIFFs=[]
	
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../GetMACDChart",
		// data : {id:code},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					DIFs.push(result[i].DIF);
					DEAs.push(result[i].DEA);
					DIFFs.push(result[i].DIFF);
				}
			}

		},
		error : function(errorMsg) {
			alert("不好意思，MACD图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
//		title : {
//			text : 'MACD分析图表',
//			left : 40
//		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend :  {
			data : [ 'DIF', 'DEA','DIFF'],
			left:'right'
		},
		grid :  {
			left : '8%',
			right : '0%',
			top:  '15%',
			height : '63%'
		},
		xAxis : {
			type : 'category',
			data : dates,
			scale : true
		},
		yAxis :  {
//			name : '',
			scale : true,
			splitArea : {
				show : true
			}
		},
		dataZoom : [ {
			type : 'inside',
			start : 60,
			end : 100,
		}, {
			show : true,
			type : 'slider',
			y : '90%',
			start : 60,
			end : 100,
		} ],
		series :[{
					name : 'DIF',
					type : 'line',
					data : DIFs,
				}, {
					name : 'DEA',
					type : 'line',
					data : DEAs,
					smooth : true,
					lineStyle : {
						normal : {
						// opacity : 0.5
						}
					}
				},{
					name : 'DIFF',
					type : 'bar',
					data : DIFFs,
					itemStyle : {
						normal : {
							color : function(params) {
								if (DIFFs[params.dataIndex] >0) {
									return 'rgb(191,23,34)';
								} else {
									return 'rgb(11,137,62)';
								}

							}
						}
					}
				} ]
	};

MACDchart.setOption(option);

}

