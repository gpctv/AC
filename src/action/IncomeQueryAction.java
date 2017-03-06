package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import biz.IncomeBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IncomeQueryAction extends ActionSupport{
	String Result;
	String errorMessage;
	String selectDate;
	 List<HashMap<String,Object>> Records;
	 HashMap<String,Object> data;
	 int jtStartIndex;
	 int jtPageSize;
	 String jtSorting;
	 private boolean isLogin;
	 IncomeBizImp incomeBizImp;
	 public void setSelectDate(String selectDate){
		 this.selectDate=selectDate;
	 }
	 public void setIncomeBizImp(IncomeBizImp incomeBizImp){
		 this.incomeBizImp=incomeBizImp;
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
		 setRecord();
		 this.Result="OK";
			 }else{
				 this.Result="ERROR";
				 this.Records=null;
				 this.errorMessage="login";
			 }
		 }catch(Exception e){
			 this.Result="ERROR";
			 this.Records=null;
		 }
		return super.execute();
	}
	 public void setRecord(){
		 
		 Records=new ArrayList<HashMap<String,Object>>();
		 Records=this.incomeBizImp.getQueryAll(this.selectDate);
		 this.selectDate="";
	 }
	 @Override
	public void validate() {
		// TODO Auto-generated method stub
		 this.isLogin=ActionContext.getContext().getSession().containsKey("account");
			
		super.validate();
	}
}
