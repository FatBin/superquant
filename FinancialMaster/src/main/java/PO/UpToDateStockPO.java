package PO;

public class UpToDateStockPO {
	private String stockId;//股票代号
	private String stockName;//股票名
	private String industry;//所属行业
	private double now;//最新单价
	private String rise_fall;//涨幅
	private double ddx;
	private double ddy;
	private double ddz;
	private double positive;//主动率
	private double tongchilv;//通吃率
	private double extraLargePurchase;//特大买入
	private double extraLargeSell;//特大卖出
	private double largePurchase;//大单买入
	private double largeSell;//大单卖出
	private double mediumPurchase;//中单买入
	private double mediumSell;//中单卖出
	private double smallPurchase;//小单买入
	private double smallSell;//小单卖出
	private double turnover;//换手率
	private double quantity_relative_ratio;//量比
	
	public UpToDateStockPO(){
		
	}
	
	public UpToDateStockPO(String stockId, String stockName, String industry, double now, String rise_fall, double ddx,
			double ddy, double ddz, double positive, double tongchilv, double extraLargePurchase, double extraLargeSell,
			double largePurchase, double largeSell, double mediumPurchase, double mediumSell, double smallPurchase,
			double smallSell, double turnover, double quantity_relative_ratio) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.industry = industry;
		this.now = now;
		this.rise_fall = rise_fall;
		this.ddx = ddx;
		this.ddy = ddy;
		this.ddz = ddz;
		this.positive = positive;
		this.tongchilv = tongchilv;
		this.extraLargePurchase = extraLargePurchase;
		this.extraLargeSell = extraLargeSell;
		this.largePurchase = largePurchase;
		this.largeSell = largeSell;
		this.mediumPurchase = mediumPurchase;
		this.mediumSell = mediumSell;
		this.smallPurchase = smallPurchase;
		this.smallSell = smallSell;
		this.turnover = turnover;
		this.quantity_relative_ratio = quantity_relative_ratio;
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
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public double getNow() {
		return now;
	}
	public void setNow(double now) {
		this.now = now;
	}
	public String getRise_fall() {
		return rise_fall;
	}
	public void setRise_fall(String rise_fall) {
		this.rise_fall = rise_fall;
	}
	public double getDdx() {
		return ddx;
	}
	public void setDdx(double ddx) {
		this.ddx = ddx;
	}
	public double getDdy() {
		return ddy;
	}
	public void setDdy(double ddy) {
		this.ddy = ddy;
	}
	public double getDdz() {
		return ddz;
	}
	public void setDdz(double ddz) {
		this.ddz = ddz;
	}
	public double getPositive() {
		return positive;
	}
	public void setPositive(double positive) {
		this.positive = positive;
	}
	public double getTongchilv() {
		return tongchilv;
	}
	public void setTongchilv(double tongchilv) {
		this.tongchilv = tongchilv;
	}
	public double getExtraLargePurchase() {
		return extraLargePurchase;
	}
	public void setExtraLargePurchase(double extraLargePurchase) {
		this.extraLargePurchase = extraLargePurchase;
	}
	public double getExtraLargeSell() {
		return extraLargeSell;
	}
	public void setExtraLargeSell(double extraLargeSell) {
		this.extraLargeSell = extraLargeSell;
	}
	public double getLargePurchase() {
		return largePurchase;
	}
	public void setLargePurchase(double largePurchase) {
		this.largePurchase = largePurchase;
	}
	public double getLargeSell() {
		return largeSell;
	}
	public void setLargeSell(double largeSell) {
		this.largeSell = largeSell;
	}
	public double getMediumPurchase() {
		return mediumPurchase;
	}
	public void setMediumPurchase(double mediumPurchase) {
		this.mediumPurchase = mediumPurchase;
	}
	public double getMediumSell() {
		return mediumSell;
	}
	public void setMediumSell(double mediumSell) {
		this.mediumSell = mediumSell;
	}
	public double getSmallPurchase() {
		return smallPurchase;
	}
	public void setSmallPurchase(double smallPurchase) {
		this.smallPurchase = smallPurchase;
	}
	public double getSmallSell() {
		return smallSell;
	}
	public void setSmallSell(double smallSell) {
		this.smallSell = smallSell;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public double getQuantity_relative_ratio() {
		return quantity_relative_ratio;
	}
	public void setQuantity_relative_ratio(double quantity_relative_ratio) {
		this.quantity_relative_ratio = quantity_relative_ratio;
	}
	
	
}
