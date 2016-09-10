package VO.TechnicalChart;

public class ROC_VO {

	 double roc12[];
	 double rocMA[];
	 double rocEMA[];	 
	 
	public ROC_VO() {
		super();
	}
	public double[] getRoc12() {
		return roc12;
	}
	public void setRoc12(double[] roc12) {
		this.roc12 = roc12;
	}
	public double[] getRocMA() {
		return rocMA;
	}
	public void setRocMA(double[] rocMA) {
		this.rocMA = rocMA;
	}
	public double[] getRocEMA() {
		return rocEMA;
	}
	public void setRocEMA(double[] rocEMA) {
		this.rocEMA = rocEMA;
	}
	 
	 
	
}
