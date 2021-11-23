package com.switchfully.eurder.entities;


import java.util.UUID;


public class User {
    public enum Role {CUSTOMER, ADMIN}
    private final String id;
    private final String firstName;
    private final String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private Role role;


    public User(String firstName, String lastName, String email, String address, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = Role.CUSTOMER;
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

    public String getId() {
        return id;
    }

    public boolean isAdmin() {
        return role.equals(Role.ADMIN);
    }

    public User setAdmin() {
        this.role = Role.ADMIN;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
