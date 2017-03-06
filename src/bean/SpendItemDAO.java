package bean;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SpendItem entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.SpendItem
 * @author MyEclipse Persistence Tools
 */
public class SpendItemDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SpendItemDAO.class);
	// property constants
	public static final String ITEM_NAME = "itemName";
	public static final String ITEM_TYPE = "itemType";

	protected void initDao() {
		// do nothing
	}

	public void save(SpendItem transientInstance) {
		log.debug("saving SpendItem instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SpendItem persistentInstance) {
		log.debug("deleting SpendItem instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SpendItem findById(java.lang.String id) {
		log.debug("getting SpendItem instance with id: " + id);
		try {
			SpendItem instance = (SpendItem) getHibernateTemplate().get(
					"bean.SpendItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SpendItem instance) {
		log.debug("finding SpendItem instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SpendItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SpendItem as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByItemName(Object itemName) {
		return findByProperty(ITEM_NAME, itemName);
	}

	public List findByItemType(Object itemType) {
		return findByProperty(ITEM_TYPE, itemType);
	}

	public List findAll() {
		log.debug("finding all SpendItem instances");
		try {
			String queryString = "from SpendItem";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SpendItem merge(SpendItem detachedInstance) {
		log.debug("merging SpendItem instance");
		try {
			SpendItem result = (SpendItem) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SpendItem instance) {
		log.debug("attaching dirty SpendItem instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SpendItem instance) {
		log.debug("attaching clean SpendItem instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SpendItemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SpendItemDAO) ctx.getBean("SpendItemDAO");
	}
}