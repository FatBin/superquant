package web.bl.simulationImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.pojo.Simulationcommission;
import ENUM.ManageState;
import PO.UpToDateStockPO;
import PO.profitPO;
import VO.SimulationStrategyVO;
import data.SimulationData.SimulationCommissionData;
import dataservice.SimulationDataService.SimulationCommissionDataService;
import web.bl.StrategyHandle.StrategyHandle;
import web.bl.stockImpl.StockImpl;
import web.blservice.StrategyHandleService.StrategyHandleService;
import web.blservice.simulationInfo.SimulationStrategyInfo;
import web.blservice.stockInfo.StockUpdateInfo;

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
        Date date=new Date();
        String endTime=simpleDateFormat.format(date);
        StrategyHandleService strategyHandleService=new StrategyHandle();
        StockUpdateInfo stockUpdateInfo=new StockImpl();        
        
		for (Simulationcommission s : list) {
			SimulationStrategyVO sVo=new SimulationStrategyVO();
			sVo.setId(""+s.getId());
			String startTime=simpleDateFormat.format(s.getTime());
			sVo.setStartTime(startTime);
			sVo.setStrategyName(s.getStrategyName());
			sVo.setStockID(s.getStockId());
			
			UpToDateStockPO upToDateStockPO=stockUpdateInfo.update(s.getStockId());
			sVo.setNow(upToDateStockPO.getNow());
			
			profitPO po=strategyHandleService.getProfit(userID, s.getStockId(), s.getStrategyName(), startTime, endTime);
			sVo.setProfit(po.getProfit());
			result.add(sVo);
		}
		
		return result;
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
