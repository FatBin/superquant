package dataservice.BenchDataService;

import java.util.List;

import DAO.pojo.Bench;

public interface BenchDataService {
	/*
	 * get all the bench,whose content is the id and name,attention to catch the exception
	 */
	public List getBench() throws Exception;
}
