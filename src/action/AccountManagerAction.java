package action;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import bean.Account;
import biz.AccountBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AccountManagerAction extends ActionSupport{
	 boolean isLogin;
	String Result;
	String errorMessage;
	AccountBizImp accountBizImp;
	 List<HashMap<String,Object>> Records;
	 HashMap<String,Object> data;
	 Account account;
	 
	 //bean set
	 
	 public void setAccountBizImp(AccountBizImp accountBizImp){
		 this.accountBizImp=accountBizImp;
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
	 
	 @Override
		public void validate() {
			// TODO Auto-generated method stub
			this.isLogin=ActionContext.getContext().getSession().containsKey("account");
			 
			super.validate();
		}
	
	public void setRecords(){
		this.Records =this.accountBizImp.getQuery();
	}
}
