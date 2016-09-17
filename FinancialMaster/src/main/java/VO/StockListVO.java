package VO;

import java.util.ArrayList;

import PO.RiseStockPO;
import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;

public class StockListVO {
	private ArrayList<RiseStockPO> stockList;
	
	private ArrayList<PeakPO> peakUp;
	
	private ArrayList<PeakPO> peakDown;
	
	private ArrayList<ContinuingTrendPO> continuingTrendUp;
	
	private ArrayList<ContinuingTrendPO> continuingTrendDown;
	
	private ArrayList<ContinuingQuantityPO> continuingQuantityUp;
	
	private ArrayList<ContinuingQuantityPO> continuingQuantityDown;
	
	private ArrayList<breakthroughPO> breakthroughUp;
	
	private ArrayList<breakthroughPO> breakthroughDown;
	
	private ArrayList<PricePO> priceUp;
	
	private ArrayList<PricePO> priceDown;

	public StockListVO() {
		super();
	}

	public ArrayList<RiseStockPO> getStockList() {
		return stockList;
	}

	public void setStockList(ArrayList<RiseStockPO> stockList) {
		this.stockList = stockList;
	}

	public ArrayList<PeakPO> getPeakUp() {
		return peakUp;
	}

	public void setPeakUp(ArrayList<PeakPO> peakUp) {
		this.peakUp = peakUp;
	}

	public ArrayList<PeakPO> getPeakDown() {
		return peakDown;
	}

	public void setPeakDown(ArrayList<PeakPO> peakDown) {
		this.peakDown = peakDown;
	}

	public ArrayList<ContinuingTrendPO> getContinuingTrendUp() {
		return continuingTrendUp;
	}

	public void setContinuingTrendUp(ArrayList<ContinuingTrendPO> continuingTrendUp) {
		this.continuingTrendUp = continuingTrendUp;
	}

	public ArrayList<ContinuingTrendPO> getContinuingTrendDown() {
		return continuingTrendDown;
	}

	public void setContinuingTrendDown(ArrayList<ContinuingTrendPO> continuingTrendDown) {
		this.continuingTrendDown = continuingTrendDown;
	}

	public ArrayList<ContinuingQuantityPO> getContinuingQuantityUp() {
		return continuingQuantityUp;
	}

	public void setContinuingQuantityUp(ArrayList<ContinuingQuantityPO> continuingQuantityUp) {
		this.continuingQuantityUp = continuingQuantityUp;
	}

	public ArrayList<ContinuingQuantityPO> getContinuingQuantityDown() {
		return continuingQuantityDown;
	}

	public void setContinuingQuantityDown(ArrayList<ContinuingQuantityPO> continuingQuantityDown) {
		this.continuingQuantityDown = continuingQuantityDown;
	}

	public ArrayList<breakthroughPO> getBreakthroughUp() {
		return breakthroughUp;
	}

	public void setBreakthroughUp(ArrayList<breakthroughPO> breakthroughUp) {
		this.breakthroughUp = breakthroughUp;
	}

	public ArrayList<breakthroughPO> getBreakthroughDown() {
		return breakthroughDown;
	}

	public void setBreakthroughDown(ArrayList<breakthroughPO> breakthroughDown) {
		this.breakthroughDown = breakthroughDown;
	}

	public ArrayList<PricePO> getPriceUp() {
		return priceUp;
	}

	public void setPriceUp(ArrayList<PricePO> priceUp) {
		this.priceUp = priceUp;
	}

	public ArrayList<PricePO> getPriceDown() {
		return priceDown;
	}

	public void setPriceDown(ArrayList<PricePO> priceDown) {
		this.priceDown = priceDown;
	}
	
    
	
	
}
