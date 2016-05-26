package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.BenchdataDaoImpl;
import DAO.DaoProxyService.BenchDataDaoService;
import DAO.dao.BenchdataDao;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;

public class BenchdataDaoProxy implements BenchDataDaoService{

	public boolean insert(Benchdata benchdata) throws Exception {
		BenchdataDao benchdataDaoImpl=new BenchdataDaoImpl();
		try {
			if (benchdataDaoImpl.findByID(benchdata.getId())==null) {
				benchdataDaoImpl.insert(benchdata);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public Benchdata findByID(BenchdataId benchdataId) throws Exception {
		BenchdataDao benchdataDaoImpl=new BenchdataDaoImpl();
		try {
			return benchdataDaoImpl.findByID(benchdataId);
		} catch (Exception e) {
			throw e;
		}
	}

	public List findAll() throws Exception {
		BenchdataDao benchdataDaoImpl=new BenchdataDaoImpl();
		try {
			return benchdataDaoImpl.findAll();
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public List getBenchRecord(String benchId,String starttime,String endtime) throws Exception {
		BenchdataDao benchdataDaoImpl=new BenchdataDaoImpl();
		try {
			String hql="from Benchdata b where "
					+ "b.id.benchId='"+benchId+"' and "
					+ "b.id.date>='"+starttime+"' and "
					+ "b.id.date<='"+endtime+"' order by b.id.date asc";
			return benchdataDaoImpl.getBenchData(hql);
		} catch (Exception e) {
			throw e;
		}
	}

}
