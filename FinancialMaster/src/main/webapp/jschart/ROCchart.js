/**
 * 
 */

/**
 * 个股技术分析————ROC
 */
function initROC(){

var ROCchart = echarts.init(document.getElementById('ROCchart'));


	// 日期
	var dates = [];
	var ROC12s = [];
	var ROCMAs = [];
	var ROCEMAs=[];
	
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../GetROCChart",
		// data : {id:code},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					ROC12s.push(result[i].ROC12);
					ROCMAs.push(result[i].ROCMA);
					ROCEMAs.push(result[i].ROCEMA);;
				}
			}

		},
		error : function(errorMsg) {
			alert("不好意思，ROC图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
//		title : {
//			text : 'ROC分析图表',
//			left : 40
//		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend :  {
			data : [ 'ROC12', 'ROCMA','ROCEMA'],
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
					name : 'ROC12',
					type : 'line',
					data : ROC12s,
				},{
					name : 'ROCMA',
					type : 'line',
					data : ROCMAs,
				},{
					name : 'ROCEMA',
					type : 'line',
					data : ROCEMAs,
				}]
	};

	ROCchart.setOption(option);
}