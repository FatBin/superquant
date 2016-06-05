package data.Initialize;

import DAO.connection.DBconnection;
import data.BenchData.BenchRecordUpdate;
import data.Database.IndustriesUpdate;
import data.Database.Update;
import data.StockData.RiseStockUpdate;
import data.StockData.UpToDateStocksUpdate;

public class Init {
	public Init(){
		DBconnection dBconnection=new DBconnection();
		BenchRecordUpdate benchRecordUpdate=new BenchRecordUpdate();
		Update update=new Update();
		IndustriesUpdate industriesUpdate=new IndustriesUpdate();
		RiseStockUpdate riseStockUpdate=new RiseStockUpdate();
		UpToDateStocksUpdate upToDateStocksUpdate=new UpToDateStocksUpdate();
	}
}
