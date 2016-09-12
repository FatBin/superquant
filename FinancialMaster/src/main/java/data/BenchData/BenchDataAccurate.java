package data.BenchData;

import java.util.ArrayList;

import DAO.DAOimpl.BenchdataAccurateDaoImpl;
import DAO.dao.BenchdataAccurateDao;
import DAO.pojo.BenchdataAccurate;
import dataservice.BenchDataService.BenchDataAccurateService;

public class BenchDataAccurate implements BenchDataAccurateService{
	BenchdataAccurateDao BenchdataAccurateDaoImpl;
	@Override
	public ArrayList<BenchdataAccurate> getBenchdataAccurates(String benchId) {
		BenchdataAccurateDaoImpl=new BenchdataAccurateDaoImpl();
		ArrayList<BenchdataAccurate> arrayList=new ArrayList<>();
		if (BenchdataAccurateDaoImpl.getBenchdataAccurate(benchId)!=null) {
			arrayList=(ArrayList<BenchdataAccurate>) BenchdataAccurateDaoImpl.getBenchdataAccurate(benchId);
		}
		return arrayList;
	}

	@Override
	public boolean clean() {
		BenchdataAccurateDaoImpl=new BenchdataAccurateDaoImpl();
		BenchdataAccurateDaoImpl.clean();
		return true;
	}

	@Override
	public boolean persist(BenchdataAccurate benchdataAccurate) {
		BenchdataAccurateDaoImpl=new BenchdataAccurateDaoImpl();
		return BenchdataAccurateDaoImpl.persist(benchdataAccurate);
	}

}
