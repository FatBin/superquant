package VO;

public class StockVO {
	private String name;//股票名
	private String date;//日期
	private double open;//开盘价
	private double high;//最高价
	private double low;//最低价
	private double close;//收盘价
	private double adj_price;//后复权价
	private int volume;//成交量
	private double turnover;//换手率
	private double pe;//市盈率
	private double pb;//市净率
	private double ups_and_lows;//涨跌幅
	private StockMarketVO stockMarketVO;//最新大盘的数据
	private double amplitude;//振幅
	private double quantity_relative_ratio;//量比
	private String[][] KLine_data;//k线图数据
	private String[][] history_data;//历史数据
	private double ratio;
			
	public StockVO(String name, String date, double open, double high,
			double low, double close, double adj_price, int volume,
			double turnover, double pe, double pb, double ups_and_lows,
			StockMarketVO stockMarketVO, double amplitude,
			double quantity_relative_ratio,String[][] kLine_data,
			String[][] history_data) {
		super();
		this.name = name;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adj_price = adj_price;
		this.volume = volume;
		this.turnover = turnover;
		this.pe = pe;
		this.pb = pb;
		this.ups_and_lows = ups_and_lows;
		this.stockMarketVO = stockMarketVO;
		this.amplitude=amplitude;
		this.quantity_relative_ratio=quantity_relative_ratio;
		this.KLine_data = kLine_data;
		this.history_data = history_data;
		this.ratio=1;
	}
	public String getName() {
		return name;
	}
	public String getDate() {
		return date;
	}
	public double getOpen() {
		return open;
	}
	public double getHigh() {
		return high;
	}
	public double getLow() {
		return low;
	}
	public double getClose() {
		return close;
	}
	public double getAdj_price() {
		return adj_price;
	}
	public int getVolume() {
		return volume;
	}
	public double getTurnover() {
		return turnover;
	}
	public double getPe() {
		return pe;
	}
	public double getPb() {
		return pb;
	}
	public double getUps_and_lows() {
		return ups_and_lows;
	}
	public StockMarketVO getStockMarketVO() {
		return stockMarketVO;
	}
	public double getTamplitude() {
		return amplitude;
	}

	public double getQuantity_relative_ratio() {
		return quantity_relative_ratio;
	}
	
	
	public String[][] getKLine_data() {
		return KLine_data;
	}

	public String[][] getHistory_data() {
		return history_data;
	}
	
	public void setHistory_data(String[][] history_data) {
		this.history_data = history_data;
	}		
	public void setRatio(double ratio) {
		this.ratio = ratio;;
	}	
}
