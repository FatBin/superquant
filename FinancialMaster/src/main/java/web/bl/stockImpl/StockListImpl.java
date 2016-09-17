package web.bl.stockImpl;

import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;

import PO.RiseStockPO;
import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;
import VO.StockListVO;
import data.StockData.RecommendedStock;
import data.StockData.StockData;
import dataservice.StockDataService.RecommendedStockService;
import dataservice.StockDataService.StockDataService;
import servlet.factory.InitFactoryServlet;
import web.blservice.stockInfo.StockListInfo;

public class StockListImpl implements StockListInfo {

	@Override
	public StockListVO getStockList() {
		StockDataService stockDataService=new StockData();
		RecommendedStockService recommendedStockService=new RecommendedStock();
		ArrayList<RiseStockPO> stockPOs=new ArrayList<RiseStockPO>();
		ArrayList<PeakPO> peakUp=new ArrayList<PeakPO>();		
		ArrayList<PeakPO> peakDown=new ArrayList<PeakPO>();	
		ArrayList<ContinuingTrendPO> continuingTrendUp=new ArrayList<ContinuingTrendPO>();	
		ArrayList<ContinuingTrendPO> continuingTrendDown=new ArrayList<ContinuingTrendPO>();
		ArrayList<ContinuingQuantityPO> continuingQuantityUp=new ArrayList<ContinuingQuantityPO>();		
		ArrayList<ContinuingQuantityPO> continuingQuantityDown=new ArrayList<ContinuingQuantityPO>();	
		ArrayList<breakthroughPO> breakthroughUp=new ArrayList<breakthroughPO>();		
		ArrayList<breakthroughPO> breakthroughDown=new ArrayList<breakthroughPO>();		
		ArrayList<PricePO> priceUp=new ArrayList<PricePO>();	
		ArrayList<PricePO> priceDown=new ArrayList<PricePO>();
				
		StockListVO stockListVO=new StockListVO();		
		
		
		
		try {
			ArrayList<RiseStockPO> old_stockPOs=stockDataService.getRiseStock();
			for (RiseStockPO riseStockPO : old_stockPOs) {
				if(InitFactoryServlet.isExist(riseStockPO.getStockId())){
					stockPOs.add(riseStockPO);
				}										
			}
			ArrayList<PeakPO> old_peakUp=recommendedStockService.getPeakUp();	
			for (PeakPO peakPO : old_peakUp) {
				if(InitFactoryServlet.isExist(peakPO.getStockId())){
					peakUp.add(peakPO);
				}	
			}
			ArrayList<PeakPO> old_peakDown=recommendedStockService.getPeakDown();
			for (PeakPO peakPO : old_peakDown) {
				if(InitFactoryServlet.isExist(peakPO.getStockId())){
					peakDown.add(peakPO);
				}	
			}
			ArrayList<ContinuingTrendPO> old_continuingTrendUp=recommendedStockService.getContinuingTrendUp();
			for (ContinuingTrendPO continuingTrendPO : old_continuingTrendUp) {
				if(InitFactoryServlet.isExist(continuingTrendPO.getStockId())){
					continuingTrendUp.add(continuingTrendPO);
				}	
			}
			ArrayList<ContinuingTrendPO> old_continuingTrendDown=recommendedStockService.getContinuingTrendDown();
			for (ContinuingTrendPO continuingTrendPO : old_continuingTrendDown) {
				if(InitFactoryServlet.isExist(continuingTrendPO.getStockId())){
					continuingTrendDown.add(continuingTrendPO);
				}	
			}
			ArrayList<ContinuingQuantityPO> old_continuingQuantityUp=recommendedStockService.getContinuingQuantityUp();
			for (ContinuingQuantityPO continuingQuantityPO : old_continuingQuantityUp) {
				if(InitFactoryServlet.isExist(continuingQuantityPO.getStockId())){
					continuingQuantityUp.add(continuingQuantityPO);
				}	
			}
			ArrayList<ContinuingQuantityPO> old_continuingQuantityDown=recommendedStockService.getContinuingQuantityDown();
			for (ContinuingQuantityPO continuingQuantityPO : old_continuingQuantityDown) {
				if(InitFactoryServlet.isExist(continuingQuantityPO.getStockId())){
					continuingQuantityDown.add(continuingQuantityPO);
				}	
			}
			ArrayList<breakthroughPO> old_breakthroughUp=recommendedStockService.getBreakthroughUp();
			for (breakthroughPO breakthroughPO : old_breakthroughUp) {
				if(InitFactoryServlet.isExist(breakthroughPO.getStockId())){
					breakthroughUp.add(breakthroughPO);
				}	
			}
			ArrayList<breakthroughPO> old_breakthroughDown=recommendedStockService.getBreakthroughDown();
			for (breakthroughPO breakthroughPO : old_breakthroughDown) {
				if(InitFactoryServlet.isExist(breakthroughPO.getStockId())){
					breakthroughDown.add(breakthroughPO);
				}	
			}
			ArrayList<PricePO> old_priceUp=recommendedStockService.getPriceUp();
			for (PricePO pricePO : old_priceUp) {
				if(InitFactoryServlet.isExist(pricePO.getStockId())){
					priceUp.add(pricePO);
				}	
			}
			ArrayList<PricePO> old_priceDown=recommendedStockService.getPriceDown();
			for (PricePO pricePO : old_priceDown) {
				if(InitFactoryServlet.isExist(pricePO.getStockId())){
					priceDown.add(pricePO);
				}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		stockListVO.setStockList(stockPOs);
		stockListVO.setBreakthroughDown(breakthroughDown);
		stockListVO.setBreakthroughUp(breakthroughUp);
		stockListVO.setContinuingQuantityDown(continuingQuantityDown);
		stockListVO.setContinuingQuantityUp(continuingQuantityUp);
		stockListVO.setContinuingTrendDown(continuingTrendDown);
		stockListVO.setContinuingTrendUp(continuingTrendUp);
		stockListVO.setPeakDown(peakDown);
		stockListVO.setPeakUp(peakUp);
		stockListVO.setPriceDown(priceDown);
		stockListVO.setPriceUp(priceUp);
		return stockListVO;
	}
}
