package biz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.SpendItem;
import bean.SpendItemDAO;

public class SpendTypeImp implements ISpendType{
	SessionFactory sessionFactory;
	SpendItemDAO spendItemDAO;
	SpendItem spendItem;
	public void setSpendItem(SpendItem spendItem){
		this.spendItem=spendItem;
	}
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory=sessionFactory;
	}

	@Override
	public void setSpendItemDao(SpendItemDAO spendItemDAO) {
		// TODO Auto-generated method stub
		this.spendItemDAO=spendItemDAO;
	}

	@Override
	public void updateSpendType(SpendItem spendItem) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.openSession();
		
		System.out.println("update--");
		this.spendItemDAO.merge(spendItem);
		session.beginTransaction().commit();
		session.close();
	}

	@Override
	public void deleteSpendType(SpendItem spendItem) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.openSession();
		
		this.spendItemDAO.delete(spendItem);
		session.beginTransaction().commit();
		session.close();
	}

}
