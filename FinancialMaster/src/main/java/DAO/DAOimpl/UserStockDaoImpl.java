package DAO.DAOimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;

/**
 * Home object for domain model class UserStock.
 * @see .UserStock
 * @author Hibernate Tools
 */
public class UserStockDaoImpl {

	private static final Log log = LogFactory.getLog(UserStockDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(UserStock transientInstance) {
		log.debug("persisting UserStock instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserStock persistentInstance) {
		log.debug("removing UserStock instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserStock merge(UserStock detachedInstance) {
		log.debug("merging UserStock instance");
		try {
			UserStock result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserStock findById(UserStockId id) {
		log.debug("getting UserStock instance with id: " + id);
		try {
			UserStock instance = entityManager.find(UserStock.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
