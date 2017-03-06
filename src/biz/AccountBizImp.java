package biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.Account;
import bean.AccountDAO;

public class AccountBizImp implements IAccountBiz{
	SessionFactory sessionFactory;
	 Account account;
	 AccountDAO accountDao;
	List<HashMap<String,Object>> findData;
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory=sessionFactory;
	}
	 public void setAccount(Account account){
		 this.account=account;
	 }
	 public void setAccountDao(AccountDAO accountDao){
		 this.accountDao=accountDao;
	 }

	@Override
	public List<HashMap<String, Object>> getQuery() {
		// TODO Auto-generated method stub
		this.findData=new ArrayList<HashMap<String,Object>>();
		Session session=this.sessionFactory.openSession();
		try{
			String command="select id_num, account,password,is_admin " +
					"from account order by id_num ";
			 session =this.sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery(command);
			List<Object[]> result=query.list();
			this.findData.clear();
			for(int i=0;i<result.size();i++){
				HashMap<String,Object> temp=new HashMap<String, Object>();
				 temp.put("Idnum", result.get(i)[0].toString());
				 temp.put("Account", result.get(i)[1].toString());
				 temp.put("Password", result.get(i)[2].toString());
				 temp.put("Admin", result.get(i)[3].toString());
				 this.findData.add(temp);
			}
			return this.findData;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public void insert(Account account) {
		// TODO Auto-generated method stub
		Session session=this.accountDao.getSessionFactory().openSession();
		
		 try{
			  this.account=account;
			 this.accountDao.save(this.account);
			 session.beginTransaction().commit();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 session.close();
			 
		 }
	}


	public void update(Account account) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.openSession();
		System.out.println("update account--");
		try{
			this.accountDao.attachDirty (account);
			 
		 
		session.beginTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
public void queryID(String id){
	
}
	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		Session session=this.accountDao.getSessionFactory().openSession();
		
		try{
			 this.account=account;
			 this.accountDao.delete(this.account);
			 session.beginTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close(); 
		}
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		String num="0";
		String command="select  max(to_number(id_num) ) " +
				"from account ";
		Session session=this.sessionFactory.openSession();
		try{
		  
		SQLQuery query=session.createSQLQuery(command);
		List result=query.list();
		for(int i=0;i<result.size();i++){
			num=result.get(i).toString();
		}
		BigDecimal n=new BigDecimal(num);
		num=n.add(new BigDecimal(1)).toString();
		return num;
		}catch(NullPointerException e){
			e.printStackTrace();
			return num="0";
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	public boolean isExistAccount(String account){
		String command="select count(account) " +
				"from account " +
				"where account=:account ";
		Session session=this.sessionFactory.openSession();
		try{
			  
				SQLQuery query=session.createSQLQuery(command);
				query.setParameter("account", account);
				List result=query.list();
				int num=0;
				for(int i=0;i<result.size();i++){
					num=Integer.valueOf(result.get(i).toString());
				}
				if(num>0){
					return true;
				}else{
					return false;
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public String isAdmin(String account){
		String command="select IS_ADMIN " +
				"from account " +
				"where account=:account ";
		String isAdmin="0";
		Session session=this.sessionFactory.openSession();
		try{
			SQLQuery query=session.createSQLQuery(command);
			query.setParameter("account", account);
			List result=query.list();
			
			for(int i=0;i<result.size();i++){
				isAdmin=result.get(i).toString();
			}
			return isAdmin;
		}catch(Exception e){
			e.printStackTrace();
			return isAdmin;
		}finally{
			session.close();
		}
	}
	
}
