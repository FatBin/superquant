package dataservice.IndustryDataService;

import java.util.ArrayList;
import java.util.List;

import PO.RiseStockPO;
import PO.industriesPO;
import PO.industryPO;

public interface IndustryDataService {
	/*
	 * get the up-to-date data of the industries,including 
	 */
	public ArrayList<industriesPO> getIndustryData() throws Exception;
	
	
	
	/*
	 * get the specific data of the industry
	 */
	public ArrayList<industryPO> getIndustry(String industryName) throws Exception;
	
	/*
	 * get the specific data of the industry from starttime to endtime
	 */
	public List getIndustryDuringTime(String industryName,String starttime,String endtime)throws Exception;

}
