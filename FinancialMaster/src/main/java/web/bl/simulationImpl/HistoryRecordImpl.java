package web.bl.simulationImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DAO.pojo.SimulationProfit;
import ENUM.Deal_enum;
import ENUM.ManageState;
import VO.SimulationRecordVO;
import data.SimulationData.SimulationProfitData;
import dataservice.SimulationDataService.SimulationProfitDataService;
import web.blservice.simulationInfo.HistoryRecordInfo;

public class HistoryRecordImpl implements HistoryRecordInfo{

	@Override
	public int addRecord(SimulationRecordVO simulationRecordVO) {
		//‘› ±Œ¥”√
		return 0;
	}

	@Override
	public ManageState hideRecord(String id) {
	    
		SimulationProfitDataService simulationProfitDataService=new SimulationProfitData();
//		simulationProfitDataService.
		return null;
	}

	@Override
	public ManageState deleteRecord(String id) {
		SimulationProfitDataService simulationProfitDataService=new SimulationProfitData();
		return simulationProfitDataService.remove(Integer.parseInt(id))?ManageState.Succeed:ManageState.Fail;
	}

	@Override
	public ArrayList<SimulationRecordVO> getHistoryRecord(String userID) {
		SimulationProfitDataService simulationProfitDataService=new SimulationProfitData();
		ArrayList<SimulationProfit> list=simulationProfitDataService.getAllSimulationProfits(userID);
		ArrayList<SimulationRecordVO> simulationRecordVOs=new ArrayList<SimulationRecordVO>();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		for (SimulationProfit s : list) {
			SimulationRecordVO simulationRecordVO=new SimulationRecordVO();
			simulationRecordVO.setId(s.getId()+"");
			simulationRecordVO.setStockID(s.getStockId());			
			simulationRecordVO.setTime(simpleDateFormat.format(s.getDate()));
			simulationRecordVO.setDeal(Deal_enum.valueOf(s.getOperation()));
			simulationRecordVO.setMoney(s.getProfit());
			simulationRecordVOs.add(simulationRecordVO);
		}
		return simulationRecordVOs;
	}

	@Override
	public double getCalculateResult(String userID) {
		SimulationProfitDataService simulationProfitDataService=new SimulationProfitData();
		return simulationProfitDataService.getProfit(userID);
	}

}
