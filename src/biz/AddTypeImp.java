package biz;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import createSessionFactory.HibernateSessionFactory;

import bean.SpendItem;
import bean.SpendItemDAO;

public class AddTypeImp implements IAddType{
    SpendItem spendItem;
    SpendItemDAO spendItemDao;
    
     SessionFactory sessionFactory;
	public void setSpendItemDao(SpendItemDAO spendItemDao){
		this.spendItemDao=spendItemDao;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	 
	@Override
	
	public void saveSpendItem(SpendItem spendItem) {
		// TODO Auto-generated method stub
		Session session=spendItemDao.getSessionFactory().openSession();
		//session.beginTransaction();
	 spendItemDao.save(spendItem);
	 session.beginTransaction().commit();
	  
	 session.close();
	}

	public void setSpendItem(SpendItem spendItem){
		 this.spendItem=spendItem;
	}
	public SpendItem setSpendItem(String ID_NUM,String ITEM_TYPE){
		spendItem.setIdNum(ID_NUM);
		spendItem.setItemType(ITEM_TYPE);
		 return spendItem;
	}
    public String getID_NUM(){
    	
    	try{
    		Integer maxId=getMaxId()+Integer.valueOf(1);
    		String id=String.valueOf(maxId);
    		return id;
    	}catch(NullPointerException nullExce){
    		return "0";
    	}
    	 
    	 
    }
    private Integer getMaxId() throws NullPointerException{
    	ArrayList<String> yearList=new ArrayList<String>();
		String command="select Max(to_number(ID_NUM)) from SPEND_ITEM";
		Session session=this.sessionFactory.openSession();
		SQLQuery query =session.createSQLQuery(command);
		List results=query.list();
		Integer num=0;
		for(int i=0;i<results.size();i++){
			num=Integer.valueOf(results.get(i).toString());
		}
		session.close();
		return num;
    }
	
}
