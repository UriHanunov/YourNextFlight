package com.example.yournextflight;

class Flight {

    String FlightId;
    String source;
    String destination;
    String Time;
    String date;
    int price;
    int places=150;

    public Flight(){

    }

    public Flight(String flightId, String source, String destination, String time, String date, int price) {
        FlightId = flightId;
        this.destination = destination;
        this.source = source;
        Time = time;
        this.date = date;
        this.price=price;
    }

    public String getFlightId() {
        return FlightId;
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
        return Time;
    }

    public String getDate() {
        return date;
    }
}
