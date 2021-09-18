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
	public static final String CA_ORDER = "California Order";
	public static final String NON_CA_ORDER = "Non-California Order";
	public static final String OVERSEAS_ORDER = "Overseas Order";
	public static final String CUBAN_ORDER = "Cuban Order";
	public static final String BLANK = "";
	public static final String CREAR = "Create";
	public static final String EDITAR = "Edit";
	public static final String LISTAR = "List";

	private JPanel createPanel, pOrderType, pOrderType2, createButtonPanel, editPanel, editButtonPanel, listPanel,
			listButtonPanel;
	private JComboBox cmbOrderType, cmbOrderList, cmbOrderType2;
	private JTextField txtOrderName, txtOrderAmount, txtOrderName2, txtOrderAmount2;
	private JLabel lblOrderCriteria, lblOrderCriteria2, lblOrderType, lblOrderList;
	private JLabel lblisLiquidated, lblOrderName, lblOrderName2, lblOrderAmount, lblOrderAmount2;
	private JLabel lblTotal, lblTotalValue;

	public OrderManager() {
		super("Ejercicio-Patrones");

		ButtonHandler objButtonHandler = new ButtonHandler(this);

		JTabbedPane tabPanel = new JTabbedPane();
		createButtonPanel = new JPanel();
		createPanel = new JPanel();
		editPanel = new JPanel();
		editButtonPanel = new JPanel();
		listPanel = new JPanel();
		listButtonPanel = new JPanel();

		// **************************************************** CreatePanel

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

		lblTotal = new JLabel("Result:");
		lblTotalValue = new JLabel("Click Create or GetTotal Button");

		createPanel = new JPanel();

		// **************************************************** CreatePanel

		tabPanel.addTab("Crear Orden", createPanel);

		JButton getTotalButton = new JButton(OrderManager.GET_TOTAL);
		getTotalButton.setMnemonic(KeyEvent.VK_G);
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
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		gridbag2.setConstraints(getTotalButton, gbc2);
		gbc2.gridx = 3;
		gbc2.gridy = 0;
		gridbag2.setConstraints(exitButton, gbc2);

		createButtonPanel.add(getTotalButton);
		createButtonPanel.add(createOrderButton);

		createButtonPanel.add(exitButton);

		getTotalButton.addActionListener(objButtonHandler);
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
		createPanel.add(lblTotal);

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

		gbc1.anchor = GridBagConstraints.NORTH;
		gbc1.gridx = 0;
		gbc1.gridy = 6;
		gridbag1.setConstraints(lblTotal, gbc1);
		gbc1.anchor = GridBagConstraints.NORTH;
		gbc1.gridx = 1;
		gbc1.gridy = 6;
		gridbag1.setConstraints(lblTotalValue, gbc1);
		gbc1.gridx = 1;
		gbc1.gridy = 7;
		gridbag1.setConstraints(createButtonPanel, gbc1);

		// **************************************************** EditPanel
		pOrderType2 = new JPanel();

		txtOrderName2 = new JTextField(15);
		txtOrderAmount2 = new JTextField(10);

		lblOrderCriteria2 = new JLabel("Order Criteria");
		lblOrderName2 = new JLabel("Order Name:");
		lblOrderAmount2 = new JLabel("Order Amount:");

		tabPanel.addTab("Editar Orden", editPanel);

		cmbOrderList = new JComboBox();
		cmbOrderList.addItem(OrderManager.BLANK);
		cmbOrderType2 = new JComboBox();
		cmbOrderType2.addItem(OrderManager.BLANK);
		cmbOrderType2.addItem("CaliforniaOrder");
		cmbOrderType2.addItem("NonCaliforniaOrder");
		cmbOrderType2.addItem("OverseasOrder");
		cmbOrderType2.addItem("CubanOrder");

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

		// createPanel.add(lblOrderType);
		// createPanel.add(cmbOrderType);
		//editPanel.add(lblisLiquidated);
		//editPanel.add(cmbOrderType2);
		editPanel.add(lblOrderList);
		editPanel.add(cmbOrderList);
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
		gbc3.gridy = 1;
		gridbag3.setConstraints(lblisLiquidated, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 1;
		gridbag3.setConstraints(cmbOrderType2, gbc3);
		gbc3.gridx = 0;
		gbc3.gridy = 2;
		gridbag3.setConstraints(lblOrderList, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 2;
		gridbag3.setConstraints(cmbOrderList, gbc3);
		gbc3.gridx = 0;
		gbc3.gridy = 3;
		gridbag3.setConstraints(lblOrderName2, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 3;
		gridbag3.setConstraints(txtOrderName2, gbc3);
		gbc3.gridx = 0;
		gbc3.gridy = 4;
		gridbag3.setConstraints(lblOrderAmount2, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 4;
		gridbag3.setConstraints(txtOrderAmount2, gbc3);
		gbc3.gridx = 0;
		gbc3.gridy = 5;
		gridbag3.setConstraints(lblOrderCriteria2, gbc3);
		gbc3.gridx = 1;
		gbc3.gridy = 6;
		gridbag3.setConstraints(pOrderType2, gbc3);

		gbc3.anchor = GridBagConstraints.NORTH;
		gbc3.gridx = 0;
		gbc3.gridy = 7;
		gridbag3.setConstraints(editButtonPanel, gbc3);

		// **************************************************** ListPanel

		// ****************************************************
		// Add the buttons and the log to the frame
		Container contentPane = getContentPane();
		// Detecta el cambio de pestaña
		tabPanel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println("Tab: " + tabPanel.getSelectedIndex());
				if(tabPanel.getSelectedIndex()==1) {
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
		frame.setSize(500, 400);
		frame.setVisible(true);

		/////////////////////////////////////////////////////////////////////////////////////

//		listOrders = new AllOrders();
//		OrderVisitor ov = new OrderVisitor();
//		listOrders.addOrder(new CaliforniaOrder("orden 1", 1000, 0));
//		listOrders.addOrder(new CaliforniaOrder("orden 1", 1000, 0));
//		listOrders.addOrder(new NonCaliforniaOrder("orden 2", 1000));
//		listOrders.addOrder(new CubanOrder("orden 3", 1000, 0, 0));
//		listOrders.addOrder(new OverseasOrder("orden 4", 1000, 0));
//		listOrders.addOrder(new CaliforniaOrder("orden 5", 1000, 0));
//		listOrders.addOrder(new CaliforniaOrder("orden 6", 1000, 0));
//		listOrders.addOrder(new NonCaliforniaOrder("orden 7", 1000));
//		listOrders.addOrder(new CubanOrder("orden 8", 1000, 0, 0));
//		listOrders.addOrder(new OverseasOrder("orden 9", 1000, 0));
//		
//		LiquidatedOrders lo=new LiquidatedOrders(listOrders,"CaliforniaOrder");
//		NonLiquidatedOrders nlo=new
//		NonLiquidatedOrders(listOrders,"CaliforniaOrder"); AllOrdersIterator aoi= new
//		AllOrdersIterator(listOrders);
//		 
//		System.out.println("todas las ordenes:"); while(aoi.hasNext()) { Order
//		auxOrder=aoi.next(); System.out.println(auxOrder);
//		if(auxOrder.getType().equals("CaliforniaOrder")) { auxOrder.accept(ov);
//		auxOrder.setName("Orden liquidada"); } }
//		System.out.println("Ordenes liquidadas:");
//		
//		while(lo.hasNext()) { System.out.println(lo.next()); }
//		System.out.println("Ordenes no liquidadas:"); while(nlo.hasNext()) {
//		System.out.println(nlo.next()); } aoi= new AllOrdersIterator(listOrders);
//		System.out.println("todas las ordenes:"); while(aoi.hasNext()) { Order
//		auxOrder=aoi.next(); System.out.println(auxOrder);
//		if(auxOrder.getType().equals("CaliforniaOrder")) { auxOrder.accept(ov); } }
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

	public JComboBox getOrderTypeCtrl() {
		return cmbOrderType;
	}

	public JComboBox getOrderType2Ctrl() {
		return cmbOrderType2;
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

	public String getOrderName() {
		return txtOrderName.getText();
	}

	public String getOrderAmount() {
		return txtOrderAmount.getText();
	}
	
	public String getEditedOrderName() {
		return txtOrderName2.getText();
	}
	public void setEditedOrderName(String newName) {
		txtOrderName2.setText(newName);
	}

}// end of the class OrderManager

class ButtonHandler implements ActionListener {

	OrderManager objOrderManager;
	UIBuilder builder;
	AllOrders ao;
	
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

		if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {

			// Get the Visitor

			/*
			 * totalResult = new Double(visitor.getOrderTotal()).toString(); totalResult =
			 * " Orders Total = " + totalResult; objOrderManager.setTotalValue(totalResult);
			 */
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

		if (e.getSource() == objOrderManager.getOrderEditListCtrl()) {
			try {
				
				if(objOrderManager.getOrderEditListCtrl().getItemCount()>0) {
					Order o= (Order) objOrderManager.getOrderEditListCtrl().getSelectedItem();
					System.out.println(o.informacion());
					objOrderManager.setEditedOrderName(o.getName());
				}
			}catch(Exception ex) {
				System.out.println(ex);
			}
			
		}
		if (e.getActionCommand().equals(OrderManager.EDITAR)) {
			Order o= (Order) objOrderManager.getOrderEditListCtrl().getSelectedItem();
			o.setName(objOrderManager.getEditedOrderName());
			ctrl.printAllOrder();
			setAllOrderToEdit();
		}
	}
	
	public void setAllOrderToEdit() {
		objOrderManager.getOrderEditListCtrl().removeAllItems();
		//objOrderManager.getOrderEditListCtrl().addItem(OrderManager.BLANK);
		for (Order order:ctrl.getAllOrders(null)) {
			objOrderManager.getOrderEditListCtrl().addItem(order);
			/*if (auxOrder.getType().equals("CaliforniaOrder")) {
				auxOrder.accept(ov);
			}*/
		}
	}

	public ButtonHandler() {
	}

	public ButtonHandler(OrderManager inObjOrderManager) {
		objOrderManager = inObjOrderManager;
		ao = new AllOrders();
		ctrl= new Controller();
	}
}