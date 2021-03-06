package web.blservice.benchInfo;

import VO.BenchListVO;
import VO.BenchVO;

public interface BenchInfo {

	//返回所有可用的大盘指数
	public BenchListVO getBenchCode();
	
	//根据指定的大盘指数返回对应的大盘VO
	public BenchVO getStockMarket(String benchCode);
	
	//返回指定大盘当日分时图所需数据
	public String[][] getTimeSharingData(String benchCode);
}
