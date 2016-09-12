package dataservice.SimulationDataService;

import java.util.ArrayList;

import DAO.pojo.Simulation;
import DAO.pojo.SimulationProfit;

public interface SimulationProfitDataService {
	//the po doesn't need the id, and the method will return an id ,a positive number, if the po has been persisted successfully
	public int persist(SimulationProfit simulationProfit);
	
	//need the id of the record.It will return a boolean value if it update the attribute successfully
	public boolean update(SimulationProfit simulationProfit);
	
	//need the id.It will return a corresponding po
	public SimulationProfit findById(int id);
	
	//get the amount of the profit
	public double getProfit();
	
	//need the id of the record.It will return a boolean value if it remove the record successfully
	public boolean remove(int id);
	
	//get the amount of the user's profit
	public double getProfit(String userId);
	
	//get all the records
	public ArrayList<SimulationProfit> getAllSimulationProfits(String userId);
}
