/**
 *  基本分析中各种基本项的雷达图
 */



var RadarChart = echarts.init(document.getElementById('radarChart'));
var values=[];
var maxs=[];
$.ajax({
	type : "post",
	async : false, //同步执行
	url : '../GetStockRadarChart',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				maxs.push(result[i].max);
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
        text: '基本项统计'
    },
    tooltip: {},
//    legend: {
//        data: ['基本项'],
//        left:'right'
//    },
    radar: {
        // shape: 'circle',
        indicator: [
           { name: '量比', max: maxs[0]},
           { name: '稳定性', max: maxs[1]},
           { name: '换手率', max: maxs[2]},
           { name: '涨跌幅', max: maxs[3]},
           { name: '市盈率', max: maxs[4]},
           { name: '市净率', max: maxs[5]}
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
RadarChart.setOption(option);