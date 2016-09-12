package web.bl.simulationImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.pojo.Simulationcommission;
import ENUM.ManageState;
import VO.SimulationStrategyVO;
import data.SimulationData.SimulationCommissionData;
import dataservice.SimulationDataService.SimulationCommissionDataService;
import web.blservice.simulationInfo.SimulationStrategyInfo;

public class SimulationStrategyImpl implements SimulationStrategyInfo{

	@Override
	public int addSimulationStrategy(SimulationStrategyVO simulationStrategyVO) {
		SimulationCommissionDataService simulationCommissionDataService=
				new SimulationCommissionData();
		Simulationcommission simulationcommission=new Simulationcommission();
		simulationcommission.setStockId(simulationStrategyVO.getStockID());
		simulationcommission.setUserId(simulationStrategyVO.getUserID());
		simulationcommission.setStrategyName(simulationStrategyVO.getStrategyName());
		simulationcommission.setTime(new Date());
		return simulationCommissionDataService.persist(simulationcommission);
	}

	@Override
	public ManageState deleteSimulationStrategy(String id) {
		SimulationCommissionDataService simulationCommissionDataService=
				new SimulationCommissionData();
		return simulationCommissionDataService.remove(Integer.parseInt(id))?ManageState.Succeed:ManageState.Fail;
	}

	@Override
	public ArrayList<SimulationStrategyVO> getAllSimulationStrategy(String userID) {
		SimulationCommissionDataService simulationCommissionDataService=
				new SimulationCommissionData();
		ArrayList<Simulationcommission> list=simulationCommissionDataService.getAllSimulationcommissions(userID);
		ArrayList<SimulationStrategyVO> result=new ArrayList<SimulationStrategyVO>();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		for (Simulationcommission s : list) {
			SimulationStrategyVO sVo=new SimulationStrategyVO();
			sVo.setId(""+s.getId());
			String startTime=simpleDateFormat.format(s.getTime());
			sVo.setStartTime(startTime);
			sVo.setStrategyName(s.getStrategyName());
			sVo.setStockID(s.getStockId());
			/**
			 * 
			 * 
			 * 
			 */
			
		}
		
		return null;
	}

	@Override
	public double getResult(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[][] getResultDetail(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
