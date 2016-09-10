package dataservice.SimulationDataService;

import java.util.ArrayList;

import DAO.pojo.Simulation;

public interface SimulationProfitDataService {
	//the po doesn't need the id, and the method will return an id ,a positive number, if the po has been persisted successfully
	public int persist(Simulation simulation);
	
	//need the id of the record.It will return a boolean value if it update the attribute successfully
	public boolean update(Simulation simulation);
	
	//need the id.It will return a corresponding po
	public Simulation findById(int id);
	
	
	//get the amount of the profit
	public double getProfit();
}
