package web.bl.simulationImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.loader.custom.Return;

import DAO.pojo.Simulation;
import ENUM.ManageState;
import VO.SimulationStockVO;
import data.SimulationData.SimulationData;
import dataservice.SimulationDataService.SimulationDataService;
import web.blservice.simulationInfo.SimulationStockInfo;

public class SimulationStockImpl implements SimulationStockInfo {

	@Override
	public int buyStock(SimulationStockVO simulationStockVO) {
		SimulationDataService simulationDataService=new SimulationData();
		Simulation simulation=new Simulation();
		simulation.setUserId(simulationStockVO.getUserID());
		simulation.setStockId(simulationStockVO.getStockID());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
	    Date date;
		try {
			date = sdf.parse(simulationStockVO.getTime());
			simulation.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}  
		simulation.setPrice(simulationStockVO.getPrice());
		simulation.setVolume(simulationStockVO.getNumber());
		return simulationDataService.persist(simulation);
	}

	private double getResult(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[][] getResuleDetail(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageState sellStock(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SimulationStockVO> getStockList(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
