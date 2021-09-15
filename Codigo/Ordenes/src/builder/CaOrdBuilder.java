package builder;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CaOrdBuilder extends UIBuilder{

	private JTextField txtAdditionalTax;
	private JLabel lblAdditionalTax;
	
	public void addUIControls() {
		orderUI = new JPanel();
		lblAdditionalTax = new JLabel("Additional Tax:");
		txtAdditionalTax = new JTextField(10);
		
		GridBagLayout gridbag = new GridBagLayout();
	    orderUI.setLayout(gridbag);
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.anchor = GridBagConstraints.WEST;
	    orderUI.add(lblAdditionalTax);
	    orderUI.add(txtAdditionalTax);
	    
	    gbc.insets.top = 5;
	    gbc.insets.bottom = 5;
	    gbc.insets.left = 5;
	    gbc.insets.right = 5;
	    
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gridbag.setConstraints(lblAdditionalTax, gbc);
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gridbag.setConstraints(txtAdditionalTax, gbc);
	}
	
	public void initialize() {
		txtAdditionalTax.setText("0");
	}
	
	public String getAdditionalTax() {
		return txtAdditionalTax.getText();
	}
	
	public String getAdditionalSH() {
		return "";
	}
	
}
