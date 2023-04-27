package com.example.myappl;

public class Booking {
    private String problemDescription;
    private String buildingNumber;
    private String streetArea;
    private String locality;
    private String state;
    private String country;
    private String pincode;
    private String phone;

    public Booking(String problemDescription, String buildingNumber, String streetArea, String locality, String state, String country, String pincode, String phone) {
        this.problemDescription = problemDescription;
        this.buildingNumber = buildingNumber;
        this.streetArea = streetArea;
        this.locality = locality;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.phone = phone;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getStreetArea() {
        return streetArea;
    }

    public String getLocality() {
        return locality;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPincode() {
        return pincode;
    }

    public String getPhone() {
        return phone;
    }
}

