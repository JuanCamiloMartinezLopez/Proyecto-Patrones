package general;

import visitador.OrderVisitor;

public class OverseasOrder extends Order{
	private double additionalSH;
	OverseasOrder(String inp_name,double inp_amount,double inp_additionalSH) {
		super(inp_name, inp_amount);
		setAdditionalSH(inp_additionalSH);
		super.setType(OrderManager.OVERSEAS_ORDER);
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
		String informacion="";
		informacion+="Orden ("+getName()+") de tipo "+getType()+"con valor "+getOrderAmount()+" SH adiccional: "+getAdditionalSH();
		if(isliquidated()) {
			informacion+=", liquidada con valor total "+getTotalOrder();
		}else {
			informacion+=", no liquidada";
		}
		return informacion;
	}

}
