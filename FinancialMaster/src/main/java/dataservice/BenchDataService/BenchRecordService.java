package dataservice.BenchDataService;

import java.util.ArrayList;
import java.util.List;

import DAO.pojo.Benchdata;

public interface BenchRecordService {
	/*
	 * get the trade record according to the benchId and time,the format of the time must be yyyy-mm-dd
	 */
	public List getBenchData(String benchId,String starttime,String endtime) throws Exception;
	
	/*
	 * get the lastest trade record
	 */
	public List getLastestRecord(String benchId) throws Exception;
}
