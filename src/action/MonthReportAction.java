package action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.json.annotations.JSON;

import biz.MonthReportBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MonthReportAction extends ActionSupport{
	ArrayList<String> day;
	ArrayList<BigDecimal> data;
	boolean isLogin;
	String monthSelect;
	MonthReportBiz monthReportBiz;
	public void setMonthSelect(String monthSelect){
		this.monthSelect=monthSelect;
	}
	public void setMonthReportBiz(MonthReportBiz monthReportBiz){
		this.monthReportBiz=monthReportBiz;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			if(this.isLogin){
		System.out.println(this.monthSelect);
		setData();
			}else{
				this.day=null;
				this.data=null;
			}
		}catch(Exception e){
			
		}
		return super.execute();
	}
	
	private void setData(){
		if(this.monthSelect==null||this.monthSelect.equals("undefined")){
			Calendar thisYear=Calendar.getInstance();
			 Integer M=(thisYear.get(thisYear.MONTH)+1);
			 String Month=String.valueOf(M);
			if(M<10){
				Month="0"+Month;
			}
			String YM=String.valueOf(thisYear.get(thisYear.YEAR))+"/"+Month;
			this.monthReportBiz.setQueryDayNDate(YM);
			this.day=this.monthReportBiz.getValidDay();
			this.data=this.monthReportBiz.getValidData();
		}else{
			this.monthReportBiz.setQueryDayNDate(this.monthSelect);
			this.day=this.monthReportBiz.getValidDay();
			this.data=this.monthReportBiz.getValidData();
		}
	}
	
	@JSON(name="Day")
	public ArrayList<String> getDay(){
		return this.day;
	}
	@JSON(name="data")
	public ArrayList<BigDecimal> getData(){
		return this.data;
	}
	@Override
		public void validate() {
			// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
			super.validate();
		}
	
}
