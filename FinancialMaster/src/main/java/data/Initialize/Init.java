package data.Initialize;

import DAO.connection.DBconnection;
import data.BenchData.BenchRecordUpdate;
import data.BenchData.BenchdataAccurateUpdate;
import data.Database.Update;
import data.IndustryData.IndustriesUpdate;
import data.StockData.RiseStockUpdate;
import data.StockData.TradeRecordAccurateUpdate;
import data.StockData.UpToDateStocksUpdate;
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

public class Init {
	public Init(){
		DBconnection dBconnection=new DBconnection();
		Update update=new Update();
		BenchRecordUpdate benchRecordUpdate=new BenchRecordUpdate();
		IndustriesUpdate industriesUpdate=new IndustriesUpdate();
		RiseStockUpdate riseStockUpdate=new RiseStockUpdate();
		UpToDateStocksUpdate upToDateStocksUpdate=new UpToDateStocksUpdate();
		
		//recommendedStock
		breakthroughDown breakthroughDown=new breakthroughDown();
		breakthroughUp breakthroughUp=new breakthroughUp();
		ContinuingQuantityDown continuingQuantityDown=new ContinuingQuantityDown();
		ContinuingQuantityUp continuingQuantityUp=new ContinuingQuantityUp();
		ContinuingTrendDown continuingTrendDown=new ContinuingTrendDown();
		ContinuingTrendUp continuingTrendUp=new ContinuingTrendUp();
		PeakDown peakDown=new PeakDown();
		PeakUp peakUp=new PeakUp();
		PriceDown priceDown=new PriceDown();
		PriceUp priceUp=new PriceUp();
		
		BenchdataAccurateUpdate benchdataAccurateUpdate=new BenchdataAccurateUpdate();
		TradeRecordAccurateUpdate tradeRecordAccurateUpdate=new TradeRecordAccurateUpdate();
	}
}
