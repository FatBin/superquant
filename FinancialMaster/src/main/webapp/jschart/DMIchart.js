/**
 * 
 */

/**
 * 个股技术分析————DMI
 */
function initDMI(){

var DMIchart = echarts.init(document.getElementById('DMIchart'));


	// 日期
	var dates = [];
	var PDIs = [];
	var MDIs = [];
	var ADXs=[]
	var ADXRs=[]
	
	$.ajax({
		type : "get",
		async : false, // 同步执行
		url : "../GetDMIChart",
		// data : {id:code},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					dates.push(result[i].date);
					PDIs.push(result[i].PDI);
					MDIs.push(result[i].MDI);
					ADXs.push(result[i].ADX);
					ADXRs.push(result[i].ADXR);
				}
			}

		},
		error : function(errorMsg) {
			alert("不好意思，DMI图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	option = {
//		title : {
//			text : 'DMI分析图表',
//			left : 40
//		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend :  {
			data : [ 'PDI', 'MDI','ADX','ADXR'],
			left:'right'
		},
		grid :  {
			left : '10%',
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
					name : 'PDI',
					type : 'line',
					data : PDIs,
				},{
					name : 'MDI',
					type : 'line',
					data : MDIs,
				},{
					name : 'ADX',
					type : 'line',
					data : ADXs,
				},{
					name : 'ADXR',
					type : 'line',
					data : ADXRs,
				} ]
	};

	DMIchart.setOption(option);
}