package org.simplestore;

import org.simplestore.model.Inventory;
import org.simplestore.model.Product;
import org.simplestore.model.ProductNotFoundException;
import org.simplestore.service.ShoppingCart;
import org.simplestore.util.InventoryLoader;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        InventoryLoader.loadInventory("src/main/resources/inventory.txt", inventory);

        ShoppingCart cart1 = new ShoppingCart(inventory);
        cart1.addItem(1, 1); // + 1
        cart1.addItem(2, 1); // + 1
        cart1.addItem(3, 5); // + 5
        cart1.removeItem(3, 4); // - 4

        int numberOfProductsInCart = 0;
        for (int i = 1; i < 11; i++) {
            numberOfProductsInCart += cart1.getItemQuantity(i);
        }
        System.out.println("Number of products in cart: " + numberOfProductsInCart);
        try {
            System.out.println("Total cost: " + cart1.calculateTotalCost());
        } catch (ProductNotFoundException e) {
            System.out.println("Product " +e.getProductName() + " not found");
        }

        // Add product to inventory, list all products
        Inventory inventory1 = new Inventory();
        inventory1.addProduct(new Product(1, "Test Product", 10.0));
        inventory1.addProduct(new Product(2, "Another Product", 20.0));
        inventory1.addProduct(new Product(3, "Third Product", 30.0));

        System.out.println("Products in inventory: " + inventory1.listAllProducts());

        // Remove product from inventory, list all products
        try {
            inventory1.removeProduct(2);
        } catch (ProductNotFoundException e) {
            System.out.println("Product " +e.getProductName() + " not found");
        }
        System.out.println("Removed product 2");
        System.out.println("Products in inventory: " + inventory1.listAllProducts());
    }
}

