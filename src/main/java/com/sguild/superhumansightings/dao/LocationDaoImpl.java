/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Location;
import com.sguild.superhumansightings.dto.Superhuman;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class LocationDaoImpl implements LocationDao {

    private static final String MYSQL_LAST_ID = "SELECT LAST_INSERT_ID();";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_ALL_LOCATIONS
            = "SELECT * FROM Locations;";

    @Override
    public List<Location> getAllLocations() {
        List<Location> locationList = jdbcTemplate.query(SQL_GET_ALL_LOCATIONS, new LocationMapper());

        return locationList;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_LOCATION
            = "SELECT * FROM Locations WHERE LocationId = ?;";

    @Override
    public Location getLocation(int locationId) {
        Location desiredLocation = jdbcTemplate.queryForObject(SQL_GET_LOCATION, new LocationMapper(), locationId);
        
        return desiredLocation;
    }

    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_ADD_LOCATION
            = "INSERT INTO Locations (Landmark, Description, StreetAddress, City, State, Zip, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ? ,?);";
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Location addLocation(Location location) {
        jdbcTemplate.update(SQL_ADD_LOCATION,
                location.getLandmark(),
                location.getDescription(),
                location.getStreetAddress(),
                location.getCity(),
                location.getState(),
                location.getZipCode(),
                location.getLatitude(),
                location.getLongitude());
        
        int locationId = jdbcTemplate.queryForObject(MYSQL_LAST_ID, Integer.class);
        location.setLocationId(locationId);
        
        return location;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_UPDATE_LOCATION
            = "UPDATE Locations SET "
            + "Landmark = ?, "
            + "Description = ?, "
            + "StreetAddress = ?, "
            + "City = ?, "
            + "State = ?, "
            + "Zip = ?, "
            + "Latitude = ?, "
            + "Longitude = ? "
            + "WHERE LocationId = ?";

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLandmark(),
                location.getDescription(),
                location.getStreetAddress(),
                location.getCity(),
                location.getState(),
                location.getZipCode(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_DELETE_LOCATION = "DELETE FROM Locations WHERE LocationId = ?";

    @Override
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    

    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_LOCATIONS_FOR_HERO
            = "SELECT LocationId, Landmark, Description, StreetAddress, City, State, Zip, Latitude, Longitude "
            + "FROM Locations "
            + "LEFT JOIN Sightings USING (LocationId) "
            + "LEFT JOIN SupersSightings USING (SightingId)" 
            + "LEFT JOIN HeroesAndVillains USING (SuperId)"
            + "WHERE SuperId = ?";
    
    @Override
    public List<Location> getLocationsForSuperhuman(Superhuman superhuman) {
        List<Location> locationsForHero = jdbcTemplate.query(SQL_GET_LOCATIONS_FOR_HERO, new LocationMapper(), superhuman.getSuperhumanId());
        return locationsForHero;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();

            location.setLocationId(rs.getInt("LocationId"));
            location.setLandmark(rs.getString("Landmark"));
            location.setDescription(rs.getString("Description"));
            location.setStreetAddress(rs.getString("StreetAddress"));
            location.setCity(rs.getString("City"));
            location.setState(rs.getString("State"));
            location.setZipCode(rs.getInt("Zip"));
            location.setLatitude(rs.getBigDecimal("Latitude"));
            location.setLongitude(rs.getBigDecimal("Longitude"));

            return location;
        }
    }

}
