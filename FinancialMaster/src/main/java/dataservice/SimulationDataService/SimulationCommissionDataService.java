package dataservice.SimulationDataService;

import java.util.ArrayList;

import DAO.pojo.Simulation;
import DAO.pojo.Simulationcommission;

public interface SimulationCommissionDataService {
	//the po doesn't need the id, and the method will return an id ,a positive number, if the po has been persisted successfully
	public int persist(Simulationcommission simulationcommission);
	
	//need the id of the record.The method will return a boolean value if the po has been removed successfully
	public boolean remove(int id);
	
	//it will return all the record in the table of simulation
	public ArrayList<Simulationcommission> getAllSimulationcommissions();
	
	//get all the records relative to the user
	public ArrayList<Simulationcommission> getAllSimulationcommissions(String userId);
	
	//get the record according to the id
	public Simulationcommission findById(int id);
}
