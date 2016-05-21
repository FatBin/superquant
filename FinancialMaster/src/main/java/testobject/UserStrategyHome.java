package testobject;
// Generated 2016-5-20 23:45:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UserStrategy.
 * @see testobject.UserStrategy
 * @author Hibernate Tools
 */
public class UserStrategyHome {

	private static final Log log = LogFactory.getLog(UserStrategyHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(UserStrategy transientInstance) {
		log.debug("persisting UserStrategy instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserStrategy persistentInstance) {
		log.debug("removing UserStrategy instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserStrategy merge(UserStrategy detachedInstance) {
		log.debug("merging UserStrategy instance");
		try {
			UserStrategy result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserStrategy findById(UserStrategyId id) {
		log.debug("getting UserStrategy instance with id: " + id);
		try {
			UserStrategy instance = entityManager.find(UserStrategy.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
