package cart;

import entity.Product;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {

    public synchronized void addItem(Product product){

    }

    public synchronized void update(Product product, int quantity) {

    }

    //public synchronized List<ShoppingCartItem> getItems() {
        
    //}

    public synchronized int getNumberOfItems() {
        return 1;
    }

    public synchronized double getTotal() {
        return 1.1;
    }

    public synchronized void clear() {
        
    }
    

}