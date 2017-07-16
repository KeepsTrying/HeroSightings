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

/**
 *
 * @author apprentice
 */
public interface SuperhumansDao {
    
    public List<Superpower> getAllSuperpowers();
    
    public List<Superpower> getSuperpowersForSuperhuman(int superheroId);
    
    public Superpower getSuperpower(int superpowerId);
    
    public Superpower addSuperpower(String superpower);
    
    public void giveSuperhumanSuperpower(int superhumanId, int superpowerId);
    
    public void removeSuperhumansSuperpower (int superhumanId, int superpowerId);
    
    public void updateSuperpower(Superpower superpower);
    
    public void deleteSuperpower(int superpowerId);
    
    public List<Superhuman> getAllSuperhumans();
    
    public Superhuman getSuperhuman(int superId);
    
    public Superhuman addSuperhuman(Superhuman superhuman);
    
    public void updateSuperhuman(Superhuman superhuman);
    
    public void deleteSuperhuman(int superId);
    
    public List<Superhuman> getAllSuperhumansWithSuperpower(Superpower superpower);
    
    public List<Membership> getMembershipsOfSuperhuman(Superhuman superhuman);
    
    public List<Membership> getMembershipsOfOrganization(Organization organization);
    
    public Membership getMembership(int organizationId, int superhumanId);
    
    public void assignSuperhumanToOrganization(Superhuman superhuman, Organization organization);
    
    public void deleteMembership(Membership membership);
    
}
