package presentation.stockcheckui;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart {
	JFreeChart chart;

	// data[时间][数据]
	public LineChart(String[][] data, String serie) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
		 
		for (int i = 0; i < data.length; i++) {
			
			Date date = null;
			try {
				date = df.parse(data[i][0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			linedataset.addValue(Double.parseDouble(data[i][1]), serie, df.format(date));
		}

		chart = ChartFactory.createLineChart("", "", "", linedataset, PlotOrientation.VERTICAL, false,
				true, false);
		
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundAlpha(0.3f);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);
		
		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setMaximumCategoryLabelLines(10);
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); 
		
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setAutoRangeIncludesZero(false);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 2.0);
		
		LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		renderer.setBaseShapesVisible(true);
		plot.setRenderer(0,renderer);
	}

	public ChartPanel getChartPane() {
		return new ChartPanel(chart);
	}
}
