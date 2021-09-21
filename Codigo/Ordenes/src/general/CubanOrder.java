package general;

import visitador.OrderVisitor;

public class CubanOrder extends Order{
	private double additionalTax;
	private double additionalSH;

	CubanOrder(String inp_name,double inp_amount, double inp_additionalTax, double inp_additionalSH) {
		super(inp_name, inp_amount);
		setAdditionalTax(inp_additionalTax);
		setAdditionalSH(inp_additionalSH);
		super.setType(OrderManager.CUBAN_ORDER);
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
	public String informacion() {
		String informacion="";
		informacion+="Orden ("+getName()+") de tipo "+getType()+"con valor "+getOrderAmount()+" impuesto adiccional: "+getAdditionalTax()+" SH adiccional: "+getAdditionalSH();
		if(isliquidated()) {
			informacion+=", liquidada con valor total "+getTotalOrder();
		}else {
			informacion+=", no liquidada";
		}
		return informacion;
	}

}
