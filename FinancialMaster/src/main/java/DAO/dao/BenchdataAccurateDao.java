package DAO.dao;

import java.util.ArrayList;
import java.util.List;

import DAO.pojo.BenchdataAccurate;

public interface BenchdataAccurateDao {
	public List getBenchdataAccurate(String benchId);
	
	public void clean();
	
	public boolean persist(BenchdataAccurate benchdataAccurate);
}
