package biz;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;

public interface IProfitReportBiz {
	public void setSessionFactory(SessionFactory sessionFactory);
	public void getValidMonth();
	public  List<HashMap<String,Object>> getQueryByYear(String year);
	public BigDecimal getThisMonthIncome(String thisMonth);
	public BigDecimal getThisMonthExpense(String thisMonth);
	public BigDecimal getRemain(BigDecimal BThisMonthincome,BigDecimal BThisMonthExpense);
}
