/**
 * 大盘k线图和个股详情k线图
 */


var myChart = echarts.init(document.getElementById('klinechart'));

// kind表示类型（market表示大盘，stock表示个股）
function getKLine(kind) {
	// 日期
	var date = [];
	// values数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)
	var values = []
	var volume = [];
	var servlet_url = "";
	var num;
	var start;
	if (kind == "market") {
		servlet_url = "../GetMarketkLine";
		start = 96;
	} else if (kind == "stock") {
		servlet_url = "../GetStockKLine";
		start = 85;
	} else {
		alert("对不起皇上，类型未匹配到！！！");
	}
	$.ajax({
		type : "post",
		async : false, // 同步执行
		url : servlet_url,
		// data : {id:code},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					date.push(result[i].date);
					values.push(result[i].value);
					volume.push(result[i].volume);
				}
			}

		},
		error : function(errorMsg) {
			alert("不好意思，大爷，图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

	// 计算均线
	function calculateMA(dayCount) {
		var result = [];
		for (var i = 0, len = values.length; i < len; i++) {
			if (i < dayCount) {
				result.push('-');
				continue;
			}
			var sum = 0;
			for (var j = 0; j < dayCount; j++) {
				sum += values[i - j][1];
			}
			result.push(sum / dayCount);
		}
		return result;
	}
	// 计算成交量均线
	function calculateVolume(dayCount) {
		var result = [];
		for (var i = 0, len = volume.length; i < len; i++) {
			if (i < dayCount) {
				result.push('-');
				continue;
			}
			var sum = 0;
			for (var j = 0; j < dayCount; j++) {
				sum += volume[i - j];
			}
			result.push(sum / dayCount);
		}
		return result;
	}
	// 计算相对强弱指标
	function calculateRSI(dayCount) {
		var result = [];
		for (var i = 0, len = values.length; i < len; i++) {
			if (i <= dayCount) {
				result.push('-');
				continue;
			}
			var risesum = 0;
			var declinesum = 0;
			for (var j = 0; j < dayCount; j++) {
				var difference = values[i - j][1] - values[i - j - 1][1];
				if (difference > 0) {
					risesum += difference;
				} else {
					declinesum -= difference;
				}
			}
			result.push(100 * risesum / (risesum + declinesum));
		}
		return result;
	}
	option = {
		title : {
			text : '',
			left : 40
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend : [ {
			data : [ '日K', 'MA5', 'MA10', 'MA20', 'MA30' ]
		}, {
			gridIndex : 1,
			orient : 'vertical',
			x : 'right',
			y : '50%',
			data : [ '成交量', 'VOL5', 'VOL10' ]
		}, {
			gridIndex : 2,
			orient : 'vertical',
			x : 'right',
			y : '72%',
			data : [ 'RSI1', 'RSI2', 'RSI3' ]
		} ],
		grid : [ {
			left : '10%',
			right : '10%',
			height : '36%'
		}, {
			left : '10%',
			right : '10%',
			top : '50%',
			height : '16%'
		}, {
			left : '10%',
			right : '10%',
			top : '72%',
			height : '16%'
		} ],
		xAxis : [ {
			type : 'category',
			data : date,
			scale : true,
			boundaryGap : false,
			axisLine : {
				onZero : false
			},
			splitLine : {
				show : false
			},
			splitNumber : 20,
			min : 'dataMin',
			max : 'dataMax'
		}, {
			gridIndex : 1,
			type : 'category',
			data : date
		}, {
			gridIndex : 2,
			type : 'category',
			data : date
		} ],
		yAxis : [ {
			name : '单位：元',
			scale : true,
			splitArea : {
				show : true
			}
		}, {
			name : '单位：百万',
			gridIndex : 1,
		}, {
			gridIndex : 2,
		} ],
		dataZoom : [ {
			type : 'inside',
			start : start,
			end : 100,
			xAxisIndex : [ 0, 1, 2 ]
		}, {
			show : true,
			type : 'slider',
			y : '92%',
			start : start,
			end : 100,
			xAxisIndex : [ 0, 1, 2 ]
		} ],
		series : [
				{
					name : '日K',
					type : 'candlestick',
					data : values,
					markPoint : {
						label : {
							normal : {
								formatter : function(param) {
									return param != null ? Math
											.round(param.value) : '';
								}
							}
						},
						data : [ {
							name : 'highest value',
							type : 'max',
							valueDim : 'highest'
						}, {
							name : 'lowest value',
							type : 'min',
							valueDim : 'lowest'
						}, {
							name : 'average value on close',
							type : 'average',
							valueDim : 'close'
						} ],
						tooltip : {
							formatter : function(param) {
								return param.name + '<br>'
										+ (param.data.coord || '');
							}
						}
					},
					markLine : {
						symbol : [ 'none', 'none' ],
						data : [ [ {
							name : 'from lowest to highest',
							type : 'min',
							valueDim : 'lowest',
							symbol : 'circle',
							symbolSize : 10,
							label : {
								normal : {
									show : false
								},
								emphasis : {
									show : false
								}
							}
						}, {
							type : 'max',
							valueDim : 'highest',
							symbol : 'circle',
							symbolSize : 10,
							label : {
								normal : {
									show : false
								},
								emphasis : {
									show : false
								}
							}
						} ], {
							name : 'min line on close',
							type : 'min',
							itemStyle : {
								normal : {
									color : 'rgb(41,60,85)'
								}
							},
							valueDim : 'close'
						}, {
							name : 'max line on close',
							type : 'max',
							valueDim : 'close'
						} ]
					}
				}, {
					name : 'MA5',
					type : 'line',
					data : calculateMA(5),
					smooth : true,
					lineStyle : {
						normal : {
							opacity : 0.5
						}
					}
				}, {
					name : 'MA10',
					type : 'line',
					data : calculateMA(10),
					smooth : true,
					lineStyle : {
						normal : {
							opacity : 0.5
						}
					}
				}, {
					name : 'MA20',
					type : 'line',
					data : calculateMA(20),
					smooth : true,
					lineStyle : {
						normal : {
							opacity : 0.5
						}
					}
				}, {
					name : 'MA30',
					type : 'line',
					data : calculateMA(30),
					smooth : true,
					lineStyle : {
						normal : {
							opacity : 0.5
						}
					}
				}, {
					name : '成交量',
					type : 'bar',
					xAxisIndex : 1,
					yAxisIndex : 1,
					data : volume,
					itemStyle : {
						normal : {
							color : 'rgb(41,60,85)'
						}
					}
				}, {
					name : 'VOL5',
					type : 'line',
					xAxisIndex : 1,
					yAxisIndex : 1,
					data : calculateVolume(5),
					smooth : true,
					lineStyle : {
						normal : {
						// opacity : 0.5
						}
					}
				}, {
					name : 'VOL10',
					type : 'line',
					xAxisIndex : 1,
					yAxisIndex : 1,
					data : calculateVolume(10),
					smooth : true,
					lineStyle : {
						normal : {
						// opacity : 0.5
						}
					}
				}, {
					name : 'RSI1',
					type : 'line',
					xAxisIndex : 2,
					yAxisIndex : 2,
					data : calculateRSI(6),
					smooth : true,
					lineStyle : {
						normal : {
						//				opacity : 0.5
						}
					}
				}, {
					name : 'RSI2',
					type : 'line',
					xAxisIndex : 2,
					yAxisIndex : 2,
					data : calculateRSI(12),
					smooth : true,
					lineStyle : {
						normal : {
						//				opacity : 0.5
						}
					}
				}, {
					name : 'RSI3',
					type : 'line',
					xAxisIndex : 2,
					yAxisIndex : 2,
					data : calculateRSI(24),
					smooth : true,
					lineStyle : {
						normal : {
						//				opacity : 10,
						//				color: 'rgb(41,60,85)'
						}
					}
				} ]
	};

	myChart.setOption(option);
}