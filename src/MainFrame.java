// The main frame, the starting point of all my applications 
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable adminTable;
    private JTable customerTable;
    private JTable ShoppingBasketTable;
    private Admin admin;
    private Customer customer1;
    private JTextField barcodeSearchText;
    private JTextField miceButtonsText;
    private JTextField barcodeShoppingBasket;
    private JTextField creditCardNumber;
    private JTextField threeDigitNumber;
    private JTextField emailText;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame() {
        admin = (Admin) UserManager.readUserFromFile().get(0); // Initialize the Admin instance
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 800); // Extend the size of the frame
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tabbed pane to hold multiple tabs
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(6, 6, 780, 829);
        contentPane.add(tabbedPane);

        // Admin tab panel
        JPanel adminPanel = new JPanel();
        tabbedPane.addTab("Admin", null, adminPanel, null);
        adminPanel.setLayout(null);

        JScrollPane adminScrollPane = new JScrollPane();
        adminScrollPane.setBounds(6, 6, 747, 451);
        adminPanel.add(adminScrollPane);
        adminTable = new JTable();
        adminScrollPane.setViewportView(adminTable);
        

        JButton adminRefreshButton = new JButton("Refresh");
        adminRefreshButton.addActionListener(e -> refreshAdminTable());
        adminRefreshButton.setBounds(211, 469, 159, 29);
        adminPanel.add(adminRefreshButton);
        
        JButton addNewProduct = new JButton("New Product");
        addNewProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddProductFrame(admin); // Open AddProductFrame with the admin instance
            }
        });
        addNewProduct.setBounds(390, 469, 159, 29);
        adminPanel.add(addNewProduct);
        

        // Iterate a list of users and add a cutomer tab for each one of them
        for(User user : UserManager.readUserFromFile()) {
        	if(user instanceof Customer) {
        		Customer customer = (Customer) user;
        		CustomerPanel customerPanel = new CustomerPanel(customer);
        		tabbedPane.addTab(customer.getPersonName(), null, customerPanel, null);
        	}
        }
        
        
        

        
        
        
        
        
        
        
        // Customer tab panel
