package general;

import visitador.OrderVisitor;

public class CaliforniaOrder extends Order{
	private double additionalTax;

	public CaliforniaOrder(String inp_name, double inp_amount, double inp_additionalTax) {
		
		super(inp_name, inp_amount);
		System.out.println("constructor "+inp_amount);
		setAdditionalTax(inp_additionalTax);
		super.setType(OrderManager.CA_ORDER);
	}

	public double getAdditionalTax() {
		return additionalTax;
	}

	public void setAdditionalTax(double additionalTax) {
		this.additionalTax = additionalTax;
	}

	@Override
	public void accept(OrderVisitor v) {
		v.visit(this);
	}

	@Override
	public String informacion() {
		String informacion="";
		informacion+="Orden ("+getName()+") de tipo "+getType()+" con valor "+getOrderAmount()+" impuesto adiccional: "+getAdditionalTax();
		if(isliquidated()) {
			informacion+=", liquidada con valor total "+getTotalOrder();
		}else {
			informacion+=", no liquidada";
		}
		return informacion;
	}

}
