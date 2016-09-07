package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.IndustriesDao;
import DAO.pojo.Industries;
import DAO.pojo.IndustriesId;

public class IndustriesDaoImpl implements IndustriesDao{

	@Override
	public boolean insert(Industries industries) throws Exception {
		Session session=DBconnection.getSession();
		session.save(industries);
		Transaction tx=session.beginTransaction();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public Industries findByID(IndustriesId industriesId) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Industries.class);
		criteria.add(Restrictions.eq("id.industry", industriesId.getIndustry()));
		criteria.add(Restrictions.eq("id.date", industriesId.getDate()));
		List industriesList=criteria.list();
		session.close();
		if(industriesList.size()==0){
			return null;
		}else{
			return (Industries)industriesList.get(0);
		}
	}

	@Override
	public List getIndustryData(String hql) throws Exception{
		Session session=DBconnection.getSession();
		Query query=session.createQuery(hql);
		List list=query.list();
		session.close();
		return list;
	}



}
