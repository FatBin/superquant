package presentation.stockcheckui;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {
	JFreeChart chart;

	// data[时间][数据]
	public BarChart(String[][] data, String serie) {

		DefaultCategoryDataset bardataset = new DefaultCategoryDataset();

		for (int i = 0; i < data.length; i++) {
			bardataset.addValue(Double.parseDouble(data[i][1]), serie, data[i][0]);
		}

		chart = ChartFactory.createBarChart("", "", "", bardataset, PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundAlpha(0.3f);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setAutoRangeIncludesZero(false);
		rangeAxis.setUpperMargin(0.30);
		plot.setRangeAxis(rangeAxis);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); 
		
		BarRenderer renderer = new BarRenderer();
		renderer.setShadowVisible(false);
		renderer.setBarPainter(new StandardBarPainter());
		plot.setRenderer(renderer);
	}

	public ChartPanel getChartPane() {
		return new ChartPanel(chart);
	}
}
