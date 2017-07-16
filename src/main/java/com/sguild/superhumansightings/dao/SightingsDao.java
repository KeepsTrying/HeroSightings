/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SightingsDao {
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsFromDate(LocalDate date);
    
    public List<Sighting> getAllSightingsAtLocation(int locationId);
    
    public List<Sighting> getAllSightingsOfSuperhuman(int superhumanId);
    
    public Sighting getSighting(int sightingId);
    
    public Sighting addSighting(Sighting sighting);
    
    public void updateSighting(Sighting sighting);
    
    public void deleteSighting(int sightingId);
    
}
