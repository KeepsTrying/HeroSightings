/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Sighting;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class SightingsDaoImpl implements SightingsDao {
    
    private static final String MYSQL_LAST_ID = "SELECT LAST_INSERT_ID();";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    //-------------------------------------------------------------------------------------------------------

    private static final String SQL_GET_ALL_SIGHTINGS
            = "SELECT * FROM Sightings;";
    
    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_GET_ALL_SIGHTINGS, new SightingMapper());
        return sightings;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_SIGHTINGS_FROM_DATE
            = "SELECT * FROM Sightings WHERE SightingDate = ?;";
    
    @Override
    public List<Sighting> getAllSightingsFromDate(LocalDate date) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_GET_SIGHTINGS_FROM_DATE, new SightingMapper(), java.sql.Date.valueOf(date));
        return sightings;
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_SIGHTINGS_AT_LOCATION
            = "SELECT * FROM Sightings WHERE LocationId = ?;";
    
    @Override
    public List<Sighting> getAllSightingsAtLocation(int locationId) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_GET_SIGHTINGS_AT_LOCATION, new SightingMapper(), locationId);
        return sightings;
    }
    
    //-------------------------------------------------------------------------------------------------------

    

    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_SIGHTINGS_OF_SUPERHUMAN
            = "SELECT SightingId, LocationId, SightingDate "
            + "FROM Sightings "
            + "LEFT JOIN SupersSightings USING (SightingId) "
            + "LEFT JOIN HeroesAndVillains USING (SuperId) "
            + "WHERE SuperId = ?;";
    
    @Override
    public List<Sighting> getAllSightingsOfSuperhuman(int superhumanId) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_GET_SIGHTINGS_OF_SUPERHUMAN, new SightingMapper(), superhumanId);
        return sightings;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_SIGHTING
            = "SELECT * FROM Sightings WHERE SightingId = ?;";

    @Override
    public Sighting getSighting(int sightingId) {
        Sighting sighting = jdbcTemplate.queryForObject(SQL_GET_SIGHTING, new SightingMapper(), sightingId);
        return sighting;
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_ADD_SIGHTING
            = "INSERT INTO Sightings (LocationId, SightingDate) "
            + "VALUES (?, ?);";
    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Sighting addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_ADD_SIGHTING, sighting.getLocationId(), Date.valueOf(sighting.getDate()));
        
        int sightingId = jdbcTemplate.queryForObject(MYSQL_LAST_ID, Integer.class);
        
        sighting.setSightingId(sightingId);
        
        return sighting;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_UPDATE_SIGHTING
            = "UPDATE Sightings SET "
            + "LocationId = ?, "
            + "SightingDate = ? "
            + "WHERE SightingId = ?";

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, sighting.getLocationId(), Date.valueOf(sighting.getDate()), sighting.getSightingId());
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_REMOVE_SIGHTINGS_FROM_SUPERHEROES
            = "DELETE FROM SupersSightings WHERE SightingId = ?";
    
    private static final String SQL_DELETE_SIGHTING
            = "DELETE FROM Sightings WHERE SightingId = ?";

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_REMOVE_SIGHTINGS_FROM_SUPERHEROES, sightingId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            
            sighting.setSightingId(rs.getInt("SightingId"));
            sighting.setLocationId(rs.getInt("LocationId"));
            sighting.setDate(rs.getDate("SightingDate").toLocalDate());
            
            return sighting;
        }
        
    }
    
}
