package org.kainos.ea.cli;

import java.util.Date;

public class Customer {
    private int customerID;
    private String name;
    private String Address;

    public Customer(int customerID, String name, String address, String phone) {
        this.customerID = customerID;
        this.name = name;
        Address = address;
        this.phone = phone;
    }

    private String phone;


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




}
