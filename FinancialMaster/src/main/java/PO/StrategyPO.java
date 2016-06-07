package PO;

public class StrategyPO {
	private String stockId;// 股票代号
	private String starttime;// 开始时间
	private String endtime;// 结束时间
	private double price;// 价格
	private double volume;// 成交量
	private double turnover;// 换手率
	private double pe;// 市盈率
	private double pb;// 市净率

	public StrategyPO(String stockId, String starttime, String endtime, double price, double volume, double turnover,
			double pe, double pb) {
		super();
		this.stockId = stockId;
		this.starttime = starttime;
		this.endtime = endtime;
		this.price = price;
		this.volume = volume;
		this.turnover = turnover;
		this.pe = pe;
		this.pb = pb;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
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
