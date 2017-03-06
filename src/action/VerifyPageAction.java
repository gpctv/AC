package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionServlet;
import org.apache.struts2.ServletActionContext;

import biz.VerifyRecaptcha;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VerifyPageAction extends ActionSupport{
String gRecaptchaResponse; //secret key
boolean verify; //back status
String statues;
VerifyRecaptcha verifyRecaptcha;

 
//build Recaptcha class
public void setVerifyRecaptcha(VerifyRecaptcha verifyRecaptcha){
	this.verifyRecaptcha=verifyRecaptcha;
}
//return ischeck
public void setVerify() throws IOException{
	gRecaptchaResponse=ServletActionContext
			.getRequest()
			.getParameter("g-recaptcha-response");//get front page sitekey
	this.verify=this.verifyRecaptcha.verify(this.gRecaptchaResponse);
}
public String getStatues(){
	return this.statues;
}

@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	
	setVerify();
	System.out.println(this.gRecaptchaResponse);
	System.out.println(this.verify);
	if(this.verify){
		this.statues="verify success";
	}else{
		this.statues="verify faile";
	}
		return super.execute();
	}
}
