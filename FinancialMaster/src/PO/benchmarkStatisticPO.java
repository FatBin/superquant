package PO;

public class benchmarkStatisticPO {	
	private String date;//日期
	private double close;//收盤時大盤指數
	private double open;//開盤時大盤指數
	public String getDate() {
		return date;
	}
	private void setDate(String date) {
		this.date = date;
	}
	public double getClose() {
		return close;
	}
	private void setClose(double close) {
		this.close = close;
	}
	public double getOpen() {
		return open;
	}
	private void setOpen(double open) {
		this.open = open;
	}
	public benchmarkStatisticPO(String date, double close, double open) {
		setDate(date);
		setClose(close);
		setOpen(open);
	}
	
}
