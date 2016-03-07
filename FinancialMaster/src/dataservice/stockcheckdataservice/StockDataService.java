package dataservice.stockcheckdataservice;

import java.util.ArrayList;

import PO.codeNamePO;
import PO.stockStatisticPO;

public interface StockDataService {
	//返回所有股票代号
	public codeNamePO getCodeName(int year,String exchange);
	
	//根据股票代号codeName，起始时间start，终止时间end返回股票交易数据
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName,String start,String end);
}
