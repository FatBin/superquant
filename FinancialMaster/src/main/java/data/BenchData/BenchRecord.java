package data.BenchData;

import java.util.List;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxyService.BenchDataDaoService;
import PO.benchCurrentDataPO;
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
	public benchCurrentDataPO getLastestRecord(String benchId) throws Exception{
		return BenchRecordUpdate.getBenchCurrentDataPO(benchId);
	}

}
