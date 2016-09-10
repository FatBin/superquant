package dataservice.SimulationDataService;

import DAO.pojo.Simulation;

public interface SimulationCommissionDataService {
	//the po doesn't need the id, and the method will return an id ,a positive number, if the po has been persisted successfully
	public int persist(Simulation simulation);
}
