package dataservice.BenchDataService;

import java.util.ArrayList;

import DAO.pojo.BenchdataAccurate;

public interface BenchDataAccurateService {
	public ArrayList<BenchdataAccurate> getBenchdataAccurates(String benchId);
	
	//delete all the record
	public boolean clean();
	
	public boolean persist(BenchdataAccurate benchdataAccurate);
}
