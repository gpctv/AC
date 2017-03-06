package biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class YearReportBiz {
     private ArrayList<String> validMonth;
     private ArrayList<BigDecimal> validData;
	 SessionFactory sessionFactory;
	 public void setSessionFactory(SessionFactory sessionFactory) {
			// TODO Auto-generated method stub
			this.sessionFactory=sessionFactory;
		}
	public ArrayList<String> validYear(){
		String command="select to_char(expense_date,'yyyy') "+
						"from expense "+
						"group by to_char(expense_date,'yyyy') ";
		Session session =this.sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery(command);
		List result=query.list();
		ArrayList<String> Years=new ArrayList<String>();
		for(int i=0;i<result.size();i++){
			Years.add(result.get(i).toString());
		}
		session.close();
		return Years;
	}
	private void setMonthNData(String year){
		String command="select sum(item_price),month "
						+"from( "
						+" select item_price,to_char(expense_date,'mm')||'月' month  "
						+" from expense "
						+"where to_char(EXPENSE_DATE,'yyyy')=:year ) group by month "
						+"order by month" ;
		Session session =this.sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery(command);
		query.setParameter("year", year);
		List<Object[]> results=query.list();
		validMonth=new ArrayList<String>();
		validData=new ArrayList<BigDecimal>();
		validMonth.clear();
		validData.clear();
		for(int i=0;i<results.size();i++){
			validMonth.add(results.get(i)[1].toString());
			validData.add(BigDecimal.valueOf(Double.valueOf(results.get(i)[0].toString())));
		}
		session.close();
	}
	public ArrayList<String> getMonth(String year){
		setMonthNData(year);
		return this.validMonth;
	}
	public ArrayList<BigDecimal> getData(){
		return this.validData;
	}
	
}
