package PO;

public class StrategyPO {
	private String stockId;// 股票代号
	private String starttime;// 开始时间
	private String endtime;// 结束时间
	private double priceLow;// 最低价格
	private double priceHigh;//最高价格
	private double volumeLow;// 最低成交量
	private double volumeHigh;//最高成交量
	private double turnoverLow;//最低换手率
 	private double turnoverHigh;// 最高换手率
 	private double peLow;//最低市盈率
	private double peHigh;// 最高市盈率
	private double pbLow;// 最低市净率
	private double pbHigh;//最高市净率
	private int frequency;//频率
	private double cost;//购买时打算花费的成本
	
	
	public StrategyPO(String stockId, String starttime, String endtime, double priceLow, double priceHigh,
			double volumeLow, double volumeHigh, double turnoverLow, double turnoverHigh, double peLow, double peHigh,
			double pbLow, double pbHigh, int frequency, double cost) {
		super();
		this.stockId = stockId;
		this.starttime = starttime;
		this.endtime = endtime;
		this.priceLow = priceLow;
		this.priceHigh = priceHigh;
		this.volumeLow = volumeLow;
		this.volumeHigh = volumeHigh;
		this.turnoverLow = turnoverLow;
		this.turnoverHigh = turnoverHigh;
		this.peLow = peLow;
		this.peHigh = peHigh;
		this.pbLow = pbLow;
		this.pbHigh = pbHigh;
		this.frequency = frequency;
		this.cost = cost;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public double getPriceLow() {
		return priceLow;
	}
	public void setPriceLow(double priceLow) {
		this.priceLow = priceLow;
	}
	public double getPriceHigh() {
		return priceHigh;
	}
	public void setPriceHigh(double priceHigh) {
		this.priceHigh = priceHigh;
	}
	public double getVolumeLow() {
		return volumeLow;
	}
	public void setVolumeLow(double volumeLow) {
		this.volumeLow = volumeLow;
	}
	public double getVolumeHigh() {
		return volumeHigh;
	}
	public void setVolumeHigh(double volumeHigh) {
		this.volumeHigh = volumeHigh;
	}
	public double getTurnoverLow() {
		return turnoverLow;
	}
	public void setTurnoverLow(double turnoverLow) {
		this.turnoverLow = turnoverLow;
	}
	public double getTurnoverHigh() {
		return turnoverHigh;
	}
	public void setTurnoverHigh(double turnoverHigh) {
		this.turnoverHigh = turnoverHigh;
	}
	public double getPeLow() {
		return peLow;
	}
	public void setPeLow(double peLow) {
		this.peLow = peLow;
	}
	public double getPeHigh() {
		return peHigh;
	}
	public void setPeHigh(double peHigh) {
		this.peHigh = peHigh;
	}
	public double getPbLow() {
		return pbLow;
	}
	public void setPbLow(double pbLow) {
		this.pbLow = pbLow;
	}
	public double getPbHigh() {
		return pbHigh;
	}
	public void setPbHigh(double pbHigh) {
		this.pbHigh = pbHigh;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
