/**
 * 行业对比图
 */
var businessContrastChart= echarts
		.init(document.getElementById('businessContrastChart'));

option = {
    title: {
        text: '行业涨跌率对比图',
        x: '5%',
        y:  '0',
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['所属行业','该股'],
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
        data: ['2016-06-01','2016-06-02',
        '2016-06-03','2016-06-04','2016-06-05',
        '2016-06-06','2016-06-07']
    },
    yAxis: {
        type: 'value'
    },
        dataZoom: [
        {
            type: 'inside',
            start: 0,
            end: 100
        },
        {
            show: true,
            type: 'slider',
            y: '90%',
            start: 0,
            end: 100
        }
    ],
    series: [
        {
            name:'所属行业',
            type:'line',
            data:[1.20, 1.32, 1.01, 1.34, 0.90, 2.30, 2.10]
        },
        {
            name:'该股',
            type:'line',
            data:[2.20, 1.82, 1.91, 2.34, 2.90, 3.30, 3.10]
        }
    ]
};

businessContrastChart.setOption(option);