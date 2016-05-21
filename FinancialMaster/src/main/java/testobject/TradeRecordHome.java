package testobject;
// Generated 2016-5-20 23:45:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TradeRecord.
 * @see testobject.TradeRecord
 * @author Hibernate Tools
 */
public class TradeRecordHome {

	private static final Log log = LogFactory.getLog(TradeRecordHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TradeRecord transientInstance) {
		log.debug("persisting TradeRecord instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TradeRecord persistentInstance) {
		log.debug("removing TradeRecord instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TradeRecord merge(TradeRecord detachedInstance) {
		log.debug("merging TradeRecord instance");
		try {
			TradeRecord result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TradeRecord findById(TradeRecordId id) {
		log.debug("getting TradeRecord instance with id: " + id);
		try {
			TradeRecord instance = entityManager.find(TradeRecord.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
