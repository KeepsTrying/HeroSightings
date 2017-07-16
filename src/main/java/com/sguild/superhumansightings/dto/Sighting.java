/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dto;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class Sighting {

    private int sightingId;

    private int locationId;

    private LocalDate date;

    private Map<Integer, Superhuman> sighted;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<Integer, Superhuman> getSighted() {
        return sighted;
    }

    public void setSighted(Map<Integer, Superhuman> sighted) {
        this.sighted = sighted;
    }

}
