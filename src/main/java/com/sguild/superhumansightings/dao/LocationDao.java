/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Location;
import com.sguild.superhumansightings.dto.Superhuman;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface LocationDao {
    
    public List<Location> getAllLocations();
    
    public Location getLocation(int locationId);
    
    public Location addLocation(Location location);
    
    public void updateLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public List<Location> getLocationsForSuperhuman(Superhuman superhuman);
    
}
