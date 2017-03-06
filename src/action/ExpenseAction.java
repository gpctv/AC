package action;

import java.util.ArrayList;
import java.util.HashMap;

import biz.ExpendsDataImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExpenseAction extends ActionSupport{
	HashMap<String,String> validDate;
	HashMap<String,String>  Date;
	String defaultMonth;
	ExpendsDataImp expenseDataImp;
	boolean isLogin;
	public void setExpenseDataImp(ExpendsDataImp expenseDataImp){
		this.expenseDataImp=expenseDataImp;
	}
	public HashMap<String,String> getValidDate(){
		if(validDate==null){
			this.validDate=new HashMap<String, String>();
			validDate.put("N/A", "");
		}
		return validDate;
	}
	 
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(this.isLogin){
		SelectDateset();
		}else{
			this.validDate=new HashMap<String, String>();
			validDate.put("N/A", "");
		}
		return super.execute();
	}
	
	private HashMap<String,String> SelectDateset(){
		this.validDate=this.expenseDataImp.getValidMonth();
	    return this.validDate;
		 
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
		super.validate();
	}
}
