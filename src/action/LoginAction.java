package action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import bean.Account;
import biz.LoginBizImp;
import biz.VerifyRecaptcha;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	LoginBizImp loginBizImp;
	String account;
	String password;
	Account accountName;
	String isAdmin;
	String isExist;
	String isNull;
	

 
	
	public void setAccountName(Account accountName){
		this.accountName=accountName;
	}
	public void setAccount(String account){
		this.account=account;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setLoginBizImp(LoginBizImp loginBizImp){
		this.loginBizImp=loginBizImp;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.account);
		System.out.println(this.password);
		System.out.println(this.isExist);
		System.out.println(this.isAdmin);
		if(!isNull.equals("true")){
		if(this.isExist.equals("true")){
		   ActionContext.getContext().getSession().put("account",this.account );
	        ActionContext.getContext().getSession().put("isAdmin", this.isAdmin);
		   this.account=null;
		   this.password="";
		   return "success";
		}else{
			ServletActionContext.getRequest().setAttribute("error", "帳號或密碼有誤");
			return "error";
		}
		}else{
			 return "error";
		}
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
		try{
		if(this.account==null||this.password==null||this.account.equals("")||this.password.equals("")){
			isExist="false";
			isNull="true";
			isAdmin="";
			 ServletActionContext.getRequest().setAttribute("error", "請輸入帳號密碼");
		}else{
			
			isNull="false";
			this.accountName.setAccount(this.account);
			this.accountName.setPassword(this.password);
			isExist=this.loginBizImp.isExist(accountName);
			isAdmin=this.loginBizImp.isAdmin(accountName);
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
			
		
			 }
}
