package PO;

public class stockStatisticPO {
	private String date;//日期
	private double high;//最高指数
	private double open;//开盘指数
	private double close;//收盘指数
	public stockStatisticPO(String date, double high, double open, double close) {
		setDate(date);
		setHigh(high);
		setOpen(open);
		setClose(close);
	}
	public String getDate() {
		return date;
	}
	private void setDate(String date) {
		this.date = date;
	}
	public double getHigh() {
		return high;
	}
	private void setHigh(double high2) {
		this.high = high2;
	}
	public double getOpen() {
		return open;
	}
	private void setOpen(double open2) {
		this.open = open2;
	}
	public double getClose() {
		return close;
	}
	private void setClose(double close) {
		this.close = close;
	}
}
