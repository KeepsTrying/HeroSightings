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

/**
 *
 * @author apprentice
 */
public interface OrganizationsDao {

    public List<Organization> getAllOrganizations();

    public Organization getOrganization(int organizationId);

    public Organization addOrganization(Organization newOrganization);

    public void updateOrganization(Organization organization);

    public void deleteOrganization(Organization organization);
    
    public Membership getMembership(int organizationId, int superhumanId);
    
    public List<Membership> getMembershipsOfOrganization(Organization organization);
    
    public void assignSuperhumanToOrganization(Superhuman superhuman, Organization organization);
    
    public void deleteMembership(Membership membership);

}
