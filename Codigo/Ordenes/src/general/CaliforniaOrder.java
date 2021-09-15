package general;

import visitador.OrderVisitor;

public class CaliforniaOrder extends Order{
	private double additionalTax;

	public CaliforniaOrder(String inp_name, double inp_amount, double inp_additionalTax) {
		super(inp_name, inp_amount);
		setAdditionalTax(inp_additionalTax);
		super.setType("CaliforniaOrder");
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
	public String toString() {
		return "Orden "+getName()+" de tipo "+getType()+" impuesto adiccional: "+getAdditionalTax()+" liquidada: "+isliquidated();
	}

}
