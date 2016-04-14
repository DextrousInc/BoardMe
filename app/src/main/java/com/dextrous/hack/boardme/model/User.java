package com.dextrous.hack.boardme.model;


import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Date createdTS;
    private String currencyType;
    private String email;
    private String firstName;
    private String fullName;
    private Integer id;
    private String lastName;
    private Date lastUpdatedTS;
    private String username;
    private Float wallet;

    public Date getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(Date createdTS) {
        this.createdTS = createdTS;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastUpdatedTS() {
        return lastUpdatedTS;
    }

    public void setLastUpdatedTS(Date lastUpdatedTS) {
        this.lastUpdatedTS = lastUpdatedTS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getWallet() {
        return wallet;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "User{" +
                "createdTS=" + createdTS +
                ", currencyType='" + currencyType + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", lastUpdatedTS=" + lastUpdatedTS +
                ", username='" + username + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