//        JPanel customerPanel = new JPanel();
//        tabbedPane.addTab("Customer", null, customerPanel, null);
//        customerPanel.setLayout(null);
//        
//        JScrollPane customerScrollPane = new JScrollPane();
//        customerScrollPane.setBounds(6, 6, 747, 158);
//        customerPanel.add(customerScrollPane);
//        customerTable = new JTable();
//        customerScrollPane.setViewportView(customerTable);
//
//        JButton customerRefreshButton = new JButton("View Products by Price ");
//        customerRefreshButton.addActionListener(e -> refreshCustomerTable(customer1,customerTable));
//        customerRefreshButton.setBounds(6, 176, 198, 29);
//        customerPanel.add(customerRefreshButton);
//        
//        
//        JScrollPane shoppingBasketScroll = new JScrollPane();
//        shoppingBasketScroll.setBounds(0, 334, 747, 158);
//        customerPanel.add(shoppingBasketScroll);
//        ShoppingBasketTable = new JTable(); 
//        shoppingBasketScroll.setViewportView(ShoppingBasketTable);
//        
//        
//        
//        //Barcode Searching
//        barcodeSearchText = new JTextField();
//        barcodeSearchText.setBounds(218, 176, 147, 29);
//        customerPanel.add(barcodeSearchText);
//        barcodeSearchText.setColumns(10);
//        
//        JButton btnNewButton = new JButton("Barcode Search");
//        btnNewButton.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		barcodeSearching(customer1, customerTable, barcodeSearchText);
//        	}
//        });
//        btnNewButton.setBounds(361, 177, 138, 26);
//        customerPanel.add(btnNewButton);
//        
//        miceButtonsText = new JTextField();
//        miceButtonsText.setBounds(511, 176, 130, 26);
//        customerPanel.add(miceButtonsText);
//        miceButtonsText.setColumns(10);
//        
//        JButton miceButton = new JButton("Filter Mice");
//        miceButton.setBounds(636, 176, 117, 29);
//        customerPanel.add(miceButton);
//        miceButton.addActionListener(e -> filterMiceByButtons(customer1, customerTable,miceButtonsText));
//        
//        JLabel lblNewLabel = new JLabel("Shopping Basket");
//        lblNewLabel.setBounds(6, 318, 130, 16);
//        customerPanel.add(lblNewLabel);
//        
//        barcodeShoppingBasket = new JTextField();
//        barcodeShoppingBasket.setBounds(16, 251, 130, 26);
//        customerPanel.add(barcodeShoppingBasket);
//        barcodeShoppingBasket.setColumns(10);
//        
//        JLabel lblNewLabel_1 = new JLabel("Barcode to add to Shopping Basket");
//        lblNewLabel_1.setBounds(16, 234, 329, 16);
//        customerPanel.add(lblNewLabel_1);
//        
//        JButton addShoppingBasket = new JButton("Add to Shopping Basket");
//        addShoppingBasket.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		addToShoppingBasket(customer1, barcodeShoppingBasket);
//        	}
//        });
//        addShoppingBasket.setBounds(6, 277, 187, 29);
//        customerPanel.add(addShoppingBasket);
//        
//        JButton retrieveShoppingBasket = new JButton("Retrieve Shopping Basket");
//        retrieveShoppingBasket.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		refreshShoppingBasketScrollPane(customer1, ShoppingBasketTable);
//        		
//        	}
//        });
//        retrieveShoppingBasket.setBounds(6, 492, 225, 29);
//        customerPanel.add(retrieveShoppingBasket);
//        
//        JButton basketClearing = new JButton("Clear Basket");
//        basketClearing.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		customer1.cancelShoppingBasket();
//        		refreshShoppingBasketScrollPane(customer1, ShoppingBasketTable);
//        	}
//        });
//        basketClearing.setBounds(243, 492, 117, 29);
//        customerPanel.add(basketClearing);
//        
//        JLabel lblNewLabel_2 = new JLabel("Credit Card Number");
//        lblNewLabel_2.setBounds(16, 547, 138, 16);
//        customerPanel.add(lblNewLabel_2);
//        
//        creditCardNumber = new JTextField();
//        creditCardNumber.setBounds(165, 542, 130, 26);
//        customerPanel.add(creditCardNumber);
//        creditCardNumber.setColumns(10);
//        
//        JLabel lblNewLabel_2_1 = new JLabel("Three Digit Number");
//        lblNewLabel_2_1.setBounds(16, 575, 138, 16);
//        customerPanel.add(lblNewLabel_2_1);
//        
//        threeDigitNumber = new JTextField();
//        threeDigitNumber.setColumns(10);
//        threeDigitNumber.setBounds(165, 570, 130, 26);
//        customerPanel.add(threeDigitNumber);
//        
//        JButton btnNewButton_1 = new JButton("Pay with Credit Card ");
//        btnNewButton_1.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        		completePaymentCreditCard(customer1, threeDigitNumber,creditCardNumber);
//        		
//        	}
//        });
//        btnNewButton_1.setBounds(6, 602, 278, 29);
//        customerPanel.add(btnNewButton_1);
//        
//        JLabel emailLable = new JLabel("Email");
//        emailLable.setBounds(437, 547, 59, 16);
//        customerPanel.add(emailLable);
//        
//        emailText = new JTextField();
//        emailText.setColumns(10);
//        emailText.setBounds(485, 542, 130, 26);
//        customerPanel.add(emailText);
//        
//        JButton payingEmail = new JButton("Pay with Email");
//        payingEmail.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        		
//        		completePaymentEmail(customer1, emailText);
//        	}
//        });
//        payingEmail.setBounds(426, 570, 278, 29);
//        customerPanel.add(payingEmail);
//        
        
        
        

        // Refresh both tables initially
        refreshAdminTable();
        //refreshCustomerTable(customer1, customerTable);
    }
    
    
    
    
    
    
    
    
    
    //Method to complete a payment
    private void completePaymentCreditCard(Customer customer, JTextField threeDigitNumber, JTextField creditCardNumber) {
    	
    	if (!threeDigitNumber.getText().matches("^\\d{3}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid three-digit number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            threeDigitNumber.setText("");
        }
    	
    	if(customer.checkAvailableItems() == false) {
    		JOptionPane.showMessageDialog(this, "Some items are not available, please check again.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	}
    	if(!creditCardNumber.getText().matches("^\\d{6}$")) {
    		JOptionPane.showMessageDialog(this, "Please enter a valid six-digit number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            creditCardNumber.setText("");
    	}
    	if(customer.checkBasketEmpty() == true) {
    		JOptionPane.showMessageDialog(this, "Please add a proper basket.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	} else {
    		try {
        		Receipt getMessage = customer.completePayment(PaymentType.CREDITCARD, Integer.parseInt(creditCardNumber.getText()), Integer.parseInt(threeDigitNumber.getText()), "");
        		JOptionPane.showMessageDialog(this, getMessage.getMessage(), "Finished Transaction", JOptionPane.OK_OPTION);
        	}
        	catch (Exception ex) {
                // If the entered text is not a valid integer, show a message to the user
                JOptionPane.showMessageDialog(this, "Please enter the right number of number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
    	}
    	
    	
    		
    }
    
  //Method to complete a payment
    private void completePaymentEmail(Customer customer, JTextField emailText) {
    	
    	
    	if(customer.checkAvailableItems() == false) {
    		JOptionPane.showMessageDialog(this, "Some items are not available, please check again.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	}
    	if(!emailText.getText().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
    		JOptionPane.showMessageDialog(this, "Please enter a valid Email", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            emailText.setText("");
    	}
    	
    	if(customer.checkBasketEmpty() == true) {
    		JOptionPane.showMessageDialog(this, "Please add a proper basket.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	}else {
    		try {
        		Receipt getMessage = customer.completePayment(PaymentType.PAYPAL, 0, 0, emailText.getText());
        		JOptionPane.showMessageDialog(this, getMessage.getMessage(), "Finished Transaction", JOptionPane.OK_OPTION);
        	}
        	catch (Exception ex) {
                // If the entered text is not a valid integer, show a message to the user
                JOptionPane.showMessageDialog(this, "Please enter the right number of number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
    	}
    	
    		
    }
    
    
    //First customer, planing to add two more
    // Method to refresh the admin table with available products
    private void refreshAdminTable() {
        List<Product> products = admin.viewAvailableProducts();
        ProductTableModel adminModel = new ProductTableModel(products);
        adminTable.setModel(adminModel);
    }
    
    
    // Methods for Customer
    //Filtering by the amount of mice buttons 
    private void filterMiceByButtons(Customer customer, JTable customerTable, JTextField miceButtonsText) {
        String buttonText = miceButtonsText.getText();
        if (buttonText.isEmpty()) {
            // If the text field is empty, show a message to the user
            JOptionPane.showMessageDialog(this, "Please enter a number of buttons to filter.", "Empty Field", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int buttonCount = Integer.parseInt(buttonText);
            List<Product> filteredProducts = customer.filterMiceByButtons(buttonCount);
            
            if (!filteredProducts.isEmpty()) {
                ProductTableModel productModel = new ProductTableModel(filteredProducts);
                customerTable.setModel(productModel);
            } else {
                // If no products match the filter criteria, show a message to the user
                JOptionPane.showMessageDialog(this, "No mice found with the specified number of buttons.", "No Match Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // If the entered text is not a valid integer, show a message to the user
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for the number of buttons.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Search by Barcode and display on table 
    private void barcodeSearching(Customer customer, JTable customerTable,JTextField barcodeSearchText) {
    	String barcodeText = barcodeSearchText.getText();
        if (barcodeText.isEmpty()) {
            // If the barcode text field is empty, show a message to the user
            JOptionPane.showMessageDialog(this, "Please enter a barcode to search.", "Empty Barcode", JOptionPane.WARNING_MESSAGE);
            return;
        }
    	int barcode = Integer.parseInt(barcodeText);
    	Product product = customer.getByBarcode(barcode);
    	
    	if (product != null) {
            List<Product> products = new ArrayList<>();
            products.add(product);
            ProductTableModel productModel = new ProductTableModel(products);
            customerTable.setModel(productModel);
        } else {
        	JOptionPane.showMessageDialog(this, "No product found for the entered barcode.", "Product Not Found", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    
    // Method to refresh the customer table with available products
    private void refreshCustomerTable(Customer customer, JTable customerTable) {
        List<Product> products = customer.viewAvailableProducts();
        ProductTableModel customerModel = new ProductTableModel(products);
        customerTable.setModel(customerModel);
    }
    
    
    //Adding a product to shopping Basket
    private void addToShoppingBasket(Customer customer,JTextField barcodeShoppingBasket) {
    	
        try {
            int barcode = Integer.parseInt(barcodeShoppingBasket.getText());
            Product productToAddToShoppingBasket = StockManager.getStockFromFileByBarcode(barcode);

            if (productToAddToShoppingBasket != null) {
                customer.addToBasket(productToAddToShoppingBasket);
                customer.viewBasketContents();
                JOptionPane.showMessageDialog(this, "Product added to the shopping basket.", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshShoppingBasketScrollPane(customer, ShoppingBasketTable);
            } else {
                JOptionPane.showMessageDialog(this, "No product found for the entered barcode.", "Product Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for the barcode.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Retriving the shopping basket
    private void refreshShoppingBasketScrollPane(Customer customer, JTable tableUpdate) {
        Map<Product, Integer> shoppingBasketContents = customer1.getShoppingBasket();
        ShoppingBasketTableModel model = new ShoppingBasketTableModel(shoppingBasketContents);
       

        // Remove any existing components from the shopping basket scroll pane
        tableUpdate.setModel(model);
    }
}
