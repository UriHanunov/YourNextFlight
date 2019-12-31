package com.example.yournextflight;

import java.util.Date;

class Flight {

    String flightId;
    String source;
    String destination;
    String time;
    String date;
    int price;
    int places=150;

    public Flight(){

    }

    public Flight(String flightId, String source, String destination, String time, String date, int price) {
        this.flightId = flightId;
        this.destination = destination;
        this.source = source;
        this.time = time;
        this.date = date;
        this.price=price;
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
}
