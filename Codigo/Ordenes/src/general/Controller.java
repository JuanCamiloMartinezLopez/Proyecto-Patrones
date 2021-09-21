package general;

import java.util.Vector;

import iterador.AllOrdersIterator;
import iterador.LiquidatedOrders;
import iterador.NonLiquidatedOrders;
import visitador.OrderVisitor;

public class Controller {
	private AllOrders ao;
	private OrderVisitor ov;

	public Controller() {
		ao= new AllOrders();
		ov= new OrderVisitor();
	}
	
	public void createOrder(String orderType, String orderName, double orderAmount, double tax, double SH) {
		System.out.println("cuando se crea: " + orderAmount);
		if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
			ao.addOrder(new CaliforniaOrder(orderName, orderAmount, tax));
		}
		if (orderType.equalsIgnoreCase(OrderManager.NON_CA_ORDER)) {
			ao.addOrder(new NonCaliforniaOrder(orderName, orderAmount));
		}
		if (orderType.equalsIgnoreCase(OrderManager.OVERSEAS_ORDER)) {
			ao.addOrder(new OverseasOrder(orderName, orderAmount, SH));
		}
		if (orderType.equalsIgnoreCase(OrderManager.CUBAN_ORDER)) {
			ao.addOrder(new CubanOrder(orderName, orderAmount, tax, SH));
		}
	}
	
	public Order createOneOrder(String orderType, String orderName, double orderAmount, double tax, double SH) {
		System.out.println("cuando se crea: " + orderAmount);
		Order order = null;
		if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
			order = new CaliforniaOrder(orderName, orderAmount, tax);
		}
		if (orderType.equalsIgnoreCase(OrderManager.NON_CA_ORDER)) {
			order = new NonCaliforniaOrder(orderName, orderAmount);
		}
		if (orderType.equalsIgnoreCase(OrderManager.OVERSEAS_ORDER)) {
			order = new OverseasOrder(orderName, orderAmount, SH);
		}
		if (orderType.equalsIgnoreCase(OrderManager.CUBAN_ORDER)) {
			order = new CubanOrder(orderName, orderAmount, tax, SH);
		}
		return order;
	}
	
	public Vector<Order> getAllOrders(String order_type) {
		Vector<Order> orders= new Vector<Order>();
		AllOrdersIterator aoi;
		if(order_type==null) {
			aoi = new AllOrdersIterator(ao);
		}else {
			aoi = new AllOrdersIterator(ao,order_type);
		}
		//OrderVisitor visitor = getOv();
		while (aoi.hasNext()) {
			Order auxOrder = aoi.next();
			System.out.println(auxOrder.toString());
			//auxOrder.accept(visitor);
			orders.add(auxOrder);
		}
		return orders;
	}
	
	public Vector<Order> getNonLiquidatedOrders(String order_type) {
		Vector<Order> orders= new Vector<Order>();
		NonLiquidatedOrders aoi;
		if(order_type==null) {
			aoi = new NonLiquidatedOrders(ao);
		}else {
			aoi = new NonLiquidatedOrders(ao,order_type);
		}
		//OrderVisitor visitor = getOv();
		while (aoi.hasNext()) {
			Order auxOrder = aoi.next();
			System.out.println(auxOrder.toString());
			//auxOrder.accept(visitor);
			orders.add(auxOrder);
		}
		return orders;
	}
	
	public Vector<Order> getLiquidatedOrders(String order_type) {
		Vector<Order> orders= new Vector<Order>();
		LiquidatedOrders aoi;
		if(order_type==null) {
			aoi = new LiquidatedOrders(ao);
		}else {
			aoi = new LiquidatedOrders(ao,order_type);
		}
		//OrderVisitor visitor = getOv();
		while (aoi.hasNext()) {
			Order auxOrder = aoi.next();
			System.out.println(auxOrder.toString());
			//auxOrder.accept(visitor);
			orders.add(auxOrder);
		}
		return orders;
	}
	
	public void liquidateOrders(String type) {
		for (Order order:getNonLiquidatedOrders(type)) {
			//System.out.println(auxOrder.toString());
			order.accept(getOrderVisitor());
		}
	}
	
	public void printAllOrder() {
		for(Order order:getAllOrders(null)) {
			System.out.println(order.informacion());
		}
	}
	
	public String informationFilteredOrders(String type, String state) {
		Vector<Order> FilteredOrders;
		if(state=="NonLiquidated") {
			FilteredOrders=getNonLiquidatedOrders(type);
		}else if(state=="Liquidated") {
			FilteredOrders=getLiquidatedOrders(type);
		}else {
			FilteredOrders=getAllOrders(type);
		}
		String orderInfomation="";
		if(FilteredOrders.size()>0) {
			for(Order order:FilteredOrders) {
				orderInfomation+=order.informacion()+"\n";
				orderInfomation+="--------------------------------------\n";
				//System.out.println(order.informacion());
			}
			if(state=="Liquidated") {
				orderInfomation+="\n\n Monto Total Ordenes liquidadas:"+getTotalAmountLiquidatedOrders(type);
			}
		}else {
			orderInfomation+="\n\n No hay ordenes que cumplan los filtros de busqueda.";
		}
		return orderInfomation;
	}
	
	public String getTotalAmountLiquidatedOrders(String type) {
		double totalAmount=0;
		for(Order order:getLiquidatedOrders(type)) {
			totalAmount+=order.getTotalOrder();
		}
		return Double.toString(totalAmount);
		
	}
	
	public void editOrder(Order oldOrder, Order newOrder) {
		ao.replaceOrder(oldOrder, newOrder);
	}
	
	private AllOrders getAllo() {
		return ao;
	}


	private OrderVisitor getOrderVisitor() {
		return ov;
	}

}
