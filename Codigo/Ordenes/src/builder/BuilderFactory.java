package builder;

import general.OrderManager;

public class BuilderFactory {
	public UIBuilder getUIBuilder(String str) {
		UIBuilder builder = null;
		if (str.equals(OrderManager.CA_ORDER)) {
			builder = new CaOrdBuilder();
		} else if (str.equals(OrderManager.NON_CA_ORDER)) {
			builder = new NonCaOrdBuilder();
		} else if (str.equals(OrderManager.OVERSEAS_ORDER)) {
			builder = new OverOrdBuilder();
		} else if (str.equals(OrderManager.CUBAN_ORDER)) {
			builder = new CubOrdBuilder();
		}
		return builder;
	}
}
