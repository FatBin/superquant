package DAO.dao;

import java.util.List;

import DAO.pojo.Industries;
import DAO.pojo.IndustriesId;

public interface IndustriesDao {
	/*
	 * insert the record
	 */
	public boolean insert(Industries industries) throws Exception;
	
	/*
	 * search an record with its id
	 */
	public Industries findByID(IndustriesId industriesId) throws Exception;
	
	/*
	 * select the industry needed by hql
	 */
	public List getIndustryData(String hql) throws Exception;
}
