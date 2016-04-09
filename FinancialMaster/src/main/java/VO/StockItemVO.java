package VO;

public class StockItemVO {
	private String stockname;//股票名称
	private String code;//股票代码
	private String item;//想查的项的值
	public StockItemVO(String stockname, String code, String item) {
		super();
		this.stockname = stockname;
		this.code = code;
		this.item = item;
	}
	public String getStockname() {
		return stockname;
	}
	public String getCode() {
		return code;
	}
	public String getItem() {
		return item;
	}
	
}
