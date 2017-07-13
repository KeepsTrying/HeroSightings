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
public class Superhuman {
    
    private int superhumanId;
    
    private String handle;
    
    private String alias;
    
    private String cover;
    
    private String affiliation;
    
    private List<Superpower> superpowers;
    
    private List<Membership> memberships;

    public int getSuperhumanId() {
        return superhumanId;
    }

    public void setSuperhumanId(int superhumanId) {
        this.superhumanId = superhumanId;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public List<Superpower> getSuperpowers() {
        return superpowers;
    }

    public void setSuperpowers(List<Superpower> superpowers) {
        this.superpowers = superpowers;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

}
