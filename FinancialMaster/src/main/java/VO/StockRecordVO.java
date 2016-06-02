package VO;

public class StockRecordVO {
	
	private String date;
	private double open;
	private double close;
	private double high;
	private double low;
	private double adjPrice;
	private long volume;
	private double turnover;
	private double pe;
	private double pb;
	public StockRecordVO(String date, double open, double close, double high, double low, double adjPrice, long volume,
			double turnover, double pe, double pb) {
		super();
		this.date = date;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.adjPrice = adjPrice;
		this.volume = volume;
		this.turnover = turnover;
		this.pe = pe;
		this.pb = pb;
	}
	public String getDate() {
		return date;
	}
	public double getOpen() {
		return open;
	}
	public double getClose() {
		return close;
	}
	public double getHigh() {
		return high;
	}
	public double getLow() {
		return low;
	}
	public double getAdjPrice() {
		return adjPrice;
	}
	public long getVolume() {
		return volume;
	}
	public double getTurnover() {
		return turnover;
	}
	public double getPe() {
		return pe;
	}
	public double getPb() {
		return pb;
	}
	
	
	
}
