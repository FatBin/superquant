/**
 * 
 */
var myChart = echarts.init(document.getElementById('pieChart_StockMessage'));
var turnOver = [ 22, 78 ];

option = {
	tooltip : {
		trigger : 'item',
		formatter : "{d}%"
	},
	series : [ {
		name : '换手率',
		type : 'pie',
		radius : [ '50%', '70%' ],
		avoidLabelOverlap : false,
		label : {
			normal : {
				show : true,
				position : 'center',
				formatter : '{b}',
				textStyle : {
					fontSize : '50',
					fontWeight : 'bold'
				}

			}
		},
		labelLine : {
			normal : {
				show : false
			}
		},
		data :  [ {
			value : turnOver[0],
			name : '换手率\n' + turnOver[0] + '%'
		}, {
			value : turnOver[1]
		} ] 
	} ]
};

myChart.setOption(option);