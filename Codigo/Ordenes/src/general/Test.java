package general;

import iterador.AllOrdersIterator;
import iterador.LiquidatedOrders;
import iterador.NonLiquidatedOrders;
import visitador.OrderVisitor;

public class Test {

	public static void main(String[] args) {
		AllOrders listOrders= new AllOrders();
		OrderVisitor ov= new OrderVisitor();
		listOrders.addOrder(new CaliforniaOrder("orden 1",1000, 0));
		listOrders.addOrder(new CaliforniaOrder("orden 1",1000, 0));
		listOrders.addOrder(new NonCaliforniaOrder("orden 2",1000));
		listOrders.addOrder(new CubanOrder("orden 3",1000, 0, 0));
		listOrders.addOrder(new OverseasOrder("orden 4",1000, 0));
		listOrders.addOrder(new CaliforniaOrder("orden 5",1000, 0));
		listOrders.addOrder(new CaliforniaOrder("orden 6",1000, 0));
		listOrders.addOrder(new NonCaliforniaOrder("orden 7",1000));
		listOrders.addOrder(new CubanOrder("orden 8",1000, 0, 0));
		listOrders.addOrder(new OverseasOrder("orden 9",1000, 0));
		
		LiquidatedOrders lo=new LiquidatedOrders(listOrders,"CaliforniaOrder");
		NonLiquidatedOrders nlo=new NonLiquidatedOrders(listOrders,"CaliforniaOrder");
		AllOrdersIterator aoi= new AllOrdersIterator(listOrders);
		
		System.out.println("todas las ordenes:");
		while(aoi.hasNext()) {
			Order auxOrder=aoi.next();
			System.out.println(auxOrder);
			if(auxOrder.getType().equals("CaliforniaOrder")) {
				auxOrder.accept(ov);
				auxOrder.setName("Orden liquidada");
			}
		}
		System.out.println("Ordenes liquidadas:");
		
		while(lo.hasNext()) {
			System.out.println(lo.next());
		}
		System.out.println("Ordenes no liquidadas:");
		while(nlo.hasNext()) {
			System.out.println(nlo.next());
		}
		aoi= new AllOrdersIterator(listOrders);
		System.out.println("todas las ordenes:");
		while(aoi.hasNext()) {
			Order auxOrder=aoi.next();
			System.out.println(auxOrder);
			if(auxOrder.getType().equals("CaliforniaOrder")) {
				auxOrder.accept(ov);
			}
		}

	}

}
