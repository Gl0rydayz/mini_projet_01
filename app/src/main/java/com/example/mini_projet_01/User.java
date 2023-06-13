package com.example.mini_projet_01;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public class User {
    private String firstName;
    private String lastName;
    private String gender;
    private String city;

    //Add image attribute
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //endregion


    //region Constructor
    public User(String firstName, String lastName, String gender, String city, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.city = city;
        this.image = image;
    }
    //endregion

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
}
