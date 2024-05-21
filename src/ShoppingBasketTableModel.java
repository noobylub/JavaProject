// Table used to display the shopping basket, and the methods 
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingBasketTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Barcode","Brand", "Price", "Quantity"};
    private final List<Product> products;
    private final Map<Product, Integer> quantities;

    public ShoppingBasketTableModel(Map<Product, Integer> quantities) {
        this.products = new ArrayList<>(quantities.keySet());
        this.quantities = quantities;
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = products.get(rowIndex);
        switch (columnIndex) {
        	case 0:
        		return product.getBarcode();
            case 1:
                return product.getBrand();
            case 2:
                return product.getRetailPrice();
            case 3:
                return quantities.get(product);
            default:
                return null;
        }
    }
}
