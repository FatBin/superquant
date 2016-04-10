package presentation.stockcheckui;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart {
	JFreeChart chart;

	// data[ʱ��][����]
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

		chart = ChartFactory.createLineChart(serie + "��һ���²������", "", "", linedataset, PlotOrientation.VERTICAL, false,
				true, false);
		
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundAlpha(0.3f);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);
		
		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setMaximumCategoryLabelLines(10);
//		xAxis.setCategoryMargin(0.5);
		
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setAutoRangeIncludesZero(false);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 2.0);
	}

	public ChartPanel getChartPane() {
		return new ChartPanel(chart);
	}
}