package biz;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.Account;
import bean.AccountDAO;

public class LoginBizImp implements ILoginBiz{
	SessionFactory sessionFactory;
	Account account;
	AccountDAO accountDao;
	public void setAccount(Account account){
		this.account=account;
	}
	public void setAccountDao(AccountDAO accountDao){
		this.accountDao=accountDao;
	}
	@Override
	public String isAdmin(Account account) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.openSession();
		
		String command="Select IS_Admin from Account where Account=:account";
		SQLQuery query=session.createSQLQuery(command);
		query.setParameter("account", account.getAccount());
		List result=query.list();
		String isAdmin="" ;
		for(int i=0;i<result.size();i++){
			isAdmin= result.get(i).toString();
		}
		session.close();
		return isAdmin;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory=sessionFactory;
	}

	@Override
	public String isExist(Account account) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.openSession();
		
		String command="Select Account , Password from Account where Account=:account";
		SQLQuery query=session.createSQLQuery(command);
		query.setParameter("account", account.getAccount());
		List<Object[]> result=query.list();
		String accountName="";
		String password="";
		String exist;
		for(int i=0;i<result.size();i++){
			accountName=result.get(i)[0].toString();
			password=result.get(i)[1].toString();
		}
		if(accountName.equals(account.getAccount())){
			if(password.equals(account.getPassword())){
				exist="true";
			}else{
				exist="false";
			}
		}else{
			exist="false";
		}
		session.close();
		return exist;
	}

}
