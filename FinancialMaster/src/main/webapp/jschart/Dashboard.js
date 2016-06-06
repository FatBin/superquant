/**
 *   股票综合分析评分所用的仪表盘
 */

var Dashboard = echarts.init(document.getElementById('dashboard'));

option = {
    //  backgroundColor: '#161627',
    title: {
        text: '综合评分',
        left: 'center',
        y:'80%',
        textStyle: {
            fontSize:20,
            color: '#161627'
        }
    },
    grid: {
    	top: '0%',
        left: '0%',
        right: '0%',
        bottom: '0%',
    },
    tooltip : {
        formatter: "{a} <br/>{b} : {c}分"
    },
    series: [
        {
            name: '综合评分',

            type: 'gauge',
            detail: {
                formatter:'{value}分',
                textStyle: {
                    //字体颜色
                    //  color: 'rgb(235, 138, 49)',
                    //字体大小
                     fontSize:22,
                     fontWeight : 'bold'
              }
          
            },
            data: [{value: 85, 
            //   itemStyle: {
            //     normal: {
            //         //指针颜色
            //         // color: 'rgb(10, 10, 135)'
            //     }
            // }
                
            }]
        }
    ]
};

Dashboard.setOption(option);