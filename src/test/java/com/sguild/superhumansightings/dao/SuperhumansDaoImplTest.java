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
        
        jdbcTemplate.update("DELETE FROM OrganizationsSupers;");
        jdbcTemplate.update("DELETE FROM SupersSightings;");
        jdbcTemplate.update("DELETE FROM Organizations;");
        jdbcTemplate.update("DELETE FROM Sightings;");
        jdbcTemplate.update("DELETE FROM Locations;");
        jdbcTemplate.update("DELETE FROM Superpowers;");
        jdbcTemplate.update("DELETE FROM SupersSuperpowers");
        jdbcTemplate.update("DELETE FROM HeroesAndVillains");
    }
    
    @After
    public void tearDown() {
    }


    
    

    /**
     * Test of getAllSuperpowers method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetAllSuperpowers() {
        System.out.println("getAllSuperpowers");
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        List<Superpower> expResult = null;
        List<Superpower> result = instance.getAllSuperpowers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuperpowersForHero method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetSuperpowersForHero() {
        System.out.println("getSuperpowersForHero");
        int superheroId = 0;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        List<Superpower> expResult = null;
        List<Superpower> result = instance.getSuperpowersForHero(superheroId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetSuperpower() {
        System.out.println("getSuperpower");
        int superpowerId = 0;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        Superpower expResult = null;
        Superpower result = instance.getSuperpower(superpowerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testAddSuperpower() {
        System.out.println("addSuperpower");
        Superpower superpower = null;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        Superpower expResult = null;
        Superpower result = instance.addSuperpower(superpower);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testUpdateSuperpower() {
        System.out.println("updateSuperpower");
        Superpower superpower = null;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        instance.updateSuperpower(superpower);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSuperpower method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testDeleteSuperpower() {
        System.out.println("deleteSuperpower");
        int superpowerId = 0;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        instance.deleteSuperpower(superpowerId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSuperhumans method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetAllSuperhumans() {
        System.out.println("getAllSuperhumans");
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        List<Superhuman> expResult = null;
        List<Superhuman> result = instance.getAllSuperhumans();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuperhuman method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetSuperhuman() {
        System.out.println("getSuperhuman");
        int superId = 0;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        Superhuman expResult = null;
        Superhuman result = instance.getSuperhuman(superId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSuperhuman method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testAddSuperhuman() {
        System.out.println("addSuperhuman");
        Superhuman superhuman = null;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        Superhuman expResult = null;
        Superhuman result = instance.addSuperhuman(superhuman);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSuperhuman method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testUpdateSuperhuman() {
        System.out.println("updateSuperhuman");
        Superhuman superhuman = null;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        instance.updateSuperhuman(superhuman);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSuperhuman method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testDeleteSuperhuman() {
        System.out.println("deleteSuperhuman");
        int superId = 0;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        instance.deleteSuperhuman(superId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipsOfHero method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetMembershipsOfHero() {
        System.out.println("getMembershipsOfHero");
        Superhuman superhuman = null;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        List<Membership> expResult = null;
        List<Membership> result = instance.getMembershipsOfHero(superhuman);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipsOfOrganization method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetMembershipsOfOrganization() {
        System.out.println("getMembershipsOfOrganization");
        Organization organization = null;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        List<Membership> expResult = null;
        List<Membership> result = instance.getMembershipsOfOrganization(organization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembership method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testGetMembership() {
        System.out.println("getMembership");
        int organizationId = 0;
        int superhumanId = 0;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        Membership expResult = null;
        Membership result = instance.getMembership(organizationId, superhumanId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMembership method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testUpdateMembership() {
        System.out.println("updateMembership");
        Membership membership = null;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        instance.updateMembership(membership);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMembership method, of class SuperhumansDaoImpl.
     */
    @Test
    public void testDeleteMembership() {
        System.out.println("deleteMembership");
        Membership membership = null;
        SuperhumansDaoImpl instance = new SuperhumansDaoImpl();
        instance.deleteMembership(membership);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
