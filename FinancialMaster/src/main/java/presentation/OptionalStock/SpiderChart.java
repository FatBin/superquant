package presentation.OptionalStock;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import businesslogicservice.stockContrastblservice.StockContrastBLService;
import presentation.repaintComponent.MySpiderWebPlot;

public class SpiderChart {
	private double data[];
	private JFreeChart chart;

	public SpiderChart(ArrayList<String> nameList, StockContrastBLService stockContrastBL) {

		String series[] = { "涨跌幅(+0.05*200)", "市净率(*3)", "市盈率(*0.15)", "换手率(+1*2)", "成交价稳定性(*200)", "成交量稳定性(*30)" };
		double polishPlus[] = { 0.05, 0, 0, 1, 0, 0 };
		double polishMul[] = { 200, 3, 0.15, 2, 200, 30 };

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String name : nameList) {
			data = stockContrastBL.getData(name);
			for (int i = 0; i < series.length; i++) {

				double dataPolish = 0;
				dataPolish = (data[i] + polishPlus[i]) * polishMul[i];

				dataset.addValue(dataPolish, name, series[i]);
			}
		}

		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
		spiderwebplot.setStartAngle(0D); // 第一条坐标轴的角度
		spiderwebplot.setInteriorGap(0.2D); // 图显示的大小
		spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
		spiderwebplot.setBackgroundPaint(null);

		spiderwebplot.setTicks(3);

		chart = new JFreeChart("", TextTitle.DEFAULT_FONT, spiderwebplot, false);
		LegendTitle legendtitle = new LegendTitle(spiderwebplot);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		chart.addSubtitle(legendtitle);
		chart.setAntiAlias(true);
		chart.setBorderPaint(null);
	}

	public ChartPanel getChart() {
		ChartPanel chartPanel = new ChartPanel(chart);

		// 通过鼠标滚轮放大缩小放大，整体都放大缩小
		chartPanel.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (-3 == e.getUnitsToScroll()) {
					chart.setPadding(
							new RectangleInsets(chart.getPadding().getTop() - 10, chart.getPadding().getBottom() - 10,
									chart.getPadding().getLeft() - 10, chart.getPadding().getRight() - 10));
				} else if (3 == e.getUnitsToScroll()) {
					chart.setPadding(
							new RectangleInsets(chart.getPadding().getTop() + 10, chart.getPadding().getBottom() + 10,
									chart.getPadding().getLeft() + 10, chart.getPadding().getRight() + 10));
				}
			}
		});

		chartPanel.setOpaque(false);
		return chartPanel;
	}
}
