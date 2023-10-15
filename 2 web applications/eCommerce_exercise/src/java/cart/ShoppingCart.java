package cart;

import entity.Product;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {
    
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();

    public synchronized void addItem(Product product){
        boolean containsItem = false;
        for (ShoppingCartItem item: shoppingCartItems) {
            if (item.getProduct().getId() == product.getId()){
                containsItem = true;
                item.setQuantity(item.getQuantity()+1);
                break;
            }
        }
        if (!containsItem){
            this.shoppingCartItems.add(new ShoppingCartItem(product));
        }
    }

    public synchronized void update(Product product, int quantity) {
        this.shoppingCartItems.get(this.shoppingCartItems.indexOf(product)).setQuantity(quantity);
    }

    public synchronized List<ShoppingCartItem> getItems() {
        return shoppingCartItems;
    }

    public synchronized int getNumberOfItems() {
        int numberOfItems = 0;
        for (ShoppingCartItem item: shoppingCartItems) {
            numberOfItems += item.getQuantity();
        }
        return numberOfItems;
    }

    public synchronized double getTotal() {
        double total = 0;
        for (ShoppingCartItem item: shoppingCartItems) {
            total += item.getTotal();
        }
        return total;
    }

    public synchronized void clear() {
        this.shoppingCartItems.clear();
    }
    

}