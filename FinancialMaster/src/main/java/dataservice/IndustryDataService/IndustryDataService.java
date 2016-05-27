package dataservice.IndustryDataService;

import java.util.ArrayList;

import PO.industryPO;

public interface IndustryDataService {
	/*
	 * get the up-to-date data of the industries,including 
	 */
	public ArrayList<industryPO> getIndustryData();
}
