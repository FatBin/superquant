package dataservice.stockcheckdataservice;

import java.util.ArrayList;

import PO.codeNamePO;
import PO.stockStatisticPO;

public interface StockDataService {
	//返回所有股票代号
    //year：选中年份中的所有股票列表；
    //exchange：交易所，目前可以为 sz或sh，代表深交所和上交所
	public codeNamePO getCodeName(int year,String exchange);
	
	
	//根据股票代号codeName，起始时间start，终止时间end（时间格式'YYYY-mm-dd'）返回股票交易数据
	//交易数据包括日期、开盘价、最高价、最低价、收盘价、后复权价、成交量、换手率、市盈率、市净率
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName,String start,String end);
	
	//初始化时调用的方法
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName); 
}
