/**
 *   大盘和个股分时图
 */

var TimeSharingDiagram = echarts
		.init(document.getElementById('timeSharingDiagram'));
var kinds = [];
var gaps = [];

$.ajax({
	type : "get",
	async : false, //同步执行
	url : '../GetStockInflowPieChart',
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				kinds.push(result[i].name);
				gaps.push(result[i].gap);
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，资金流向差柱状图数据加载失败啦!");
		myChart.hideLoading();
	}
})

date=['8:00','9:00','10:00','11:00','12:00','13:00']
data=[1,2,3,4,5,6]

option = {
    title: {
        text: '大盘分时图'
    },
    tooltip: {
        trigger: 'axis',
        // formatter: function (params) {
        //     params = params[0];
        //     return  params.value[1];
        // },
        axisPointer: {
            animation: false
        }
    },
    xAxis: {
        type: 'category',
        data:date,
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%'],
        splitLine: {
            show: false
        }
    },
    dataZoom: [
                 {
                     type: 'slider',    //支持鼠标滚轮缩放
                     start: 0,            //默认数据初始缩放范围为10%到90%
                     end: 100
                 },
                 {
                     type: 'inside',    //支持单独的滑动条缩放
                     start: 0,            //默认数据初始缩放范围为10%到90%
                     end: 100
                 }
            ],
    series: [{
        name: '成交价',
        type: 'line',
        // showSymbol: false,
        hoverAnimation: false,
        data: data
    }]
};

app.timeTicket = setInterval(function () {

    
    //  data.shift();
    //  date.shift();
     date.push('14:00');
     data.push(5);
    

    myChart.setOption({
        xAxis: {
            data: date    //填入X轴数据
        },
        series: [{
            data: data
        }]
    });
}, 2000);

TimeSharingDiagram.setOption(option);
