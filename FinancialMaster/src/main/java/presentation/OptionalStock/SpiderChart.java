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

		String series[] = { "修正涨跌幅", "修正市净率", "修正市盈率", "修正换手率", "修正成交价稳定性", "修正成交量稳定性" };

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String name : nameList) {
			int isEmpty = 0;// 用来判断是否没有收藏列表

			if (!name.equals("")) {
				data = stockContrastBL.getData(name);
				isEmpty = data.length;
			}
			for (int i = 0; i < series.length; i++) {
				if (isEmpty > 0) {
					dataset.addValue(data[i], name, series[i]);
				} else {
					dataset.addValue(10, name, series[i]);
				}
			}
		}

		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
		spiderwebplot.setStartAngle(0D); // 第一条坐标轴的角度
		spiderwebplot.setInteriorGap(0.2D); // 图显示的大小
		spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
		spiderwebplot.setBackgroundPaint(null);

		// spiderwebplot.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		spiderwebplot.setTicks(4);

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
