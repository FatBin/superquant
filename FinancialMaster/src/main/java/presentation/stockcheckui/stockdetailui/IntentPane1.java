package presentation.stockcheckui.stockdetailui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import ENUM.attentionState;
import VO.StockVO;
import businesslogic.managestockbl.ManageStockBL;
import businesslogic.stockcheckbl.StockMessageBL;
import businesslogicservice.managestockblservice.ManageStockBLService;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import presentation.repaintComponent.IntentPane;
import presentation.repaintComponent.barPanel;
import presentation.stockmarketui.SearchBar;

@SuppressWarnings("serial")
public class IntentPane1 extends IntentPane {

	private ImageIcon imageVolume;
	private JButton likeButton;
	private ImageIcon image2;
	private ImageIcon image3;
	private Font labelFont = new Font("微软雅黑", Font.PLAIN, 18);
	private attentionState state;
	private ManageStockBLService manageStockBL = new ManageStockBL();
	StockMessageBLService Message = new StockMessageBL();
	private Color brown = new Color(54, 42, 29);
	private Color green = new Color(37, 120, 38);
	private Color red = new Color(179, 43, 56);
	private StockVO datavo;

	@SuppressWarnings("static-access")
	public IntentPane1(String id, SearchBar searchBar) {
		IntentPane intentPane1 = this;

		datavo = Message.getStockMessage(id);

		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);

		intentPane1.setPreferredSize(new Dimension(700, 300));
		intentPane1.setLayout(null);

