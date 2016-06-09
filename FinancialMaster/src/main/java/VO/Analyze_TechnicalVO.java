package VO;

public class Analyze_TechnicalVO {

	
	private double[] close;
	private double[] volume;
	private double[] price_MA;
	private double[] volume_MA;
	private double[] RSI;
	public Analyze_TechnicalVO() {
		super();
	}
	public double[] getClose() {
		return close;
	}
	public void setClose(double[] close) {
		this.close = close;
	}
	public double[] getPrice_MA() {
		return price_MA;
	}
	public void setPrice_MA(double[] price_MA) {
		this.price_MA = price_MA;
	}
	public double[] getVolume_MA() {
		return volume_MA;
	}
	public void setVolume_MA(double[] volume_MA) {
		this.volume_MA = volume_MA;
	}
	public double[] getRSI() {
		return RSI;
	}
	public void setRSI(double[] rSI) {
		RSI = rSI;
	}
	public double[] getVolume() {
		return volume;
	}
	public void setVolume(double[] volume) {
		this.volume = volume;
	}
	
	
}
