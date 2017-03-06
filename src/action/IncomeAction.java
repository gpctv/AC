package action;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import biz.IncomeBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IncomeAction extends ActionSupport{
	private TreeMap<String, String> validDate;
	private String  sDate;
	private String selectDate;
	private IncomeBizImp incomeBizImp;
	boolean isLogin;
	public void setIncomeBizImp(IncomeBizImp incomeBizImp){
		this.incomeBizImp=incomeBizImp;
	}
	 public void setSelectDate(String selectDate){
		 this.selectDate=selectDate;
	 }
	public TreeMap<String, String> getSelectDate(){
		if(validDate==null){
			this.validDate=new TreeMap<String, String>();
			validDate.put("N/A", "N/A");
		}
		return validDate;
	}
	
	public void setSdate(String sDate){
		this.sDate=sDate;
	}
	 
	 @Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		 if(this.isLogin){
		 validDateGet();
		 }else{
			 this.validDate=new TreeMap<String, String>();
				validDate.put("N/A", "N/A");
		 }
		 
		return super.execute();
	} 
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		
		if(this.sDate==null||this.sDate.equals("")){
			 this.sDate=thisMonth();
			
		}else{
			System.out.println(this.sDate);
			 
		}
		super.validate();
	}
	private void validDateGet(){
		this.validDate=this.incomeBizImp.getValidDate();
		 
	}
	private String thisMonth(){
		Calendar d=Calendar.getInstance();
		 int m=d.get(d.MONTH)+1;
		 String month;
		 if(m<10){
			 month="0"+String.valueOf(m);
		 }else{
			 month=String.valueOf(m);
		 }
		 return month;
	}
	
	
}
