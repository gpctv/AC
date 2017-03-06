package biz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import bean.Profit;
import bean.ProfitDAO;
import bean.ProfitId;

public class ProfitBiz {
	 SessionFactory sessionFactory;
	  ProfitDAO profitDao;
	 Profit profit;
	 ProfitId profitId;
	 ArrayList<String> monthMap;
	 public void setSessionFactory(SessionFactory sessionFactory) {
			// TODO Auto-generated method stub
			this.sessionFactory=sessionFactory;
		}
	 public void setProfitDao(ProfitDAO profitDao){
		 this.profitDao=profitDao;
	 }
	 public void setProfit(Profit profit){
		 this.profit=profit;
	 }
	 public void setProfitId(ProfitId profitId){
		 this.profitId=profitId;
	 }
	 public ArrayList<BigDecimal> getRemain(String yearMM){
		 String command="select profit_remain  "+
				 		"from profit "+
				 "where  to_char(profit_date,'yyyy/MM')<= :yearMM "+
				 "and to_char(profit_date,'yyyy-MM')not" +
				 " in(to_char(sysdate,'yyyy-MM'))  "+
				 "order by profit_date asc"
				 ;	
		 Session session=this.sessionFactory.openSession();
		 SQLQuery query=session.createSQLQuery(command);
		 
		 query.setParameter("yearMM", yearMM);
		 try{
				List result=query.list();
				ArrayList<BigDecimal> d=new ArrayList<BigDecimal>();
				 d.clear();
				 
				
				for(int i=0;i<result.size();i++){
					d.add(BigDecimal.valueOf(Double.valueOf(result.get(i).toString())));
					 
				}
				return d ;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally{
				session.close();
			}
				 		
	 }
	 public ArrayList<String> getMonth(String yearMM){
		 String command="select to_char(profit_date,'yyyy-MM') month  "+
				 		"from profit "+
				 "where  to_char(profit_date,'yyyy/MM') <= :yearMM "+
				 "and to_char(profit_date,'yyyy-MM')not" +
				 " in(to_char(sysdate,'yyyy-MM'))  "+
				 "order by profit_date asc"
				 ;	
		 Session session=this.sessionFactory.openSession();
		 SQLQuery query=session.createSQLQuery(command);
		 this.monthMap=new ArrayList<String>();
		 this.monthMap.clear();
		 query.setParameter("yearMM", yearMM);
		 try{
				List result=query.list();
				ArrayList<String> d=new ArrayList<String>();
				 d.clear();
				 
				
				for(int i=0;i<result.size();i++){
					d.add(result.get(i).toString());
					 this.monthMap.add(result.get(i).toString());
				}
				return d ;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally{
				session.close();
			}
				 		
	 }
	 public ArrayList<BigDecimal> getIncome(String yearMM){
		 String command="select sum(amount) ,month " +
		 		"from( " +
		 		"select amount,to_char(income_date,'yyyy-mm') month " +
		 		"from income " +
		 		"where to_char(income_date,'yyyy/MM')<=:yearMM  " +
		 		"and to_char(income_date,'yyyy-MM') " +
		 		"not in(to_char(sysdate,'yyyy-MM')) " +
		 		") " +
		 		"group by month " +
		 		" order by month asc";
		 Session session=this.sessionFactory.openSession();
		 SQLQuery query=session.createSQLQuery(command);
		 query.setParameter("yearMM", yearMM);
		 
		 try{
			 List<Object[]> result=query.list();
			 ArrayList<BigDecimal> d=new ArrayList<BigDecimal>();
			 HashMap<String,BigDecimal> m=new HashMap<String, BigDecimal>();
			 m.clear();
			 d.clear();
			 for(int i=0;i<result.size();i++){
				m.put(result.get(i)[1].toString(), BigDecimal.valueOf(Double.valueOf(result.get(i)[0].toString())));
			 }
			 
			 for(int i=0;i<this.monthMap.size();i++){
				 
				boolean isExitMonth=m.containsKey(this.monthMap.get(i));
				if(isExitMonth){
					d.add(m.get(this.monthMap.get(i)));
				}else{
					d.add(new BigDecimal(0));
				}
				
				 
				}
				return d ;
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }finally{
			 session.close();
		 }
	 }
	 public ArrayList<BigDecimal> getExpense(String yearMM){
		 String command="select sum(item_price),month " +
		 		"from( " +
		 		"select item_price,to_char(expense_date,'yyyy-mm') month " +
		 		"from expense " +
		 		"where to_char(EXPENSE_DATE,'yyyy/MM') <=:yearMM " +
		 		"and to_char(expense_date,'yyyy-mm') not in " +
		 		"(to_char(sysdate,'yyyy-MM')) " +
		 		") " +
		 		"group by month " +
		 		"order by month";
		 Session session=this.sessionFactory.openSession();
		 SQLQuery query=session.createSQLQuery(command);
		 query.setParameter("yearMM", yearMM);
		 try{
			 List<Object[]> result=query.list();
			 ArrayList<BigDecimal> d=new ArrayList<BigDecimal>();
			 HashMap<String,BigDecimal> m=new HashMap<String, BigDecimal>();
			 m.clear();
			 d.clear();
			 for(int i=0;i<result.size();i++){
					m.put(result.get(i)[1].toString(), BigDecimal.valueOf(Double.valueOf(result.get(i)[0].toString())));
				 }
			 for(int i=0;i<this.monthMap.size();i++){
				 
					boolean isExitMonth=m.containsKey(this.monthMap.get(i));
					if(isExitMonth){
						d.add(m.get(this.monthMap.get(i)));
					}else{
						d.add(new BigDecimal(0));
					}
					
					 
					}
					return d ;
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }finally{
			 session.close();
		 }
		 
	 }
	 public BigDecimal getProfite(String Date){
		 String command="select nvl(income,0)-nvl(expense,0) "+
				 		"from  (select sum(Amount) income from income "+
				 		"where to_char(income_date,'YYYY/MM')=:SDate), (select sum(item_price) expense "+
				 		"from expense where to_char(expense_date,'yyyy/MM')=:SDate)";
		 Session session=this.sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery(command);
			query.setParameter("SDate", Date);
			try{
				List result=query.list();
				ArrayList<BigDecimal> d=new ArrayList<BigDecimal>();
				for(int i=0;i<result.size();i++){
					d.add(BigDecimal.valueOf(Double.valueOf(result.get(i).toString())));
					
				}
				return d.get(0);
			}catch(Exception e){
				e.printStackTrace();
				return new BigDecimal(0);
			}finally{
				session.close();
			}
			
	 }
	 public BigDecimal getLastMonthProfit(String YYYYMM){
		 String command="select PROFIT_REMAIN  "+
			 		"from profit "+
			 		"where to_char(PROFIT_DATE,'yyyy/mm')=:YYYYMM "+
			 		" ";
	 Session session=this.sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery(command);
		query.setParameter("YYYYMM", YYYYMM);
		try{
			List result=query.list();
			ArrayList<BigDecimal> d=new ArrayList<BigDecimal>();
			for(int i=0;i<result.size();i++){
				d.add(BigDecimal.valueOf(Double.valueOf(result.get(i).toString())));
				
			}
			return d.get(0);
		}catch(Exception e){
			e.printStackTrace();
			return new BigDecimal(0);
		}finally{
			session.close();
		}
	 }
	 public void insertProfit(Date Date,BigDecimal Profit_amount,BigDecimal Profit_remain,String ID_NUM ){
		 Session session =this.profitDao.getSessionFactory().openSession();
	     try{
		 this.profitId.setIdNum(ID_NUM);
		 this.profitId.setProfitDate(Date);
		 this.profit.setId(this.profitId);
		 this.profit.setProfitAmount(Profit_amount);
		 this.profit.setProfitRemain(Profit_remain);
		 this.profitDao.save(this.profit);
		 session.beginTransaction().commit();
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }finally{
	    	 session.close();
	     }
		 
	 }
	 public String getMaxID(){
		 String num="0";
		 String command="select max(to_number(ID_num)) from PROFIT";
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
		 }catch(NullPointerException e){
			 return num="0";
		 }finally{
				session.close();
			}
		 
	 }
	 public void update(Date Date,BigDecimal Profit_amount,BigDecimal Profit_remain,String ID_NUM){
		 Session session =this.profitDao.getSessionFactory().openSession();
		 try{
			 this.profitId.setIdNum(ID_NUM);
			 this.profitId.setProfitDate(Date);
			 this.profit.setId(this.profitId);
			 this.profit.setProfitAmount(Profit_amount);
			 this.profit.setProfitRemain(Profit_remain);
			
			 this.profitDao.merge(this.profit);
			 session.beginTransaction().commit();
			 
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 session.close();
		 }
	 }
	 /**
	  * 
	  * @param date(yyyy/MM)
	  */
	 public boolean isExist(String date){
		 String command="select count(ID_num) from PROFIT "+
				 		"where to_char(Profit_date,'YYYY/MM')=:SDate";
		 Session session=this.sessionFactory.openSession();
		 String num="0";
		 try{
			 SQLQuery query=session.createSQLQuery(command);
			 query.setParameter("SDate", date);
				List result=query.list();
				for(int i=0;i<result.size();i++){
					num=result.get(i).toString();
				}
				Integer temp=Integer.valueOf(num) ;
				 
				if(temp>Integer.valueOf(0)){
					return true;
				}else{
					return false;
				}
				 
		 }catch(NullPointerException e){
			 return false;
		 }
		  catch(Exception e){
			 e.printStackTrace();
			 return false;
		 }finally{
			 session.close();
		 }
	 }
	 public String getID(String date){
		 String command="select ID_num from PROFIT "+
			 		"where to_char(Profit_date,'YYYY/MM')=:SDate";
	 Session session=this.sessionFactory.openSession();
	 String num="0";
	 try{
		 SQLQuery query=session.createSQLQuery(command);
		 query.setParameter("SDate", date);
			List result=query.list();
			for(int i=0;i<result.size();i++){
				num=result.get(i).toString();
			}
			Integer temp=Integer.valueOf(num) ;
			 
			 return temp.toString();
	 }catch(Exception e){
		 e.printStackTrace();
		 return Integer.valueOf(0).toString();
	 }finally{
		 session.close();
	 }
	 }
	 
	  
	 public ArrayList<String> getValidDate( ){
		 String command="select to_char(profit_date,'yyyy/MM') from profit " +
		 		" where to_char(profit_date,'yyyy/MM') < :date " +
		 		" order by profit_date desc";
		 Session session=this.sessionFactory.openSession();
		 try{
			 SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM");
			 Calendar c=Calendar.getInstance();
			 String thisMonth=format1.format(c.getTime());
			 SQLQuery query=session.createSQLQuery(command);
			  query.setParameter("date",thisMonth );
			 List results=query.list();
			 ArrayList<String> queryData=new ArrayList<String> ();
			 queryData.clear();
			 for(int i=0;i<results.size();i++){
				queryData.add(results.get(i).toString()); 
			 }
			 return queryData;
		 }catch(Exception e){
			 ArrayList<String> queryData=new ArrayList<String> ();
			 e.printStackTrace();
			 return queryData;
		 }finally{
			 session.close();
		 }
	 }
}
