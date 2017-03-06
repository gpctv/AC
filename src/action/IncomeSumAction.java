package action;

import java.math.BigDecimal;

import org.apache.struts2.json.annotations.JSON;

import biz.IncomeBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IncomeSumAction extends ActionSupport{
String sum;
String date;
private boolean isLogin;
 public void setSum(String sum){
	this.sum=sum;
 }
	public void setDate(String date){
		this.date=date;
	}
	@JSON(name="sum")
	public String getSum(){
		return this.sum;
	}
	private IncomeBizImp incomeBizImp;
	public void setIncomeBizImp(IncomeBizImp incomeBizImp){
		this.incomeBizImp=incomeBizImp;
	}
	
	
	
	@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
		if(this.isLogin){
			
		    sumProce(this.date);
		}else{
			this.sum="null";
		}
			return super.execute();
		}
	@Override
		public void validate() {
			// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext()
				.getSession().containsKey("account");
		this.sum="";
			super.validate();
		}
	private void sumProce(String date){
		BigDecimal Bsum=this.incomeBizImp.sumIncome(date);
		this.sum=Bsum.toString();
		
	}
	
}
