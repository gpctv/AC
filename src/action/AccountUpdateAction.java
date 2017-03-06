package action;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.struts2.json.annotations.JSON;
import org.omg.CORBA.DynAnyPackage.Invalid;

import bean.Account;
import biz.AccountBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AccountUpdateAction extends ActionSupport{
	 private String Result;
	 private HashMap<String,Object> Record;
	 private String errorMessage;
	 private   boolean isnull;
	 private boolean isLogin;
	 private  boolean isEmpty;
	 private String Admin;
	 private AccountBizImp accountBizImp;
	 private Account aaccount;
	 private String jtRecordKey;
	 private String account;
	 private String Password;
	 private String isAdmin;
	 private String Idnum;
	 public void setAccountBizImp(AccountBizImp accountBizImp){
    	 this.accountBizImp=accountBizImp;
    	 
     }
     public void setAaccount(Account aaccount){
    	 this.aaccount=aaccount;
     }
     public void setJtRecordKey(String jtRecordKey){
    	 this.jtRecordKey=jtRecordKey;
     }
     public void setAccount(String account){
    	 this.account=account;
     }
     public void setPassword(String password){
    	 this.Password=password;
     }
     public void setIsAdmin(){
    	 this.isAdmin="2";
     }
     public void setIdnum(String id){
    	 this.Idnum=id;
     }
	 
	    @JSON(name="Result")
		public String getResult(){
			return this.Result;
		}
		@JSON(name="Record")
		public HashMap<String ,Object> getRecord(){
			return this.Record;
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
			if(this.isEmpty||this.isnull||this.Admin.equals("1")){
				this.Record=null;
				this.Result="ERROR";
			}else{
				updateAccount();
				this.Result="OK";
			}
			}else{
				this.Record=null;
				this.Result="ERROR";
				this.errorMessage="";
			}
			}catch(Exception e){
				e.printStackTrace();
				this.Record=null;
				this.Result="ERROR";
				this.errorMessage="";
			}
			return super.execute();
		}
		@Override
		public void validate() {
			// TODO Auto-generated method stub
			this.isLogin=ActionContext.getContext()
					.getSession().containsKey("account");
			this.Admin=this.accountBizImp.isAdmin(this.jtRecordKey);
		   	   
			this.isEmpty=this.jtRecordKey.equals("")||this.Password.equals("");
			this.isnull=this.jtRecordKey==null||this.Password==null;
			if(isnull||isEmpty){
				this.errorMessage="請勿空白";
				 
				}
			if(this.Admin.equals("1")){
				this.errorMessage="此帳號不允許更改";
			}
			super.validate();
		}
		private void updateAccount(){
			this.Record=new HashMap<String, Object>();
			this.aaccount.setAccount(this.jtRecordKey.replaceAll("\\s+", ""));
			this.aaccount.setIdNum(this.Idnum);
			this.aaccount.setPassword(this.Password.replaceAll("\\s+", ""));
			setIsAdmin();
			this.aaccount.setIsAdmin(new BigDecimal(this.isAdmin));
		   this.accountBizImp.update(this.aaccount);
		   this.Record.put("Idnum", this.aaccount.getIdNum());
		   this.Record.put("Account", this.aaccount.getAccount());
		   this.Record.put("Password", "*****");
		   this.Record.put("Admin",this.isAdmin);
		}
}
