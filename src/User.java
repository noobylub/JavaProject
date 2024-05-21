// The users of the app, both any kind of users must be able to view products
// IN the future, we can possibly implement other type of users, but all users MUST BE ABLE TO VIEW 
import java.util.*;

public interface User {
	List<Product> viewAvailableProducts();
}
