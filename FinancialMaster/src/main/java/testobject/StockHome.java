package testobject;
// Generated 2016-5-20 23:45:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Stock.
 * @see testobject.Stock
 * @author Hibernate Tools
 */
public class StockHome {

	private static final Log log = LogFactory.getLog(StockHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Stock transientInstance) {
		log.debug("persisting Stock instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Stock persistentInstance) {
		log.debug("removing Stock instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Stock merge(Stock detachedInstance) {
		log.debug("merging Stock instance");
		try {
			Stock result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Stock findById(String id) {
		log.debug("getting Stock instance with id: " + id);
		try {
			Stock instance = entityManager.find(Stock.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
