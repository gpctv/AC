package biz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MonthReportBiz {
	private ArrayList<String> validDay;
    private ArrayList<BigDecimal> validData;
	 SessionFactory sessionFactory;
	 List<HashMap<String,Object>> findData;
	 public void setSessionFactory(SessionFactory sessionFactory) {
			// TODO Auto-generated method stub
			this.sessionFactory=sessionFactory;
		}
	 public ArrayList<String> validYMonth(){
			String command="select to_char(expense_date,'yyyy/MM') expense_date "+
							"from expense "+
							"group by to_char(expense_date,'yyyy/MM') order by   expense_date desc";
			Session session=this.sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery(command);
			try{
			List result=query.list();
			ArrayList<String> Years=new ArrayList<String>();
			for(int i=0;i<result.size();i++){
				Years.add(result.get(i).toString());
			}
			return Years;
			}catch(Exception e){
				ArrayList<String> Years=new ArrayList<String>();
				Years.add("NA");
				return null;
			}finally{
				session.close();
			}
			
			
		}
	 public void setQueryDayNDate(String month){
		 String command="select sum(ITEM_PRICE),to_char(EXPENSE_DATE,'yyyy-MM-DD') "
				 		+"from expense "
				 		+"where to_char(expense_date,'yyyy/mm')=:month "
				 		+"group by Expense_date "
				 		+"order by expense_date "
				 		+"";
		
		 Session session;
	
			 session=this.sessionFactory.openSession();
		
		 SQLQuery query=session.createSQLQuery(command);
		 try{
		 query.setParameter("month", month);
			List<Object[]> results=query.list();
			validDay=new ArrayList<String>();
			validData=new ArrayList<BigDecimal>();
			validDay.clear();
			validData.clear();
			for(int i=0;i<results.size();i++){
				validDay.add(results.get(i)[1].toString());
				validData.add(BigDecimal.valueOf(Double.valueOf(results.get(i)[0].toString())));
			}
		 }catch(Exception e){
			 System.out.println(e);
		 }finally{
			 session.close();
		 }
			
	 }
	 public ArrayList<String>  getValidDay(){
		 return this.validDay;
	 }
	 public ArrayList<BigDecimal> getValidData(){
		 return this.validData;
	 }
	
	 
}
