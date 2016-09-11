package dataservice.StockDataService;

import java.util.ArrayList;

import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;
import web.bl.StrategyHandle.Price;

public interface RecommendedStockService {
	public ArrayList<PeakPO> getPeakUp();
	
	public ArrayList<PeakPO> getPeakDown();
	
	public ArrayList<ContinuingTrendPO> getContinuingTrendUp();
	
	public ArrayList<ContinuingTrendPO> getContinuingTrendDown();
	
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityUp();
	
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityDown();
	
	public ArrayList<breakthroughPO> getBreakthroughUp();
	
	public ArrayList<breakthroughPO> getBreakthroughDown();
	
	public ArrayList<PricePO> getPriceUp();
	
	public ArrayList<PricePO> getPriceDown();
}
