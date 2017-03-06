package action;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.struts.action.Action;
import org.apache.struts2.json.annotations.JSON;

import biz.ProfitBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProfitReportSummaryAction extends ActionSupport{
    Calendar today;
    Calendar lastMonth;
	BigDecimal summay;
	BigDecimal remain;
	BigDecimal result;
	boolean isLogin;
	ProfitBiz profitBiz;
	public void setProfitBiz(ProfitBiz profitBiz){
		
		this.profitBiz=profitBiz;
	}
	@JSON(name="sum")
	public BigDecimal getSummay(){
		return summay;
	}
	@JSON(name="remain")
	public BigDecimal getRemain(){
		return remain;
	}
	@JSON(name="Result")
	public BigDecimal getResult(){
		return result;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			if(this.isLogin){
				setSummary();
				setRemain();
				 this.result=this.summay.add(this.remain);
			}else{
				this.today=null;
						this.remain=null;
						this.result=null;
						this.summay=null;
						this.today=null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.execute();
	}
	private void setSummary(){
		String year=String.valueOf(this.today.get(Calendar.YEAR));
		Integer M=(this.today.get(Calendar.MONTH)+1);
		 String Month=String.valueOf(M);
		if(M<10){
			Month="0"+Month;
		}
		 this.summay=this.profitBiz.getProfite(year+"/"+Month);
	}
	private void setRemain(){
		this.lastMonth.add(Calendar.MONTH, -1);
		String year=String.valueOf(this.lastMonth.get(Calendar.YEAR));
		Integer M=(this.lastMonth.get(Calendar.MONTH)+1);
		 String Month=String.valueOf(M);
		if(M<10){
			Month="0"+Month;
		}
		 this.remain=this.profitBiz.getLastMonthProfit(year+"/"+Month);
	
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext().getSession().containsKey("account");
		 this.today=Calendar.getInstance();
		 this.lastMonth=Calendar.getInstance();
		super.validate();
	}
	
}
