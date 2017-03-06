package action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.struts2.json.annotations.JSON;

import bean.Income;
import biz.IncomeBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IncomeCreateAction extends ActionSupport{
	Income income;
	IncomeBizImp incomeBizImp;
	String Result;
	String errorMessage;
	HashMap<String,Object> Record;
	boolean isnull;
	boolean isEmpty;
	boolean isAvilideDate;
	 boolean isLogin;
	 boolean isFormate;
	String Account;
	String Amount;
	BigDecimal BAmount;
	String Income_date;
	public void setIncome(Income income){
		this.income=income;
	}
	public void setIncomeBizImp(IncomeBizImp incomeBizImp){
		this.incomeBizImp=incomeBizImp;
	}
	public void setAccount(String Account){
		this.Account=Account;
	}
	public void setAmount(String Amount){
		this.Amount=Amount;
	}
	public void setIncome_date(String Income_date){
		this.Income_date=Income_date;
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
			if(this.isEmpty||this.isnull||isAvilideDate||isFormate){
				this.Result="ERROR";
				return super.execute();
			}else{
				insertIncome();
				System.out.println("insert income");
				return super.execute();
			}
		}else{
			this.Result="ERROR";
			this.errorMessage="Login";
			this.Record=null;
			return super.execute();
		}
		 
	}
	private void insertIncome(){
		try{

			Calendar today=Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date cTime=sdf.parse(this.Income_date);
			Date d=today.getTime();
			System.out.println("income+"+this.Account);
			this.Record=new HashMap<String,Object>();
			this.income.setAccount(this.Account);
			this.income.setAmount(this.BAmount);
			this.income.setIdNum(this.incomeBizImp.getIdNum());
			this.income.setIncomeDate(cTime);
			this.income.setModifyDate(d);
			this.incomeBizImp.insertData(this.income);
			this.Result="OK";
			this.Record=new HashMap<String,Object>();
			this.Record.put("Id_num", this.income.getIdNum());
			this.Record.put("Account", this.income.getAccount());
			this.Record.put("Amount", this.income.getAmount());
			this.Record.put("Income_date", sdf.format(this.income.getIncomeDate()));
			
			
			
		}catch(Exception e){
			this.Result="ERROR";
		}
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
		isnull=this.Account==null||this.Amount==null||this.Income_date==null;
		this.isEmpty=this.Account.equals("")||this.Income_date.equals("")||this.Amount.equals("");
		isAvilideDate=false;
		 isFormate=false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		try{
			this.BAmount=new BigDecimal(this.Amount);
			   Date d=sdf.parse(this.Income_date);
			   Calendar today=Calendar.getInstance();
			   Date t=today.getTime();
			   Date t2=sdf.parse(today.get(today.YEAR)+"-"+(today.get(today.MONTH)+1)+"-"+"01");
			   
			   isAvilideDate=t.before(d)||t2.after(d);
			   
		   } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.errorMessage="非日期格式";
			isAvilideDate=true;
		}catch(NumberFormatException e){
			   this.errorMessage="非數字格式";
			   isFormate=true;
		   }
		if(isnull||isEmpty){
			this.errorMessage="請勿空白";	
			isAvilideDate=true;
			}
		if(isAvilideDate){
			this.errorMessage="無效日期";
			isAvilideDate=true;
		}
	}
}
