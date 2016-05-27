package data.BenchData;

import java.util.List;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxyService.BenchDataDaoService;
import DAO.pojo.Benchdata;
import dataservice.BenchDataService.BenchRecordService;

public class BenchRecord implements BenchRecordService{

	@Override
	public List getBenchData(String benchId, String starttime, String endtime) throws Exception{
		BenchDataDaoService benchDataDaoService=DaoFactory.getBenchdataDaoProxy();
		try {
			return benchDataDaoService.getBenchRecord(benchId, starttime, endtime);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Benchdata getLastestRecord(String benchId) {
		return null;
	}

}
