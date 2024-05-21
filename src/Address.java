//The address of the user
public class Address {
    private String streetAddress;
   
    private String state;
    private String postalCode;

    public Address(String streetAddress,  String postalCode, String state) {
    	// Add validation for address 
    	
        this.streetAddress = streetAddress;
       
        this.state = state;
        this.postalCode = postalCode;
    }

    // Getter methods
    public String getStreetAddress() {
        return streetAddress;
    }

  

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    // Setter methods
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

   
    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
