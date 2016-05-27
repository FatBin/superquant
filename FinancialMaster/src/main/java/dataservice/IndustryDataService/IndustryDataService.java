package dataservice.IndustryDataService;

import java.util.ArrayList;

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
}
