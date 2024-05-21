// Two types of product keyboard and mouse 
// Keyboard has additional informaiton which this class handles 
public class Keyboard extends Product {
    private DeviceType deviceType;
    private KeyboardLayout country;

    public Keyboard(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category, DeviceType deviceType, KeyboardLayout country) {
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        this.deviceType = deviceType;
        this.country = country;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public KeyboardLayout getCountry() {
        return country;
    }
}