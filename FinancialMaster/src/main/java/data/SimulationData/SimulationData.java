package data.SimulationData;

import java.util.ArrayList;

import DAO.pojo.Simulation;
import dataservice.SimulationDataService.SimulationDataService;

public class SimulationData implements SimulationDataService{

	@Override
	public int persist(Simulation simulation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Simulation> getAllSimulations() {
		// TODO Auto-generated method stub
		return null;
	}

}
