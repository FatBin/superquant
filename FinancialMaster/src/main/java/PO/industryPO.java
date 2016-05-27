package PO;

public class industryPO {
	private String industry;//行业名
	private int company;//包含公司数
	private double average_price;//平均价格
	private double rise_fall;//涨跌率
	private double volume;//成交量
	private double turnover;//换手率
	private double inflows;//流入资金量
	private String leaderstock;//领涨股
	private double price;//最新价
	private double stock_rise_fall;//领涨股涨跌率
	
	public industryPO(String industry, int company, double average_price, double rise_fall, double volume,
			double turnover, double inflows, String leaderstock, double price, double stock_rise_fall) {
		super();
		this.industry = industry;
		this.company = company;
		this.average_price = average_price;
		this.rise_fall = rise_fall;
		this.volume = volume;
		this.turnover = turnover;
		this.inflows = inflows;
		this.leaderstock = leaderstock;
		this.price = price;
		this.stock_rise_fall = stock_rise_fall;
	}
	
	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}


	public int getCompany() {
		return company;
	}


	public void setCompany(int company) {
		this.company = company;
	}


	public double getAverage_price() {
		return average_price;
	}


	public void setAverage_price(double average_price) {
		this.average_price = average_price;
	}


	public double getRise_fall() {
		return rise_fall;
	}


	public void setRise_fall(double rise_fall) {
		this.rise_fall = rise_fall;
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


	public double getInflows() {
		return inflows;
	}


	public void setInflows(double inflows) {
		this.inflows = inflows;
	}


	public String getLeaderstock() {
		return leaderstock;
	}


	public void setLeaderstock(String leaderstock) {
		this.leaderstock = leaderstock;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getStock_rise_fall() {
		return stock_rise_fall;
	}


	public void setStock_rise_fall(double stock_rise_fall) {
		this.stock_rise_fall = stock_rise_fall;
	}
	
}
