package dataservice.stockmarketdataservice;

import ENUM.ManageState;

public interface BenchKLineDataService {
    //读取网上最新的数据，更新数据文件中的数据
	public ManageState update();
	//返回k线图所需要的数据
	public String[][] getStatisticData(String kLine_enum);
}
