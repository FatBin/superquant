package businesslogicservice.stockContrastblservice;

public interface StockContrastBLService {

//	根据股票编号得到雷达图需要显示的数据，依次是：涨跌幅、市净率、市盈率、换手率
	public double[] getData(String id);

//   得到股票名称列表	
	public String[] getList();
}
