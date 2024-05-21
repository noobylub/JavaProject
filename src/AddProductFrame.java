import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductFrame extends JFrame {

    private JPanel contentPane;
    private JTextField barcodeTextField;
    private JTextField brandTextField;
    private JTextField colorTextField;
    private JTextField quantityTextField;
    private JTextField originalCostTextField;
    private JTextField retailPriceTextField;

    // Additional fields for Mouse
    private JLabel lblMouseButtons;
    private JTextField buttonsTextField;

    // Additional fields for Keyboard
    private JLabel lblKeyboardType;
    private JTextField keyboardTypeTextField;
    private JComboBox connection;
    private JLabel DeviceTypeAsk;
    private JComboBox deviceTypeAsk;
    private JComboBox productCategoryCB;

    public AddProductFrame(Admin admin) {
        setTitle("Add New Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel lblBarcode = new JLabel("Barcode:");
        panel.add(lblBarcode);

        barcodeTextField = new JTextField();
        panel.add(barcodeTextField);
        barcodeTextField.setColumns(10);

        JLabel lblCategory = new JLabel("Category:");
        panel.add(lblCategory);
        
        productCategoryCB = new JComboBox(ProductCategory.values());
        panel.add(productCategoryCB);

        JLabel lblBrand = new JLabel("Brand:");
        panel.add(lblBrand);

        brandTextField = new JTextField();
        panel.add(brandTextField);
        brandTextField.setColumns(10);

        JLabel lblColor = new JLabel("Color:");
        panel.add(lblColor);

        colorTextField = new JTextField();
        panel.add(colorTextField);
        colorTextField.setColumns(10);

        JLabel lblConnectivity = new JLabel("Connectivity:");
        panel.add(lblConnectivity);
        
        connection = new JComboBox(ConnectivityType.values());
        panel.add(connection);

        JLabel lblQuantity = new JLabel("Quantity:");
        panel.add(lblQuantity);

        quantityTextField = new JTextField();
        panel.add(quantityTextField);
        quantityTextField.setColumns(10);

        JLabel lblOriginalCost = new JLabel("Original Cost:");
        panel.add(lblOriginalCost);

        originalCostTextField = new JTextField();
        panel.add(originalCostTextField);
        originalCostTextField.setColumns(10);

        JLabel lblRetailPrice = new JLabel("Retail Price:");
        panel.add(lblRetailPrice);

        retailPriceTextField = new JTextField();
        panel.add(retailPriceTextField);
        retailPriceTextField.setColumns(10);

        // Additional field for Mouse - Number of Buttons
        lblMouseButtons = new JLabel("Number of Buttons:");
        panel.add(lblMouseButtons);

        buttonsTextField = new JTextField();
        panel.add(buttonsTextField);
        buttonsTextField.setColumns(10);

        // Additional field for Keyboard - Keyboard Type
        lblKeyboardType = new JLabel("Keyboard Type:");
        panel.add(lblKeyboardType);

        keyboardTypeTextField = new JTextField();
        panel.add(keyboardTypeTextField);
        keyboardTypeTextField.setColumns(10);
        
        DeviceTypeAsk = new JLabel("Device Type");
        panel.add(DeviceTypeAsk);
        
        deviceTypeAsk = new JComboBox(DeviceType.values());
        panel.add(deviceTypeAsk);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 if (barcodeTextField.getText().isEmpty() || brandTextField.getText().isEmpty() ||
            	            colorTextField.getText().isEmpty() || quantityTextField.getText().isEmpty() ||
            	            originalCostTextField.getText().isEmpty() || retailPriceTextField.getText().isEmpty() ||
            	            (productCategoryCB.getSelectedItem() == null) || (connection.getSelectedItem() == null) ||
            	            (deviceTypeAsk.getSelectedItem() == null)) {
            	            JOptionPane.showMessageDialog(AddProductFrame.this,
            	                    "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            	            return;
            	        }
            	
            	
                // Get the product details from the text fields
                int barcode = Integer.parseInt(barcodeTextField.getText());
                ProductCategory category = (ProductCategory) productCategoryCB.getSelectedItem();
                //DeviceType device = (DeviceType) 
                String brand = brandTextField.getText();
                String color = colorTextField.getText();
                ConnectivityType connectivity =(ConnectivityType) connection.getSelectedItem();
                DeviceType devicetp =(DeviceType) deviceTypeAsk.getSelectedItem();
                int quantity = Integer.parseInt(quantityTextField.getText());
                double originalCost = Double.parseDouble(originalCostTextField.getText());
                double retailPrice = Double.parseDouble(retailPriceTextField.getText());
                //String details = detailsTextField.getText();

                // Determine the product category and additional details
                String additionalDetails = "";
                if (category.equals(ProductCategory.MOUSE)) {
                    additionalDetails = buttonsTextField.getText();
                } else if (category.equals(ProductCategory.KEYBOARD)) {
                    additionalDetails = keyboardTypeTextField.getText();
                }

                // Create a new product based on the provided details
                Product product = null;
                if (category != null ) {
                    if (category.equals(ProductCategory.MOUSE)) {
                        // If category is mouse, create a Mouse product
                        int buttons = Integer.parseInt(additionalDetails);
                        product = new Mouse(barcode, brand, color, connectivity, quantity, originalCost, retailPrice, ProductCategory.MOUSE, devicetp, buttons);
                    } else if (category.equals(ProductCategory.KEYBOARD)) {
                        // If category is keyboard, create a Keyboard product
                        product = new Keyboard(barcode, brand, color, connectivity, quantity, originalCost, retailPrice, ProductCategory.KEYBOARD, devicetp, KeyboardLayout.valueOf(additionalDetails));
                    } 
                } 

                // Call the addProduct method from the admin instance
                admin.addProduct(product, quantity);

                // Close the frame after saving
                dispose();
            }
        });
        contentPane.add(btnSave, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
}
