package DAO.DAOimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;

/**
 * Home object for domain model class Benchdata.
 * @see .Benchdata
 * @author Hibernate Tools
 */
public class BenchdataDaoImpl {

	private static final Log log = LogFactory.getLog(BenchdataDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Benchdata transientInstance) {
		log.debug("persisting Benchdata instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Benchdata persistentInstance) {
		log.debug("removing Benchdata instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Benchdata merge(Benchdata detachedInstance) {
		log.debug("merging Benchdata instance");
		try {
			Benchdata result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Benchdata findById(BenchdataId id) {
		log.debug("getting Benchdata instance with id: " + id);
		try {
			Benchdata instance = entityManager.find(Benchdata.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
