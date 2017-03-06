package biz;

import org.hibernate.SessionFactory;

import bean.Income;
 

public interface IIncomeBiz {

	public void setSessionFactory(SessionFactory sessionFactory);
	public void insertData(Income income);
	public void update(String id ,Income income);
	public void delete(String id);
	public Income query(String id);
	
	
}
