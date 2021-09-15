package general;

import java.util.*;
import java.io.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import com.sun.java.swing.plaf.windows.*;

import javax.swing.JFrame;

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

	private JComboBox cmbOrderType;
	private JPanel pOrderType;
	private JTextField txtOrderName, txtOrderAmount;
	private JLabel lblOrderCriteria, lblOrderType, lblOrderName, lblOrderAmount;
	private JLabel lblTotal, lblTotalValue;

	private OrderVisitor objVisitor;

	public OrderManager() {
		super("Ejercicio-Patrones");

		// Create the visitor instance
		objVisitor = new OrderVisitor();

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
		lblOrderCriteria = new JLabel("Order Criteria");
		lblOrderName = new JLabel("Order Name:");
		lblOrderAmount = new JLabel("Order Amount:");

		lblTotal = new JLabel("Result:");
		lblTotalValue = new JLabel("Click Create or GetTotal Button");

		// Create the open button
		JButton getTotalButton = new JButton(OrderManager.GET_TOTAL);
		getTotalButton.setMnemonic(KeyEvent.VK_G);
		JButton createOrderButton = new JButton(OrderManager.CREATE_ORDER);
		getTotalButton.setMnemonic(KeyEvent.VK_C);
		JButton exitButton = new JButton(OrderManager.EXIT);
		exitButton.setMnemonic(KeyEvent.VK_X);
		ButtonHandler objButtonHandler = new ButtonHandler(this);

		getTotalButton.addActionListener(objButtonHandler);
		createOrderButton.addActionListener(objButtonHandler);
		exitButton.addActionListener(new ButtonHandler());
		cmbOrderType.addActionListener(objButtonHandler);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel();

		JPanel panel = new JPanel();
		GridBagLayout gridbag2 = new GridBagLayout();
		panel.setLayout(gridbag2);
		GridBagConstraints gbc2 = new GridBagConstraints();

		panel.add(getTotalButton);
		panel.add(createOrderButton);
		panel.add(exitButton);

		gbc2.anchor = GridBagConstraints.EAST;
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gridbag2.setConstraints(createOrderButton, gbc2);
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		gridbag2.setConstraints(getTotalButton, gbc2);
		gbc2.gridx = 2;
		gbc2.gridy = 0;
		gridbag2.setConstraints(exitButton, gbc2);

		// ****************************************************
		GridBagLayout gridbag = new GridBagLayout();
		buttonPanel.setLayout(gridbag);
		GridBagConstraints gbc = new GridBagConstraints();

		buttonPanel.add(lblOrderType);
		buttonPanel.add(cmbOrderType);
		buttonPanel.add(lblOrderCriteria);
		buttonPanel.add(pOrderType);
		buttonPanel.add(lblOrderName);
		buttonPanel.add(txtOrderName);
		buttonPanel.add(lblOrderAmount);
		buttonPanel.add(txtOrderAmount);
		buttonPanel.add(lblTotalValue);
		buttonPanel.add(lblTotal);
		
		gbc.insets.top = 5;
		gbc.insets.bottom = 5;
		gbc.insets.left = 5;
		gbc.insets.right = 5;

		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gridbag.setConstraints(lblOrderType, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gridbag.setConstraints(cmbOrderType, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gridbag.setConstraints(lblOrderName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gridbag.setConstraints(txtOrderName, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gridbag.setConstraints(lblOrderAmount, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gridbag.setConstraints(txtOrderAmount, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gridbag.setConstraints(lblOrderCriteria, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gridbag.setConstraints(pOrderType, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
	    gbc.gridx = 0;
	    gbc.gridy = 6;
	    gridbag.setConstraints(lblTotal, gbc);
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 1;
	    gbc.gridy = 6;
	    gridbag.setConstraints(lblTotalValue, gbc);
		
		gbc.insets.left = 2;
		gbc.insets.right = 2;
		gbc.insets.top = 40;

		// ****************************************************
		// Add the buttons and the log to the frame
		Container contentPane = getContentPane();

		contentPane.add(buttonPanel, BorderLayout.NORTH);
		contentPane.add(panel, BorderLayout.CENTER);
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
		
		 /*AllOrders listOrders= new AllOrders(); OrderVisitor ov= new OrderVisitor();
		 listOrders.addOrder(new CaliforniaOrder("orden 1",1000, 0));
	     listOrders.addOrder(new CaliforniaOrder("orden 1",1000, 0));
		 listOrders.addOrder(new NonCaliforniaOrder("orden 2",1000));
		 listOrders.addOrder(new CubanOrder("orden 3",1000, 0, 0));
		 listOrders.addOrder(new OverseasOrder("orden 4",1000, 0));
		 listOrders.addOrder(new CaliforniaOrder("orden 5",1000, 0));
		 listOrders.addOrder(new CaliforniaOrder("orden 6",1000, 0));
		 listOrders.addOrder(new NonCaliforniaOrder("orden 7",1000));
		 listOrders.addOrder(new CubanOrder("orden 8",1000, 0, 0));
		 listOrders.addOrder(new OverseasOrder("orden 9",1000, 0));
		 
		 LiquidatedOrders lo=new LiquidatedOrders(listOrders,"CaliforniaOrder");
		 NonLiquidatedOrders nlo=new
		 NonLiquidatedOrders(listOrders,"CaliforniaOrder"); AllOrdersIterator aoi= new
		 AllOrdersIterator(listOrders);
		  
		 System.out.println("todas las ordenes:"); while(aoi.hasNext()) { Order
		 auxOrder=aoi.next(); System.out.println(auxOrder);
		 if(auxOrder.getType().equals("CaliforniaOrder")) { auxOrder.accept(ov);
		 auxOrder.setName("Orden liquidada"); } }
		 System.out.println("Ordenes liquidadas:");
		 
		 while(lo.hasNext()) { System.out.println(lo.next()); }
		 System.out.println("Ordenes no liquidadas:"); while(nlo.hasNext()) {
		 System.out.println(nlo.next()); } aoi= new AllOrdersIterator(listOrders);
		 System.out.println("todas las ordenes:"); while(aoi.hasNext()) { Order
		 auxOrder=aoi.next(); System.out.println(auxOrder);
		 if(auxOrder.getType().equals("CaliforniaOrder")) { auxOrder.accept(ov); } }
		  */
	}

	public void setTotalValue(String msg) {
		lblTotalValue.setText(msg);
	}

	public OrderVisitor getOrderVisitor() {
		return objVisitor;
	}

	public String getOrderType() {
		return (String) cmbOrderType.getSelectedItem();
	}

	public JComboBox getOrderTypeCtrl() {
		return cmbOrderType;
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
	
}

class ButtonHandler implements ActionListener {
	OrderManager objOrderManager;
	UIBuilder builder;
	AllOrders ao;

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
			//Order order = createOrder(orderType, orderName, dblOrderAmount, dblTax, dblSH);
			createOrder(orderType, orderName, dblOrderAmount, dblTax, dblSH);
			//System.out.println(order);

			// Get the Visitor
			OrderVisitor visitor = objOrderManager.getOrderVisitor();

			// accept the visitor x
			//order.accept(visitor);

			objOrderManager.setTotalValue(" Order Created Successfully");
		}

		if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {
			AllOrdersIterator aoi= new AllOrdersIterator(ao);
			OrderVisitor visitor = objOrderManager.getOrderVisitor();
			while(aoi.hasNext()) {
				Order auxOrder=aoi.next(); 
				System.out.println(auxOrder.toString());
				auxOrder.accept(visitor); 
				System.out.println(auxOrder.toString());
				System.out.println("Order total:"+auxOrder.getTotalOrder());
			}
	
			// Get the Visitor
			
			/*totalResult = new Double(visitor.getOrderTotal()).toString();
			totalResult = " Orders Total = " + totalResult;
			objOrderManager.setTotalValue(totalResult);*/
		}
		
		if (e.getSource() == objOrderManager.getOrderTypeCtrl()) {      
	        String selection = objOrderManager.getOrderType();

	      if (selection.equals("") == false) {
	        BuilderFactory factory = new BuilderFactory();
	        //create an appropriate builder instance
	        builder = factory.getUIBuilder(selection);
	        //configure the director with the builder
	        UIDirector director = new UIDirector(builder);
	        //director invokes different builder
	        // methods
	        director.build();
	        //get the final build object
	        JPanel UIObj = builder.getOrderUI();
	        objOrderManager.displayNewUI(UIObj);
	      }

	    }
	}

	public void createOrder(String orderType, String orderName, double orderAmount, double tax, double SH) {
		System.out.println("cuando se crea: "+orderAmount);
		if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
			ao.addOrder(new CaliforniaOrder(orderName, orderAmount, tax)); 
		}
		if (orderType.equalsIgnoreCase(OrderManager.NON_CA_ORDER)) {
			ao.addOrder(new NonCaliforniaOrder(orderName, orderAmount));
		}
		if (orderType.equalsIgnoreCase(OrderManager.OVERSEAS_ORDER)) {
			ao.addOrder(new OverseasOrder(orderName, orderAmount, SH));
		}
		if (orderType.equalsIgnoreCase(OrderManager.CUBAN_ORDER)) {
			ao.addOrder(new CubanOrder(orderName, orderAmount, tax, SH));
		}
	}

	public ButtonHandler() {
	}

	public ButtonHandler(OrderManager inObjOrderManager) {
		objOrderManager = inObjOrderManager;
		ao= new AllOrders();
	}
}
