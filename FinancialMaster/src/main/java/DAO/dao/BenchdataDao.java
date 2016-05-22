package DAO.dao;

import DAO.pojo.Bench;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;
import java.util.List;

public interface BenchdataDao {
	//insert a PO
	public boolean insert(Benchdata benchdata)throws Exception;
	
	//search a PO with its id(primary key),in this case,it is BenchdataId
	public Benchdata findByID(BenchdataId benchdataId)throws Exception;
	
	//get all benchdata 
	public List findAll()throws Exception;
}
