package action;

import java.util.ArrayList;

import biz.YearReportBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class YearReportSelectAction extends ActionSupport{
	ArrayList<String> years;
	YearReportBiz yearReportBiz;
	boolean isLogin;
	public void setYearReportBiz(YearReportBiz yearReportBiz){
		this.yearReportBiz=yearReportBiz;
	}
	public ArrayList<String> getYears(){
		if(years==null){
			this.years=new ArrayList<String>();
			years.add("n/a");
		}
		return years;
	}
	public void setYears(){
		this.years=this.yearReportBiz.validYear();
	}
    @Override
    public String execute() throws Exception {
    	// TODO Auto-generated method stub
    	try{
    		if(this.isLogin){
    	setYears();
    		}else{
    			this.years=new ArrayList<String>();
    			years.add("n/a");
    		}
    	}catch(Exception e){
    		System.out.println("未登入");
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
