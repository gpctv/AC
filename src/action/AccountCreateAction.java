package action;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.struts2.json.annotations.JSON;

import bean.Account;
import bean.AccountDAO;
import biz.AccountBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AccountCreateAction extends ActionSupport{
	boolean isnull;
	boolean isEmpty;
	 boolean isLogin;
	 boolean isExist;
	 HashMap<String,Object> Record;
	 String Result;
	 String errorMessage;
	 Account aaccount;
	  AccountBizImp accountBizImp;
	 String  account;
	 String password;
	 String admin;
	 @JSON(name="Result")
	 public String getResult(){
		
		 return Result;
	 }
	 @JSON(name="Record")
	 public HashMap<String,Object> getRecord(){
		 return this.Record;
	 }
	 @JSON(name="Message")
	 public String getMessage(){
		 return this.errorMessage;
	 }
	 
	 public void setAccount(String account){
		 this.account=account;
	 }
	 public void setPassword(String password){
		 this.password=password;
	 }
	 public void setAdmin(String admin){
		 this.admin=admin;
	 }
	 public void setAccountBizImp(AccountBizImp accountBizImp){
		 this.accountBizImp=accountBizImp;
	 }
	 public void setAaccount(Account account){
		 this.aaccount=account;
	 }
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(this.isLogin){
		if(this.isEmpty||this.isnull||this.isExist||this.admin.equals("1")){
			this.Result="ERROR";
		}else{
			insertAccount();
		}
		}else{
			this.Record=null;
			this.errorMessage="you should Login";
			this.Result="ERROR";
		}
		return super.execute();
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isEmpty=this.account.equals("")||this.password.equals("")||this.admin.equals("");
		this.isnull=this.account==null||this.password==null||this.admin==null;
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		this.isExist=this.accountBizImp.isExistAccount(this.account);
		if(this.admin.equals("1")){
			this.errorMessage="error";
		}
		if(isnull||isEmpty){
			this.errorMessage="請勿空白";	
			
			}
		if(isExist){
			this.errorMessage="此帳號已存在";	
		}
		super.validate();
	}
	private void insertAccount(){
		try{
			String num=this.accountBizImp.getID();
			this.Record=new HashMap<String, Object>();
			this.aaccount.setAccount(this.account);
			this.aaccount.setIdNum(num);
			this.aaccount.setIsAdmin(new BigDecimal(this.admin));
			this.aaccount.setPassword(this.password);
			this.accountBizImp.insert (this.aaccount);
			this.Result="OK";
			this.Record.put("Idnum", this.aaccount.getIdNum());
			this.Record.put("Account", this.aaccount.getAccount().replaceAll("\\s+", ""));
			this.Record.put("Password", this.aaccount.getPassword().replaceAll("\\s+", ""));
			this.Record.put("Admin", "2");//目前只新增使用者
		}catch(Exception e){
			this.Result="ERROR";
			e.printStackTrace();
		}
		
		
	}
	
	
}
