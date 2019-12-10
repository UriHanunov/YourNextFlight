package com.example.yournextflight;

public class Flight {

    String FlightId;
    String source;
    String destination;
    String Time;
    String date;

    public Flight(){

    }

    public Flight(String flightId, String source, String destination, String time, String date) {
        FlightId = flightId;
        this.destination = destination;
        this.source = source;
        Time = time;
        this.date = date;
    }

    public String getFlightId() {
        return FlightId;
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
