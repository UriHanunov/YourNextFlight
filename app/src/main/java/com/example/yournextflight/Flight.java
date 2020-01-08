package com.example.yournextflight;

import java.util.Date;

class Flight {

    String flightId;
    String source;
    String destination;
    String time;
    String date;
    int price;
    int places;

    public Flight(){

    }

    public Flight(String flightId, String source, String destination, String time, String date, int price, int place) {
        this.flightId = flightId;
        this.destination = destination;
        this.source = source;
        this.time = time;
        this.date = date;
        this.price=price;
        this.places=place;
    }

    public String getFlightId() {
        return flightId;
    }

    public int getprice() {
        return price;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public int getplaces() {
        return places;
    }

    public void setplaces() {
         places--;
    }

    public void setplacesPlus() {
         places++;
    }


}
