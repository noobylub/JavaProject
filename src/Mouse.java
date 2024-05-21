// Two types of product keyboard and mouse 
// Mouse has additional informaiton which this class handles 
public class Mouse extends Product {
    private DeviceType deviceType;
    private int buttons;

    public Mouse(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category, DeviceType deviceType, int buttons) {
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        this.deviceType = deviceType;
        this.buttons = buttons;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public int getButtons() {
        return buttons;
    }
}