package builder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CubOrdBuilder extends UIBuilder {

	private JTextField txtAdditionalSH, txtAdditionalTax;
	private JLabel lblAdditionalSH, lblAdditionalTax;

	public void addUIControls() {
		orderUI = new JPanel();
		lblAdditionalSH = new JLabel("Additional S & H:");
		lblAdditionalTax = new JLabel("Additional Tax:");
		txtAdditionalTax = new JTextField(10);
		txtAdditionalSH = new JTextField(10);

		GridBagLayout gridbag = new GridBagLayout();
		orderUI.setLayout(gridbag);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		orderUI.add(lblAdditionalTax);
		orderUI.add(txtAdditionalTax);
		orderUI.add(lblAdditionalSH);
		orderUI.add(txtAdditionalSH);

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
		gbc.gridx = 0;
		gbc.gridy = 1;
		gridbag.setConstraints(lblAdditionalSH, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gridbag.setConstraints(txtAdditionalSH, gbc);
	}

	public void initialize() {
		txtAdditionalTax.setText("0");
		txtAdditionalSH.setText("0");
	}

	public String getAdditionalTax() {
		return txtAdditionalTax.getText();
	}

	public String getAdditionalSH() {
		return txtAdditionalSH.getText();
	}

	@Override
	public void setAddittionalTax(String s) {
		// TODO Auto-generated method stub
		txtAdditionalTax.setText(s);
	}

	@Override
	public void setAdditionalSH(String s) {
		// TODO Auto-generated method stub
		txtAdditionalSH.setText(s);
	}
}
