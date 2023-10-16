package cart;

import entity.Product;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {
    
    private List<ShoppingCartItem> shoppingCart = new ArrayList<ShoppingCartItem>();

    public synchronized void addItem(Product product){
        boolean containsItem = false;
        for (ShoppingCartItem item: shoppingCart) {
            if (item.getProduct().getId() == product.getId()){
                containsItem = true;
                item.setQuantity(item.getQuantity()+1);
                break;
            }
        }
        if (!containsItem){
            System.out.println("adding item");
            ShoppingCartItem item = new ShoppingCartItem(product);
            item.setQuantity(1);
            this.shoppingCart.add(item);
            
        }
    }

    public synchronized void update(Product product, int quantity) {
        this.shoppingCart.get(this.shoppingCart.indexOf(product)).setQuantity(quantity);
    }

    public synchronized List<ShoppingCartItem> getItems() {
        return shoppingCart;
    }

    public synchronized int getNumberOfItems() {
        int numberOfItems = 0;
        for (ShoppingCartItem item: shoppingCart) {
            System.out.println(item);
            numberOfItems += item.getQuantity();
        }
        return numberOfItems;
    }

    public synchronized double getTotal() {
        double total = 0;
        for (ShoppingCartItem item: shoppingCart) {
            total += item.getTotal();
        }
        return total;
    }

    public synchronized void clear() {
        this.shoppingCart.clear();
    }
    

}