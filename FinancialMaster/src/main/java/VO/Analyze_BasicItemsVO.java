package VO;

public class Analyze_BasicItemsVO {

	private double quantity_relative_ratio;  //量比
	private double priceStability;   //股价稳定性
	private double turnOver;   //换手率
	private double ups_and_downs;  //涨跌幅
	private double pe;  //市盈率
	private double pb;  //市净率
	
	public Analyze_BasicItemsVO() {
		super();
	}

	public double getQuantity_relative_ratio() {
		return quantity_relative_ratio;
	}

	public void setQuantity_relative_ratio(double quantity_relative_ratio) {
		this.quantity_relative_ratio = quantity_relative_ratio;
	}

	public double getPriceStability() {
		return priceStability;
	}

	public void setPriceStability(double priceStability) {
		this.priceStability = priceStability;
	}

	public double getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(double turnOver) {
		this.turnOver = turnOver;
	}

	public double getUps_and_downs() {
		return ups_and_downs;
	}

	public void setUps_and_downs(double ups_and_downs) {
		this.ups_and_downs = ups_and_downs;
	}

	public double getPe() {
		return pe;
	}

	public void setPe(double pe) {
		this.pe = pe;
	}

	public double getPb() {
		return pb;
	}

	public void setPb(double pb) {
		this.pb = pb;
	}
	
	 
	
	
}
