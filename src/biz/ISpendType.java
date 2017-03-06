package biz;

import org.hibernate.SessionFactory;

import bean.SpendItem;
import bean.SpendItemDAO;

public interface ISpendType {

	public void setSessionFactory(SessionFactory sessionFactory);
	public void setSpendItemDao(SpendItemDAO spendItemDAO);
	public void updateSpendType(SpendItem spendItem);
	public void deleteSpendType(SpendItem spendItem);
}
