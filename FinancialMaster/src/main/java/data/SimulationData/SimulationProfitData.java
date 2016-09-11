package data.SimulationData;

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

}
