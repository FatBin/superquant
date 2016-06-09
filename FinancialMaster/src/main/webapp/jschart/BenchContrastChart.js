/**
 * 大盘对比图
 */

var benchContrastChart= echarts
		.init(document.getElementById('benchContrastChart'));

var dates = [];
var rise_falls = [];
var bench_rise_falls = [];

$.ajax({
	type : "post",
	async : false, //同步执行
	url : '../GetBenchContrast',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				dates.push(result[i].date);
				rise_falls.push(result[i].rise_fall);
				bench_rise_falls.push(result[i].bench_rise_fall);
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
        text: '大盘涨跌率对比图',
        x: '5%'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['大盘','该股'],
        left:'right'
    },
    grid: {
    	top:  '15%',
        left: '3%',
        right: '4%',
        bottom: '12%',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: dates
    },
    yAxis: {
        type: 'value',
        scale : true,
    },
        dataZoom: [
        {
            type: 'inside',
            start: 90,
            end: 100
        },
        {
            show: true,
            type: 'slider',
            y: '90%',
            start: 90,
            end: 100
        }
    ],
    series: [
        {
            name:'大盘',
            type:'line',
            data:bench_rise_falls
        },
        {
            name:'该股',
            type:'line',
            data:rise_falls
        }
    ]
};

benchContrastChart.setOption(option);