package presentation.stockmarketui;

import java.util.Date;
import java.awt.Color;
import java.awt.Paint;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

import presentation.repaintComponent.MyCandlestickRender;

@SuppressWarnings("serial")
public class KLineChart extends JPanel {

	JFreeChart chart;
	Date startDate;
	Date endDate;
		
	public KLineChart(String[][] data, int index) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		double highValue = Double.MIN_VALUE; // 设置K线数据当中的最大值
		double minValue = Double.MAX_VALUE; // 设置K线数据当中的最小值
		double high2Value = Double.MIN_VALUE; // 设置成交量的最大值
		double min2Value = Double.MAX_VALUE; // 设置成交量的最低值

		

		try {
			startDate = df.parse(data[0][0]);
			endDate = df.parse(data[data.length - 1][0]);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		OHLCSeries series = new OHLCSeries(""); // 高开低收数据序列，股票K线图的四个数据，依次是开，高，低，收
		TimeSeries series2 = new TimeSeries(""); // 对应时间成交量数据

		for (int i = 0; i < data.length; i++) {

			Date date = new Date();
			try {
				date = df.parse(data[i][0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			series.add(new Day(date), Double.parseDouble(data[i][1]), Double.parseDouble(data[i][2]),
					Double.parseDouble(data[i][3]), Double.parseDouble(data[i][4]));

			series2.add(new Day(date), Double.parseDouble(data[i][5]));
		}

		// 保留K线数据的数据集
		final OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();
		seriesCollection.addSeries(series);

		// 保留成交量数据的集合
		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection(); // 保留成交量数据的集合
		timeSeriesCollection.addSeries(series2);

		// 获取K线数据的最高值和最低值
		int seriesCount = seriesCollection.getSeriesCount(); // 一共有多少个序列，目前为一个
		for (int i = 0; i < seriesCount; i++) {
			int itemCount = seriesCollection.getItemCount(i); // 每一个序列有多少个数据项
			for (int j = 0; j < itemCount; j++) {
				// 取第i个序列中的第j个数据项的最大值
				if (highValue < seriesCollection.getHighValue(i, j)) {
					highValue = seriesCollection.getHighValue(i, j);
				}
				// 取第i个序列中的第j个数据项的最小值
				if (minValue > seriesCollection.getLowValue(i, j)) {
					minValue = seriesCollection.getLowValue(i, j);
				}
			}
		}

		// 获取最高值和最低值
		int seriesCount2 = timeSeriesCollection.getSeriesCount(); // 一共有多少个序列，目前为一个
		for (int i = 0; i < seriesCount2; i++) {
			int itemCount = timeSeriesCollection.getItemCount(i); // 每一个序列有多少个数据项
			for (int j = 0; j < itemCount; j++) {
				// 取第i个序列中的第j个数据项的值
				if (high2Value < timeSeriesCollection.getYValue(i, j)) {
					high2Value = timeSeriesCollection.getYValue(i, j);
				}
				// 取第i个序列中的第j个数据项的值
				if (min2Value > timeSeriesCollection.getYValue(i, j)) {
					min2Value = timeSeriesCollection.getYValue(i, j);
				}
			}
		}

		final MyCandlestickRender candlestickRender = new MyCandlestickRender();// 设置K线图的画图器，必须申明为final，后面要在匿名内部类里面用到
		candlestickRender.setUseOutlinePaint(true); // 置是否使用自定义的边框线，程序自带的边框线的颜色不符合中国股票市场的习惯
		
		candlestickRender.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);// 设置如何对K线图的宽度进行设定
		candlestickRender.setAutoWidthGap(0.001);// 设置各个K线图之间的间隔
		candlestickRender.setUpPaint(new Color(206, 4, 14));// 设置股票上涨的K线图颜色
		candlestickRender.setDownPaint(new Color(25, 155, 83));// 设置股票下跌的K线图颜色
		DateAxis x1Axis = new DateAxis();// 设置x轴，也就是时间轴
		
		x1Axis.setRange(startDate, endDate);// 设置时间范围，注意时间的最大值要比已有的时间最大值要多一天
		x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期(很多人都不知道有此方法)，使图形看上去连续
		x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// 设置标记的位置
		x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
		x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
		NumberAxis y1Axis = new NumberAxis();// 设定y轴，就是数字轴
		y1Axis.setRange(minValue * 0.98, highValue * 1.02);// 设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
		XYPlot plot1 = new XYPlot(seriesCollection, x1Axis, y1Axis, candlestickRender);// 设置画图区域对象

		XYBarRenderer xyBarRender = new XYBarRenderer() {
			public Paint getItemPaint(int i, int j) {// 匿名内部类用来处理当日的成交量柱形图的颜色与K线图的颜色保持一致
				if (seriesCollection.getCloseValue(i, j) > seriesCollection.getOpenValue(i, j)) {// 收盘价高于开盘价，股票上涨，选用股票上涨的颜色
					return candlestickRender.getUpPaint();
				} else {
					return candlestickRender.getDownPaint();
				}
			}
		};
		
		double[] margin = {0, 0.3, 0.05, 0.05};
		xyBarRender.setMargin(margin[index]);// 设置柱形图之间的间隔
		xyBarRender.setShadowVisible(false);
		xyBarRender.setBarPainter(new StandardXYBarPainter()); 
		NumberAxis y2Axis = new NumberAxis();// 设置Y轴，为数值,后面的设置，参考上面的y轴设置
		y2Axis.setRange(min2Value * 0.9, high2Value * 1.1);
		XYPlot plot2 = new XYPlot(timeSeriesCollection, null, y2Axis, xyBarRender);// 建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴

		CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);// 建立一个恰当的联合图形区域对象，以x轴为共享轴
		combineddomainxyplot.add(plot1, 2);// 添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
		combineddomainxyplot.add(plot2, 1);// 添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
		combineddomainxyplot.setGap(10);// 设置两个图形区域对象之间的间隔空间

		// 背景透明
		plot1.setBackgroundPaint(new Color(255, 255, 255));
		plot2.setBackgroundPaint(new Color(255, 255, 255));
		
//		String title[] = {"时分图","日K线图","周K线图","月K线图"};
		chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
		chart.setAntiAlias(true);
		chart.setBackgroundPaint(new Color(246, 246, 246));
		this.add(new ChartPanel(chart));
	}
	
	public ChartPanel getChartPane(){
		return new ChartPanel(chart);
	}
}
