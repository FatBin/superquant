/**
 * 
 */

function pieChart(name, value1, value2, flag, fontSize,id) {
	var myChart = echarts.init(document.getElementById(id));
	var values = [];
	values.push(value1);
	values.push(value2);

	function text(f) {
		if (f == 1) {
			return '亿元'
		} else {
			return '万手'
		}
	}
	
	option = {
		tooltip : {
			trigger : 'item',
			formatter : '{b}'
		},
		series : [ {
			name : name,
			type : 'pie',
			radius : [ '50%', '70%' ],
			avoidLabelOverlap : false,
			label : {
				normal : {
					show : true,
					position : 'center',
					formatter : '{b}',
					textStyle : {
						fontSize : fontSize,
						fontWeight : 'bold'
					}

				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : [ {
				value : values[0],
				name : name + '\n' + values[0] + text(flag)
				

			}, {
				value : values[1]
			} ]
		} ]
	};

	myChart.setOption(option);
}