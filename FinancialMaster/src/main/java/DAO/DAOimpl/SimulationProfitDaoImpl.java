package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.connection.DBconnection;
import DAO.dao.SimulationProfitDao;
import DAO.pojo.SimulationProfit;

public class SimulationProfitDaoImpl implements SimulationProfitDao{

	@Override
	public int persist(SimulationProfit simulationProfit) {
		try {
			Session session=DBconnection.getSession();
			try {
				session.save(simulationProfit);
				Transaction transaction=session.beginTransaction();
				transaction.commit();
				session.close();
				return simulationProfit.getId();
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
	public boolean update(SimulationProfit simulationProfit) {
		try {
			Session session=DBconnection.getSession();
			try {
				session.update(simulationProfit);
				Transaction transaction=session.beginTransaction();
				transaction.commit();
				session.close();
				return true;
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
	public List getAllSimulationProfits() {
		try {
			Session session=DBconnection.getSession();
			try {
				List list=session.createCriteria(SimulationProfit.class).list();
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

	@Override
	public SimulationProfit findById(int id) {
		try {
			Session session=DBconnection.getSession();
			try {
				SimulationProfit simulationProfit=session.get(SimulationProfit.class, id);
				session.close();
				return simulationProfit;
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
	public double getAmontOfProfits() {
		String hql="select sum(s.profit) from SimulationProfit s";
		try {
			Session session=DBconnection.getSession();
			try {
				double result=0;
				if (session.createQuery(hql).uniqueResult()!=null) {
					result=(double) session.createQuery(hql).uniqueResult();
				}
				session.close();
				return result;
			} catch (Exception e) {
				session.close();
				e.printStackTrace();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
