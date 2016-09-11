package data.StockData;

import java.util.ArrayList;

import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;
import data.StockData.RecommendedData.ContinuingQuantityDown;
import data.StockData.RecommendedData.ContinuingQuantityUp;
import data.StockData.RecommendedData.ContinuingTrendDown;
import data.StockData.RecommendedData.ContinuingTrendUp;
import data.StockData.RecommendedData.PeakDown;
import data.StockData.RecommendedData.PeakUp;
import data.StockData.RecommendedData.PriceDown;
import data.StockData.RecommendedData.PriceUp;
import data.StockData.RecommendedData.breakthroughDown;
import data.StockData.RecommendedData.breakthroughUp;
import dataservice.StockDataService.RecommendedStockService;

public class RecommendedStock implements RecommendedStockService{

	@Override
	public ArrayList<PeakPO> getPeakUp() {
		// TODO Auto-generated method stub
		return PeakUp.arrayList;
	}

	@Override
	public ArrayList<PeakPO> getPeakDown() {
		// TODO Auto-generated method stub
		return PeakDown.arrayList;
	}

	@Override
	public ArrayList<ContinuingTrendPO> getContinuingTrendUp() {
		// TODO Auto-generated method stub
		return ContinuingTrendUp.arrayList;
	}

	@Override
	public ArrayList<ContinuingTrendPO> getContinuingTrendDown() {
		// TODO Auto-generated method stub
		return ContinuingTrendDown.arrayList;
	}

	@Override
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityUp() {
		// TODO Auto-generated method stub
		return ContinuingQuantityUp.arrayList;
	}

	@Override
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityDown() {
		// TODO Auto-generated method stub
		return ContinuingQuantityDown.arrayList;
	}

	@Override
	public ArrayList<breakthroughPO> getBreakthroughUp() {
		// TODO Auto-generated method stub
		return breakthroughUp.arrayList;
	}

	@Override
	public ArrayList<breakthroughPO> getBreakthroughDown() {
		// TODO Auto-generated method stub
		return breakthroughDown.arrayList;
	}

	@Override
	public ArrayList<PricePO> getPriceUp() {
		// TODO Auto-generated method stub
		return PriceUp.arrayList;
	}

	@Override
	public ArrayList<PricePO> getPriceDown() {
		// TODO Auto-generated method stub
		return PriceDown.arrayList;
	}

}
