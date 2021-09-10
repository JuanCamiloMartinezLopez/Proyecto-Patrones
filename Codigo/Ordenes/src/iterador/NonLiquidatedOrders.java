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
	
	public NonLiquidatedOrders(AllOrders inp_ao) {
		ao=inp_ao;
		eo=inp_ao.getAllCandidates();
	}

	@Override
	public boolean hasNext() {
		boolean matchFound = false;
		while (eo.hasMoreElements()) {
			Order tempObj = (Order) eo.nextElement();
			if (!tempObj.isliquidated()) {
				matchFound = true;
				nextOrder = tempObj;
				break;
			}
		}
		
		if (matchFound == true) {
		} else {
			nextOrder = null;
		}
		return matchFound;

	}

	@Override
	public Object next() {
		if (nextOrder == null) {
			throw new NoSuchElementException();
		} else {
			return nextOrder;
		}
	}
}
