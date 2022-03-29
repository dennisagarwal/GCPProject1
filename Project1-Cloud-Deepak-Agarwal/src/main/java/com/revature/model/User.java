package com.revature.model;

import java.util.Objects;

public class User {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String emailId;
    private String userRole;

    public User() {

    }

    public User(int id, String username,String firstName, String lastName,  String emailId, String userRole) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.password = password;
        this.emailId =emailId;
        this.userRole = userRole;

    }

    public User(int aId, String aUserName, String aFirst, String aLast, String aEmail) {
        this.id = aId;
        this.username =  aUserName;
        this.firstName = aFirst;
        this.lastName = aLast;
        this.emailId =aEmail;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(userRole, user.userRole) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(emailId, user.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, userRole, firstName, lastName, emailId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}

