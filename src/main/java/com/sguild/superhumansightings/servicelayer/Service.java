/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.servicelayer;

import com.sguild.superhumansightings.dto.Location;
import com.sguild.superhumansightings.dto.Membership;
import com.sguild.superhumansightings.dto.Organization;
import com.sguild.superhumansightings.dto.Sighting;
import com.sguild.superhumansightings.dto.Superhuman;
import com.sguild.superhumansightings.dto.Superpower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface Service {

    public List<Location> getAllLocations();

    public Location getLocation(int locationId);

    public Location addLocation(Location location);

    public void updateLocation(Location location);

    public void deleteLocation(int locationId);

    public List<Location> getLocationsForSuperhuman(Superhuman superhuman);

    public List<Organization> getAllOrganizations();

    public Organization getOrganization(int organizationId);

    public Organization addOrganization(Organization newOrganization);

    public void updateOrganization(Organization organization);

    public void deleteOrganization(Organization organization);

    public Membership getMembership(int organizationId, int superhumanId);

    public List<Membership> getMembershipsOfOrganization(Organization organization);

    public void assignSuperhumanToOrganization(Superhuman superhuman, Organization organization);

    public void deleteMembership(Membership membership);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> getRecentSightings();

    public List<Sighting> getAllSightingsFromDate(LocalDate date);

    public List<Sighting> getAllSightingsAtLocation(int locationId);

    public List<Sighting> getAllSightingsOfSuperhuman(int superhumanId);

    public Sighting getSighting(int sightingId);

    public Sighting addSighting(Sighting sighting);

    public void updateSighting(Sighting sighting);

    public void deleteSighting(int sightingId);

    public List<Superpower> getAllSuperpowers();

    public List<Superpower> getSuperpowersForSuperhuman(int superheroId);

    public Superpower getSuperpower(int superpowerId);

    public Superpower addSuperpower(String superpower);

    public void giveSuperhumanSuperpower(int superhumanId, int superpowerId);

    public void removeSuperhumansSuperpower(int superhumanId, int superpowerId);

    public void updateSuperpower(Superpower superpower);

    public void deleteSuperpower(int superpowerId);

    public List<Superhuman> getAllSuperhumans();

    public Superhuman getSuperhuman(int superId);

    public Superhuman addSuperhuman(Superhuman superhuman);

    public void updateSuperhuman(Superhuman superhuman);

    public void deleteSuperhuman(int superId);

    public List<Superhuman> getAllSuperhumansWithSuperpower(Superpower superpower);

    public List<Membership> getMembershipsOfSuperhuman(Superhuman superhuman);

}
