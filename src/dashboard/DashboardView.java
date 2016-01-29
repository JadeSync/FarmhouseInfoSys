package dashboard;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import user.User;
import user.UserMDL;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelListener;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import tools.TableCellListener;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardView {

	private static DashboardView thisDash;
	private JFrame frame;
	private static UserMDL userModel = new UserMDL();
	private static User loggedInUser;
	private JInternalFrame newUserFrame = new JInternalFrame( "New User" );
	private JTextField txtUsername;
	private JPasswordField txtPwd;
	private JTextField txtName;
	private JTextField txtLFUsername;
	private JPasswordField txtLFPwd;
	private JInternalFrame internalFrame = new JInternalFrame("Login");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardView window = new DashboardView();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
					window.frame.setTitle("Farmhouse InfoSys");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void AuthAndStartNewSession( String username, String password ) {
		loggedInUser = userModel.authenticate( username, password );
		if( loggedInUser == null ) {
			JOptionPane.showMessageDialog(null, "Invalid credentials.");
		}
		else {
			DashboardView.thisDash.internalFrame.setVisible(false);
			
			if( DashboardView.loggedInUser.getUserRole().toUpperCase().equals("Cashier".toUpperCase() )) {
				DashboardView.thisDash.frame.getJMenuBar().getMenu(0).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(1).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(1).getItem(0).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(1).getItem(1).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(1).getItem(2).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(1).getItem(3).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(1).getItem(4).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(2).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(3).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(4).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(5).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(6).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(7).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(8).setEnabled(false);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(9).setEnabled(true);
			}
			
			if( DashboardView.loggedInUser.getUserRole().toUpperCase().equals("staff".toUpperCase())) {
				
			}
			
			if( DashboardView.loggedInUser.getUserRole().toUpperCase().equals("admin".toUpperCase()) || DashboardView.loggedInUser.getUserRole().toUpperCase().equals("superadmin".toUpperCase() )) {
				DashboardView.thisDash.frame.getJMenuBar().getMenu(0).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(1).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(2).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(3).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(4).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(5).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(6).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(7).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(8).setEnabled(true);
				DashboardView.thisDash.frame.getJMenuBar().getMenu(9).setEnabled(true);
				
			}
			
		}
	}

	/**
	 * Create the application.
	 */
	public DashboardView() {
		this.loggedInUser = null;
		initialize();
	}
	
	public User getCurrentUser() {
		return this.loggedInUser;
	}
	
	public Boolean createNewUser( String username, String pwd, String userrole, String name ) {
		User newUser = new User( username, pwd, userrole, name );
		if( userModel.createNewUser( newUser ) ) {
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.thisDash = this;
		frame = new JFrame();
		frame.setBounds(40, 40, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 120 , (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 120);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
		    frame.setIconImage(ImageIO.read(new File("resources/icon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}
		

	    JMenuBar menuBar = new JMenuBar();
	    frame.setJMenuBar(menuBar);
	    
	    JMenu mnOperations = new JMenu("Operations");
	    menuBar.add(mnOperations);
	    
	    JMenuItem mntmOpenDay = new JMenuItem("Open Day");
	    mnOperations.add(mntmOpenDay);
	    
	    JMenuItem mntmCloseDay = new JMenuItem("Close Day");
	    mnOperations.add(mntmCloseDay);
	    
	    JSeparator separator = new JSeparator();
	    mnOperations.add(separator);
	    
	    JMenuItem mntmViewLogs = new JMenuItem("View Logs");
	    mnOperations.add(mntmViewLogs);
	    
	    JSeparator separator_1 = new JSeparator();
	    mnOperations.add(separator_1);
	    
	    JMenuItem mntmEventStart = new JMenuItem("Event Start");
	    mnOperations.add(mntmEventStart);
	    
	    JMenuItem mntmEventEnd = new JMenuItem("Event End");
	    mnOperations.add(mntmEventEnd);
	    
	    JSeparator separator_2 = new JSeparator();
	    mnOperations.add(separator_2);
	    
	    JMenuItem mntmExit = new JMenuItem("Exit");
	    mnOperations.add(mntmExit);
	    
	    JMenu mnModules = new JMenu("Modules");
	    menuBar.add(mnModules);
	    
	    JMenuItem mntmBilling = new JMenuItem("Billing");
	    mnModules.add(mntmBilling);
	    
	    JMenuItem mntmInventory = new JMenuItem("Inventory");
	    mnModules.add(mntmInventory);
	    
	    JMenuItem mntmEmployees = new JMenuItem("Employees");
	    mnModules.add(mntmEmployees);
	    
	    JMenuItem mntmBands = new JMenuItem("Bands");
	    mnModules.add(mntmBands);
	    
	    JMenuItem mntmEvents = new JMenuItem("Events");
	    mnModules.add(mntmEvents);
	    
	    JMenu mnItemMenu = new JMenu("Item Menu");
	    menuBar.add(mnItemMenu);
	    
	    JMenuItem mntmViewMenu = new JMenuItem("View & Edit Menu");
	    mnItemMenu.add(mntmViewMenu);
	    
	    JMenu mnReporting = new JMenu("Reporting");
	    menuBar.add(mnReporting);
	    
	    JMenuItem mntmSalesReport = new JMenuItem("Sales Report");
	    mnReporting.add(mntmSalesReport);
	    
	    JMenuItem mntmWeeklyReport = new JMenuItem("Weekly Report");
	    mnReporting.add(mntmWeeklyReport);
	    
	    JMenuItem mntmMonthly = new JMenuItem("Monthly Report");
	    mnReporting.add(mntmMonthly);
	    
	    JMenuItem mntmEmployeeReoprt = new JMenuItem("Employee Report");
	    mnReporting.add(mntmEmployeeReoprt);
	    
	    JMenuItem mntmFinancialReport = new JMenuItem("Financial Report");
	    mnReporting.add(mntmFinancialReport);
	    
	    JMenuItem mntmCustomerReport = new JMenuItem("Customer Report");
	    mnReporting.add(mntmCustomerReport);
	    
	    JMenuItem mntmInventoryReport = new JMenuItem("Inventory Report");
	    mnReporting.add(mntmInventoryReport);
	    
	    JMenuItem mntmEventReport = new JMenuItem("Event Report");
	    mnReporting.add(mntmEventReport);
	    
	    JMenuItem mntmBandReport = new JMenuItem("Band Report");
	    mnReporting.add(mntmBandReport);
	    
	    JMenuItem mntmScreeningReport = new JMenuItem("Screening Report");
	    mnReporting.add(mntmScreeningReport);
	    
	    JMenuItem mntmCreditReport = new JMenuItem("Credit Report");
	    mnReporting.add(mntmCreditReport);
	    
	    JMenu mnExpenseTracker = new JMenu("Expense Tracker");
	    menuBar.add(mnExpenseTracker);
	    
	    JMenuItem mntmNewExpense = new JMenuItem("New Expense");
	    mnExpenseTracker.add(mntmNewExpense);
	    
	    JMenuItem mntmViewDailyExpense = new JMenuItem("View Daily Expense");
	    mnExpenseTracker.add(mntmViewDailyExpense);
	    
	    JMenuItem mntmViewWeeklyExpense = new JMenuItem("View Weekly Expense");
	    mnExpenseTracker.add(mntmViewWeeklyExpense);
	    
	    JMenuItem mntmViewMonthlyExpense = new JMenuItem("View Monthly Expense");
	    mnExpenseTracker.add(mntmViewMonthlyExpense);
	    
	    JMenu mnPayrollManagement = new JMenu("Payroll Management");
	    menuBar.add(mnPayrollManagement);
	    
	    JMenuItem mntmManageEmployeesPayroll = new JMenuItem("Manage Employees Payroll ");
	    mnPayrollManagement.add(mntmManageEmployeesPayroll);
	    
	    JMenuItem mntmViewPayrollInfo = new JMenuItem("View Payroll Info");
	    mnPayrollManagement.add(mntmViewPayrollInfo);
	    
	    JMenu mnUsers = new JMenu("Users");
	    menuBar.add(mnUsers);
	    
	    JMenuItem mntmCreateNewUser = new JMenuItem("Create New User");
	    
	    mntmCreateNewUser.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		newUserFrame.setVisible(true);
	    		
	    	}
	    });
	    mnUsers.add(mntmCreateNewUser);
	    
	    JMenuItem mntmViewAllUsers = new JMenuItem("View All Users");
	    mnUsers.add(mntmViewAllUsers);
	    
	    JMenuItem mntmChangeYourPassword = new JMenuItem("Change Your Password");
	    mnUsers.add(mntmChangeYourPassword);
	    
	    JMenuItem mntmAccessControl = new JMenuItem("Access Control");
	    mnUsers.add(mntmAccessControl);
	    
	    JMenu mnSystem = new JMenu("System");
	    menuBar.add(mnSystem);
	    
	    JMenuItem mntmHelp = new JMenuItem("Help");
	    mnSystem.add(mntmHelp);
	    
	    JMenuItem mntmCoreAdjustmentMechanism = new JMenuItem("Core Adjustment Mechanism");
	    mntmCoreAdjustmentMechanism.setEnabled(false);
	    mnSystem.add(mntmCoreAdjustmentMechanism);
	    
	    JMenuItem mntmAbout = new JMenuItem("About");
	    mnSystem.add(mntmAbout);
	    
	    JMenu mnAddressBook = new JMenu("Address Book");
	    menuBar.add(mnAddressBook);
	    
	    JMenuItem mntmNewContact = new JMenuItem("New Contact");
	    mnAddressBook.add(mntmNewContact);
	    
	    JMenuItem mntmViewAllContacts = new JMenuItem("View All Contacts");
	    mnAddressBook.add(mntmViewAllContacts);
	    
	    JMenuItem mntmTextContacts = new JMenuItem("Text Contacts");
	    mnAddressBook.add(mntmTextContacts);
	    
	    JMenu mnLogOut = new JMenu("Quit");
	    menuBar.add(mnLogOut);
	    
	    JMenuItem mntmExit_1 = new JMenuItem("Log Out");
	    mnLogOut.add(mntmExit_1);
	    
	    JMenuItem mntmLogOut = new JMenuItem("Log Out & Exit");
	    mnLogOut.add(mntmLogOut);
	    frame.getContentPane().setLayout(null);
	    newUserFrame.setClosable(true);
	    
	    this.newUserFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    this.newUserFrame.setBounds(624, 289, 518, 252);
	    this.newUserFrame.setVisible(false);
	  
	    DefaultTableModel dm = new DefaultTableModel(0,0);
	    String[] header = new String[] { "SN", "Item", "Price" };
	    dm.setColumnIdentifiers(header);
	    Vector<Object> data = new Vector<Object>();
	    data.add("SN");
	    data.add("YOUO");
	    data.add(123);
	    
	    dm.addRow(data);
	    
	    Action action = new AbstractAction() {
	    	public void actionPerformed( ActionEvent e ) {
	    		TableCellListener tcl = (TableCellListener)e.getSource();
	    		System.out.println("Row   : " + tcl.getRow());
	            System.out.println("Column: " + tcl.getColumn());
	            System.out.println("Old   : " + tcl.getOldValue());
	            System.out.println("New   : " + tcl.getNewValue());
	    	}
	    };
	    
	    JPopupMenu popupMenu = new JPopupMenu();
	    addPopup(frame.getContentPane(), popupMenu);
	    
	    JMenuItem mntmNewBill = new JMenuItem("New Bill");
	    popupMenu.add(mntmNewBill);
	    
	    JMenuItem mntmNewMenuItem = new JMenuItem("New Order");
	    popupMenu.add(mntmNewMenuItem);
	    
	    JMenuItem mntmChangeTable = new JMenuItem("Change Table");
	    popupMenu.add(mntmChangeTable);
	    
	    JMenuItem mntmQuickViewMenu = new JMenuItem("Quick View Menu");
	    popupMenu.add(mntmQuickViewMenu);
	    
	    JMenuItem mntmNewExpense_1 = new JMenuItem("New Expense");
	    popupMenu.add(mntmNewExpense_1);
	    
	    internalFrame.setBounds(624, 246, 518, 216);
	    frame.getContentPane().add(internalFrame);
	    internalFrame.getContentPane().setLayout(null);
	    internalFrame.setVisible(false);
	    
	    JLabel lblUsername_1 = new JLabel("Username");
	    lblUsername_1.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblUsername_1.setBounds(161, 48, 77, 16);
	    internalFrame.getContentPane().add(lblUsername_1);
	    
	    txtLFUsername = new JTextField();
	    txtLFUsername.setBounds(250, 42, 122, 28);
	    internalFrame.getContentPane().add(txtLFUsername);
	    txtLFUsername.setColumns(10);
	    
	    JLabel lblPassword_1 = new JLabel("Password");
	    lblPassword_1.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblPassword_1.setBounds(161, 88, 77, 16);
	    internalFrame.getContentPane().add(lblPassword_1);
	    
	    txtLFPwd = new JPasswordField();
	    txtLFPwd.setBounds(250, 82, 122, 28);
	    internalFrame.getContentPane().add(txtLFPwd);
	    
	    JButton btnLogin = new JButton("Login");
	    btnLogin.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		DashboardView.AuthAndStartNewSession( txtLFUsername.getText(), txtLFPwd.getText() );
	    	}
	    });
	    btnLogin.setBounds(211, 122, 90, 28);
	    internalFrame.getContentPane().add(btnLogin);
	    frame.getContentPane().add(this.newUserFrame);
	    newUserFrame.getContentPane().setLayout(null);
	    
	    JLabel lblUsername = new JLabel("Username");
	    lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblUsername.setBounds(154, 30, 71, 16);
	    newUserFrame.getContentPane().add(lblUsername);
	    
	    txtUsername = new JTextField();
	    txtUsername.setBounds(237, 21, 114, 30);
	    newUserFrame.getContentPane().add(txtUsername);
	    txtUsername.setColumns(10);
	    
	    JLabel lblPassword = new JLabel("Password");
	    lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblPassword.setBounds(154, 62, 71, 16);
	    newUserFrame.getContentPane().add(lblPassword);
	    
	    txtPwd = new JPasswordField();
	    txtPwd.setBounds(237, 53, 114, 30);
	    newUserFrame.getContentPane().add(txtPwd);
	    
	    JLabel lblUserRole = new JLabel("User role");
	    lblUserRole.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblUserRole.setBounds(154, 120, 71, 16);
	    newUserFrame.getContentPane().add(lblUserRole);
	    
	    JSpinner spinner = new JSpinner();
	    spinner.setModel(new SpinnerListModel(new String[] {"Superadmin", "Admin", "Cashier", "Staff"}));
	    spinner.setBounds(237, 113, 114, 30);
	    newUserFrame.getContentPane().add(spinner);
	    
	    JLabel lblName = new JLabel("Name");
	    lblName.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblName.setBounds(154, 92, 71, 16);
	    newUserFrame.getContentPane().add(lblName);
	    
	    txtName = new JTextField();
	    txtName.setBounds(237, 83, 114, 30);
	    newUserFrame.getContentPane().add(txtName);
	    txtName.setColumns(10);
	    
	    JSeparator separator_3 = new JSeparator();
	    separator_3.setBounds(6, 167, 502, 2);
	    newUserFrame.getContentPane().add(separator_3);
	    
	    JButton btnSave = new JButton("Save");
	    btnSave.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String username = txtUsername.getText();
	    		String pwd = txtPwd.getText();
	    		String userrole = spinner.getValue().toString();
	    		String name = txtName.getText();
	    		
	    		if(createNewUser(username, pwd, userrole, name)) {
	    			JOptionPane.showMessageDialog(frame, "New User Created.");
	    			newUserFrame.setVisible(false);
	    			
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(frame, "New User Creation Failure.");
	    		}
	    	}
	    });
	    btnSave.setBounds(208, 172, 86, 36);
	    newUserFrame.getContentPane().add(btnSave);
		
		User user = new User();
		
		if( this.getCurrentUser() != null ) {
			internalFrame.setVisible(false);
			if( this.getCurrentUser().getUserRole() == "Cashier" ) {
				mnOperations.setEnabled(false);
			    mnModules.setEnabled(true);
			    mnUsers.setEnabled(false);
			    mnSystem.setEnabled(false);
			    mnReporting.setEnabled(false);
			    mnExpenseTracker.setEnabled(false);
			    mnPayrollManagement.setEnabled(false);
			    mnLogOut.setEnabled(true);
			    mnAddressBook.setEnabled(true);
			}
			
			if( this.getCurrentUser().getUserRole() == "Staff") {
				
			}
			
			if( this.getCurrentUser().getUserRole() == "Admin" ) {
				mnOperations.setEnabled(true);
			    mnModules.setEnabled(true);
			    mnUsers.setEnabled(true);
			    mnSystem.setEnabled(true);
			    mnReporting.setEnabled(true);
			    mnExpenseTracker.setEnabled(true);
			    mnPayrollManagement.setEnabled(true);
			    mnLogOut.setEnabled(true);
			    mnAddressBook.setEnabled(true);
			}
		}
		
		else {
			internalFrame.setVisible(true);
		    mnOperations.setEnabled(false);
		    mnModules.setEnabled(false);
		    mnUsers.setEnabled(false);
		    mnSystem.setEnabled(false);
		    mnReporting.setEnabled(false);
		    mnExpenseTracker.setEnabled(false);
		    mnPayrollManagement.setEnabled(false);
		    mnLogOut.setEnabled(false);
		    mnAddressBook.setEnabled(false);
		    
		   internalFrame.setVisible(true);
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}