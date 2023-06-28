package org.kainos.ea.cli;

public class Customer {
    private int customerID;
    private String name;
    private String address;
    private String phone;

    public Customer(int customerID, String name, String address, String phone) {
        setCustomerID(customerID);
        setName(name);
        setAddress(address);
        setPhone(phone);
    }

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
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
