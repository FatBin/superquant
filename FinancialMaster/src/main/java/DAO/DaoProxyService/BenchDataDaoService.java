package DAO.DaoProxyService;

import java.util.List;

public interface BenchDataDaoService {
	/*
	 * get the trade record according to the benchId and time,the format of the time must be yyyy-mm-dd
	 */
	public List getBenchRecord(String benchId,String starttime,String endtime)throws Exception;
}
