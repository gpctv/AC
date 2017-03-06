package biz;

import org.hibernate.SessionFactory;

import bean.Account;

public interface ILoginBiz {

	public String isAdmin(Account account);
	public void setSessionFactory(SessionFactory sessionFactory);
	public String isExist(Account account);
}
