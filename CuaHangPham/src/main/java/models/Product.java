package models;

import service.IModel;

public class Product implements IModel<Product> {
    private long idProduct;
    private String name;
    private double price;
    private int quantity;
    private String description;

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.getIdProduct(),this.getName(),this.getPrice(),this.getQuantity(),this.getDescription());
    }

    public Product(long idProduct, String name, double price, int quantity, String description) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Product() {
    }

    @Override
    public Product parseData(String line) {
        String[] array = line.split(",");
        Product product = new Product();
        product.setIdProduct(Long.parseLong(array[0]));
        product.setName(array[1]);
        product.setPrice(Double.parseDouble(array[2]));
        product.setQuantity(Integer.parseInt(array[3]));
        product.setDescription(array[4]);
        return product;
    }
}
