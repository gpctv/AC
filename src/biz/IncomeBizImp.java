package biz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bean.Expense;
import bean.Income;
import bean.IncomeDAO;

public class IncomeBizImp implements IIncomeBiz{
	SessionFactory sessionFactory;
	Income income;
	IncomeDAO incomeDao;
 
	List<HashMap<String,Object>> findData;
 
	public void setIncome(Income income){
		this.income=income;
	}
    public void setIncomeDao(IncomeDAO incomeDao){
    	this.incomeDao=incomeDao;
    }
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory=sessionFactory;
	}

	@Override
	public void insertData(Income income) {
		// TODO Auto-generated method stub
		Session session=this.incomeDao.getSessionFactory().openSession();
		
		this.incomeDao.save(income);
		session.beginTransaction().commit();
		session.close();
		
	}

	@Override
	public void update(String id, Income income) {
		// TODO Auto-generated method stub
		System.out.println("income Update"+id);
		Session session=this.sessionFactory.openSession();
		this.incomeDao.merge(income); 
		session.beginTransaction().commit();
		session.close();
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.openSession();
		
		this.income.setIdNum(id);
		this.incomeDao.delete(this.income);
		session.beginTransaction().commit();
		session.close();
	}

	@Override
	public Income query(String id) {
		// TODO Auto-generated method stub
		this.income=this.incomeDao.findById(id);
		return this.income;
			}
	public String getIdNum(){
		String num="0";
		 Session session=this.sessionFactory.openSession();
		try{
			String command="select max(to_number(id_num)) from income";
			  
			SQLQuery query=session.createSQLQuery(command);
			List result=query.list();
			
			for(int i=0;i<result.size();i++){
				num=result.get(i).toString();
			}
			Integer temp=Integer.valueOf(num)+Integer.valueOf("1");
			num=temp.toString();
			
			return num;
			}catch (NullPointerException e){
				 
				return num="0";
			}finally{
				session.close();
			}
	}
	public List<HashMap<String,Object>> getQueryAll(String month){
		
			this.findData=new ArrayList<HashMap<String,Object>>();
			Session session=this.sessionFactory.openSession();
			try{
			session.beginTransaction();
		this.findData.clear();
		String command="select * "+
	               " from income "+
	               " where to_char(INCOME_DATE,'yyyy/mm')= :month order by INCOME_DATE "+
	               "  ";
		SQLQuery query=session.createSQLQuery(command);

		Calendar c=Calendar.getInstance();
		String month2=String.valueOf(c.get(c.MONTH)+1);
		String thisYear=String.valueOf(c.get(c.YEAR));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(Integer.valueOf(month2)<10){
			month2="0"+month2;
		}
         if(month==null||month.equals("")){
			
			query.setParameter("month", thisYear+"/"+month2);
		}else{
			
		query.setParameter("month", month);
		}
         List<Object[]> results=query.list();
         for(int i=0;i<results.size();i++){
 			HashMap<String,Object> queryData=new HashMap<String,Object>();
 			queryData.put("Amount", results.get(i)[0]);
 			queryData.put("Income_date",   sdf.format(sdf.parse(results.get(i)[1].toString())) );
 			queryData.put("Account",results.get(i)[2].toString());
 			queryData.put("Id_num", results.get(i)[3]); 
 			findData.add(queryData);
 			System.out.println(queryData.get("Account"));
 			
         }
          
         return findData;
		}catch(Exception e){
			 
			return findData;
		}finally{
			session.close();
		}
	}
	public TreeMap<String ,String> getValidDate(){
		
		Session session=this.sessionFactory.openSession();
		try{
		session.beginTransaction();
		String command="select d "+
		               " from(select to_char(income.INCOME_DATE,'yyyy/mm') d "+
		               " from income) d"+
		               " group by d  ";
		SQLQuery query=session.createSQLQuery(command);
		List result=query.list();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
		TreeMap<String ,String> sDate=new TreeMap<String ,String>();
		sDate.clear();
		for(int i=0;i<result.size();i++){
			 
			sDate.put(result.get(i).toString(), result.get(i).toString());
		}
		 
		return sDate;
		}catch(Exception e){
			
			return null;
		}finally{
			session.close();
		}
		 
	}
	public ArrayList<HashMap<String, Object>> AccountSelect(){
	
			ArrayList<HashMap<String, Object>> itemType=new ArrayList<HashMap<String, Object>>();
			Session session=this.sessionFactory.openSession();
			try{
			session.beginTransaction();
			String command="select account from account ";
			SQLQuery query=session.createSQLQuery(command);
			List result=query.list();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
			  for(int i=0;i<result.size();i++){
				  HashMap<String, Object> itemMap=new HashMap<String, Object>();
					itemMap.put("DisplayText", result.get(i).toString());
					itemMap.put("Value",result.get(i).toString() );
					itemType.add(itemMap);
			 }
			   
			 return itemType;
		}catch(Exception e){
		
			return null;
		}finally{
			session.close();
		}
	}
	public BigDecimal sumIncome(String month){
		String num="0";
		 Session session=this.sessionFactory.openSession();
		try{
			String command="select  sum(Amount) from income where to_char(income_date,'YYYY/MM')=:month ";
			SQLQuery query=session.createSQLQuery(command);
			query.setParameter("month", month);
			List result=query.list();
			 
			for(int i=0;i<result.size();i++){
				num=result.get(i).toString();
			}
			return new BigDecimal(num);
		}catch(Exception e){
			e.printStackTrace();
			return new BigDecimal(num);
		}finally{
			session.close();
			
		}
	}

}
