package action;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.json.annotations.JSON;

import biz.IncomeBizImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IncomeAccountSelectAction extends ActionSupport{
   
	String Result;
	String errorMessage;
	ArrayList<HashMap<String,Object>> Options ;
	boolean isLogin;
	IncomeBizImp incomeBizImp;
	public void setIncomeBizImp(IncomeBizImp incomeBizImp){
		this.incomeBizImp=incomeBizImp;
	}
	 @JSON(name="Message")
	 public String getMessage(){
		 return this.errorMessage;
	 }
	@JSON(name="Result")
	public String getResult(){
		return this.Result;
	}
	@JSON(name="Options")
	public ArrayList<HashMap<String,Object>> getOptions(){
		return this.Options;
	}
	@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
		try{
			if(this.isLogin){
			setOption();
			this.Result="OK";
			}else{
				this.Options=null;
				this.Result="ERROR";
				this.errorMessage="Login";
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
		 
		super.validate();
	}
	private void setOption(){
		this.Options=this.incomeBizImp.AccountSelect();
	}
	
}
