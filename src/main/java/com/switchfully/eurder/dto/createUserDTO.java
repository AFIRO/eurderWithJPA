package com.switchfully.eurder.dto;

public class createUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;


    public createUserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public createUserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public createUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public createUserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public createUserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
