package com.example.yournextflight;

import java.util.ArrayList;

public class User
{
    String userId;
    String firstName;
    String lastName;
    String city;
    String streetAddress;
    String phoneNumber;
    ArrayList<Flight> myFlights;

    public User()
    {

    }

    public User(String userId, String firstName, String lastName, String city, String streetAddress, String phoneNumber)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.streetAddress = streetAddress;
        this.phoneNumber = phoneNumber;
        myFlights = new ArrayList<Flight>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Flight> getMyFlights() {
        return myFlights;
    }
}
