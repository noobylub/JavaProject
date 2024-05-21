//For interacting with text database for Users
import java.io.*;

import java.util.*;
public class UserManager {
	 private static final String FILE_NAME = "UserAccounts.txt";
	 
	 	//Gets all the list of Users, both customers and Admin,they share same interface and superclass
	    public static List<User> readUserFromFile() {
	        List<User> allUser = new ArrayList<>();
	       
	        //Reading lines from the text
	        try (BufferedReader br = new BufferedReader(new FileReader("UserAccounts.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	            	
	                String[] parts = line.split(", ");
	               
	                if(parts[6].equals("customer")) {
	                	int systemsID = Integer.parseInt(parts[0]);
//		                String personName = parts[2];     
		                allUser.add(new Customer(systemsID, new Address(parts[3], parts[4], parts[5] ),parts[2]));
	                }else if(parts[6].equals("admin")){
	                	int systemsID = Integer.parseInt(parts[0]);
		                   
		                allUser.add(new Admin(systemsID, new Address(parts[3], parts[4], parts[5] ),parts[2]));
	                }
	                
	                
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return allUser;
	    }
	    
	    
//	   TESTING PURPOSES
//	    public static void main(String[] args) {
//	    	//System.out.println("Current working directory: " + System.getProperty("user.dir"));
//
//	    	List<User> users = readUserFromFile();
//	    	for(User user: users) {
//	    		if (user instanceof Admin) {
//	    	        // It's an Admin
//	    	        Admin admin = (Admin) user;
//	    	        System.out.println("Admin Information:");
//	    	        System.out.println("Systems ID: " + admin.getSystemID());
//	    	        System.out.println("Name: " + admin.getPersonName());
//	    	        // Print additional information specific to Admin if needed
//	    	    } 
//	    		if (user instanceof Customer) {
//	    	        // It's an Admin
//	    	        Customer customer = (Customer) user;
//	    	        System.out.println("Customer Information:");
//	    	        System.out.println("Systems ID: " + customer.getSystemID());
//	    	        System.out.println("Name: " + customer.getPersonName());
//	    	        // Print additional information specific to Admin if needed
//	    	    } 
//	    	}
//	    }

}
