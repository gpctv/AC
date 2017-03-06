package action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.struts2.json.annotations.JSON;

import bean.Expense;
import biz.ExpendsDataImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.core.ParseException;

public class CreatExpendsDataAction extends ActionSupport{
	Expense expense;
	ExpendsDataImp expendsDataImp;
	String Result;
	String errorMessage;
	HashMap<String,Object> Record;
	boolean isnull;
	boolean isEmpty;
	boolean isAvilideDate;
	boolean isFormate;
	 String name;
	 String itemType;
	 String num;
	 String price;
	 String time;
	 BigDecimal Bnumber;
	 BigDecimal Bprice;
	 boolean isLogin;
	 public void setExpenseDataImp(ExpendsDataImp expendsDataImp){
		 this.expendsDataImp=expendsDataImp;
	 }
	 public void setExpense(Expense expense){
		 this.expense=expense;
	 }
	
	 public void setName(String name){
		 this.name=name;
	 }
	 public void setItemType(String itemType){
		 this.itemType=itemType;
	 }
	 public void setNum(String num){
		 this.num=num;
	 }
	 public void setPrice(String price){
		 this.price=price;
	 }
	 public void setTime(String time){
		 this.time=time;
	 }
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
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(this.isLogin){
		if(isnull||isEmpty||isAvilideDate||isFormate){
			this.Result="ERROR";
			return super.execute();
			
		}else{
			insertData(this.name,this.itemType,this.Bprice,this.Bnumber);
			System.out.println(this.name);
			System.out.println(this.price);
			return super.execute();
		}
		}else{
			this.Result="ERROR";
			this.errorMessage="Login";
			this.Record=null;
			return super.execute();
		}
		
	}
	private void insertData(String name,String ItemType,BigDecimal price,BigDecimal num){
		try{
			System.out.println("time+"+this.time);
			this.Record=new HashMap<String,Object>();
		
		Calendar today=Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date cTime=sdf.parse(this.time);
		Date d=today.getTime();
		this.expense.setModifyDate(d);
		this.expense.setExpenseDate(cTime);
		this.expense.setIdNum(this.expendsDataImp.getIDNum());
		this.expense.setItemName(name);
		this.expense.setItemNum(num);
		this.expense.setItemPrice(price);
		this.expense.setItemType(ItemType);
		this.expendsDataImp.insertData(this.expense);
		this.Result="OK";
		this.Record=new HashMap<String,Object>();
		this.Record.put("IdNumber", this.expense.getIdNum());
		this.Record.put("Name", this.expense.getItemName());
		this.Record.put("ItemType", this.expense.getItemType());
		this.Record.put("Num",this.expense.getItemNum());
		this.Record.put("Price", this.expense.getItemPrice());
		 this.Record.put("Time", sdf.format(this.expense.getExpenseDate()));
		}catch(Exception e){
			this.Result="ERROR";
			//this.errorMessage="Error";
		}
		
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		 isnull=this.name==null||this.itemType==null||this.num==null||this.price==null||this.time==null;
		 isEmpty=this.name.equals("")||this.itemType.equals("")||this.time.equals("")||this.num.equals("")||this.price.equals("");
		 isFormate=false;
		 
		 isAvilideDate=false;	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 	
	   try{
		    Bnumber=new BigDecimal(this.num);
			 Bprice=new BigDecimal(this.price);
		   Date d=sdf.parse(this.time);
		   Calendar today=Calendar.getInstance();
		   Date t=today.getTime();
		   Date t2=sdf.parse(today.get(today.YEAR)+"-"+(today.get(today.MONTH)+1)+"-"+"01");
			  
		   isAvilideDate=t.before(d)||t2.after(d);
		   
	   } catch (java.text.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		this.errorMessage="非日期格式";
		isFormate=true;
	   }catch(NumberFormatException e){
		   this.errorMessage="非數字格式";
		   isFormate=true;
	   }
		if(isnull||isEmpty){
		this.errorMessage="請勿空白";	
		isFormate=true;
		}
		 
	if(isAvilideDate){
		this.errorMessage="無效日期";
		isAvilideDate=true;
	}
		super.validate();
	}
	 
}
