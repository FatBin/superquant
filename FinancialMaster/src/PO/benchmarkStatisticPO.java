package PO;

public class benchmarkStatisticPO {	
	private String date;//日期
	private double close;//收盤時大盤指數
	private double open;//開盤時大盤指數
	private double low;//最低价
	private double high;//最高价
	private double adj_price;//后复价权
	private double volume;//成交量
	public benchmarkStatisticPO(String date, double close, double open, double low, double high, double adj_price,
			double volume) {
		super();
		this.date = date;
		this.close = close;
		this.open = open;
		this.low = low;
		this.high = high;
		this.adj_price = adj_price;
		this.volume = volume;
	}
	public String getDate() {
		return date;
	}
	public double getClose() {
		return close;
	}
	public double getOpen() {
		return open;
	}
	public double getLow() {
		return low;
	}
	public double getHigh() {
		return high;
	}
	public double getAdj_price() {
		return adj_price;
	}
	public double getVolume() {
		return volume;
	}
	
}
