package data.SimulationData;

import java.util.ArrayList;

import DAO.DAOimpl.SimulationDaoImpl;
import DAO.dao.SimulationDao;
import DAO.pojo.Simulation;
import dataservice.SimulationDataService.SimulationDataService;

public class SimulationData implements SimulationDataService{
	SimulationDao SimulationDaoImpl;
	@Override
	public int persist(Simulation simulation) {
		SimulationDaoImpl=new SimulationDaoImpl();
		return SimulationDaoImpl.persist(simulation);
	}

	@Override
	public boolean remove(int id) {
		SimulationDaoImpl=new SimulationDaoImpl();
		return SimulationDaoImpl.remove(id);
	}

	@Override
	public ArrayList<Simulation> getAllSimulations() {
		SimulationDaoImpl=new SimulationDaoImpl();
		ArrayList<Simulation> arrayList=new ArrayList<>();
		if (SimulationDaoImpl.getAllSimulations()!=null) {
			arrayList=(ArrayList<Simulation>) SimulationDaoImpl.getAllSimulations();
		}
 		return arrayList;
	}

	@Override
	public Simulation getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Simulation> getUserRecords(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
