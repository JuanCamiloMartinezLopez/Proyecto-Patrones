package general;

import java.util.*;
import java.io.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
//import com.sun.java.swing.plaf.windows.*;
import javax.swing.event.ChangeListener;

import builder.BuilderFactory;
import builder.UIBuilder;
import builder.UIDirector;
import iterador.AllOrdersIterator;
import iterador.LiquidatedOrders;
import iterador.NonLiquidatedOrders;
import visitador.OrderVisitor;

public class OrderManager extends JFrame {
	public static final String newline = "\n";
	public static final String GET_TOTAL = "Get Total";
	public static final String CREATE_ORDER = "Create Order";
	public static final String EXIT = "Exit";
	public static final String CA_ORDER = "CaliforniaOrder";
	public static final String NON_CA_ORDER = "NonCaliforniaOrder";
	public static final String OVERSEAS_ORDER = "OverseasOrder";
	public static final String CUBAN_ORDER = "CubanOrder";
	public static final String BLANK = "";
	public static final String CREAR = "Create";
	public static final String EDITAR = "Edit";
	public static final String LIQUIDAR = "Liquidate";
	public static final String LISTAR = "List";
	public static final String LISTALL = "ListAll";
	public static final String ALL = "All";

	private JPanel createPanel, pOrderType, pOrderType2, createButtonPanel, editPanel, editButtonPanel;
	private JPanel liquidatePanel, liquidateButtonPanel, listPanel, listButtonPanel;
	private JComboBox cmbOrderType, cmbOrderList, cmbOrderType2;
	private JComboBox cmbOrderType4, cmbOrderType3, cmbIsLiq, cmbOrderTypeEdit;
	private JTextArea txtNonLiqOrders, txtListOrders;
	private JTextField txtOrderName, txtOrderAmount, txtOrderName2, txtOrderAmount2;
	private JLabel lblOrderCriteria, lblOrderCriteria2, lblOrderType, lblOrderList;
	private JLabel lblisLiquidated, lblOrderName, lblOrderName2, lblOrderAmount, lblOrderAmount2, lblOrderType2;
	private JLabel lblNonLiqOrders, lblListOrders, lblTotalValue;

