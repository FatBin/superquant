/**
 * 
 */

var Dashboard = echarts.init(document.getElementById('dashboard'));

option = {
    //  backgroundColor: '#161627',
    title: {
        text: '股票评分',
        left: 'center',
        y:'80%',
        textStyle: {
            fontSize:30,
            color: '#161627'
        }
    },
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    series: [
        {
            name: '综合指标',

            type: 'gauge',
            detail: {
                formatter:'{value}分',
                textStyle: {
                    //字体颜色
                    //  color: 'rgb(235, 138, 49)',
                    //字体大小
                     fontSize:50
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