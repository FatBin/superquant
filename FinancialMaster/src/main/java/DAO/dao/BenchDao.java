package DAO.dao;

import java.util.List;

import DAO.pojo.Bench;

public interface BenchDao {
	//insert a PO
	public boolean insert(Bench bench)throws Exception;
	
	//search a bench with its id
	public Bench findByID(String benchID)throws Exception;
	
	//get all bench data
	public List findAll()throws Exception;
}
