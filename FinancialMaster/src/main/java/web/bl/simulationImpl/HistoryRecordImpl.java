package web.bl.simulationImpl;

import java.util.ArrayList;

import ENUM.ManageState;
import VO.SimulationRecordVO;
import web.blservice.simulationInfo.HistoryRecordInfo;

public class HistoryRecordImpl implements HistoryRecordInfo{

	@Override
	public int addRecord(SimulationRecordVO simulationRecordVO) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public ManageState hideRecord(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageState deleteRecord(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SimulationRecordVO> getHistoryRecord(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getCalculateResult(String userID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
