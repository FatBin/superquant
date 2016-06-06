package data.Initialize;

import DAO.connection.DBconnection;
import data.BenchData.BenchRecordUpdate;
import data.Database.Update;
import data.IndustryData.IndustriesUpdate;
import data.StockData.RiseStockUpdate;
import data.StockData.UpToDateStocksUpdate;

public class Init {
	public Init(){
		DBconnection dBconnection=new DBconnection();
		Update update=new Update();
		BenchRecordUpdate benchRecordUpdate=new BenchRecordUpdate();
		IndustriesUpdate industriesUpdate=new IndustriesUpdate();
		RiseStockUpdate riseStockUpdate=new RiseStockUpdate();
		UpToDateStocksUpdate upToDateStocksUpdate=new UpToDateStocksUpdate();
	}
}
