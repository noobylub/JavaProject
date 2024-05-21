// Table model to manage product data for general
import java.util.List;

public class ProductTableModel extends javax.swing.table.AbstractTableModel {

        private static final long serialVersionUID = 1L;
        private String[] columnNames = {"Barcode", "Category", "Device Type", "Brand", "Color", "Connectivity", "Quantity", "Original Cost", "Retail Price", "Details"};
        private List<Product> products;

        public ProductTableModel(List<Product> products) {
            this.products = products;
        }

        @Override
        public int getRowCount() {
            return products.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            Product product = products.get(row);
            switch (column) {
                case 0:
                    return product.getBarcode();
                case 1:
                    return product.getCategory();
                case 2:
                    if (product instanceof Keyboard) {
                        return ((Keyboard) product).getDeviceType();
                    } else if (product instanceof Mouse) {
                        return ((Mouse) product).getDeviceType();
                    }
                    return "";
                case 3:
                    return product.getBrand();
                case 4:
                    return product.getColor();
                case 5:
                    return product.getConnectivity();
                case 6:
                    return product.getQuantityInStock();
                case 7:
                    return product.getOriginalCost();
                case 8:
                    return product.getRetailPrice();
                case 9:
                    if (product instanceof Keyboard) {
                        return ((Keyboard) product).getCountry();
                    } else if (product instanceof Mouse) {
                        return ((Mouse) product).getButtons();
                    }
                    return "";
                default:
                    return "";
            }
        }
    }