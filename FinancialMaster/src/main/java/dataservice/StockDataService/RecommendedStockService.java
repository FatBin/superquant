package dataservice.StockDataService;

import java.util.ArrayList;

import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;
import web.bl.StrategyHandle.Price;

public interface RecommendedStockService {
	public ArrayList<PeakPO> getPeakPOs();
	
	public ArrayList<ContinuingTrendPO> getContinuingTrendPOs();
	
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityPOs();
	
	public ArrayList<breakthroughPO> getBreakthroughPOs();
	
	public ArrayList<PricePO> getPricePOs();
}
