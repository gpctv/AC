package biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.Account;
import bean.AccountDAO;

public class AccountWebServiceBiz {

	SessionFactory sessionFactory;
	 Account account;
	 AccountDAO accountDao;
	 List<HashMap<String, String>> findData;
	 public void setSessionFactory(SessionFactory sessionFactory) {
			// TODO Auto-generated method stub
			this.sessionFactory=sessionFactory;
		}
	 
	 public List<HashMap<String, String>> getAccount(){
		 findData=new ArrayList<HashMap<String,String>>();
			Session session=this.sessionFactory.openSession();
			try{
				String command="select id_num, account,password,is_admin " +
						"from account order by id_num ";
				 session =this.sessionFactory.openSession();
				SQLQuery query=session.createSQLQuery(command);
				List<Object[]> result=query.list();
				this.findData.clear();
				for(int i=0;i<result.size();i++){
					HashMap<String,String> temp=new HashMap<String, String>();
					 temp.put("Idnum", result.get(i)[0].toString());
					 temp.put("Account", result.get(i)[1].toString());
					 temp.put("Password", result.get(i)[2].toString());
					 temp.put("Admin", result.get(i)[3].toString());
					 findData.add(temp);
				}
				return findData;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.close();
			}
			return null;
	 }
	 public String searchAccount(String id){
		 String command="select id_num, account  " +
					"from account  " +
					"where  id_num=:id";
		 Session session=this.sessionFactory.openSession();
			try{
				  
					SQLQuery query=session.createSQLQuery(command);
					query.setParameter("id", id);
					List<Object[]> result=query.list();
					String account="";
					for(int i=0;i<result.size();i++){
						account=result.get(i)[0].toString();
					}
					 return account;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
	 }
	
}
