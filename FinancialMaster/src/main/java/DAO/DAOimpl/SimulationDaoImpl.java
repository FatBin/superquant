package DAO.DAOimpl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.connection.DBconnection;
import DAO.dao.SimulationDao;
import DAO.pojo.Simulation;

public class SimulationDaoImpl implements SimulationDao{

	@Override
	public int persist(Simulation simulation) {
		try {
			Session session=DBconnection.getSession();
			try {
				session.save(simulation);
				Transaction transaction=session.beginTransaction();
				transaction.commit();
				session.close();
				return simulation.getId();
			} catch (Exception e) {
				session.close();
				e.printStackTrace();
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
					Simulation simulation=findById(id);
					session.delete(simulation);
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
	public List getAllSimulations() {
		try {
			Session session=DBconnection.getSession();
			try {
				List list = session.createCriteria(Simulation.class).list();
				session.close();
				return list;
			} catch (Exception e) {
				session.close();
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Simulation findById(int id) {
		// TODO Auto-generated method stub
		try {
			Session session=DBconnection.getSession();
			try {
				Simulation simulation=session.get(Simulation.class,id);
				session.close();
				return simulation;
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

}
