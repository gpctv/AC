package action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.json.annotations.JSON;

import biz.YearReportBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class YearReportAction extends ActionSupport{
ArrayList<String> month;
ArrayList<BigDecimal> data;
 
boolean isLogin;
String yearSelect;
YearReportBiz yearReportBiz;
public void setYearSelect(String yearSelect){
	this.yearSelect=yearSelect;
}
public void setYearReportBiz(YearReportBiz yearReportBiz){
	this.yearReportBiz=yearReportBiz;
}
@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	try{
		if(this.isLogin){
	System.out.println(this.yearSelect);
	ValueSeting();
		}else{
			this.month=null;
			this.data=null;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
		return super.execute();
	}
	private void ValueSeting(){
		if(this.yearSelect==null||this.yearSelect.equals("undefined")){
		 Calendar thisYear=Calendar.getInstance();
			month=this.yearReportBiz.getMonth(String.valueOf(thisYear.get(thisYear.YEAR)));
			this.data=this.yearReportBiz.getData();
		}else{
			month=this.yearReportBiz.getMonth(this.yearSelect);
			this.data=this.yearReportBiz.getData();
		}
		
	}
	@JSON(name="month")
	public ArrayList<String> getMonth(){
		return month;
	}
	@JSON(name="data")
	public ArrayList<BigDecimal> getData(){
		return data;
	}
	@Override
		public void validate() {
			// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
			super.validate();
		}
}
