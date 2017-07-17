/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.servicelayer;

import com.sguild.superhumansightings.dao.LocationDao;
import com.sguild.superhumansightings.dao.OrganizationsDao;
import com.sguild.superhumansightings.dao.SightingsDao;
import com.sguild.superhumansightings.dao.SuperhumansDao;
import com.sguild.superhumansightings.dto.Location;
import com.sguild.superhumansightings.dto.Membership;
import com.sguild.superhumansightings.dto.Organization;
import com.sguild.superhumansightings.dto.Sighting;
import com.sguild.superhumansightings.dto.Superhuman;
import com.sguild.superhumansightings.dto.Superpower;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ServiceImpl implements Service {

    private LocationDao lDao;
    private OrganizationsDao oDao;
    private SightingsDao sDao;
    private SuperhumansDao shDao;

    public LocationDao getlDao() {
        return lDao;
    }

    public void setlDao(LocationDao lDao) {
        this.lDao = lDao;
    }

    public OrganizationsDao getoDao() {
        return oDao;
    }

    public void setoDao(OrganizationsDao oDao) {
        this.oDao = oDao;
    }

    public SightingsDao getsDao() {
        return sDao;
    }

    public void setsDao(SightingsDao sDao) {
        this.sDao = sDao;
    }

    public SuperhumansDao getShDao() {
        return shDao;
    }

    public void setShDao(SuperhumansDao shDao) {
        this.shDao = shDao;
    }

    @Override
    public List<Sighting> getRecentSightings() {
        List<Sighting> sightings = sDao.getRecentSightings();
        List<Sighting> recentSightings = new ArrayList<>();
        for (Sighting sighting : sightings) {
            sighting.setLocation(lDao.getLocation(sighting.getLocationId()));
            recentSightings.add(sighting);
        }
        return recentSightings;
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = sDao.getAllSightings();
        return sightings;
    }

    @Override
    public List<Location> getAllLocations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location getLocation(int locationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location addLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLocation(int locationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> getLocationsForSuperhuman(Superhuman superhuman) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization getOrganization(int organizationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization addOrganization(Organization newOrganization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Membership getMembership(int organizationId, int superhumanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Membership> getMembershipsOfOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void assignSuperhumanToOrganization(Superhuman superhuman, Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMembership(Membership membership) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightingsFromDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightingsAtLocation(int locationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightingsOfSuperhuman(int superhumanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting getSighting(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSighting(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Superpower> getSuperpowersForSuperhuman(int superheroId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Superpower getSuperpower(int superpowerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Superpower addSuperpower(String superpower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void giveSuperhumanSuperpower(int superhumanId, int superpowerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSuperhumansSuperpower(int superhumanId, int superpowerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperpower(int superpowerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Superhuman> getAllSuperhumans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Superhuman getSuperhuman(int superId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Superhuman addSuperhuman(Superhuman superhuman) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSuperhuman(Superhuman superhuman) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperhuman(int superId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Superhuman> getAllSuperhumansWithSuperpower(Superpower superpower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Membership> getMembershipsOfSuperhuman(Superhuman superhuman) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
