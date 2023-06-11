package com.example.mini_projet_01;

import androidx.annotation.NonNull;

public class User {
    private String firstName;
    private String lastName;
    private String gender;
    private String city;

    //region Getters and Setters
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    //endregion


    //region Constructor
    public User(String firstName, String lastName, String gender, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.city = city;
    }
    //endregion

    //region Methods
    public String fullName() {
        return String.format("%s%s %s", this.getFirstName().substring(0,1).toUpperCase(), this.getFirstName().substring(1).toLowerCase(), this.getLastName().toUpperCase());
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Hi, i'm %s, i am %s\nI live in %s",
                this.fullName(),
                this.getGender().equals("male") ? "♂("+this.getGender()+")" : "♀("+this.getGender()+")",
                this.getCity());
    }
    //endregion
}
