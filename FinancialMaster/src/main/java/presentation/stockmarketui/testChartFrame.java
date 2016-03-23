package presentation.stockmarketui;


import javax.swing.JFrame;

public class testChartFrame {
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		WeekKline weekKline = new WeekKline();
		frame.add(weekKline);
		frame.setVisible(true);
	}

}
