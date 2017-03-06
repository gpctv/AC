package action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import bean.Income;
import bean.IncomeDAO;
import biz.IncomeBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IncomDeleteAction  extends ActionSupport{
	 private String Id_num;
     private String Result;
     private String errorMessage;
     private boolean isLogin;
     private Income income;
     private IncomeBizImp incomeBizImp;
     private String account;
     private boolean  isAvilideDate;
	public void setIncome(Income income){
	this.income=income;	
	}
     public void setIncomeBizImp(IncomeBizImp incomeBizImp){
    	 this.incomeBizImp=incomeBizImp;
     }
     public void setId_num(String Id_num){
    	 this.Id_num=Id_num;
     }
     public void setAccount(String account){
    	 this.account=account;
     }
     @JSON(name="Result")
     public String getResult(){
  	   return this.Result;
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
				if(this.isAvilideDate){
					this.Result="ERROR";
					
				}else{
				System.out.println("deleteIncome"+this.account);
				this.incomeBizImp.delete(this.Id_num);
				 this.Result="OK";
				}
			}
		}catch(Exception e){
			 this.Result="ERROR";
			  this.errorMessage="error";
		}
		return super.execute();
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 isAvilideDate=false;
		 this.income=this.incomeBizImp.query(this.Id_num);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		 try{
			 Date d=this.income.getIncomeDate();
			 Calendar today=Calendar.getInstance();
			   Date t=today.getTime();
			   Date t2=sdf.parse(today.get(today.YEAR)+"-"+(today.get(today.MONTH)+1)+"-"+"01");
			   isAvilideDate=t.before(d)||t2.after(d);
		 }catch(Exception e){
			 e.printStackTrace();
			 isAvilideDate=true;
		 }
		 if(isAvilideDate){
				this.errorMessage="無效日期不能刪除";
				isAvilideDate=true;
			}
		super.validate();
	}
}
