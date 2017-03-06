package action;

import java.util.ArrayList;

import biz.ProfitBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProfitReportSelectAction extends ActionSupport {
	ArrayList<String> yearMonth;
	boolean isLogin;
	ProfitBiz profitBiz;
	public void setProfitBiz(ProfitBiz profitBiz){
		
		this.profitBiz=profitBiz;
	}
	public ArrayList<String> getYearMonth(){
		if(yearMonth==null){
			this.yearMonth=new ArrayList<String>();
			yearMonth.add("N/A");
		}
		return yearMonth;
	}
	public void setYearMonth(){
		
		this.yearMonth=this.profitBiz.getValidDate();
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
		String test=super.execute();
		return test;
	}
	@Override
    public void validate() {
    	// TODO Auto-generated method stub
    	this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
    	super.validate();
    }
}
