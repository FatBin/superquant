package web.blservice.simulationInfo;

import java.util.ArrayList;

import ENUM.ManageState;
import VO.SimulationRecordVO;

public interface HistoryRecordInfo {

	//增加一条历史盈亏记录
	public int addRecord(SimulationRecordVO simulationRecordVO);
	
	//隐藏一条历史盈亏记录
	public ManageState hideRecord(String id);
	
	//删除一条历史盈亏记录
	public ManageState deleteRecord(String id);
	
	//得到所有历史盈亏记录
	public ArrayList<SimulationRecordVO> getHistoryRecord(String userID);
	
	//得到所有历史记录计算的盈亏结果
	public double getCalculateResult(String userID);
	
}
