package org.simplestore.model;

public class ProductNotFoundException extends Exception {

    private String productName;

    public ProductNotFoundException(String message) {
        super("Product not found. " + message);

    }
    public ProductNotFoundException(String message, String productName) {
        super("Product not found: " + message);
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
