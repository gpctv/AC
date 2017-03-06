package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import bean.SpendItem;
import bean.SpendItemDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SpendTypeDataAction extends ActionSupport{
 
	String Result;
	String errorMessage;
	List<HashMap<String,Object>> Records;
	 HashMap<String,Object> data;
	 SpendItemDAO spendItemDao;
	 private boolean isLogin;
	 public void setSpendItemDao(SpendItemDAO spendItemDao){
		 this.spendItemDao=spendItemDao;
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
		 Records=new ArrayList<HashMap<String,Object>>();
		 setRecords();
		 this.Result="OK";
			 }else{
				 this.Records=null;
				 this.Result="ERROR";
				 this.errorMessage="login";
			 }
		 }catch(Exception e){
			 this.Result="ERROR";
			 this.errorMessage="error";
		 }
		return super.execute();
	}
	 private void setRecords(){
		List<SpendItem> spendItem= this.spendItemDao.findAll();
			for(int i=0;i<spendItem.size();i++){
				 data=new HashMap<String,Object>();
				 data.put("IdNumber", spendItem.get(i).getIdNum());
				 data.put("Name", spendItem.get(i).getItemType());
				 
				 Records.add(data);
			}
	 }
	 @Override
	public void validate() {
		// TODO Auto-generated method stub
		 this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
		super.validate();
	}
	
}
