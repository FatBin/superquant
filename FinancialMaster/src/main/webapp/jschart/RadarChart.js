/**
 * 
 */

var RadarChart = echarts.init(document.getElementById('radarChart'));
var values=[];

$.ajax({
	type : "post",
	async : false, //同步执行
	url : '../GetStockRadarChart',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				values.push(result[i].value);
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，大爷，图表请求数据失败啦!");
		myChart.hideLoading();
	}
})


option = {
    title: {
        text: '基本项统计雷达图'
    },
    tooltip: {},
    legend: {
        data: ['基本项']
    },
    radar: {
        // shape: 'circle',
        indicator: [
           { name: '量比', max: 3},
           { name: '风险性', max: 1},
           { name: '换手率', max: 10},
           { name: '涨跌幅', max: 5},
           { name: '修正市盈率', max: 10},
           { name: '市净率', max: 10}
        ],
          name: {
            textStyle: {
                fontSize:15,
                color: 'rgb(0, 0, 0)'
            }
        },
    },
    series: [{
        // name: '预算分配（Allocated Budget）',
        type: 'radar',
        
             itemStyle: {
                normal: {
                    color: 'rgb(0, 0, 102)'
                }
            },
            areaStyle: {
                normal: {
                    opacity: 0.5
                }
            },
          lineStyle :{
           normal: {
        width: 1,
        opacity: 1
    }
},
        data : [
            {
                value : values,
                 name : '基本项'
            }
        ]
    }]
};