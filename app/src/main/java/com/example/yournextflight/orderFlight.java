package com.example.yournextflight;

public class orderFlight {

    String orderId;
    String flightId;
    String userId;

    public orderFlight(String flightId, String userId, String orderId) {
        this.flightId = flightId;
        this.userId = userId;
        this.orderId= orderId;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getUserId() {
        return userId;
    }

    public String getOrderId() {
        return orderId;
    }
}
