/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dto;

import java.util.List;

/**
 *
 * @author apprentice
 */
public class Organization {

    private int OrganizationId;

    private String organizationName;

    private String organizationDescription;

    private int orginizationLocationId;

    private List<Membership> members;

    public int getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(int OrganizationId) {
        this.OrganizationId = OrganizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public int getOrginizationLocationId() {
        return orginizationLocationId;
    }

    public void setOrginizationLocationId(int orginizationLocationId) {
        this.orginizationLocationId = orginizationLocationId;
    }

    public List<Membership> getMembers() {
        return members;
    }

    public void setMembers(List<Membership> members) {
        this.members = members;
    }

}
