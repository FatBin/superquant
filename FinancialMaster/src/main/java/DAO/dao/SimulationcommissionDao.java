package DAO.dao;

import java.util.List;

import DAO.pojo.Simulationcommission;

public interface SimulationcommissionDao {
	public int persist(Simulationcommission simulationcommission);
	
	public boolean remove(int id);
	
	public Simulationcommission findById(int id);
	
	public List getAllSimualtioncommissions();
	
	public List getAllSimualtioncommissions(String userId);
}
