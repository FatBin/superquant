package web.blservice.simulationInfo;

import java.util.ArrayList;

import ENUM.ManageState;
import VO.SimulationStockVO;

public interface SimulationStockInfo {

	
	//买入一条持仓股票
	public int buyStock(SimulationStockVO simulationStockVO); 
	
	//计算一条持有股票的盈亏结果  
	public double getResult(String id);	
	
	//计算一条持有股票的
	public String[][] getResuleDetail(String id);
	
	//卖出一条持仓股票
	public ManageState sellStock(String id);
	
	//得到所有持仓股票
	public ArrayList<SimulationStockVO> getStockList(String userID);
}
