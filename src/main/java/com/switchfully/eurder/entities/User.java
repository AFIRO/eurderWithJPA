package com.switchfully.eurder.entities;


import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    public enum Role {CUSTOMER, ADMIN}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private  String userId;
    @Column (name = "user_first_name")
    private  String firstName;
    @Column (name = "user_last_name")
    private  String lastName;
    @Column (name = "user_email")
    private String email;
    @Column (name = "user_address")
    private String address;
    @Column (name = "user_phonenumber")
    private String phoneNumber;
    @Column (name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {

    }

    public User(String firstName, String lastName, String email, String address, String phoneNumber) {
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

    public String getUserId() {
        return userId;
    }

    public boolean isAdmin() {
        return role.equals(Role.ADMIN);
    }

    public Role getRole() {
        return role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
