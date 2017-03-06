package action;

import java.util.ArrayList;

import biz.MonthReportBiz;
import biz.YearReportBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MonthReportSelectAction extends ActionSupport{
	ArrayList<String> yearMonth;
	MonthReportBiz monthReportBiz;
	
	boolean isLogin;
	public void setMonthReportBiz(MonthReportBiz monthReportBiz){
		this.monthReportBiz=monthReportBiz;
	}
	public ArrayList<String> getYearMonth(){
		if(yearMonth==null){
			this.yearMonth=new ArrayList<String>();
			yearMonth.add("N/A");
		}
		return yearMonth;
	}
	public void setYearMonth(){
		this.yearMonth=this.monthReportBiz.validYMonth();
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			if(this.isLogin){
			setYearMonth();
			}else{
				yearMonth=null;
			}
	    	}catch(Exception e){
	    		System.out.println(e);
	    	}
		return super.execute();
	}
	@Override
    public void validate() {
    	// TODO Auto-generated method stub
    	this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
    	super.validate();
    }
}
