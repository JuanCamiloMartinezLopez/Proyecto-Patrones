package builder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OverOrdBuilder extends UIBuilder {

	private JTextField txtAdditionalSH;
	private JLabel lblAdditionalSH;

	public void addUIControls() {
		orderUI = new JPanel();
		lblAdditionalSH = new JLabel("Additional S & H:");
		txtAdditionalSH = new JTextField(10);

		GridBagLayout gridbag = new GridBagLayout();
		orderUI.setLayout(gridbag);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		orderUI.add(lblAdditionalSH);
		orderUI.add(txtAdditionalSH);

		gbc.insets.top = 5;
		gbc.insets.bottom = 5;
		gbc.insets.left = 5;
		gbc.insets.right = 5;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gridbag.setConstraints(lblAdditionalSH, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gridbag.setConstraints(txtAdditionalSH, gbc);
	}

	public void initialize() {
		txtAdditionalSH.setText("0");
	}

	public String getAdditionalTax() {
		return "";
	}

	public String getAdditionalSH() {
		return txtAdditionalSH.getText();
	}
}
