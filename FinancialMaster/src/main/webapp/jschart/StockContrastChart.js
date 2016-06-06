/**
 * 同行业股票对比
 */
var stockContrastChart= echarts
		.init(document.getElementById('stockContrastChart'));
var up_and_downs=[3.2,2.2,2.0,0.9];

option = {
    title : {
        text: '同行业涨跌率排名',
    },
    tooltip : {
        trigger: 'axis'
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['A','B','C','D']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    grid: {
    	top:  '35%',
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    series : [
        {
            name:'涨跌率',
            type:'bar',
            data:up_and_downs,
            label : {
			normal : {
				show : true,
				formatter : '{c}%',
				position:'top'
			}
		},
            itemStyle : {
			normal : {
				color : function(params) {
					if (params.dataIndex < 3) {
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
stockContrastChart.setOption(option);