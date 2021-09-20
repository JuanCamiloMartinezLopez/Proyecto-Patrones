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
		String informacion="";
		informacion+="Orden ("+getName()+") de tipo "+getType()+" con valor "+getOrderAmount();
		if(isliquidated()) {
			informacion+=", liquidada con valor total "+getTotalOrder();
		}else {
			informacion+=", no liquidada";
		}
		return informacion;
	}

}
