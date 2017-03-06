package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.struts.action.ActionServlet;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import bean.ExpenseDAO;
import bean.Expense;
import biz.ExpendsDataImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExpendsDataAction extends ActionSupport {
	String Result;
	String errorMessage;
	 List<HashMap<String,Object>> Records;
	 HashMap<String,Object> data;
	 int jtStartIndex;
	 int jtPageSize;
	 String jtSorting;
	 ExpenseDAO expenseDao;
	 Expense expense;
	 ExpendsDataImp expendsDataImp;
	 String selectDate;
	 boolean isLogin;
	 public void setSelectDate( String selectDate){
		 this.selectDate=selectDate;
	 }
	 public void setExpendsDataImp(ExpendsDataImp expendsDataImp){
		 this.expendsDataImp=expendsDataImp;
	 }
	 public void setExpenseDao(ExpenseDAO expenseDao){
		 this.expenseDao=expenseDao;
	 }
	 public void setExpense(Expense expense){
		 this.expense=expense;
	 }
	 public void setJtStartIndex(int jtStartIndex){
		 this.jtStartIndex=jtStartIndex;
	 }
	 public void setJtPageSize(int jtPageSize){
		 this.jtPageSize=jtPageSize;
	 }
	 public void setJtSorting(String jtSorting){
		  this.jtSorting=jtSorting;
	 }
	 @JSON(name="Result")
	 public String  getResult(){
		  
		 return Result;
	 }
	 @JSON(name="Records")
	 public List<HashMap<String,Object>> getRecords(){
		 return Records;
	 }
	 @JSON(name="Message")
	 public String getMessage(){
		 return this.errorMessage;
	 }
	 
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
		if(this.isLogin){
		setRecords();
		System.out.println(this.jtPageSize);
		System.out.println(this.jtSorting);
		System.out.println(this.selectDate);
		this.Result="OK";
		this.isLogin=false;
		this.errorMessage="";
		}else{
			this.Records=null;
			 
			this.isLogin=false;
			this.Result="ERROR";
			this.errorMessage="請先登入";
		}
		}catch(Exception e){
			this.Result="ERROR";
			this.errorMessage="ERROR";
		}
		return super.execute();
	}
	
	private void setRecords() throws Exception{
		 
		Records=new ArrayList<HashMap<String,Object>>();
		
		//Records=this.expendsDataImp.getQueryAll();
		Records=this.expendsDataImp.getQueryByMonth(selectDate);
		this.selectDate="";
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
		super.validate();
	}
}
