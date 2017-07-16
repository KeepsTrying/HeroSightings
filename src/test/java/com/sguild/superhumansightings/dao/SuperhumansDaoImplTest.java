/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Membership;
import com.sguild.superhumansightings.dto.Organization;
import com.sguild.superhumansightings.dto.Superhuman;
import com.sguild.superhumansightings.dto.Superpower;
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
public class SuperhumansDaoImplTest {

    SuperhumansDao dao;
    JdbcTemplate jdbcTemplate;

    public SuperhumansDaoImplTest() {
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
        dao = ctx.getBean("superhumansDao", SuperhumansDao.class);

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

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllSuperpowers method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetAllSuperpowers() {
        int actual = dao.getAllSuperpowers().size();
        Assert.assertEquals(1, actual);

        List<Superpower> testPowers = dao.getAllSuperpowers();
        Superpower testPower = testPowers.get(0);

        Assert.assertTrue(testPower.getSuperpower().equalsIgnoreCase("testSuperpower"));
    }

    /**
     * Test of getSuperpowersForHero method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetSuperpowersForHero() {
        List<Superpower> testPowers = dao.getSuperpowersForSuperhuman(1);
        Superpower testPower = testPowers.get(0);

        Assert.assertTrue(testPowers.size() == 1);
        Assert.assertTrue(testPower.getSuperpower().equalsIgnoreCase("testSuperpower"));
    }

    /**
     * Test of getSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetSuperpower() {
        Superpower testPower = dao.getSuperpower(1);

        Assert.assertTrue(testPower.getSuperpower().equalsIgnoreCase("testSuperpower"));
        Assert.assertTrue(testPower.getSuperpowerId() == 1);
    }

    /**
     * Test of addSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testAddSuperpower() {
        String testPower = "newSuperpower";

        Superpower testSuperpower = dao.addSuperpower(testPower);

        Assert.assertTrue(dao.getAllSuperpowers().size() == 2);

        Assert.assertTrue(testSuperpower.getSuperpower().equalsIgnoreCase("newSuperpower"));
    }

    /**
     * Test of giveSuperhumanSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGiveSuperhumanSuperpower() {
        Superpower superpower = dao.addSuperpower("newSuperpower");

        dao.giveSuperhumanSuperpower(1, superpower.getSuperpowerId());
        Assert.assertTrue(dao.getSuperpowersForSuperhuman(1).size() == 2);

    }

    /**
     * Test of removeSuperhumansSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testRemoveSuperhumansSuperpower() {
        dao.removeSuperhumansSuperpower(1, 1);

        Superpower testSuperpower = new Superpower();
        testSuperpower.setSuperpowerId(1);
        Assert.assertTrue(dao.getAllSuperhumansWithSuperpower(testSuperpower).isEmpty());
    }

    /**
     * Test of updateSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = dao.getSuperpower(1);
        superpower.setSuperpower("newSuperpower");

        dao.updateSuperpower(superpower);

        Superpower updated = dao.getSuperpower(1);
        Assert.assertTrue(updated.getSuperpower().equalsIgnoreCase("newSuperpower"));
    }

    /**
     * Test of deleteSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testDeleteSuperpower() {
        //Superpower superpower = dao.getSuperpower(1);
        dao.deleteSuperpower(1);

        // Assert.assertTrue(superpower.getSuperpower().equalsIgnoreCase("testSuperpower"));
        Assert.assertTrue(dao.getAllSuperpowers().isEmpty());
    }

    /**
     * Test of getAllSuperhumans method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetAllSuperhumans() {
        List<Superhuman> testSuperhumans = dao.getAllSuperhumans();
        Superhuman testSuper = testSuperhumans.get(0);

        Assert.assertEquals(1, testSuperhumans.size());
        Assert.assertTrue(testSuper.getHandle().equalsIgnoreCase("testSuperName"));
    }

    /**
     * Test of getSuperhuman method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetSuperhuman() {
        Superhuman testSuperhuman = dao.getSuperhuman(1);

        Assert.assertTrue(testSuperhuman.getAlias().equalsIgnoreCase("testAlias"));
    }

    /**
     * Test of addSuperhuman method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testAddSuperhuman() {
        Superhuman testSuperhuman = new Superhuman();
        testSuperhuman.setHandle("newHandle");
        testSuperhuman.setAlias("newAlias");
        testSuperhuman.setCover("newCover");
        testSuperhuman.setAffiliation("Good");

        Superhuman addedSuperhuman = dao.addSuperhuman(testSuperhuman);

        Assert.assertTrue(addedSuperhuman.getAlias().equals("newAlias"));
    }

    /**
     * Test of updateSuperhuman method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testUpdateSuperhuman() {
        Superhuman superhuman = dao.getSuperhuman(1);

        superhuman.setHandle("newHandle");
        dao.updateSuperhuman(superhuman);

        Superhuman updatedSuperhuman = dao.getSuperhuman(1);

        Assert.assertTrue(updatedSuperhuman.getHandle().equals("newHandle"));
    }

    /**
     * Test of deleteSuperhuman method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testDeleteSuperhuman() {
        dao.deleteSuperhuman(1);
    }

    /**
     * Test of getAllSuperhumansWithSuperpower method, of class
     * SuperhumansDaoImpl.
     */
    @Test
    public void testGetAllSuperhumansWithSuperpower() {
        Superhuman testSuperhuman = new Superhuman();
        testSuperhuman.setHandle("newHandle");
        testSuperhuman.setAlias("newAlias");
        testSuperhuman.setCover("newCover");
        testSuperhuman.setAffiliation("Good");

        //create method to add superpower to list of powers for superhuman
        Superpower superpower = dao.getSuperpower(1);
        List<Superhuman> superhumans = dao.getAllSuperhumansWithSuperpower(superpower);

        Assert.assertTrue(superhumans.size() == 1);
    }

    /**
     * Test of getMembershipsOfHero method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetMembershipsOfHero() {
        Superhuman superhuman = dao.getSuperhuman(1);
        List<Membership> memberships = dao.getMembershipsOfSuperhuman(superhuman);
        Membership membership = memberships.get(0);

        Assert.assertEquals(1, memberships.size());
        Assert.assertTrue(membership.getOrganizationName().equals("testName"));
    }

    /**
     * Test of getMembershipsOfOrganization method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetMembershipsOfOrganization() {
        Organization organization = new Organization();
        organization.setOrganizationId(1);
        List<Membership> memberships = dao.getMembershipsOfOrganization(organization);
        Membership membership = memberships.get(0);

        Assert.assertEquals(1, memberships.size());
        Assert.assertTrue(membership.getMemberName().equals("testSuperName"));
    }

    /**
     * Test of getMembership method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetMembership() {
        Membership membership = dao.getMembership(1, 1);

        Assert.assertTrue(membership.getMemberName().equals("testSuperName"));
    }

    /**
     * Test of assignSuperhumanToOrganization method, of class
     * SuperhumansDaoImpl.
     */
    @Test
    public void testAssignSuperhumanToOrganization() {
        Superhuman superhuman = dao.getSuperhuman(1);

        Organization newOrganization = new Organization();
        newOrganization.setOrganizationId(2);

        jdbcTemplate.update("INSERT INTO Organizations (OrganizationId, Name) VALUES (2, \"newOrganization\")");

        dao.assignSuperhumanToOrganization(superhuman, newOrganization);

        Assert.assertTrue(dao.getMembershipsOfSuperhuman(superhuman).size() == 2);
    }

    /**
     * Test of deleteMembership method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testDeleteMembership() {
        Membership membership = dao.getMembership(1, 1);

        dao.deleteMembership(membership);
        Superhuman superhuman = dao.getSuperhuman(1);

        Assert.assertTrue(dao.getMembershipsOfSuperhuman(superhuman).isEmpty());
    }

}
