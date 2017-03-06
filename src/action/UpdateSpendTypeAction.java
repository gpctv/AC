package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import bean.SpendItem;
import bean.SpendItemDAO;
import biz.SpendTypeImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateSpendTypeAction extends ActionSupport{
	String Result;
	String errorMessage;
	String name;
	String idNumber;
	SpendTypeImp spendTypeImp;
	HashMap<String,Object> Record;
	boolean isLogin;
	 SpendItemDAO spendItemDao;
	 SpendItem spendItem;
	 public void setSpendTypeImp(SpendTypeImp spendTypeImp){
		 this.spendTypeImp=spendTypeImp;
	 }
	 public void setName(String name){
		 this.name=name;
	 }
	 public void setIdNumber(String idNumber){
		 this.idNumber=idNumber;
	 }
	 public void setSpendItem(SpendItem spendItem){
		 this.spendItem=spendItem;
	 }
	 
	 public void setSpendItemDao(SpendItemDAO spendItemDao){
		 this.spendItemDao=spendItemDao;
	 }
	 @JSON(name="Result")
	 public String  getResult(){
		  
		 return Result;
	 }
	 @JSON(name="Record")
	 public HashMap<String,Object> getRecords(){
		 return Record;
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
			 Record=new HashMap<String,Object>();
			  typeUpdate(this.spendItem);
			 this.Result="OK";
				 }else{
					 this.Record=null;
					 this.Result="ERROR";
					 this.errorMessage="login"; 
				 }
			 }catch(Exception e){
				 this.Result="ERROR";
				 this.errorMessage="error";
			 }
			return super.execute();
		}
	private void typeUpdate(SpendItem spendItem){
		System.out.println(this.idNumber);
		System.out.println(this.name);
		this.spendItem.setIdNum(this.idNumber);
		this.spendItem.setItemType(this.name);
		this.spendTypeImp.updateSpendType(this.spendItem);
		this.Record.put("IdNumber", this.idNumber);
		this.Record.put("Name", this.name);
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
		super.validate();
	}
	
}