	public OrderManager() {
		super("Ejercicio-Patrones");

		ButtonHandler objButtonHandler = new ButtonHandler(this);

		JTabbedPane tabPanel = new JTabbedPane();

		// **************************************************** CreatePanel

		createButtonPanel = new JPanel();
		createPanel = new JPanel();

		tabPanel.addTab("Create Order", createPanel);

		cmbOrderType = new JComboBox();
		cmbOrderType.addItem(OrderManager.BLANK);
		cmbOrderType.addItem(OrderManager.CA_ORDER);
		cmbOrderType.addItem(OrderManager.NON_CA_ORDER);
		cmbOrderType.addItem(OrderManager.OVERSEAS_ORDER);
		cmbOrderType.addItem(OrderManager.CUBAN_ORDER);

		pOrderType = new JPanel();

		txtOrderName = new JTextField(15);
		txtOrderAmount = new JTextField(10);

		lblOrderType = new JLabel("Order Type:");
		lblOrderList = new JLabel("Orders:");
		lblisLiquidated = new JLabel("Seleccione:");
		lblOrderCriteria = new JLabel("Order Criteria");
		lblOrderName = new JLabel("Order Name:");
		lblOrderAmount = new JLabel("Order Amount:");
		lblTotalValue = new JLabel("Select the type of Order to create");

		JButton createOrderButton = new JButton(OrderManager.CREATE_ORDER);
		createOrderButton.setMnemonic(KeyEvent.VK_C);
		JButton exitButton = new JButton(OrderManager.EXIT);
		exitButton.setMnemonic(KeyEvent.VK_X);

		createPanel.add(createButtonPanel);

		GridBagLayout gridbag2 = new GridBagLayout();
		createButtonPanel.setLayout(gridbag2);
		GridBagConstraints gbc2 = new GridBagConstraints();

		gbc2.anchor = GridBagConstraints.CENTER;
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gridbag2.setConstraints(createOrderButton, gbc2);
		gbc2.gridx = 3;
		gbc2.gridy = 0;
		gridbag2.setConstraints(exitButton, gbc2);

		createButtonPanel.add(createOrderButton);

		createButtonPanel.add(exitButton);

		createOrderButton.addActionListener(objButtonHandler);
		exitButton.addActionListener(new ButtonHandler());
		cmbOrderType.addActionListener(objButtonHandler);

		GridBagLayout gridbag1 = new GridBagLayout();
		createPanel.setLayout(gridbag1);
		GridBagConstraints gbc1 = new GridBagConstraints();

		createPanel.add(lblOrderType);
		createPanel.add(cmbOrderType);
		createPanel.add(lblOrderCriteria);
		createPanel.add(pOrderType);
		createPanel.add(lblOrderName);
		createPanel.add(txtOrderName);
		createPanel.add(lblOrderAmount);
		createPanel.add(txtOrderAmount);
		createPanel.add(lblTotalValue);

		gbc1.insets.top = 5;
		gbc1.insets.bottom = 5;
		gbc1.insets.left = 5;
		gbc1.insets.right = 5;

		gbc1.anchor = GridBagConstraints.NORTH;

		gbc1.gridx = 0;
		gbc1.gridy = 1;
		gridbag1.setConstraints(lblOrderType, gbc1);
		gbc1.gridx = 1;
		gbc1.gridy = 1;
		gridbag1.setConstraints(cmbOrderType, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy = 2;
		gridbag1.setConstraints(lblOrderName, gbc1);
		gbc1.gridx = 1;
		gbc1.gridy = 2;
		gridbag1.setConstraints(txtOrderName, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy = 3;
		gridbag1.setConstraints(lblOrderAmount, gbc1);
		gbc1.gridx = 1;
		gbc1.gridy = 3;
		gridbag1.setConstraints(txtOrderAmount, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy = 4;
		gridbag1.setConstraints(lblOrderCriteria, gbc1);
		gbc1.gridx = 1;
		gbc1.gridy = 5;
		gridbag1.setConstraints(pOrderType, gbc1);
		gbc1.gridx = 1;
		gbc1.gridy = 6;
		gridbag1.setConstraints(lblTotalValue, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy = 7;
		gridbag1.setConstraints(createButtonPanel, gbc1);

		// **************************************************** EditPanel
		editPanel = new JPanel();
		editButtonPanel = new JPanel();
		pOrderType2 = new JPanel();

		txtOrderName2 = new JTextField(15);
		txtOrderAmount2 = new JTextField(10);

		lblOrderCriteria2 = new JLabel("Order Criteria");
		lblOrderName2 = new JLabel("Order Name:");
		lblOrderAmount2 = new JLabel("Order Amount:");
		lblOrderType2 = new JLabel("OrderType:");

		cmbOrderTypeEdit = new JComboBox();
		cmbOrderTypeEdit.addItem(OrderManager.BLANK);
		cmbOrderTypeEdit.addItem(OrderManager.CA_ORDER);
		cmbOrderTypeEdit.addItem(OrderManager.NON_CA_ORDER);
		cmbOrderTypeEdit.addItem(OrderManager.OVERSEAS_ORDER);
		cmbOrderTypeEdit.addItem(OrderManager.CUBAN_ORDER);

		tabPanel.addTab("Edit Orden", editPanel);

		cmbOrderList = new JComboBox();
		cmbOrderList.addItem(OrderManager.BLANK);
		
		cmbOrderType2 = new JComboBox();
		cmbOrderType2.addItem(OrderManager.BLANK);
		cmbOrderType2.addItem(OrderManager.CA_ORDER);
		cmbOrderType2.addItem(OrderManager.NON_CA_ORDER);
		cmbOrderType2.addItem(OrderManager.OVERSEAS_ORDER);
		cmbOrderType2.addItem(OrderManager.CUBAN_ORDER);

		cmbOrderTypeEdit.addActionListener(objButtonHandler);

		cmbOrderList.addActionListener(objButtonHandler);
		cmbOrderType2.addActionListener(objButtonHandler);

		JButton editButton = new JButton(OrderManager.EDITAR);
		editButton.setMnemonic(KeyEvent.VK_L);
		editButton.addActionListener(objButtonHandler);

		editPanel.add(editButtonPanel);

		GridBagLayout gridbag4 = new GridBagLayout();
		editButtonPanel.setLayout(gridbag4);
		GridBagConstraints gbc4 = new GridBagConstraints();

		gbc4.gridx = 0;
		gbc4.gridy = 0;
		gridbag2.setConstraints(editButton, gbc4);

		editButtonPanel.add(editButton);

		GridBagLayout gridbag3 = new GridBagLayout();
		editPanel.setLayout(gridbag3);
		GridBagConstraints gbc3 = new GridBagConstraints();

		editPanel.add(lblOrderType2);
		// createPanel.add(cmbOrderType);
		// editPanel.add(lblisLiquidated);
		editPanel.add(lblOrderList);
		editPanel.add(cmbOrderList);
		editPanel.add(cmbOrderType2);
		editPanel.add(lblOrderCriteria2);
		editPanel.add(pOrderType2);
		editPanel.add(lblOrderName2);
		editPanel.add(txtOrderName2);
		editPanel.add(lblOrderAmount2);
		editPanel.add(txtOrderAmount2);
		// createPanel.add(lblTotalValue);
		// createPanel.add(lblTotal);

		gbc3.insets.top = 5;
		gbc3.insets.bottom = 5;
		gbc3.insets.left = 5;
		gbc3.insets.right = 5;

		gbc3.anchor = GridBagConstraints.NORTH;

		gbc3.gridx = 0;
		gbc3.gridy = 2;
		gridbag3.setConstraints(lblOrderType2, gbc3);
		gbc3.gridx = 0;
		gbc3.gridy = 1;
		gridbag3.setConstraints(lblOrderList, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 2;
		gridbag3.setConstraints(cmbOrderType2, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 1;
		gridbag3.setConstraints(cmbOrderList, gbc3);
		gbc3.gridx = 0;
		gbc3.gridy = 4;
		gridbag3.setConstraints(lblOrderName2, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 4;
		gridbag3.setConstraints(txtOrderName2, gbc3);
		gbc3.gridx = 0;
		gbc3.gridy = 5;
		gridbag3.setConstraints(lblOrderAmount2, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 5;
		gridbag3.setConstraints(txtOrderAmount2, gbc3);
		gbc3.gridx = 0;
		gbc3.gridy = 6;
		gridbag3.setConstraints(lblOrderCriteria2, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 7;
		gridbag3.setConstraints(pOrderType2, gbc3);

		gbc3.anchor = GridBagConstraints.NORTH;
		gbc3.gridx = 0;
		gbc3.gridy = 8;
		gridbag3.setConstraints(editButtonPanel, gbc3);

		// **************************************************** liquidatePanel

		liquidatePanel = new JPanel();
		liquidateButtonPanel = new JPanel();
		txtNonLiqOrders = new JTextArea(10, 100);
		lblNonLiqOrders = new JLabel("Non Liquidated Orders:");

		cmbOrderType4 = new JComboBox();
		cmbOrderType4.addItem(OrderManager.BLANK);
		cmbOrderType4.addItem(OrderManager.ALL);
		cmbOrderType4.addItem(OrderManager.CA_ORDER);
		cmbOrderType4.addItem(OrderManager.NON_CA_ORDER);
		cmbOrderType4.addItem(OrderManager.OVERSEAS_ORDER);
		cmbOrderType4.addItem(OrderManager.CUBAN_ORDER);

		cmbOrderType4.addActionListener(objButtonHandler);

		tabPanel.addTab("Liquidate", liquidatePanel);

		JButton listNonLiqButton = new JButton(OrderManager.LIQUIDAR);
		listNonLiqButton.setMnemonic(KeyEvent.VK_C);
		listNonLiqButton.addActionListener(objButtonHandler);

		liquidatePanel.add(liquidateButtonPanel);

		GridBagLayout gridbag6 = new GridBagLayout();
		liquidateButtonPanel.setLayout(gridbag6);
		GridBagConstraints gbc6 = new GridBagConstraints();

		gbc6.gridx = 0;
		gbc6.gridy = 0;
		gridbag6.setConstraints(listNonLiqButton, gbc6);

		liquidateButtonPanel.add(listNonLiqButton);

		GridBagLayout gridbag5 = new GridBagLayout();
		liquidatePanel.setLayout(gridbag5);
		GridBagConstraints gbc5 = new GridBagConstraints();

		liquidatePanel.add(cmbOrderType4);
		liquidatePanel.add(lblNonLiqOrders);
		liquidatePanel.add(txtNonLiqOrders);

		gbc5.insets.top = 5;
		gbc5.insets.bottom = 5;
		gbc5.insets.left = 5;
		gbc5.insets.right = 5;

		gbc5.anchor = GridBagConstraints.WEST;

		gbc5.gridx = 0;
		gbc5.gridy = 1;
		gridbag5.setConstraints(cmbOrderType4, gbc5);
		gbc5.gridx = 0;
		gbc5.gridy = 2;
		gridbag5.setConstraints(lblNonLiqOrders, gbc5);
		gbc5.gridx = 0;
		gbc5.gridy = 3;
		gridbag5.setConstraints(txtNonLiqOrders, gbc5);

		gbc5.gridx = 0;
		gbc5.gridy = 4;
		gridbag5.setConstraints(liquidateButtonPanel, gbc5);

		// **************************************************** ListPanel

		listPanel = new JPanel();
		listButtonPanel = new JPanel();
		txtListOrders = new JTextArea(10, 100);
		lblListOrders = new JLabel("Orders info:");

		tabPanel.addTab("List Orders", listPanel);

		cmbOrderType3 = new JComboBox();
		cmbOrderType3.addItem(OrderManager.BLANK);
		cmbOrderType3.addItem(OrderManager.ALL);
		cmbOrderType3.addItem(OrderManager.CA_ORDER);
		cmbOrderType3.addItem(OrderManager.NON_CA_ORDER);
		cmbOrderType3.addItem(OrderManager.OVERSEAS_ORDER);
		cmbOrderType3.addItem(OrderManager.CUBAN_ORDER);

		cmbIsLiq = new JComboBox();
		cmbIsLiq.addItem(OrderManager.BLANK);
		cmbIsLiq.addItem(OrderManager.ALL);
		cmbIsLiq.addItem("Liquidated");
		cmbIsLiq.addItem("NonLiquidated");

		cmbOrderType3.addActionListener(objButtonHandler);
		cmbIsLiq.addActionListener(objButtonHandler);

		/*
		 * JButton listOrdersButton = new JButton(OrderManager.LISTAR);
		 * listOrdersButton.setMnemonic(KeyEvent.VK_L);
		 * listOrdersButton.addActionListener(objButtonHandler); JButton
		 * listAllOrdButton = new JButton(OrderManager.LISTALL);
		 * listAllOrdButton.setMnemonic(KeyEvent.VK_Y);
		 * listAllOrdButton.addActionListener(objButtonHandler);
		 */

		listPanel.add(listButtonPanel);

		GridBagLayout gridbag8 = new GridBagLayout();
		listButtonPanel.setLayout(gridbag8);
		GridBagConstraints gbc8 = new GridBagConstraints();

		gbc8.gridx = 0;
		gbc8.gridy = 0;
		// gridbag8.setConstraints(listOrdersButton, gbc8);
		gbc8.gridx = 1;
		gbc8.gridy = 0;
		// gridbag8.setConstraints(listAllOrdButton, gbc8);

		// listButtonPanel.add(listOrdersButton);
		// listButtonPanel.add(listAllOrdButton);

		GridBagLayout gridbag7 = new GridBagLayout();
		listPanel.setLayout(gridbag7);
		GridBagConstraints gbc7 = new GridBagConstraints();

		listPanel.add(lblListOrders);
		listPanel.add(txtListOrders);
		listPanel.add(cmbOrderType3);
		listPanel.add(cmbIsLiq);

		gbc7.insets.top = 5;
		gbc7.insets.bottom = 5;
		gbc7.insets.left = 5;
		gbc7.insets.right = 5;

		gbc7.anchor = GridBagConstraints.WEST;

		gbc7.gridx = 0;
		gbc7.gridy = 1;
		gridbag7.setConstraints(cmbOrderType3, gbc7);
		gbc7.gridx = 0;
		gbc7.gridy = 2;
		gridbag7.setConstraints(cmbIsLiq, gbc7);
		gbc7.gridx = 0;
		gbc7.gridy = 3;
		gridbag7.setConstraints(lblListOrders, gbc7);
		gbc7.gridx = 0;
		gbc7.gridy = 4;
		gridbag7.setConstraints(txtListOrders, gbc7);

		gbc7.anchor = GridBagConstraints.WEST;
		gbc7.gridx = 0;
		gbc7.gridy = 5;
		gridbag7.setConstraints(listButtonPanel, gbc7);

		// ****************************************************
		// Add the buttons and the log to the frame
		Container contentPane = getContentPane();
		// Detecta el cambio de pesta�a
		tabPanel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println("Tab: " + tabPanel.getSelectedIndex());
				if (tabPanel.getSelectedIndex() == 1) {
					objButtonHandler.setAllOrderToEdit();
				}
			}
		});
		contentPane.add(tabPanel, BorderLayout.CENTER);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(OrderManager.this);
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void main(String[] args) {
		JFrame frame = new OrderManager();

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// frame.pack();
		frame.setSize(1000, 400);
		frame.setVisible(true);
	}

	public void setTotalValue(String msg) {
		lblTotalValue.setText(msg);
	}

	public String getOrderType() {
		return (String) cmbOrderType.getSelectedItem();
	}

	public Order getOrderItem() {
		return (Order) cmbOrderList.getSelectedItem();
	}

	public String getOrderEditType() {
		return (String) cmbOrderType2.getSelectedItem();
	}
	public void setCmbOrderType2(String item) {
		cmbOrderType2.setSelectedItem(item);
	}

	public JComboBox getOrderTypeCtrl() {
		return cmbOrderType;
	}

	public JComboBox getOrderType2Ctrl() {
		return cmbOrderType2;
	}

	public JComboBox getOrderType4Ctrl() {
		return cmbOrderType4;
	}

	public JComboBox getOrderType3Ctrl() {
		return cmbOrderType3;
	}

	public JComboBox getIsLiqCtrl() {
		return cmbIsLiq;
	}

	public JComboBox getOrderEditListCtrl() {
		return cmbOrderList;
	}

	public void displayNewUI(JPanel panel) {
		pOrderType.removeAll();
		pOrderType.add(panel);
		pOrderType.validate();
		validate();
	}

	public void displayNewEditUI(JPanel panel) {
		pOrderType2.removeAll();
		pOrderType2.add(panel);
		pOrderType2.validate();
		validate();
	}

	public String getOrderName() {
		return txtOrderName.getText();
	}

	public String getOrderAmount() {
		return txtOrderAmount.getText();
	}

	public String getEditedOrderName() {
		return txtOrderName2.getText();
	}

	public void setNonLiqtxt(String tipo) {
		txtNonLiqOrders.setText(tipo);
	}

	public void setTxtListOrders(String tipo) {
		txtListOrders.setText(tipo);
	}

	public void setEditedOrderName(String newName, double newAmount) {
		txtOrderName2.setText(newName);
		txtOrderAmount2.setText(String.valueOf(newAmount));
	}

}// end of the class OrderManager

class ButtonHandler implements ActionListener {

	OrderManager objOrderManager;
	UIBuilder builder;
	AllOrders ao;
	String type, editedName, editedAmount;

	Controller ctrl;

	public void actionPerformed(ActionEvent e) {
		String totalResult = null;

		if (e.getActionCommand().equals(OrderManager.EXIT)) {
			System.exit(1);
		}
		if (e.getActionCommand().equals(OrderManager.CREATE_ORDER)) {

			// get input values
			String orderType = objOrderManager.getOrderType();
			String orderName = objOrderManager.getOrderName();
			String strOrderAmount = objOrderManager.getOrderAmount();
			String strTax = builder.getAdditionalTax();
			String strSH = builder.getAdditionalSH();

			double dblOrderAmount = 0.0;
			double dblTax = 0.0;
			double dblSH = 0.0;

			if (strOrderAmount.trim().length() == 0) {
				strOrderAmount = "0.0";
			}
			if (strTax.trim().length() == 0) {
				strTax = "0.0";
			}
			if (strSH.trim().length() == 0) {
				strSH = "0.0";
			}

			dblOrderAmount = new Double(strOrderAmount).doubleValue();
			dblTax = new Double(strTax).doubleValue();
			dblSH = new Double(strSH).doubleValue();

			// Create the order
			// Order order = createOrder(orderType, orderName, dblOrderAmount, dblTax,
			// dblSH);
			ctrl.createOrder(orderType, orderName, dblOrderAmount, dblTax, dblSH);
			// System.out.println(order);

			// accept the visitor x
			// order.accept(visitor);

			objOrderManager.setTotalValue(" Order Created Successfully");
		}

		if (e.getSource() == objOrderManager.getOrderTypeCtrl()) {
			String selection = objOrderManager.getOrderType();

			if (selection.equals("") == false) {
				BuilderFactory factory = new BuilderFactory();
				// create an appropriate builder instance
				builder = factory.getUIBuilder(selection);
				// configure the director with the builder
				UIDirector director = new UIDirector(builder);
				// director invokes different builder
				// methods
				director.build();
				// get the final build object
				JPanel UIObj = builder.getOrderUI();
				objOrderManager.displayNewUI(UIObj);
				objOrderManager.setTotalValue("Click Create button");
			}

		}

		if (e.getSource() == objOrderManager.getOrderTypeCtrl()) {
			String selection = objOrderManager.getOrderEditType();

			if (selection.equals("") == false) {
				objOrderManager.getOrderEditListCtrl().removeAllItems();
				OrderVisitor ov = new OrderVisitor();
				LiquidatedOrders lo = new LiquidatedOrders(ao, selection);
				NonLiquidatedOrders nlo = new NonLiquidatedOrders(ao, selection);
				AllOrdersIterator aoi = new AllOrdersIterator(ao);
				while (aoi.hasNext()) {
					Order auxOrder = aoi.next();
					System.out.println(auxOrder);
					if (auxOrder.getType().equals(selection)) {
						auxOrder.accept(ov);
					}
				}
				System.out.println("Ordenes liquidadas:");
				while (lo.hasNext()) {
					System.out.println(lo.next());
				}
				System.out.println("Ordenes no liquidadas:");
				while (nlo.hasNext()) {
					System.out.println(nlo.next());
				}

			}
		}

		if (e.getSource() == objOrderManager.getOrderType2Ctrl()) {
			try {

				if (objOrderManager.getOrderEditListCtrl().getItemCount() > 0) {
					Order o = (Order) objOrderManager.getOrderEditListCtrl().getSelectedItem();
					System.out.println(o.informacion());
					objOrderManager.setEditedOrderName(o.getName(), o.getOrderAmount());

					BuilderFactory factory = new BuilderFactory();
					// create an appropriate builder instance
					builder = factory.getUIBuilder(objOrderManager.getOrderType2Ctrl().getSelectedItem().toString());
					// configure the director with the builder
					UIDirector director = new UIDirector(builder);
					// director invokes different builder
					// methods
					director.build();
					// get the final build object
					JPanel UIObj = builder.getOrderUI();
					objOrderManager.displayNewEditUI(UIObj);
					
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource() == objOrderManager.getOrderEditListCtrl()) {
			try {

				if (objOrderManager.getOrderEditListCtrl().getItemCount() > 0) {
					Order o = (Order) objOrderManager.getOrderEditListCtrl().getSelectedItem();
					System.out.println(o.informacion());
					objOrderManager.setEditedOrderName(o.getName(), o.getOrderAmount());
					objOrderManager.setCmbOrderType2(o.getType());
					
					if (o.getType().equals(OrderManager.CA_ORDER)) {
						CaliforniaOrder cal = (CaliforniaOrder) o;
						builder.setAddittionalTax(String.valueOf(cal.getAdditionalTax()));
					} else if (o.getType().equals(OrderManager.OVERSEAS_ORDER)) {
						OverseasOrder over = (OverseasOrder) o;
						builder.setAdditionalSH(String.valueOf(over.getAdditionalSH()));
					} else if (o.getType().equals(OrderManager.CUBAN_ORDER)) {
						CubanOrder cub = (CubanOrder) o;
						builder.setAdditionalSH(String.valueOf(cub.getAdditionalSH()));
						builder.setAddittionalTax(String.valueOf(cub.getAdditionalTax()));
					}
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}

		}
		if (e.getActionCommand().equals(OrderManager.EDITAR)) {
			Order o = (Order) objOrderManager.getOrderEditListCtrl().getSelectedItem();
			o.setName(objOrderManager.getEditedOrderName());
			ctrl.printAllOrder();
//			setAllOrderToEdit();
		}
		if (e.getSource() == objOrderManager.getOrderType4Ctrl()) {
			String type = objOrderManager.getOrderType4Ctrl().getSelectedItem().toString();
			if (type == OrderManager.ALL)
				type = null;
			objOrderManager.setNonLiqtxt(ctrl.informationFilteredOrders(type, "NonLiquidated"));
		}
		if (e.getActionCommand().equals(OrderManager.LIQUIDAR)) {
			ctrl.liquidateOrders(objOrderManager.getOrderType4Ctrl().getSelectedItem().toString());
			objOrderManager.setNonLiqtxt("Ordenes liquidadas");
		}
		if (e.getSource() == objOrderManager.getOrderType3Ctrl() || e.getSource() == objOrderManager.getIsLiqCtrl()) {
			String type = objOrderManager.getOrderType3Ctrl().getSelectedItem().toString();
			String state = objOrderManager.getIsLiqCtrl().getSelectedItem().toString();
			if (type == OrderManager.ALL)
				type = null;
			if (state == OrderManager.ALL)
				state = null;
			if (state != "" || type != "")
				objOrderManager.setTxtListOrders(ctrl.informationFilteredOrders(type, state));

		}
		/*
		 * if (e.getActionCommand().equals(OrderManager.LISTAR)) { if
		 * (objOrderManager.getIsLiqCtrl().getSelectedItem()=="Liquidated") {
		 * 
		 * } else if (objOrderManager.getIsLiqCtrl().getSelectedItem()=="NonLiquidated")
		 * { objOrderManager.setTxtListOrders(ctrl.informationFilteredOrders(
		 * objOrderManager.getOrderType3Ctrl().getSelectedItem().toString(),
		 * "NonLiquidated")); } } if (e.getActionCommand().equals(OrderManager.LISTALL))
		 * { objOrderManager.setTxtListOrders(ctrl.informationFilteredOrders(null,
		 * null)); }
		 */
	}

	public void setAllOrderToEdit() {
		objOrderManager.getOrderEditListCtrl().removeAllItems();
		// objOrderManager.getOrderEditListCtrl().addItem(OrderManager.BLANK);
		for (Order order : ctrl.getAllOrders(null)) {
			objOrderManager.getOrderEditListCtrl().addItem(order);
			/*
			 * if (auxOrder.getType().equals("CaliforniaOrder")) { auxOrder.accept(ov); }
			 */
		}
	}

	public ButtonHandler() {
	}

	public ButtonHandler(OrderManager inObjOrderManager) {
		objOrderManager = inObjOrderManager;
		ao = new AllOrders();
		ctrl = new Controller();
	}
}