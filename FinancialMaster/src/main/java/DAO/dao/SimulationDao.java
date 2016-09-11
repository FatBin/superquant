package DAO.dao;

import java.util.List;

import DAO.pojo.Simulation;

public interface SimulationDao {
	public int persist(Simulation simulation);
	
	public boolean remove(int id);
	
	public List getAllSimulations();
	
	public Simulation findById(int id);
}
