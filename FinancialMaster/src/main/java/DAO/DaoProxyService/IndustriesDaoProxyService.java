package DAO.DaoProxyService;

import java.util.List;

import DAO.pojo.Industries;
import DAO.pojo.IndustriesId;

public interface IndustriesDaoProxyService {
	/*
	 * insert the record
	 */
	public boolean insert(Industries industries) throws Exception;
	
	/*
	 * search an record with its id
	 */
	public Industries findByID(IndustriesId industriesId) throws Exception;
	
	
	/*
	 * search records with its industryName and time
	 */
	public List getIndustryRecord(String industry,String starttime,String endtime) throws Exception;
}
