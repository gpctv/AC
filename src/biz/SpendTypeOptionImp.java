package biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SpendTypeOptionImp implements ISpendTypeOption{
	SessionFactory sessionFactory;
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		this.sessionFactory=sessionFactory;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getSpendTypeOption() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> itemType=new ArrayList<HashMap<String, Object>>();
		
		String command="select id_num,item_type "+
						"from spend_item "+
						"order by id_num ";
		Session session=this.sessionFactory.openSession();
		
		SQLQuery query =session.createSQLQuery(command);
		List<Object[]> results=query.list();
		itemType.clear();
		for(int i=0;i<results.size();i++){
			HashMap<String, Object> itemMap=new HashMap<String, Object>();
			itemMap.put("DisplayText",results.get(i)[1].toString() );
			itemMap.put("Value", results.get(i)[0].toString());
			itemType.add(itemMap);
		}
		session.close();
		return itemType;
	}

	
	
	
}
