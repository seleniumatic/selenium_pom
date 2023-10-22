package com.example.selenium.common;

public class CustomerInfoBuilder {
    private String name;
    private String gender;
    private String dob;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String mobileNumber;
    private String email;
    private String password;

    public CustomerInfoBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public CustomerInfoBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }
    public CustomerInfoBuilder setDob(String dob) {
        this.dob = dob;
        return this;
    }
    public CustomerInfoBuilder setAddress(String address) {
        this.address = address;
        return this;
    }
    public CustomerInfoBuilder setCity(String city) {
        this.city = city;
        return this;
    }
    public CustomerInfoBuilder setState(String state) {
        this.state = state;
        return this;
    }
    public CustomerInfoBuilder setPin(String pin) {
        this.pin = pin;
        return this;
    }
    public CustomerInfoBuilder setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }
    public CustomerInfoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }
    public CustomerInfoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerInfo build() {
        return new CustomerInfo(name, gender, dob, address, city, state, pin, mobileNumber, email, password);
    } 
}
