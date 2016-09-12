package data.SimulationData;

import java.util.ArrayList;

import DAO.DAOimpl.SimulationDaoImpl;
import DAO.DAOimpl.SimulationProfitDaoImpl;
import DAO.dao.SimulationDao;
import DAO.dao.SimulationProfitDao;
import DAO.pojo.Simulation;
import DAO.pojo.SimulationProfit;
import dataservice.SimulationDataService.SimulationProfitDataService;

public class SimulationProfitData implements SimulationProfitDataService{
	SimulationProfitDao simulationProfitDaoImpl;
	@Override
	public int persist(SimulationProfit simulationProfit) {
		simulationProfitDaoImpl=new SimulationProfitDaoImpl();
		return simulationProfitDaoImpl.persist(simulationProfit);
	}

	@Override
	public boolean update(SimulationProfit simulationProfit) {
		simulationProfitDaoImpl=new SimulationProfitDaoImpl();
		return simulationProfitDaoImpl.update(simulationProfit);
	}

	@Override
	public SimulationProfit findById(int id) {
		simulationProfitDaoImpl=new SimulationProfitDaoImpl();
		return simulationProfitDaoImpl.findById(id);
	}

	@Override
	public double getProfit() {
		simulationProfitDaoImpl=new SimulationProfitDaoImpl();
		return simulationProfitDaoImpl.getAmontOfProfits();
	}

	@Override
	public boolean remove(int id) {
		simulationProfitDaoImpl=new SimulationProfitDaoImpl();
		return simulationProfitDaoImpl.remove(id);
	}

	@Override
	public double getProfit(String userId) {
		simulationProfitDaoImpl=new SimulationProfitDaoImpl();
		return simulationProfitDaoImpl.getUserProfit(userId);
	}

	@Override
	public ArrayList<SimulationProfit> getAllSimulationProfits(String userId) {
		simulationProfitDaoImpl=new SimulationProfitDaoImpl();
		ArrayList<SimulationProfit> arrayList=new ArrayList<>();
		if (simulationProfitDaoImpl.getAllSimulationProfits(userId)!=null) {
			arrayList=(ArrayList<SimulationProfit>) simulationProfitDaoImpl.getAllSimulationProfits(userId);
		}
 		return arrayList;
	}

}
