package web.blservice.simulationInfo;

import java.util.ArrayList;

import ENUM.ManageState;
import VO.SimulationStrategyVO;

public interface SimulationStrategy {

	//增加一条回测策略的记录
	public ManageState addSimulationStrategy(SimulationStrategyVO simulationStrategyVO);
	
	//停止使用一条策略
	public ManageState deleteSimulationStrategy(String id);
	
	//得到第三张表所有内容
	public ArrayList<SimulationStrategyVO> getAllSimulationStrategy(String userID);
	
	//计算一条回测的策略的盈亏情况
	public double getResult(String id);
	
	//得到一条回测策略的详细盈亏情况
	public String[][] getResultDetail(String id);
	
	
}
