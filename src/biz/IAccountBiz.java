package biz;


import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;

import bean.Account;

public interface IAccountBiz {

	public void setSessionFactory(SessionFactory sessionFactory);
	public List<HashMap<String,Object>> getQuery();
	public void insert(Account account);
	public void update(Account account);
	public void delete(Account account);
	public String getID();
	
}
