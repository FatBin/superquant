/**
 *   大盘和个股分时图
 */


function getTimeSharingDiagram(kind){
var TimeSharingDiagram = echarts
		.init(document.getElementById('timeSharingDiagram'));

var servlet_url;
var timeInterval;

if (kind == "market") {
	servlet_url = "../GetBenchTimeSharingDiagram";
	timeInterval=10000;
} else if (kind == "stock") {
	servlet_url = "../GetStockTimeSharingDiagram";
	timeInterval=300000;
} else {
	alert("不好意思，分时图类型未匹配到！！！");
}


var dates=[];
var datas=[];

$.ajax({
	type : "get",
	async : false, //同步执行
	url : servlet_url,
	dataType : "json", //返回数据形式为json
	success : function(result) {
		if (result) {
			for (var i = 0; i < result.length; i++) {
				dates.push(result[i].date);
				datas.push(result[i].data);
			}
		}
	},
	error : function(errorMsg) {
		alert("不好意思，时分图数据加载失败啦!");
		myChart.hideLoading();
	}
})


option = {
//    title: {
//        text: '大盘分时图'
//    },
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
        data:dates,
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        scale:true,
        splitLine: {
            show: false
        }
    },
    dataZoom: [
                 {
                     type: 'slider',    //支持鼠标滚轮缩放
                     start: 0,            //默认数据初始缩放范围为0%到100%
                     end: 100
                 },
                 {
                     type: 'inside',    //支持单独的滑动条缩放
                     start: 0,            //默认数据初始缩放范围为0%到100%
                     end: 100
                 }
            ],
    series: [{
        name: '成交价',
        type: 'line',
        // showSymbol: false,
        hoverAnimation: false,
        data: datas
    }]
};

app.timeTicket = setInterval(function () {

    
    //  data.shift();
    //  date.shift();
     date.push('14:00');
     data.push(5);
    

    myChart.setOption({
        xAxis: {
            data: dates    //填入X轴数据
        },
        series: [{
            data: datas
        }]
    });
}, timeInterval);

TimeSharingDiagram.setOption(option);
}