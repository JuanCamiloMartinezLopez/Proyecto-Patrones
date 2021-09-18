package iterador;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import general.AllOrders;
import general.Order;

public class NonLiquidatedOrders implements Iterator{
	private Vector<Order> v;
	AllOrders ao;
	Order nextOrder;
	Enumeration<Order> eo;
	String type;
	
	public NonLiquidatedOrders(AllOrders inp_ao) {
		ao=inp_ao;
		eo=inp_ao.getAllCandidates();
		type=null;
	}
	
	public NonLiquidatedOrders(AllOrders inp_ao, String inp_type) {
		ao=inp_ao;
		eo=inp_ao.getAllCandidates();
		type=inp_type;
	}

	@Override
	public boolean hasNext() {
		boolean matchFound = false;
		while (eo.hasMoreElements()) {
			Order tempObj = (Order) eo.nextElement();
			if(type!=null) {
				if (!tempObj.isliquidated() && tempObj.getType().equals(type)) {
					matchFound = true;
					nextOrder = tempObj;
					break;
				}
			}else {
				if (!tempObj.isliquidated()) {
					matchFound = true;
					nextOrder = tempObj;
					break;
				}
			}
		}
		
		if (matchFound == true) {
		} else {
			nextOrder = null;
		}
		return matchFound;

	}

	@Override
	public Order next() {
		if (nextOrder == null) {
			throw new NoSuchElementException();
		} else {
			return nextOrder;
		}
	}
}
