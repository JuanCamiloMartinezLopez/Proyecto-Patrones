package general;

import visitador.OrderVisitor;

public class OverseasOrder extends Order{
	private double additionalSH;
	OverseasOrder(String inp_name,double inp_amount,double inp_additionalSH) {
		super(inp_name, inp_amount);
		setAdditionalSH(inp_additionalSH);
		super.setType("OverseasOrder");
	}
	public double getAdditionalSH() {
		return additionalSH;
	}
	public void setAdditionalSH(double additionalSH) {
		this.additionalSH = additionalSH;
	}

	public void accept(OrderVisitor v) {
		v.visit(this);
	}
	
	@Override
	public String informacion() {
		return "Orden "+getName()+" de tipo "+getType()+"con valor "+getOrderAmount()+" SH adiccional: "+getAdditionalSH()+" liquidada: "+isliquidated();
	}

}
