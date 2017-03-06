package action;

import java.math.BigDecimal;

import org.apache.struts2.json.annotations.JSON;

import bean.Account;
import biz.AccountBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AccountDeleteAction extends ActionSupport{
	 private String Idnum;
     private String Result;
     private String errorMessage;
     private boolean isLogin;
     private AccountBizImp accountBizImp;
     private Account aaccount;
     private String isAdmin;
     private String AccountName;
     
     public void setAccountBizImp(AccountBizImp accountBizImp){
    	 this.accountBizImp=accountBizImp;
    	 
     }
     public void setAaccount(Account aaccount){
    	 this.aaccount=aaccount;
     }
     
     public void setIdnum(String Idnum){
    	 this.Idnum=Idnum;
     }
     public void setAccount(String AccountName){
    	 this.AccountName=AccountName;
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
    			 if(this.isAdmin.equals("1")){
    				 this.errorMessage="此帳號不得刪除";
            		 this.Result="ERROR";
    			 }else{
    				this.aaccount.setAccount(this.AccountName);
    				 this.aaccount.setIdNum(this.Idnum);
    				 this.aaccount.setIsAdmin(new BigDecimal(this.isAdmin));
    				 this.aaccount.setPassword("");
    				 this.accountBizImp.delete(this.aaccount);
    				 this.Result="OK";
    			 }
    		 }else{
    			 this.errorMessage="";
        		 this.Result="ERROR";
    		 }
    	 }catch(Exception e){
    		 this.errorMessage="";
    		 this.Result="ERROR";
    		 
    		 e.printStackTrace();
    	 }
    	return super.execute();
    }
     
     @Override
    public void validate() {
    	// TODO Auto-generated method stub
    	 this.isLogin=ActionContext.getContext().getSession().containsKey("account");
   	   this.isAdmin=this.accountBizImp.isAdmin(this.AccountName);
   	   
    	super.validate();
    }
}
