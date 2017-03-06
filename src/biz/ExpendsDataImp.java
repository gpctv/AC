package biz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import bean.Expense;
import bean.ExpenseDAO;

public class ExpendsDataImp implements IExpendsData{
       SessionFactory sessionFactory;
       Expense expense;
       ExpenseDAO expenseDao;
       List<HashMap<String,Object>> findData;
       public void setExpense(Expense expense){
    	   this.expense=expense;
       }
       public void setExpenseDao(ExpenseDAO expenseDao){
    	   this.expenseDao=expenseDao;
       }
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory=sessionFactory;
	}

	@Override
	public void insertData(Expense expense) {
		// TODO Auto-generated method stub
		Session session=this.expenseDao.getSessionFactory().openSession();
		
		this.expenseDao.save(expense);
		session.beginTransaction().commit();
		
	session.close();
	}

	@Override
	@Transactional
	public void update(String id,Expense expense) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.openSession();
		
		try{
		/*String command="UPDATE  Expense "+
						"set expenseDate=to_date(:ExpneseDate,\'YYYY/MM/DD\') "+
				        "itemNum=:itemNum "+
						"itemName=:itemName "+
				        "itemPrice=:itemPrice "+
				        "itemType=:itemType "+
				        "WHERE idNum=:idNum "+
				        "";
		Query update=this.sessionFactory.openSession().createQuery(command);
		update.setParameter("ExpneseDate", expense.getExpenseDate());
		update.setParameter("itemNum", expense.getItemNum());
		update.setParameter("itemName", expense.getItemName());
		update.setParameter("itemPrice", expense.getItemPrice());
		update.setParameter("itemType", expense.getItemType());
		update.setParameter("idNum", expense.getIdNum());
		int i=update.executeUpdate();*/
		System.out.println("update--");
		this.expenseDao.merge(expense);
		session.beginTransaction().commit();
		
		}finally{
			session.close();
		}
		
	}

	@Override
	
	public void delete(String id) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.openSession();
		try{
			System.out.println(id);
		 this.expense.setIdNum(id);
		this.expenseDao.delete(this.expense);
		session.beginTransaction().commit();
		  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	@Override
	public Expense query(String id) {
		// TODO Auto-generated method stub
		this.expense=this.expenseDao.findById(id);
		
		return this.expense;
	}
	
	public List<HashMap<String,Object>> getQueryByMonth(String month) throws Exception{
		this.findData=new ArrayList<HashMap<String,Object>>();
		String command="select *  from expense where to_char(expense_date,'yyyy/mm') = :month order by expense_date ";
		Session session=this.sessionFactory.openSession();
		try{
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
		 findData.clear();
		for(int i=0;i<results.size();i++){
			HashMap<String,Object> queryData=new HashMap<String,Object>();
			queryData.put("ItemType", results.get(i)[0]);
			queryData.put("IdNumber",  results.get(i)[1]);
			queryData.put("Time", sdf.format(sdf.parse(results.get(i)[2].toString())) );
			queryData.put("Num", results.get(i)[3]);
			queryData.put("Name", results.get(i)[4]);
			queryData.put("Price", results.get(i)[5]);
			findData.add(queryData);
			System.out.println(sdf.format(sdf.parse(results.get(i)[2].toString())));
		}
		}finally{
			session.close();
		}
		
		
		return findData;
	}
	public String getIDNum(){
	String num="0";

	String command="select max(to_number(id_num)) from expense";
	Session session=this.sessionFactory.openSession();
	try{
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
	public HashMap<String,String> getValidMonth(){
		String command="select d "+
						"from ( "+
						"select to_char(expense.expense_date,'yyyy/mm') d "+
						"from expense) "+
						"group by d "+
						""+
						"" ;
		HashMap<String,String> month=new HashMap<String,String>();
		Session session=this.sessionFactory.openSession();
		try{
		SQLQuery query=session.createSQLQuery(command);
		List result=query.list();
		for(int i=0;i<result.size();i++){
			month.put(result.get(i).toString(), result.get(i).toString());
			
		}
		}catch(Exception e){
			month.clear();
			month.put("", "");
			return month;
		}finally{
			session.close();
		}
		
			return month;	
	}
	
	
}
