/**
 *   ddx,ddy,ddz 柱状图
 */

var barChart_dd= echarts
		.init(document.getElementById('barChart_dd'));
var dds = [];

$.ajax({
	type : "post",
	async : false, //同步执行
	url : '../GetStcokDD_barChart',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				dds.push(result[i].dd);
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，大爷，图表请求数据失败啦!");
		myChart.hideLoading();
	}
})

option = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
    	top: '2%',
        left: '0%',
        right: '23%',
        bottom: '0%',
        containLabel: true
    },
    xAxis: {
        type : 'value',
        position: 'top',
        axisLine: {show: false},
        axisLabel: {show: false},
        axisTick: {show: false},
        splitLine: {lineStyle:{type:'dashed'}},
    },
    yAxis: {
        type : 'category',
        axisTick: {show: false},
        splitLine: {show: false},
        data : ['DDZ','DDY','DDX']
    },
    series : [
        {
            name:'数值',
            type:'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    formatter: '{c}',
                    position: 'right',
                }
            },
            data:dds
        }
    ]
};
barChart_dd.setOption(option);