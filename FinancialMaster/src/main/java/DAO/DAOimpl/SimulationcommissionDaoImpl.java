package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.connection.DBconnection;
import DAO.dao.SimulationcommissionDao;
import DAO.pojo.Simulation;
import DAO.pojo.SimulationProfit;
import DAO.pojo.Simulationcommission;

public class SimulationcommissionDaoImpl implements SimulationcommissionDao{

	@Override
	public int persist(Simulationcommission simulationcommission) {
		try {
			Session session=DBconnection.getSession();
			try {
				session.save(simulationcommission);
				Transaction transaction=session.beginTransaction();
				transaction.commit();
				session.close();
				return simulationcommission.getId();
			} catch (Exception e) {
				e.printStackTrace();
				session.close();
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean remove(int id) {
		try {
			Session session=DBconnection.getSession();
			try {
				if (findById(id)!=null) {
					Simulationcommission simulationcommission=findById(id);
					session.delete(simulationcommission);
					Transaction transaction=session.beginTransaction();
					transaction.commit();
					session.close();
					return true;
				}else {
					session.close();
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				session.close();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Simulationcommission findById(int id) {
		try {
			Session session=DBconnection.getSession();
			try {
				Simulationcommission simulationcommission=session.get(Simulationcommission.class,id);
				session.close();
				return simulationcommission;
			} catch (Exception e) {
				e.printStackTrace();
				session.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List getAllSimualtioncommissions() {
		try {
			Session session=DBconnection.getSession();
			try {
				List list=session.createCriteria(Simulationcommission.class).list();
				session.close();
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				session.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
