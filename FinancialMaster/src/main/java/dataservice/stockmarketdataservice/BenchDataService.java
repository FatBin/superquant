package dataservice.stockmarketdataservice;

import java.util.ArrayList;

import PO.benchmarkPO;
import PO.benchmarkStatisticPO;
import PO.fieldStatisticPO;

public interface BenchDataService {
	//获取所有可用的benchmark，大盘指数，目前只有沪深300指数
	public benchmarkPO getBenchmark();
	
	//获取指定大盘指数的数据,start:起始时间,格式 YYYY-mm-dd;end: 结束时间,格式 YYYY-mm-dd
	//时间，开盘指数，收盘指数
	public ArrayList<benchmarkStatisticPO> getStatisticOfBenchmark (String benchCode , String start,String end);
}
