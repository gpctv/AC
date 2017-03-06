package action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.struts2.json.annotations.JSON;

import bean.Expense;
import bean.ExpenseDAO;
import biz.ExpendsDataImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateExpendsDataAction extends ActionSupport {

	private Expense expense;
	private ExpendsDataImp expenseDataImp;
	private String IdNumber;
	private String Name;
	private String ItemType;
	private String Num;
	private String Price;
	private BigDecimal BNum;
	private BigDecimal BPrice;
	private String Result;
	private HashMap<String,Object> Record;
	boolean isFormate;
	 private String errorMessage;
	 private String Time;
	 boolean isnull;
	 private boolean isLogin;
		boolean isEmpty;
		boolean isAvilideDate;
	public void setExpense(Expense expense){
		this.expense=expense;
	}
	public void setExpenseDataImp(ExpendsDataImp expenseDataImp){
		this.expenseDataImp=expenseDataImp;
	}
	public void setIdNumber(String IdNumber){
		this.IdNumber=IdNumber;
	}
	public void setName(String Name){
		this.Name=Name;
	}
	public void setItemType(String ItemType){
		this.ItemType=ItemType;
	}
	public void setNum(String Num){
		this.Num=Num;
	}
	public void setPrice(String Price){
		this.Price=Price;
	}
	public void setTime(String Time){
		this.Time=Time;
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
			if(isnull||isEmpty||isAvilideDate||isFormate){
				this.Result="ERROR";
				return super.execute();
			}else{
				update();
			}
			}else{
				this.Result="ERROR";
				this.Record=null;
				 this.errorMessage="login";
			}
		
		}catch(Exception e){
			this.Result="ERROR";
			//this.errorMessage="ERROR";
			System.out.println(e);
		}
		return super.execute();
	}
	private void update() throws Exception{
		Calendar d=Calendar.getInstance();
		Date d2=d.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date STime=sdf.parse(this.Time);
		this.Record=new HashMap<String, Object>();
		this.Record.put("IdNumber", this.IdNumber);
		this.Record.put("Name", this.Name);
		this.Record.put("ItemType", this.ItemType);
		this.Record.put("Num", this.Num);
		this.Record.put("Price", this.Price);
		this.Record.put("Time",this.Time );
		this.expense.setExpenseDate(STime);
		this.expense.setIdNum(this.IdNumber);
		this.expense.setItemName(this.Name);
		this.expense.setItemNum(this.BNum);
		this.expense.setItemPrice( this.BPrice);
		this.expense.setItemType(this.ItemType);
		this.expense.setModifyDate(d2);
		System.out.println("time"+this.Time);
		this.expenseDataImp.update(this.expense.getIdNum(),this.expense);
		this.Result="OK";
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 
		 isnull=this.Name==null||this.ItemType==null||this.Num==null||this.Price==null||this.Time==null;
		 isEmpty=this.Name.equals("")||this.ItemType.equals("")||this.Num.equals("")||this.Price.equals("")||this.Time.equals("");
			 isAvilideDate=false;	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		 isAvilideDate=false;	
		try{
			this.BNum=new BigDecimal(this.Num);
			 this.BPrice=new BigDecimal(this.Price);
			   Date d=sdf.parse(this.Time);
			   Calendar today=Calendar.getInstance();
			   Date t=today.getTime();
			   Date t2=sdf.parse(today.get(today.YEAR)+"-"+(today.get(today.MONTH)+1)+"-"+"01");
				 
			   isAvilideDate=t.before(d)||t2.after(d);
			   
		   } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isFormate=true;
			this.errorMessage="非日期格式";
			 
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
