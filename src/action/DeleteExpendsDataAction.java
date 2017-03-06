package action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import bean.Expense;
import biz.ExpendsDataImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteExpendsDataAction extends ActionSupport{
       private String IdNumber;
       private String Result;
       private String errorMessage;
       private Expense expense;
       private ExpendsDataImp expenseDataImp;
       private String Name;
       private boolean isLogin;
       private boolean  isAvilideDate;
       public void setIdNumber(String IdNumber){
    	   this.IdNumber=IdNumber;
       }
       public void setName(String Name){
    	   this.Name=Name;
       }
       @JSON(name="Result")
       public String getResult(){
    	   return this.Result;
       }
       @JSON(name="Message")
       public String getMessage(){
    	   return this.errorMessage;
       }
       
       public void setExpense(Expense expense){
    	   this.expense=expense;
       }
       public void setExpenseDataImp(ExpendsDataImp expenseDataImp){
    	   this.expenseDataImp=expenseDataImp;
       }
       @Override
    public String execute() throws Exception {
    	// TODO Auto-generated method stub
    	  try{
    		  if(this.isLogin){
    	   System.out.println("Delete"+this.IdNumber);
    	   if(this.isAvilideDate){
    		   this.Result="ERROR";
    	   }else{
    	   this.expenseDataImp.delete(this.IdNumber);
    	   this.Result="OK";
    	   }
    		  }else{
    			  this.Result="ERROR";
    			  //this.errorMessage="error";
    			   
    		  }
    	  }catch(Exception e){
    		  this.Result="ERROR";
    		  this.errorMessage="error";
    	  }
    	return super.execute();
    }
       @Override
    public void validate() {
    	// TODO Auto-generated method stub
    	   this.isLogin=ActionContext.getContext().getSession().containsKey("account");
    	   isAvilideDate=false;
    	   this.expense=this.expenseDataImp.query(this.IdNumber);
    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
    	   try{
    		   Calendar today=Calendar.getInstance();
    		   Date d=this.expense.getExpenseDate();
    		   Date t=today.getTime();
			   Date t2=sdf.parse(today.get(today.YEAR)+"-"+(today.get(today.MONTH)+1)+"-"+"01");
			   isAvilideDate=t.before(d)||t2.after(d);
    	   }catch(Exception e){
    		   e.printStackTrace();
  			 isAvilideDate=true;
    	   }
    	   if(isAvilideDate){
				this.errorMessage="無效日期不能刪除";
				isAvilideDate=true;
			}
    	super.validate();
    }
}
