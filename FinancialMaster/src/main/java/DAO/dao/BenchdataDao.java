package DAO.dao;

import java.util.List;

import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;

public interface BenchdataDao {
	//insert a PO
	public boolean insert(Benchdata benchdata)throws Exception;
	
	//search a PO with its id(primary key),in this case,it is BenchdataId
	public Benchdata findByID(BenchdataId benchdataId)throws Exception;
	
	//get all benchdata 
	public List findAll()throws Exception;
	
	/*
	 * get the trade record according to hql
	 */
	public List getBenchData(String hql)throws Exception;
	
}
