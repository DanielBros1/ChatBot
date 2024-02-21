package org.simplestore.model;

public record Product(int id, String name, double price) {

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" +"'" + name + "'" +
                ", price=" + price +
                '}';
    }
}
