package data.SimulationData;

import java.util.ArrayList;

import DAO.pojo.Simulation;
import dataservice.SimulationDataService.SimulationDataService;

public class SimulationData implements SimulationDataService{

	@Override
	public int persist(Simulation simulation) {
		return 0;
	}

	@Override
	public boolean remove(int id) {
		return false;
	}

	@Override
	public ArrayList<Simulation> getAllSimulations() {
 		return null;
	}

}
