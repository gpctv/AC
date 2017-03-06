package action;

 
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import biz.AccountWebServiceBiz;

import com.opensymphony.xwork2.ActionSupport;

public class AccountWebService extends ActionSupport {
  String id;
  String account;
  AccountWebServiceBiz accountService;
  
  public void setAccountService(AccountWebServiceBiz accountService) {
	this.accountService = accountService;
}
 
 
@JSON(name="account")
public String getAccount() {
	return accountService.searchAccount("2");
}
public void setAccount(String account) {
	this.account = account;
}
@JSON(name="select")
public List<HashMap<String,String>> getSelect(){
	return accountService.getAccount();
}
@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}


  
}
