package biz;

import org.hibernate.SessionFactory;

import bean.Expense;

public interface IExpendsData {

	public void setSessionFactory(SessionFactory sessionFactory);
	public void insertData(Expense expense);
	public void update(String id ,Expense expense);
	public void delete(String id);
	public Expense query(String id);
	
}
