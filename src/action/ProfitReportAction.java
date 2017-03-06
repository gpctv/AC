package action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.json.annotations.JSON;

import biz.ProfitBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProfitReportAction  extends ActionSupport{
	ArrayList<String> Month;
	ArrayList<BigDecimal> data;//remain
	ArrayList<BigDecimal> data2;//income
	ArrayList<BigDecimal> data3;//expense
	boolean isLogin;
	String monthSelect;
	ProfitBiz profitBiz;
	public void setProfitBiz(ProfitBiz profitBiz){
		
		this.profitBiz=profitBiz;
	}
	public void setMonthSelect(String monthSelect){
		this.monthSelect=monthSelect;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			if(this.isLogin){
				System.out.println(this.monthSelect);
				setData();
				setMonth();
				setData2();//income
				setData3();
					}else{
						this.Month=null;
						this.data=null;
						 this.data2=null;
						 this.data3=null;
					}
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.execute();
	}
	private void setData(){
		Calendar thisYear=Calendar.getInstance();
		thisYear.add(thisYear.MONTH, -1);
		Integer M=(thisYear.get(thisYear.MONTH)+1);
		 String Month=String.valueOf(M);
		if(M<10){
			Month="0"+Month;
		}
		if(this.monthSelect==null||this.monthSelect.equals("undefined")){
			
			String year=String.valueOf(thisYear.get(thisYear.YEAR));
			this.data=this.profitBiz.getRemain(year+"/"+Month);
		}else{
			 
			this.data=this.profitBiz.getRemain(this.monthSelect );
		}
	}
	private void setData2(){
		Calendar thisYear=Calendar.getInstance();
		thisYear.add(thisYear.MONTH, -1);
		Integer M=(thisYear.get(thisYear.MONTH)+1);
		 String Month=String.valueOf(M);
		if(M<10){
			Month="0"+Month;
		}
		if(this.monthSelect==null||this.monthSelect.equals("undefined")){
			
			String year=String.valueOf(thisYear.get(thisYear.YEAR));
			this.data2=this.profitBiz.getIncome(year+"/"+Month);
		}else{
			 
			this.data2=this.profitBiz.getIncome(this.monthSelect );
		}
	}
	private void setData3(){
		Calendar thisYear=Calendar.getInstance();
		thisYear.add(thisYear.MONTH, -1);
		Integer M=(thisYear.get(thisYear.MONTH)+1);
		 String Month=String.valueOf(M);
		if(M<10){
			Month="0"+Month;
		}
		if(this.monthSelect==null||this.monthSelect.equals("undefined")){
			
			String year=String.valueOf(thisYear.get(thisYear.YEAR));
			this.data3=this.profitBiz.getExpense(year+"/"+Month);
		}else{
			 
			this.data3=this.profitBiz.getExpense(this.monthSelect );
		}
	}
	private void setMonth(){
		Calendar thisYear=Calendar.getInstance();
		Integer M=(thisYear.get(thisYear.MONTH)+1);
		 String Month=String.valueOf(M);
		if(M<10){
			Month="0"+Month;
		}
if(this.monthSelect==null||this.monthSelect.equals("undefined")){
			
			String year=String.valueOf(thisYear.get(thisYear.YEAR));
			this.Month=this.profitBiz.getMonth(year+"/"+Month);
		}else{
			 
			this.Month=this.profitBiz.getMonth(this.monthSelect);
		}
	}
	@JSON(name="data")
	public ArrayList<BigDecimal> getData(){
		return this.data;
	}
	@JSON(name="Month")
	public ArrayList<String> getMonth(){
		return this.Month;
	}
	@JSON(name="data2")//income
	public ArrayList<BigDecimal> getIncome(){
		return this.data2;
	} 
	@JSON(name="data3")//expense
	public ArrayList<BigDecimal> getExpense(){
		return this.data3;
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
	this.isLogin=ActionContext.getContext().getSession().containsKey("account");
	 
		super.validate();
	}
}
