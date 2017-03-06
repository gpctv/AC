package biz;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.SessionFactory;

public interface ISpendTypeOption {

	public void setSessionFactory(SessionFactory sessionFactory);
	public ArrayList<HashMap<String,Object>> getSpendTypeOption();
	
}
