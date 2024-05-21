import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

// The panel to display each of the customers
public class CustomerPanel extends JPanel {

    private Customer customer;
    private JTable customerTable;
    private JTextField barcodeSearchText;
    private JTextField miceButtonsText;
    private JTextField barcodeShoppingBasket;
    private JTextField creditCardNumber;
    private JTextField threeDigitNumber;
    private JTextField emailText;
    private JTable ShoppingBasketTable;

    public CustomerPanel(Customer customer) {
    	
    	//Setup 
        this.customer = customer;
        setLayout(null);

        // Tab contents 
        JScrollPane customerScrollPane = new JScrollPane();
        customerScrollPane.setBounds(6, 6, 747, 158);
        add(customerScrollPane);
        customerTable = new JTable();
        customerScrollPane.setViewportView(customerTable);

        JButton customerRefreshButton = new JButton("View Products by Price ");
        customerRefreshButton.addActionListener(e -> refreshCustomerTable());
        customerRefreshButton.setBounds(6, 176, 198, 29);
        add(customerRefreshButton);

        JScrollPane shoppingBasketScroll = new JScrollPane();
        shoppingBasketScroll.setBounds(0, 334, 747, 158);
        add(shoppingBasketScroll);
        ShoppingBasketTable = new JTable();
        shoppingBasketScroll.setViewportView(ShoppingBasketTable);

        barcodeSearchText = new JTextField();
        barcodeSearchText.setBounds(218, 176, 147, 29);
        add(barcodeSearchText);
        barcodeSearchText.setColumns(10);

        JButton btnNewButton = new JButton("Barcode Search");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                barcodeSearching();
            }
        });
        btnNewButton.setBounds(361, 177, 138, 26);
        add(btnNewButton);

        miceButtonsText = new JTextField();
        miceButtonsText.setBounds(511, 176, 130, 26);
        add(miceButtonsText);
        miceButtonsText.setColumns(10);

        JButton miceButton = new JButton("Filter Mice");
        miceButton.setBounds(636, 176, 117, 29);
        add(miceButton);
        miceButton.addActionListener(e -> filterMiceByButtons());

        JLabel lblNewLabel = new JLabel("Shopping Basket");
        lblNewLabel.setBounds(6, 318, 130, 16);
        add(lblNewLabel);

        barcodeShoppingBasket = new JTextField();
        barcodeShoppingBasket.setBounds(16, 251, 130, 26);
        add(barcodeShoppingBasket);
        barcodeShoppingBasket.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Barcode to add to Shopping Basket");
        lblNewLabel_1.setBounds(16, 234, 329, 16);
        add(lblNewLabel_1);

        JButton addShoppingBasket = new JButton("Add to Shopping Basket");
        addShoppingBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToShoppingBasket();
            }
        });
        addShoppingBasket.setBounds(6, 277, 187, 29);
        add(addShoppingBasket);

        JButton retrieveShoppingBasket = new JButton("Retrieve Shopping Basket");
        retrieveShoppingBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshShoppingBasketScrollPane();
            }
        });
        retrieveShoppingBasket.setBounds(6, 492, 225, 29);
        add(retrieveShoppingBasket);

        JButton basketClearing = new JButton("Clear Basket");
        basketClearing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customer.cancelShoppingBasket();
                refreshShoppingBasketScrollPane();
            }
        });
        basketClearing.setBounds(243, 492, 117, 29);
        add(basketClearing);

        JLabel lblNewLabel_2 = new JLabel("Credit Card Number");
        lblNewLabel_2.setBounds(16, 547, 138, 16);
        add(lblNewLabel_2);

        creditCardNumber = new JTextField();
        creditCardNumber.setBounds(165, 542, 130, 26);
        add(creditCardNumber);
        creditCardNumber.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Three Digit Number");
        lblNewLabel_2_1.setBounds(16, 575, 138, 16);
        add(lblNewLabel_2_1);

        threeDigitNumber = new JTextField();
        threeDigitNumber.setColumns(10);
        threeDigitNumber.setBounds(165, 570, 130, 26);
        add(threeDigitNumber);

        JButton btnNewButton_1 = new JButton("Pay with Credit Card ");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                completePaymentCreditCard();
            }
        });
        btnNewButton_1.setBounds(6, 602, 278, 29);
        add(btnNewButton_1);

        JLabel emailLable = new JLabel("Email");
        emailLable.setBounds(437, 547, 59, 16);
        add(emailLable);

        emailText = new JTextField();
        emailText.setColumns(10);
        emailText.setBounds(485, 542, 130, 26);
        add(emailText);

        JButton payingEmail = new JButton("Pay with Email");
        payingEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                completePaymentEmail();
            }
        });
        payingEmail.setBounds(426, 570, 278, 29);
        add(payingEmail);

        // Refresh customer table initially
        refreshCustomerTable();
    }

    // Invoked when there is an update to stock text, and retrieve it to the local table
    private void refreshCustomerTable() {
        List<Product> products = customer.viewAvailableProducts();
        ProductTableModel customerModel = new ProductTableModel(products);
        customerTable.setModel(customerModel);
    }

    //Barcode search and some validation
    private void barcodeSearching() {
        String barcodeText = barcodeSearchText.getText();
        if (barcodeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a barcode to search.", "Empty Barcode", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int barcode = Integer.parseInt(barcodeText);
            Product product = customer.getByBarcode(barcode);
            if (product != null) {
                List<Product> products = List.of(product);
                ProductTableModel productModel = new ProductTableModel(products);
                customerTable.setModel(productModel);
            } else {
                JOptionPane.showMessageDialog(this, "No product found for the entered barcode.", "Product Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for the barcode.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Filter by amount of buttons
    private void filterMiceByButtons() {
        String buttonText = miceButtonsText.getText();
        if (buttonText.isEmpty()) {
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
                JOptionPane.showMessageDialog(this, "No mice found with the specified number of buttons.", "No Match Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for the number of buttons.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addToShoppingBasket() {
        try {
            int barcode = Integer.parseInt(barcodeShoppingBasket.getText());
            Product productToAddToShoppingBasket = StockManager.getStockFromFileByBarcode(barcode);

            if (productToAddToShoppingBasket != null) {
                customer.addToBasket(productToAddToShoppingBasket);
                customer.viewBasketContents();
                JOptionPane.showMessageDialog(this, "Product added to the shopping basket.", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshShoppingBasketScrollPane();
            } else {
                JOptionPane.showMessageDialog(this, "No product found for the entered barcode.", "Product Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for the barcode.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Retrive the latest shopping basket to the display 
    private void refreshShoppingBasketScrollPane() {
        Map<Product, Integer> shoppingBasketContents = customer.getShoppingBasket();
        ShoppingBasketTableModel model = new ShoppingBasketTableModel(shoppingBasketContents);
        ShoppingBasketTable.setModel(model);
    }

    //Payment methods 
    private void completePaymentCreditCard() {
        String creditCardNumberText = creditCardNumber.getText();
        String threeDigitNumberText = threeDigitNumber.getText();

        if (creditCardNumberText.isEmpty() || !creditCardNumberText.matches("^\\d{6}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid six-digit credit card number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (threeDigitNumberText.isEmpty() || !threeDigitNumberText.matches("^\\d{3}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid three-digit security code.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!customer.checkAvailableItems()) {
            JOptionPane.showMessageDialog(this, "Some items are not available, please check again.", "Unavailable Items", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (customer.checkBasketEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add items to the shopping basket before proceeding.", "Empty Basket", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int creditCardNumber = Integer.parseInt(creditCardNumberText);
            int threeDigitNumber = Integer.parseInt(threeDigitNumberText);
            Receipt receipt = customer.completePayment(PaymentType.CREDITCARD, creditCardNumber, threeDigitNumber, "");
            JOptionPane.showMessageDialog(this, receipt.getMessage(), "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for credit card number and security code.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void completePaymentEmail() {
        String emailAddress = emailText.getText();

        if (emailAddress.isEmpty() || !emailAddress.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!customer.checkAvailableItems()) {
            JOptionPane.showMessageDialog(this, "Some items are not available, please check again.", "Unavailable Items", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (customer.checkBasketEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add items to the shopping basket before proceeding.", "Empty Basket", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Receipt receipt = customer.completePayment(PaymentType.PAYPAL, 0, 0, emailAddress);
        JOptionPane.showMessageDialog(this, receipt.getMessage(), "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
    }

}
