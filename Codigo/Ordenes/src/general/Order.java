package general;

import visitador.OrderVisitor;

public abstract class Order {
	private String type;
	private boolean isliquidated;
	private double amount;
	private String name;
	private double totalOrder;
	
	Order(String inp_name,double inp_amount){
		isliquidated=false;
		name=inp_name;
		amount= inp_amount;
	}
	
	public abstract void accept(OrderVisitor v);

	public boolean isliquidated() {
		return isliquidated;
	}

	public void setliquidated(boolean isliquidated) {
		this.isliquidated = isliquidated;
	}

	public String getName() {
		return name;
	}

	public void setName(String inp_name) {
		this.name = inp_name;
	}
	
	public abstract String toString();

	public double getOrderAmount() {
		return amount;
	}

	public void setOrderAmount(double amount) {
		this.amount = amount;
	}

	public double getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(double totalOrder) {
		this.totalOrder = totalOrder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
