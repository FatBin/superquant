package DAO.DaoProxy;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import DAO.DAOimpl.BenchDaoImpl;
import DAO.dao.BenchDao;
import DAO.pojo.Bench;

public class BenchDaoProxy implements BenchDao{
	
	@Override
	public boolean insert(Bench bench) throws Exception{
		BenchDao BenchDaoImpl=new BenchDaoImpl();
		try {
			if(BenchDaoImpl.findByID(bench.getBenchId())==null){
				BenchDaoImpl.insert(bench);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		} 
	}

	@Override
	public Bench findByID(String benchID) throws Exception {
		BenchDao BenchDaoImpl=new BenchDaoImpl();
		try {
			return BenchDaoImpl.findByID(benchID);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List findAll() throws Exception {
		BenchDao BenchDaoImpl=new BenchDaoImpl();
		try {
			return BenchDaoImpl.findAll();
		} catch (Exception e) {
			throw e;
		}
	}
	
}
