package data.BenchData;

import java.util.List;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxyService.BenchDaoProxyService;
import DAO.dao.BenchDao;
import dataservice.BenchDataService.BenchDataService;

public class BenchData implements BenchDataService{

	@Override
	public List getBench() throws Exception{
		BenchDaoProxyService benchDao=DaoFactory.getBenchDaoProxy();
		try {
			List result=benchDao.findAll();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
}
