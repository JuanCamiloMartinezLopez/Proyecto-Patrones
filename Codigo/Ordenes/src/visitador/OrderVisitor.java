package visitador;

import java.util.Vector;

import general.CaliforniaOrder;
import general.CubanOrder;
import general.NonCaliforniaOrder;
import general.Order;
import general.OverseasOrder;

public class OrderVisitor implements VisitorInterface {
  private Vector<Order> orderObjList;

  public OrderVisitor() {
    orderObjList = new Vector<Order>();
  }
  public void visit(NonCaliforniaOrder inp_order) {
    inp_order.setTotalOrder(inp_order.getOrderAmount());
    inp_order.setliquidated(true);
  }
  public void visit(CaliforniaOrder inp_order) {
    inp_order.setTotalOrder(inp_order.getOrderAmount() +
                 inp_order.getAdditionalTax());
    inp_order.setliquidated(true);
  }
  
  public void visit(OverseasOrder inp_order) {
    inp_order.setTotalOrder(inp_order.getOrderAmount() +inp_order.getAdditionalSH());
    inp_order.setliquidated(true);
  }
  
  public void visit(CubanOrder inp_order) {
	 inp_order.setTotalOrder(inp_order.getOrderAmount()+inp_order.getAdditionalSH()+inp_order.getAdditionalTax());
	 inp_order.setliquidated(true);	
  }
  

}
