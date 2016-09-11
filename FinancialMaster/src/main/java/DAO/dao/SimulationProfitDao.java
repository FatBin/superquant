package DAO.dao;

import java.util.List;

import DAO.pojo.SimulationProfit;

public interface SimulationProfitDao {
	public int persist(SimulationProfit simulationProfit);
	
	public boolean update(SimulationProfit simulationProfit);
	
	public List getAllSimulationProfits();
	
	public SimulationProfit findById(int id);
	
	public double getAmontOfProfits();
}
