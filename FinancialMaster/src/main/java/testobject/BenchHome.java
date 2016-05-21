package testobject;
// Generated 2016-5-20 23:45:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Bench.
 * @see testobject.Bench
 * @author Hibernate Tools
 */
public class BenchHome {

	private static final Log log = LogFactory.getLog(BenchHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Bench transientInstance) {
		System.out.println("yes");
		log.debug("persisting Bench instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Bench persistentInstance) {
		log.debug("removing Bench instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Bench merge(Bench detachedInstance) {
		log.debug("merging Bench instance");
		try {
			Bench result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Bench findById(String id) {
		log.debug("getting Bench instance with id: " + id);
		try {
			Bench instance = entityManager.find(Bench.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
