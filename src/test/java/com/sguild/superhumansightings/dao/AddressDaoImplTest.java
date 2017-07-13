/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Address;
import java.math.BigDecimal;
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
public class AddressDaoImplTest {

    private AddressDao dao;

    public AddressDaoImplTest() {
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

        dao = ctx.getBean("addressDao", AddressDao.class);

        List<Address> addresses = dao.getAllAddresses();
        for (Address eachAddress : addresses) {
            dao.deleteAddress(eachAddress.getAddressId());
        }
    }

    @After
    public void tearDown() {
    }

    

    /**
     * Test of getAllAddresses method, of class AddressDaoImpl.
     */
    @Test
    public void testGetAllAddresses() {
        List<Address> addresses = dao.getAllAddresses();
        int numOfAddresses = addresses.size();

        Assert.assertEquals(0, numOfAddresses);
    }

    /**
     * Test of getAddress method, of class AddressDaoImpl.
     */
    @Test
    public void testGetAddress() {
        Address testAddress = new Address();
        testAddress.setStreetAddress("3306 Colonial Manor Circle");
        testAddress.setCity("Louisville");
        testAddress.setState("KY");
        testAddress.setZipCode(40218);

        BigDecimal latitude, longitude;

        latitude = new BigDecimal("38.2017").setScale(4);
        longitude = new BigDecimal("-85.6802").setScale(4);

        testAddress.setLatitude(latitude);
        testAddress.setLongitude(longitude);

        Address newAddress = dao.addAddress(testAddress);

        //how to get the addressId to be able to get it and make sure they're 
        //the same?
        int addressId = newAddress.getAddressId();
        dao.getAllAddresses();
        Assert.assertEquals(newAddress.getZipCode(), dao.getAddress(addressId).getZipCode());

    }

    /**
     * Test of addAddress method, of class AddressDaoImpl.
     */
    @Test
    public void testAddAddress() {
        Address testAddress = new Address();
        testAddress.setStreetAddress("3306 Colonial Manor Circle");
        testAddress.setCity("Louisville");
        testAddress.setState("KY");
        testAddress.setZipCode(40218);

        BigDecimal latitude, longitude;

        latitude = new BigDecimal("38.2017").setScale(4);
        longitude = new BigDecimal("-85.6802").setScale(4);

        testAddress.setLatitude(latitude);
        testAddress.setLongitude(longitude);

        dao.addAddress(testAddress);

        int numOfAddresses = dao.getAllAddresses().size();

        Assert.assertEquals(1, numOfAddresses);
    }

    /**
     * Test of updateAddress method, of class AddressDaoImpl.
     */
    @Test
    public void testUpdateAddress() {
        BigDecimal latitude, longitude;

        latitude = new BigDecimal("38.2017").setScale(4);
        longitude = new BigDecimal("-85.6802").setScale(4);
        
        
        
        Address testAddress = new Address();
        testAddress.setStreetAddress("3306 Colonial Manor Circle");
        testAddress.setCity("Louisville");
        testAddress.setState("KY");
        testAddress.setZipCode(40218);
        testAddress.setLatitude(latitude);
        testAddress.setLongitude(longitude);
        
        testAddress = dao.addAddress(testAddress);
        
        testAddress.setZipCode(40208);
        
        dao.updateAddress(testAddress);
        
        Assert.assertEquals(40208, dao.getAddress(testAddress.getAddressId()).getZipCode());
        
        
    }

    /**
     * Test of deleteAddress method, of class AddressDaoImpl.
     */
    @Test
    public void testDeleteAddress() {
        BigDecimal latitude, longitude;

        latitude = new BigDecimal("38.2017").setScale(4);
        longitude = new BigDecimal("-85.6802").setScale(4);

        List<Address> addresses = dao.getAllAddresses();
        for (Address eachAddress : addresses) {
            dao.deleteAddress(eachAddress.getAddressId());
        }

        int numOfAddresses = addresses.size();
        System.out.println("Initial Address List Size: " + numOfAddresses);

        Address testAddress = new Address();
        testAddress.setStreetAddress("3306 Colonial Manor Circle");
        testAddress.setCity("Louisville");
        testAddress.setState("KY");
        testAddress.setZipCode(40218);
        testAddress.setLatitude(latitude);
        testAddress.setLongitude(longitude);

        int testId = dao.addAddress(testAddress).getAddressId();
        
        numOfAddresses = addresses.size();

        System.out.println("After Adding an address: " + numOfAddresses);
        
        dao.deleteAddress(testId);
//        addresses = dao.getAllAddresses();
//        for (Address eachAddress : addresses) {
//            dao.deleteAddress(eachAddress.getAddressId());
//        }

        numOfAddresses = dao.getAllAddresses().size();

        Assert.assertEquals(0, numOfAddresses);

    }

}
