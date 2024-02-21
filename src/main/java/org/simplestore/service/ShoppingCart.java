package org.simplestore.service;

import org.simplestore.model.Inventory;
import org.simplestore.model.Product;
import org.simplestore.model.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Inventory inventory;
    private final Map<Integer, Integer> cartItems;


    public ShoppingCart(Inventory inventory) {
        this.inventory = inventory;
        this.cartItems = new HashMap<>();
    }

    public synchronized void addItem(int productId, int quantity) {
        cartItems.merge(productId, quantity, Integer::sum);  // Equivalent of lambda (a, b) -> Integer.sum(a, b)
    }

    public synchronized void removeItem(int productId, int quantity) {
        cartItems.merge(productId, quantity, (a, b) -> a - b);
    }


    public double calculateTotalCost() throws ProductNotFoundException {
        double totalCost = 0.0;
        for (Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
            Product product = inventory.getProduct(entry.getKey());
            totalCost += product.price() * entry.getValue();
        }
        return Double.parseDouble(String.format("%.2f", totalCost));
    }

    public void clearCart() {
        cartItems.clear();
    }

    public int getItemQuantity(int productId) {
        return cartItems.getOrDefault(productId, 0);
    }


}
