package general;

import visitador.OrderVisitor;

public class CubanOrder extends Order{
	private double additionalTax;
	private double additionalSH;

	CubanOrder(String inp_name,double inp_amount, double inp_additionalTax, double inp_additionalSH) {
		super(inp_name, inp_amount);
		setAdditionalTax(inp_additionalTax);
		setAdditionalSH(inp_additionalSH);
		super.setType("CubanOrder");
	}

	public double getAdditionalSH() {
		return additionalSH;
	}

	public void setAdditionalSH(double additionalSH) {
		this.additionalSH = additionalSH;
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
		return "Orden "+getName()+" de tipo "+getType()+" impuesto adiccional: "+getAdditionalTax()+" SH adiccional: "+getAdditionalSH()+" liquidada: "+isliquidated();
	}

}
