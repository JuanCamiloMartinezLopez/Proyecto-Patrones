package general;

import iterador.LiquidatedOrders;
import iterador.NonLiquidatedOrders;

public class Test {

	public static void main(String[] args) {
		AllOrders listOrders= new AllOrders();
		listOrders.addOrder(new CaliforniaOrder("orden 1",true));
		listOrders.addOrder(new CaliforniaOrder("orden 1",true));
		listOrders.addOrder(new NonCaliforniaOrder("orden 2",true));
		listOrders.addOrder(new CubanOrder("orden 3",true));
		listOrders.addOrder(new OverseasOrder("orden 4",true));
		listOrders.addOrder(new CaliforniaOrder("orden 5",false));
		listOrders.addOrder(new CaliforniaOrder("orden 6",true));
		listOrders.addOrder(new NonCaliforniaOrder("orden 7",false));
		listOrders.addOrder(new CubanOrder("orden 8",false));
		listOrders.addOrder(new OverseasOrder("orden 9",false));
		
		LiquidatedOrders lo=new LiquidatedOrders(listOrders);
		NonLiquidatedOrders nlo=new NonLiquidatedOrders(listOrders);
		
		System.out.println("Ordenes liquidadas");
		while(lo.hasNext()) {
			System.out.println(lo.next());
		}
		System.out.println("Ordenes no liquidadas");
		while(nlo.hasNext()) {
			System.out.println(nlo.next());
		}

	}

}
