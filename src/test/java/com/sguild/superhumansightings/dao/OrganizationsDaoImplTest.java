/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Membership;
import com.sguild.superhumansightings.dto.Organization;
import com.sguild.superhumansightings.dto.Superhuman;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
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
public class OrganizationsDaoImplTest {
    
    OrganizationsDao dao;
    JdbcTemplate jdbcTemplate;

    
    public OrganizationsDaoImplTest() {
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
        dao = ctx.getBean("organizationsDao", OrganizationsDao.class);

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
        
        jdbcTemplate.update("INSERT INTO HeroesAndVillains (SuperId, SuperName, Alias, Cover, isVillain)\n"
                + "VALUES (2, \"secondSuperName\", \"secondAlias\", \"secondCover\", 0);");


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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testGetAllOrganizations() {
        List<Organization> organizations = dao.getAllOrganizations();
        Organization organization = organizations.get(0);
        
        Assert.assertEquals(1, organizations.size());
        Assert.assertTrue(organization.getOrganizationName().equals("testName"));
    }

    /**
     * Test of getOrganization method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testGetOrganization() {
        Organization organization = dao.getOrganization(1);
        
        Assert.assertTrue(organization.getOrganizationDescription().equals("testDescription"));
    }

    /**
     * Test of addOrganization method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testAddOrganization() {
        Organization newOrganization = new Organization();
        newOrganization.setOrganizationName("newName");
        newOrganization.setOrganizationDescription("newDescription");
        newOrganization.setOrginizationLocationId(1);
        
        newOrganization = dao.addOrganization(newOrganization);
        
        Assert.assertEquals(newOrganization.getOrganizationId(), 
                dao.getOrganization(newOrganization.getOrganizationId()).getOrganizationId());
    }

    /**
     * Test of updateOrganization method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testUpdateOrganization() {
        Organization organization = dao.getOrganization(1);
        organization.setOrganizationDescription("newOrganizationDescription");
        String expectedDescription = organization.getOrganizationDescription();
        dao.updateOrganization(organization);
        Organization updatedOrganization = dao.getOrganization(1);
        String actualDescription = updatedOrganization.getOrganizationDescription();
        Assert.assertEquals(expectedDescription, actualDescription);
    }

    /**
     * Test of deleteOrganization method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testDeleteOrganization() {
        Organization organization = dao.getOrganization(1);
        dao.deleteOrganization(organization);
        
        Assert.assertTrue(dao.getAllOrganizations().isEmpty());
    }

    /**
     * Test of getMembership method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testGetMembership() {
        Membership membership = dao.getMembership(1, 1);

        Assert.assertTrue(membership.getMemberName().equals("testSuperName"));
    }

    /**
     * Test of getMembershipsOfOrganization method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testGetMembershipsOfOrganization() {
        Organization organization = dao.getOrganization(1);
        List<Membership> memberships = dao.getMembershipsOfOrganization(organization);
        
        Assert.assertEquals(1, memberships.size());
    }

    /**
     * Test of assignSuperhumanToOrganization method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testAssignSuperhumanToOrganization() {
        Superhuman superhuman = new Superhuman();
        superhuman.setSuperhumanId(2);
        
        Organization organization = dao.getOrganization(1);
        

        dao.assignSuperhumanToOrganization(superhuman, organization);

        org.junit.Assert.assertTrue(dao.getMembershipsOfOrganization(organization).size() == 2);
    }

    /**
     * Test of deleteMembership method, of class OrganizationsDaoImpl.
     */
    @Test
    public void testDeleteMembership() {
        Membership membership = dao.getMembership(1, 1);
        Organization organization = dao.getOrganization(1);
        
        dao.deleteMembership(membership);

        Assert.assertTrue(dao.getMembershipsOfOrganization(organization).size() == 1);
    }
    
}
