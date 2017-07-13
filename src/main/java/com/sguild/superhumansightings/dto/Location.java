/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Location {
    
    //LocationId, Landmark, Description, StreetAddress, City, State, Zip, Latitude, Longitude
    
    private int locationId;
    
    private String landmark;
    
    private String description;
    
    private String streetAddress;
    
    private String city;
    
    private String state;
    
    private int zipCode;
    
    private BigDecimal Latitude;
    
    private BigDecimal Longitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public BigDecimal getLatitude() {
        return Latitude;
    }

    public void setLatitude(BigDecimal Latitude) {
        this.Latitude = Latitude;
    }

    public BigDecimal getLongitude() {
        return Longitude;
    }

    public void setLongitude(BigDecimal Longitude) {
        this.Longitude = Longitude;
    }



    
}
