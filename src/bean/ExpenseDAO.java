package bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Expense entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.Expense
 * @author MyEclipse Persistence Tools
 */
public class ExpenseDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ExpenseDAO.class);
	// property constants
	public static final String ITEM_TYPE = "itemType";
	public static final String ITEM_NAME = "itemName";

	protected void initDao() {
		// do nothing
	}

	public void save(Expense transientInstance) {
		log.debug("saving Expense instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Expense persistentInstance) {
		log.debug("deleting Expense instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Expense findById(java.lang.String id) {
		log.debug("getting Expense instance with id: " + id);
		try {
			Expense instance = (Expense) getHibernateTemplate().get(
					"bean.Expense", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Expense instance) {
		log.debug("finding Expense instance by example");
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
		log.debug("finding Expense instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Expense as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByItemType(Object itemType) {
		return findByProperty(ITEM_TYPE, itemType);
	}

	public List findByItemName(Object itemName) {
		return findByProperty(ITEM_NAME, itemName);
	}

	public List findAll() {
		log.debug("finding all Expense instances");
		try {
			String queryString = "from Expense";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Expense merge(Expense detachedInstance) {
		log.debug("merging Expense instance");
		try {
			Expense result = (Expense) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Expense instance) {
		log.debug("attaching dirty Expense instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Expense instance) {
		log.debug("attaching clean Expense instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ExpenseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ExpenseDAO) ctx.getBean("ExpenseDAO");
	}
}