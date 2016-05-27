package PO;

public class RiseStockPO {
	private String stockId;//股票代号
	private String stockName;//股票名
	private double now;//最新价
	private double high;//最高价
	private double low;//最低价
	private int rise_days;//连涨天数
	private String rise_fall;//连续涨跌幅,因为含百分号，所以是string
	private String total_turnover;//累计换手率，因为含百分号，所以是string
	private String industry;//所属行业
	
	public RiseStockPO(String stockId, String stockName, double now, double high, double low, int rise_days,
			String rise_fall, String total_turnover, String industry) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.now = now;
		this.high = high;
		this.low = low;
		this.rise_days = rise_days;
		this.rise_fall = rise_fall;
		this.total_turnover = total_turnover;
		this.industry = industry;
	}
	
	
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public double getNow() {
		return now;
	}
	public void setNow(double now) {
		this.now = now;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public int getRise_days() {
		return rise_days;
	}
	public void setRise_days(int rise_days) {
		this.rise_days = rise_days;
	}
	public String getRise_fall() {
		return rise_fall;
	}
	public void setRise_fall(String rise_fall) {
		this.rise_fall = rise_fall;
	}
	public String getTotal_turnover() {
		return total_turnover;
	}
	public void setTotal_turnover(String total_turnover) {
		this.total_turnover = total_turnover;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	
	
}
