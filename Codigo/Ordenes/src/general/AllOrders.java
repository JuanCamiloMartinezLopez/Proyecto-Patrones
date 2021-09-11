package general;

import java.util.Enumeration;
import java.util.Vector;

public class AllOrders {
	private Vector<Order> data;
	
	public AllOrders() {
		data=new Vector<Order>();
	}

	
	public Enumeration<Order> getAllCandidates() {
		return data.elements();
	}
	
	public void addOrder( Order o) {
		data.add(o);
	}
}
