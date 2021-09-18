package general;

import visitador.OrderVisitor;

public class NonCaliforniaOrder extends Order{

	NonCaliforniaOrder(String inp_name, double inp_amount) {
		super(inp_name, inp_amount);
		super.setType("NonCaliforniaOrder");
	}

	public void accept(OrderVisitor v) {
		v.visit(this);
		
	}

	@Override
	public String informacion() {
		return "Orden "+getName()+" de tipo "+getType()+"con valor "+getOrderAmount()+" liquidada: "+isliquidated();
	}

}
