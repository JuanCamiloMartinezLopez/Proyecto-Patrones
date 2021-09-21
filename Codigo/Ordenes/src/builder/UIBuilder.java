package builder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class UIBuilder {
    protected JPanel orderUI;
    
    public abstract void addUIControls();
    public abstract void initialize();
    public abstract String getAdditionalTax();
    public abstract String getAdditionalSH();
    public abstract void setAddittionalTax(String s);
    public abstract void setAdditionalSH(String s);
    
    public JPanel getOrderUI() {
    	return orderUI;
    }
}
