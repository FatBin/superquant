/**
 * 
 */

/**
 * 个股技术分析————CCI
 */


var CCIchart = echarts.init(document.getElementById('CCIchart'));


	// 日期
	var dates = [];
	var CCIs = [];
	
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../GetCCIChart",
		// data : {id:code},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					CCIs.push(result[i].CCI);
				}
			}

		},
		error : function(errorMsg) {
			alert("不好意思，CCI图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
		title : {
			text : 'CCI分析图表',
			left : 40
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend :  {
			data : [ 'CCI'],
			left:'right'
		},
		grid :  {
			left : '5%',
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
					name : 'CCI',
					type : 'line',
					data : CCIs,
				}]
	};

	CCIchart.setOption(option);
