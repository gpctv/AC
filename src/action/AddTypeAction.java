package action;

import java.util.Map;

import biz.AddTypeImp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddTypeAction extends ActionSupport{
	String  itemType[];
	private boolean isLogin;
	AddTypeImp addTypeimp;
	public void setAddTypeimp(AddTypeImp impAddType){
		this.addTypeimp=impAddType;
	}
	public void setItemType(String[] itemType ){
		this.itemType=itemType;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			if(this.isLogin){
	        ItemType(this.addTypeimp.getID_NUM(),this.itemType);
			}else{
				Map request = (Map) ActionContext.getContext().get("request");
				request.put("error","重新登入");
			}
		}catch(NullPointerException e){
			return "error";
		}
		return "success";
	}
	private void  ItemType(String ID_NUM,String ItemType[]){
		for(int i=0;i<ItemType.length;i++){
			if(ItemType[i].equals("")||ItemType[i]==null){
				System.out.println("null");
				Map request = (Map) ActionContext.getContext().get("request");
				request.put("error","物品種類不得為空白");
				throw new NullPointerException();
			} 
		}
		for(int i=0;i<ItemType.length;i++){
			Integer num=Integer.valueOf(ID_NUM)+i; 
			System.out.println(num);
			System.out.println(ItemType[i]);
			
			this.addTypeimp.saveSpendItem(this.addTypeimp.setSpendItem(num.toString(), ItemType[i]));
	
		}
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		this.isLogin=ActionContext.getContext()
				.getSession().containsKey("account");
		
		super.validate();
	}

}
