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

public class IncomeUpdateAction extends ActionSupport{
	private String Result;
	private HashMap<String,Object> Record;
	 private String errorMessage;
	private boolean isFormate;
	         boolean isnull;
	 private boolean isLogin;
	 		 boolean isEmpty;
	         boolean isAvilideDate;
	 private String Account;
	 private BigDecimal BAmount;
	 private String Amount;
	 private String Income_date;
	 private String Modify_date;
	 private String Id_num;
	 
		
		private Income income;
		private IncomeBizImp incomeBizImp;
		
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
	   public void setId_num(String Id_num){
		   this.Id_num=Id_num;
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
					if(isnull||isEmpty||isAvilideDate){
						this.Result="ERROR";
						return super.execute();
					}else{
						updateIncome();
					}
				}else{
					this.Result="ERROR";
					this.Record=null;
					 this.errorMessage="login";
				}
			}catch(Exception e){
				this.Result="ERROR";
				this.Record=null;
				 this.errorMessage="login";
			}
			return super.execute();
		}
		
		
		private void updateIncome(){
			try{
			Calendar d=Calendar.getInstance();
			Date d2=d.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date STime=sdf.parse(this.Income_date);
			this.Record=new HashMap<String, Object>();
			this.income.setAccount(this.Account);
			this.income.setAmount(this.BAmount);
			this.income.setIdNum(this.Id_num);
			this.income.setIncomeDate(STime);
			this.income.setModifyDate(d2);
			this.incomeBizImp.update(this.income.getIdNum(), this.income);
			Record.put("Id_num", this.income.getIdNum());
			Record.put("Account", this.income.getAccount());
			Record.put("Amount", this.income.getAmount());
			Record.put("Income_date", this.Income_date); 
			this.Result="OK";
			System.out.println(this.income.getAccount()+" update finish");
			}catch(Exception e){
				this.errorMessage="無效日期";
			}
		}
		
		
		@Override
		public void validate() {
			// TODO Auto-generated method stub
			this.isLogin=ActionContext.getContext()
					.getSession().containsKey("account");//判斷是否登入
			 isnull= this.Account==null||this.Amount==null
					 ||this.Income_date==null;//判斷USER輸入是否為空值
			 isEmpty= this.Account.equals("")||
					 this.Income_date.equals("")||this.Amount.equals("");//判斷USER輸入是否為空白
					isFormate=false;
			 isAvilideDate=false;//有效日期判斷
			 SimpleDateFormat sdf = 
					 new SimpleDateFormat("yyyy-MM-dd");
			 try{
				 this.BAmount=new BigDecimal(this.Amount);
				   Date d=sdf.parse(this.Income_date);
				   Calendar today=Calendar.getInstance();
				   Date t=today.getTime();
				   Date t2=sdf.parse(today.get(today.YEAR)+"-"+(today.get(today.MONTH)+1)+"-"+"01");
				   isAvilideDate=t.before(d)||t2.after(d);
			 }catch(java.text.ParseException e){
				 e.printStackTrace();
					this.errorMessage="非日期格式";
					isAvilideDate=true;
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
