package action;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{
	
@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	ActionContext.getContext().getSession().clear();
	ActionContext.getContext().getApplication().clear();
 
	ServletActionContext.getRequest().getSession().invalidate();
	System.out.println(ServletActionContext.getRequest().getSession().getAttribute("account"));
	return super.execute();
}

}
