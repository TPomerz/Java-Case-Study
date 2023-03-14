package com.ecommerce.dto;

public class EcommerceRegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private String country;

    public EcommerceRegisterRequest() {
    }

    public EcommerceRegisterRequest(String email, String password, String firstName, String lastName, String country) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String getCountry) {
        this.country = getCountry;
    }

    @Override
    public String toString() {
        return "EcommerceRegisterRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", getCountry='" + country + '\'' +
                '}';
    }
}
