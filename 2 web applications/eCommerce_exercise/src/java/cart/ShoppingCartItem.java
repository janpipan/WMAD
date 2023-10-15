package cart;

import entity.Product;

/**
 *
 * @author juanluis
 */
public class ShoppingCartItem {
    
    private Product product;
    private int quantity;

    public ShoppingCartItem(Product product) {
        this.product = product;
    }   
    
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

}