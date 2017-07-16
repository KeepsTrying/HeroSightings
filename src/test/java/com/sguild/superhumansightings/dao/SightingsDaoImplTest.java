/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Sighting;
import com.sguild.superhumansightings.dto.Superhuman;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class SightingsDaoImplTest {
    
    SightingsDao dao;
    JdbcTemplate jdbcTemplate;
    
    public SightingsDaoImplTest() {
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
        dao = ctx.getBean("sightingsDao", SightingsDao.class);

        jdbcTemplate.update("DELETE FROM SupersSuperpowers");
        jdbcTemplate.update("DELETE FROM OrganizationsSupers;");
        jdbcTemplate.update("DELETE FROM SupersSightings;");
        jdbcTemplate.update("DELETE FROM Organizations;");
        jdbcTemplate.update("DELETE FROM Sightings;");
        jdbcTemplate.update("DELETE FROM Locations;");
        jdbcTemplate.update("DELETE FROM Superpowers;");
        jdbcTemplate.update("DELETE FROM HeroesAndVillains");

        jdbcTemplate.update("INSERT INTO HeroesAndVillains (SuperId, SuperName, Alias, Cover, isVillain)\n"
                + "VALUES (1, \"testSuperName\", \"testAlias\", \"testCover\", 0);");

        jdbcTemplate.update("INSERT INTO Superpowers (SuperpowerId, Superpower)\n"
                + "VALUES (1, \"testSuperpower\");");

        jdbcTemplate.update("INSERT INTO SupersSuperpowers (SuperId, SuperpowerId)\n"
                + "VALUES (1, 1);");

        jdbcTemplate.update("INSERT INTO Locations (LocationId, Landmark)\n"
                + "VALUES (1, \"testLandmark\");");

        jdbcTemplate.update("INSERT INTO Organizations (OrganizationId, Name, Description, LocationId)\n"
                + "VALUES (1, \"testName\", \"testDescription\", 1);");

        jdbcTemplate.update("INSERT INTO OrganizationsSupers (OrganizationId, SuperId)\n"
                + "VALUES (1, 1);");
        
        LocalDate testDate = LocalDate.of(2017, 07, 15);
        
        jdbcTemplate.update("INSERT INTO Sightings (SightingId, LocationId, SightingDate) VALUES (1, 1, "
                + "'" + java.sql.Date.valueOf(testDate) + "'" + ")");
        
        jdbcTemplate.update("INSERT INTO SupersSightings (SightingId, SuperId) VALUES (1, 1)");
    }
    
    @After
    public void tearDown() {
    }

    

    /**
     * Test of getAllSightings method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllSightings() {
        List<Sighting> sightings = dao.getAllSightings();
        Assert.assertEquals(1, sightings.size());
        
        Sighting sighting = sightings.get(0);
        Assert.assertTrue(sighting.getLocationId() == 1);
    }

    /**
     * Test of getAllSightingsFromDate method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllSightingsFromDate() {
        LocalDate testDate = LocalDate.of(2017, 07, 15);
        List<Sighting> sightings = dao.getAllSightingsFromDate(testDate);
        Assert.assertEquals(1, sightings.size());
        
        Sighting sighting = sightings.get(0);
        Assert.assertTrue(sighting.getLocationId() == 1);
    }

    /**
     * Test of getAllSightingsAtLocation method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllSightingsAtLocation() {
        List<Sighting> sightings = dao.getAllSightingsAtLocation(1);
        Assert.assertEquals(1, sightings.size());
        
        Sighting sighting = sightings.get(0);
        Assert.assertTrue(sighting.getLocationId() == 1);
    }

    /**
     * Test of getAllSightingsOfSuperhuman method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllSightingsOfSuperhuman() {
        List<Sighting> sightings = dao.getAllSightingsOfSuperhuman(1);
        Assert.assertEquals(1, sightings.size());
        
        Sighting sighting = sightings.get(0);
        Assert.assertTrue(sighting.getLocationId() == 1);
    }

    /**
     * Test of getSighting method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetSighting() {
        Sighting sighting = dao.getSighting(1);
        Assert.assertEquals(1, sighting.getLocationId());
    }

    /**
     * Test of addSighting method, of class SightingsDaoImpl.
     */
    @Test
    public void testAddSighting() {
        Sighting expectedSighting = new Sighting();
        expectedSighting.setLocationId(1);
        LocalDate testDate = LocalDate.of(2015, 9, 14);
        expectedSighting.setDate(testDate);
        
        expectedSighting = dao.addSighting(expectedSighting);
        Assert.assertEquals(expectedSighting.getLocationId(), dao.getSighting(expectedSighting.getLocationId()).getLocationId());
    }

    /**
     * Test of updateSighting method, of class SightingsDaoImpl.
     */
    @Test
    public void testUpdateSighting() {
        Sighting sighting = dao.getSighting(1);
        LocalDate newDate = LocalDate.of(2020, 1, 1);
        sighting.setDate(newDate);
        
        dao.updateSighting(sighting);
        Sighting updatedSighting = dao.getSighting(1);
        Assert.assertTrue(newDate.compareTo(updatedSighting.getDate()) == 0);
        
    }

    /**
     * Test of deleteSighting method, of class SightingsDaoImpl.
     */
    @Test
    public void testDeleteSighting() {
        dao.deleteSighting(1);
        int actual = dao.getAllSightings().size();
        Assert.assertEquals(0, actual);
        
    }
    
}
