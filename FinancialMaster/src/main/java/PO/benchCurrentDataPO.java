package PO;

public class benchCurrentDataPO {
	private double now;//现在的价格
	private double rise_fall_price;//涨跌价
	private double rise_fall_percent;//涨跌率
	private double high;//最高价
	private double low;//最低价
	private double open;//开盘价
	private double yesterday_close;//昨天收盘价
	private String price;//成交额
	private String volume;//成交量
	private String rise_company;//价格增长的公司
	private String fall_company;//价格降低的公司
	private String company;//不增不降的公司
	
	
	
	public benchCurrentDataPO(double now, double rise_fall_price, double rise_fall_percent, double high, double low,
			double open, double yesterday_close, String price, String volume, String rise_company, String fall_company,
			String company) {
		super();
		this.now = now;
		this.rise_fall_price = rise_fall_price;
		this.rise_fall_percent = rise_fall_percent;
		this.high = high;
		this.low = low;
		this.open = open;
		this.yesterday_close = yesterday_close;
		this.price = price;
		this.volume = volume;
		this.rise_company = rise_company;
		this.fall_company = fall_company;
		this.company = company;
	}



	public double getNow() {
		return now;
	}



	public void setNow(double now) {
		this.now = now;
	}



	public double getRise_fall_price() {
		return rise_fall_price;
	}



	public void setRise_fall_price(double rise_fall_price) {
		this.rise_fall_price = rise_fall_price;
	}



	public double getRise_fall_percent() {
		return rise_fall_percent;
	}



	public void setRise_fall_percent(double rise_fall_percent) {
		this.rise_fall_percent = rise_fall_percent;
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



	public double getOpen() {
		return open;
	}



	public void setOpen(double open) {
		this.open = open;
	}



	public double getYesterday_close() {
		return yesterday_close;
	}



	public void setYesterday_close(double yesterday_close) {
		this.yesterday_close = yesterday_close;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getVolume() {
		return volume;
	}



	public void setVolume(String volume) {
		this.volume = volume;
	}



	public String getRise_company() {
		return rise_company;
	}



	public void setRise_company(String rise_company) {
		this.rise_company = rise_company;
	}



	public String getFall_company() {
		return fall_company;
	}



	public void setFall_company(String fall_company) {
		this.fall_company = fall_company;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}
	
	
}
