package action;

import org.apache.struts2.json.annotations.JSON;

import bean.SpendItem;
import biz.SpendTypeImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteSpendType extends ActionSupport{
	
	 private String IdNumber;
     private String Result;
     private String errorMessage;
     private SpendItem spendItem;
     private SpendTypeImp spendTypeImp;
     private boolean isLogin;
     public void setSpendItem(SpendItem spendItem){
    	 this.spendItem=spendItem;
     }
     public void setSpendTypeImp(SpendTypeImp spendTypeImp){
    	 this.spendTypeImp=spendTypeImp;
     }
     public void setIdNumber(String IdNumber){
    	 this.IdNumber=IdNumber;
     }
     @JSON(name="Result")
     public String getResult(){
  	   return this.Result;
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
    		 deleteType(this.IdNumber);
    		 this.Result="OK";
    		 }else{
    			 
    			 this.Result="ERROR";
    			 this.errorMessage="login";
    		 }
    	 }catch (Exception e){
    		 this.Result="ERROR";
			 this.errorMessage="error";
    	 }
    	return super.execute();
    }
    public void deleteType(String id){
    	this.spendItem.setIdNum(id);
    	this.spendTypeImp.deleteSpendType(this.spendItem);
    }
    @Override
    public void validate() {
    	// TODO Auto-generated method stub
    	this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
    	super.validate();
    }
}
