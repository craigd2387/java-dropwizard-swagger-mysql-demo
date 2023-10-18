package org.kainos.ea.cli;

import java.util.Date;

public class Product implements Comparable<Product> {
    private int productID;
    private String name;
    private String description;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    private Date createdDate;

    public Product(int productID, String name, String description, double price, Date createdDate) {
       setProductID(productID);
        setName(name);
        setDescription(description);
        setPrice(price);
        setCreatedDate(createdDate);
    }

    private double price;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public int compareTo(Product product) {
        //return this.getName().compareTo(product.getName());
        return Double.compare(this.getPrice(), product.getPrice());
    }

    @Override
    public String toString(){
        return "Product name "+this.getName()+", Product price: £"+this.getPrice();
    }
}
