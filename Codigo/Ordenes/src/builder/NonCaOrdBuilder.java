package builder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NonCaOrdBuilder extends UIBuilder {

	public void addUIControls() {

		orderUI = new JPanel();
	}

	public void initialize() {
		// :v
	}

	public String getAdditionalTax() {
		return "";
	}

	public String getAdditionalSH() {
		return "";
	}

	@Override
	public void setAddittionalTax(String s) {
		// TODO Auto-generated method stub
		
	}

	public void setAdditionalSH(String s) {
		// TODO Auto-generated method stub
		
	}
}
