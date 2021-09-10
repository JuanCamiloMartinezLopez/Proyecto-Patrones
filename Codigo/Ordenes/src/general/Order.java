package general;

public abstract class Order {
	
	private boolean isliquidated;
	private String nombre;
	
	Order(String inp_nombre,boolean liquidated){
		isliquidated=liquidated;
		nombre=inp_nombre;
	}

	public boolean isliquidated() {
		return isliquidated;
	}

	public void setliquidated(boolean isliquidated) {
		this.isliquidated = isliquidated;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		return "Orden "+nombre+" liquidada: "+isliquidated;
	}

}
