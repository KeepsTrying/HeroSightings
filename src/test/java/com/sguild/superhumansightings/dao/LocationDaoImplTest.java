/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Location;
import com.sguild.superhumansightings.dto.Superhuman;
import java.math.BigDecimal;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class LocationDaoImplTest {

    private LocationDao dao;
    private JdbcTemplate jdbcTemplate;

    public LocationDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");

        jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        dao = ctx.getBean("locationDao", LocationDao.class);
        
        
        jdbcTemplate.update("DELETE FROM OrganizationsSupers;");
        jdbcTemplate.update("DELETE FROM SupersSightings;");
        jdbcTemplate.update("DELETE FROM Organizations;");
        jdbcTemplate.update("DELETE FROM Sightings;");
        jdbcTemplate.update("DELETE FROM Locations;");
        jdbcTemplate.update("INSERT INTO Locations (LocationId, Landmark, Description, StreetAddress, City, State, Zip, Latitude, Longitude) VALUES (1, \"The Software Guild\", \"Louisville Branch, developing developers\", \"252 E Market St\", \"Louisville\", \"KY\", 40202, 38.2540, -85.7484)");
        jdbcTemplate.update("DELETE FROM SupersSuperpowers");
        jdbcTemplate.update("DELETE FROM HeroesAndVillains");
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllLocations method, of class LocationDaoImpl.
     */
    @Test
    public void testGetAllLocations() {
        List<Location> locations = dao.getAllLocations();
        int numOfLocations = locations.size();

        Assert.assertEquals(1, numOfLocations);

        Location testLocation = locations.get(0);
        Assert.assertTrue(testLocation.getState().equalsIgnoreCase("KY"));
    }

    /**
     * Test of getLocation method, of class LocationDaoImpl.
     */
    @Test
    public void testGetLocation() {
        Location testLocation = dao.getLocation(1);

        Assert.assertTrue(testLocation.getCity().equalsIgnoreCase("Louisville"));

    }

    /**
     * Test of addLocation method, of class LocationDaoImpl.
     */
    @Test
    public void testAddLocation() {
        BigDecimal latitude, longitude;
        latitude = new BigDecimal("38.2017").setScale(4);
        longitude = new BigDecimal("-85.6802").setScale(4);

        Location testLocation = new Location();

        testLocation.setLandmark("testLandmark");
        testLocation.setDescription("testDescription");
        testLocation.setCity("testCity");
        testLocation.setState("testState");
        testLocation.setZipCode(11111);
        testLocation.setLatitude(latitude);
        testLocation.setLongitude(longitude);
        
        Location newLocation = dao.addLocation(testLocation);
        
        Assert.assertEquals(testLocation.getCity(), newLocation.getCity());
    }

    /**
     * Test of updateLocation method, of class LocationDaoImpl.
     */
    @Test
    public void testUpdateLocation() {
        Location testLocation = dao.getLocation(1);
        testLocation.setCity("testCity");
        dao.updateLocation(testLocation);
        
        Location updatedLocation = dao.getLocation(1);
        
        Assert.assertTrue(updatedLocation.getCity().equalsIgnoreCase("testCity"));
    }

    /**
     * Test of deleteLocation method, of class LocationDaoImpl.
     */
    @Test
    public void testDeleteLocation() {
        dao.deleteLocation(1);
        
        Assert.assertEquals(0, dao.getAllLocations().size());
    }
    
    
    /**
     * Test of getLocationsForSuperhuman method, of class LocationDaoImpl.
     */
    @Test
    public void testGetLocationsForSuperhuman() {
        //jdbcTemplate.update("INSERT INTO Superpowers (SuperpowerId, Superpower) VALUES (1, \"testSuperpower\")");
        jdbcTemplate.update("INSERT INTO HeroesAndVillains (SuperId, SuperName, Alias, Cover, IsVillain) VALUES (1, \"testSuperhuman\", \"testAlias\", \"testCover\", 0)");
        jdbcTemplate.update("INSERT INTO Sightings (SightingId, LocationId, SightingDate) VALUES (1, 1, \"2017-06-21\")");
        jdbcTemplate.update("INSERT INTO SupersSightings (SuperId, SightingId) VALUES (1, 1)");
        
        Superhuman testSuperhuman = new Superhuman();
        testSuperhuman.setSuperhumanId(1);
        
        List<Location> testLocations = dao.getLocationsForSuperhuman(testSuperhuman);
        Assert.assertEquals(1, testLocations.size());
    }
    
    

}
