package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private Date createdDate;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public Date getCreatedDate() {return createdDate;}

    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate;}

@JsonCreator
public ProductRequest (
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("price") Double price,
    @JsonProperty("createdDate") Date createdDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdDate = createdDate;
    }
}



