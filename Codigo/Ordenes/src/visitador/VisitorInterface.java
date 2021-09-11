package visitador;

import general.CaliforniaOrder;
import general.CubanOrder;
import general.NonCaliforniaOrder;
import general.OverseasOrder;

public interface VisitorInterface {
  /*public void visit(NonCaliforniaOrder nco);
  public void visit(CaliforniaOrder co);
  public void visit(OverseasOrder oo);
  public void visit(CubanOrder cuo);*/
  public void visit(NonCaliforniaOrder nco);
  public void visit(CaliforniaOrder co);
  public void visit(OverseasOrder oo);
  public void visit(CubanOrder cuo);
}
