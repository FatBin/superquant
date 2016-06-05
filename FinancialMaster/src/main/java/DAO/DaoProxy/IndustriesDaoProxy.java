package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.IndustriesDaoImpl;
import DAO.DaoProxyService.IndustriesDaoProxyService;
import DAO.dao.IndustriesDao;
import DAO.pojo.Industries;
import DAO.pojo.IndustriesId;

public class IndustriesDaoProxy implements IndustriesDaoProxyService{

	@Override
	public boolean insert(Industries industries) throws Exception {
		IndustriesDao industriesDao=new IndustriesDaoImpl();
		try {
			if (industriesDao.findByID(industries.getId())==null) {
				industriesDao.insert(industries);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Industries findByID(IndustriesId industriesId) throws Exception {
		IndustriesDao industriesDao=new IndustriesDaoImpl();
		try {
			return industriesDao.findByID(industriesId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List getIndustryRecord(String industry, String starttime, String endtime) throws Exception{
		IndustriesDao industriesDao=new IndustriesDaoImpl();
		try {
			String hql="from Industries i where"
					+ " i.id.industry='"+industry+"' and "
					+ "i.id.date>='"+starttime+"' and "
					+ "i.id.date<='"+endtime+"' order by i.id.date desc";
			return industriesDao.getIndustryData(hql);
		} catch (Exception e) {
			throw e;
		}
	}

}