		// 另外四个数据图标
		JLabel volumePanel = new JLabel();
		volumePanel.setBounds(430, 74, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/volume.png");
		Image tempCircle = imageVolume.getImage().getScaledInstance(volumePanel.getWidth(), volumePanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		imageVolume = new ImageIcon(tempCircle);
		volumePanel.setOpaque(false);
		volumePanel.setIcon(imageVolume);
		intentPane1.add(volumePanel);

		JLabel turnoverPanel = new JLabel();
		turnoverPanel.setBounds(518, 74, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/turnover.png");
		tempCircle = imageVolume.getImage().getScaledInstance(turnoverPanel.getWidth(), turnoverPanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imageTurnOver = new ImageIcon(tempCircle);
		turnoverPanel.setIcon(imageTurnOver);
		turnoverPanel.setOpaque(false);
		intentPane1.add(turnoverPanel);

		JLabel pePanel = new JLabel();
		pePanel.setBounds(430, 175, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/pe.png");
		tempCircle = imageVolume.getImage().getScaledInstance(pePanel.getWidth(), pePanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imagePe = new ImageIcon(tempCircle);
		pePanel.setIcon(imagePe);
		pePanel.setOpaque(false);
		intentPane1.add(pePanel);

		JLabel pbPanel = new JLabel();
		pbPanel.setBounds(518, 175, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/pb.png");
		tempCircle = imageVolume.getImage().getScaledInstance(pbPanel.getWidth(), pbPanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imagePb = new ImageIcon(tempCircle);
		pbPanel.setIcon(imagePb);
		pbPanel.setOpaque(false);
		intentPane1.add(pbPanel);

		JLabel amplitudePanel = new JLabel();
		amplitudePanel.setBounds(606, 74, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/amplitude.png");
		tempCircle = imageVolume.getImage().getScaledInstance(amplitudePanel.getWidth(), amplitudePanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imageamplitude = new ImageIcon(tempCircle);
		amplitudePanel.setIcon(imageamplitude);
		amplitudePanel.setOpaque(false);
		intentPane1.add(amplitudePanel);

		JLabel quantityPanel = new JLabel();
		quantityPanel.setBounds(606, 175, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/quantity.png");
		tempCircle = imageVolume.getImage().getScaledInstance(quantityPanel.getWidth(), quantityPanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imagequantity = new ImageIcon(tempCircle);
		quantityPanel.setIcon(imagequantity);
		quantityPanel.setOpaque(false);
		intentPane1.add(quantityPanel);

		// 当前的数据展示
		JLabel openLabel1 = new JLabel("开盘价");
		openLabel1.setBounds(44, 76, 70, 30);
		openLabel1.setFont(labelFont);
		openLabel1.setForeground(brown);
		intentPane1.add(openLabel1);
		JLabel openLabel2 = new JLabel("最低价");
		openLabel2.setBounds(44, 118, 70, 30);
		openLabel2.setFont(labelFont);
		openLabel2.setForeground(brown);
		intentPane1.add(openLabel2);
		JLabel openLabel3 = new JLabel("最高价");
		openLabel3.setBounds(44, 159, 70, 30);
		openLabel3.setFont(labelFont);
		openLabel3.setForeground(brown);
		intentPane1.add(openLabel3);
		JLabel openLabel4 = new JLabel("收盘价");
		openLabel4.setBounds(44, 200, 70, 30);
		openLabel4.setFont(labelFont);
		openLabel4.setForeground(brown);
		intentPane1.add(openLabel4);
		JLabel openLabel5 = new JLabel("后复权价");
		openLabel5.setBounds(44, 241, 90, 30);
		openLabel5.setFont(labelFont);
		openLabel5.setForeground(brown);
		intentPane1.add(openLabel5);

		double high = datavo.getHigh();
		double open;
		double low;
		double close;
		double adj_price;
		if (high == 0) {
			open = 0;
			low = 0;
			close = 0;
			adj_price = 0;
		} else {
			open = datavo.getOpen() / high;
			low = datavo.getLow() / high;
			close = datavo.getClose() / high;
			adj_price = datavo.getAdj_price() / high;
		}

		// 当前价格条
		barPanel bPanel1 = new barPanel(datavo.getOpen(), high);
		bPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel1.setBounds(150, 78, Math.max(30, (int) (210 * Math.pow(open, 12))), 26);
		JLabel label5 = new JLabel(datavo.getOpen() + "");
		label5.setBounds(15, 6, 20, 18);
		label5.setForeground(new Color(105, 76, 36));
		bPanel1.add(label5);
		intentPane1.add(bPanel1);

		barPanel bPanel2 = new barPanel(datavo.getLow(), high);
		bPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel2.setBounds(150, 120, Math.max(20, (int) (210 * Math.pow(low, 12))), 26);
		JLabel label1 = new JLabel(datavo.getLow() + "");
		label1.setBounds(15, 6, 20, 18);
		label1.setForeground(new Color(105, 76, 36));
		bPanel2.add(label1);
		intentPane1.add(bPanel2);

		barPanel bPanel3 = new barPanel(high, high);
		bPanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		if (high == 0) {
			bPanel3.setBounds(150, 161, 20, 26);
		} else {
			bPanel3.setBounds(150, 161, 210, 26);
		}
		JLabel label2 = new JLabel(datavo.getHigh() + "");
		label2.setBounds(15, 6, 20, 18);
		label2.setForeground(new Color(105, 76, 36));
		bPanel3.add(label2);
		intentPane1.add(bPanel3);

		barPanel bPanel4 = new barPanel(datavo.getClose(), high);
		bPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel4.setBounds(150, 202, Math.max(20, (int) (210 * Math.pow(close, 12))), 26);
		JLabel label3 = new JLabel(datavo.getClose() + "");
		label3.setBounds(15, 6, 20, 18);
		label3.setForeground(new Color(105, 76, 36));
		bPanel4.add(label3);
		intentPane1.add(bPanel4);

		barPanel bPanel5 = new barPanel(datavo.getAdj_price(), high);
		bPanel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel5.setBounds(150, 243, Math.max(20, (int) (210 * Math.pow(adj_price, 12))), 26);
		JLabel label4 = new JLabel(datavo.getAdj_price() + "");
		label4.setBounds(15, 6, 20, 18);
		label4.setForeground(new Color(105, 76, 36));
		bPanel5.add(label4);
		intentPane1.add(bPanel5);

		likeButton = new JButton();
		likeButton.setBounds(640, 11, 26, 23);
		likeButton.setContentAreaFilled(false);
		likeButton.setBorderPainted(false);
		// image2 未关注图标
		image2 = new ImageIcon("src/main/resources/image/heart.png");
		Image temp2 = image2.getImage().getScaledInstance(likeButton.getWidth(), likeButton.getHeight(),
				image2.getImage().SCALE_SMOOTH);
		image2 = new ImageIcon(temp2);
		// image3 已关注图标
		image3 = new ImageIcon("src/main/resources/image/heart-selected.png");
		Image temp3 = image3.getImage().getScaledInstance(likeButton.getWidth(), likeButton.getHeight(),
				image3.getImage().SCALE_SMOOTH);

		image3 = new ImageIcon(temp3);

		state = manageStockBL.isAttented(id);
		if (state == attentionState.No) {
			likeButton.setIcon(image2);
		} else {
			likeButton.setIcon(image3);
		}
		likeButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				if (state == attentionState.Yes) {
					likeButton.setIcon(image2);
					state = attentionState.No;
					manageStockBL.deleteStock(id);
				} else {
					likeButton.setIcon(image3);
					state = attentionState.Yes;
					manageStockBL.addStock(id);
				}
			}
		});
		intentPane1.add(likeButton);

		JLabel namelbl = new JLabel();
		namelbl.setText(datavo.getName() + "(" + id + ")");
		namelbl.setForeground(brown);
		namelbl.setBounds(10, 5, 250, 32);
		namelbl.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		intentPane1.add(namelbl);

		// 个股涨跌幅数据
		JLabel raiseRate = new JLabel();
		double upAndDown = datavo.getUps_and_lows();
		raiseRate.setText(nf.format(upAndDown));
		raiseRate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		raiseRate.setForeground(new Color(62, 56, 49, 240));
		if (upAndDown > 0) {
			raiseRate.setForeground(red);
		} else if (upAndDown < 0) {
			raiseRate.setForeground(green);
		}
		raiseRate.setBounds(250, 10, 250, 24);
		intentPane1.add(raiseRate);

		// 成交量
		JLabel volumeLabel = new JLabel();
		double volume = datavo.getVolume();
		volumeLabel.setText((volume / 10000 + "") + "W");
		volumeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		volumeLabel.setForeground(new Color(62, 56, 49, 240));
		volumeLabel.setBounds(432, 143, 80, 24);
		intentPane1.add(volumeLabel);
		// 换手率
		JLabel turnoverLabel = new JLabel();
		double turnover = datavo.getTurnover();
		turnoverLabel.setText((turnover + "") + "%");
		turnoverLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		turnoverLabel.setForeground(new Color(62, 56, 49, 240));
		turnoverLabel.setBounds(529, 143, 80, 24);
		intentPane1.add(turnoverLabel);
		// 市盈率
		JLabel peLabel = new JLabel();
		double pe = datavo.getPe();
		peLabel.setText((pe + "") + "%");
		peLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		peLabel.setForeground(new Color(62, 56, 49, 240));
		peLabel.setBounds(441, 247, 80, 24);
		intentPane1.add(peLabel);
		// 市净率
		JLabel pbLabel = new JLabel();
		double pb = datavo.getPb();
		pbLabel.setText((pb + "") + "%");
		pbLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		pbLabel.setForeground(new Color(62, 56, 49, 240));
		pbLabel.setBounds(529, 247, 80, 24);
		intentPane1.add(pbLabel);
		// 振幅
		JLabel amplitudeLabel = new JLabel();
		double amplitude = datavo.getTamplitude();
		amplitudeLabel.setText(nf.format(amplitude));
		amplitudeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		amplitudeLabel.setForeground(new Color(62, 56, 49, 240));
		amplitudeLabel.setBounds(616, 143, 80, 24);
		intentPane1.add(amplitudeLabel);
		// 量比
		JLabel quantityLabel = new JLabel();
		double quantity = datavo.getQuantity_relative_ratio();
		quantityLabel.setText(nf.format(quantity));
		quantityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		quantityLabel.setForeground(new Color(62, 56, 49, 240));
		quantityLabel.setBounds(616, 247, 80, 24);
		intentPane1.add(quantityLabel);
	}
}
