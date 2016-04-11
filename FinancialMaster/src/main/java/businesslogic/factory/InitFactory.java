package businesslogic.factory;

import businesslogic.stockContrastbl.StockContrastBL;
import businesslogic.stockcheckbl.StockItemBL;
import businesslogic.stockcheckbl.StockListBL;
import businesslogic.stockcheckbl.StockSearchBL;
import businesslogic.stockmarketbl.MarketKLineBL;
import businesslogic.stockmarketbl.StockMarketBL;

public class InitFactory {

	private StockItemBL stockItemBL;//行情对比--排行榜
	private StockListBL stockListBL;//股票列表
	private StockSearchBL stockSearchBL;//搜索框
	private StockContrastBL stockContrastBL;//行情对比--雷达图
	private MarketKLineBL marketKLineBL;//更新本地周k和月k数据；（update）
	private static InitFactory factory = null;
	private StockMarketBL stockMarketBL;//大盘数据
	
	private InitFactory() {
		stockItemBL =new StockItemBL();
		stockListBL =new StockListBL();
		stockSearchBL =new StockSearchBL();
		stockContrastBL =new StockContrastBL();
		marketKLineBL =new MarketKLineBL();
		marketKLineBL.update();
		stockMarketBL=new StockMarketBL();
	}
	
   
	public static InitFactory getFactory(){
		if (factory == null) {
			factory = new InitFactory();
		}
		return factory;
	}
	
	 //关注或取消关注时重新初始化
	public void update(){
		stockItemBL =new StockItemBL();
		stockListBL =new StockListBL();
		stockContrastBL =new StockContrastBL();
	}


	public StockItemBL getStockItemBL() {
		return stockItemBL;
	}


	public StockListBL getStockListBL() {
		return stockListBL;
	}


	public StockSearchBL getStockSearchBL() {
		return stockSearchBL;
	}


	public StockContrastBL getStockContrastBL() {
		return stockContrastBL;
	}


	public MarketKLineBL getMarketKLineBL() {
		return marketKLineBL;
	}


	public StockMarketBL getStockMarketBL() {
		return stockMarketBL;
	}
	
	
}
