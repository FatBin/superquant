package presentation.stockcheckui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		DefaultCategoryDataset bardataset = new DefaultCategoryDataset();

		for (int i = 0; i < data.length; i++) {

//			Date date = null;
//			try {
//				date = df.parse(data[i][0]);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}

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
		
		BarRenderer renderer = new BarRenderer();
//		renderer.setSeriesPaint(0, Color.gray);
		renderer.setShadowVisible(false);
		renderer.setBarPainter(new StandardBarPainter());
		plot.setRenderer(renderer);
	}

	public ChartPanel getChartPane() {
		return new ChartPanel(chart);
	}
}
