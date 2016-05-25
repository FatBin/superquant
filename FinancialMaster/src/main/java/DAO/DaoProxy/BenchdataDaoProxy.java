package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.BenchdataDaoImpl;
import DAO.dao.BenchdataDao;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;

public class BenchdataDaoProxy implements BenchdataDao{

	@Override
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

	@Override
	public Benchdata findByID(BenchdataId benchdataId) throws Exception {
		BenchdataDao benchdataDaoImpl=new BenchdataDaoImpl();
		try {
			return benchdataDaoImpl.findByID(benchdataId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List findAll() throws Exception {
		BenchdataDao benchdataDaoImpl=new BenchdataDaoImpl();
		try {
			return benchdataDaoImpl.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

}
