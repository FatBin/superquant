/**
 * 
 */
var barChart_score = echarts
		.init(document.getElementById('barChart_score'));
var values = [];
var auxiliarys = [];


$.ajax({
	type : "get",
	async : false, //同步执行
	url : '../GetStockDashboard',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				values.push(result[i].value);
				auxiliarys.push(result[i].auxiliary);
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
        text: '分项得分',
        left:'center'
    },
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params) {
            var tar = params[0];
            return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type : 'category',
        splitLine: {show:false},
        data : ['技术分析','大盘分析','行业分析','基本分析','资金分析'],
		axisLabel : {
			interval : 0,
//			rotate : 45,
		}
    },
    yAxis: {
        type : 'value'
    },
    series: [
             {
            name: '得分',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
            itemStyle: {
                normal: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: '#FFA500'
                },
            },
             
            data:values
        },
        {
            name: '辅助',
            type: 'bar',
            stack:  '总量',
            itemStyle: {
                normal: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: '#FFEC8B'
                },
                emphasis: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                }
            },
            data: auxiliarys
        },
   
    ]
};

barChart_score.setOption(option);