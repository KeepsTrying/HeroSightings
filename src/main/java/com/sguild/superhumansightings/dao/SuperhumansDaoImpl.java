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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class SuperhumansDaoImpl implements SuperhumansDao {
    
    private static final String MYSQL_LAST_ID = "SELECT LAST_INSERT_ID();";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
    //----------------------------------//
    //                                  //
    //      INDEX:                      //
    //                                  //
    //          Superpowers             //
    //                                  //
    //          Superhumans             //
    //                                  //
    //          Memberships             //
    //                                  //
    //----------------------------------//
    
    
    
    
    
    
    
    
    
    
    //----------------------\\
    //      Superpowers     \\
    //----------------------\\
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_ALL_SUPERPOWERS
            = "SELECT * FROM Superpowers;";

    @Override
    public List<Superpower> getAllSuperpowers() {
        List<Superpower> superpowers = jdbcTemplate.query(SQL_GET_ALL_SUPERPOWERS, new SuperpowerMapper());
        return superpowers;
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_HERO_SUPERPOWERS
            = "SELECT SuperpowerId, Superpower FROM "
            + "LEFT JOIN SupersSuperpowers USING (SuperpowerId) "
            + "LEFT JOIN HeroesAndVillains USING (SuperID)"
            + "WHERE SuperId = ?";
    
    @Override
    public List<Superpower> getSuperpowersForHero(int superheroId) {
        List<Superpower> thisHeroesPowers = jdbcTemplate.query(SQL_GET_HERO_SUPERPOWERS, new SuperpowerMapper(), superheroId);
        return thisHeroesPowers;
    }

    //-------------------------------------------------------------------------------------------------------
    
    




    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_SUPERPOWER
            = "SELECT * FROM SUPERPOWERS WHERE SuperpowerId = ?";
    
    @Override
    public Superpower getSuperpower(int superpowerId) {
        Superpower superpower = jdbcTemplate.queryForObject(SQL_GET_SUPERPOWER, new SuperpowerMapper(), Integer.class);
        return superpower;
    }

    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    private static final String SQL_ADD_SUPERPOWER
            = "INSERT INTO Superpowers (Superpower)";
    
    @Override
    public Superpower addSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_ADD_SUPERPOWER,
                superpower.getSuperpower());
        
        int superpowerId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setSuperpowerId(superpowerId);
        
        return superpower;
    }

    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    private static final String SQL_UPDATE_SUPERPOWER
            = "UPDATE Superpowers "
            + "SET Superpower = ?"
            + "WHERE SuperpowerId = ?";
    
    @Override
    public void updateSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                superpower.getSuperpower(),
                superpower.getSuperpowerId());
    }

    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    private static final String SQL_DELETE_SUPERPOWER
            = "DELETE FROM Superpowers WHERE SuperpowerId = ?";
    
    @Override
    public void deleteSuperpower(int superpowerId) {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superpowerId);
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    private static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower superpower = new Superpower();
            
            superpower.setSuperpowerId(rs.getInt("SuperpowerId"));
            superpower.setSuperpower(rs.getString("Superpower"));
            
            return superpower;
        }
        
    }
    
    
    
    
    
    
    
    //----------------------\\
    //      Superhumans     \\
    //----------------------\\
    
    
 
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_ALL_SUPERHUMANS
            = "SELECT * FROM HeroesAndVillains";

    @Override
    public List<Superhuman> getAllSuperhumans() {
        List<Superhuman> superhumans = jdbcTemplate.query(SQL_GET_ALL_SUPERHUMANS, new SuperhumanMapper());
        return superhumans;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    



    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_SUPERHUMAN
            = "SELECT * FROM HeroesAndVillains WHERE SuperId = ?;";
    
    @Override
    public Superhuman getSuperhuman(int superId) {
        Superhuman superhuman = jdbcTemplate.queryForObject(SQL_GET_SUPERHUMAN, new SuperhumanMapper(), superId);
        return superhuman;
    }
      
    //-------------------------------------------------------------------------------------------------------






    //-------------------------------------------------------------------------------------------------------
    
    private static final String ADD_SUPERHUMAN
            = "INSERT INTO HeroesAndVillains (SuperName, Alias, Cover, IsVillain) "
            + "VALUES (?, ?, ?, ?)";
    
    @Override
    public Superhuman addSuperhuman(Superhuman superhuman) {
        String handle = superhuman.getHandle();
        String alias = superhuman.getAlias();
        String cover = superhuman.getCover();
        int affiliationIndex = -1;
        if (superhuman.getAffiliation().equalsIgnoreCase("good")) {
            affiliationIndex = 0;
        } else {
            affiliationIndex = 1;
        }
        
        jdbcTemplate.update(ADD_SUPERHUMAN, handle, alias, cover, affiliationIndex);
        int superhumanId = jdbcTemplate.queryForObject(MYSQL_LAST_ID, Integer.class);
        superhuman.setSuperhumanId(superhumanId);
        
        return superhuman;
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_UPDATE_SUPERHUMAN
            = "UPDATE HeroesAndVillains SET"
            + "SuperName = ?"
            + "Alias = ?"
            + "Cover = ?"
            + "IsVillain = ?"
            + "WHERE SuperId = ?";
    
    @Override
    public void updateSuperhuman(Superhuman superhuman) {
        String handle = superhuman.getHandle();
        String alias = superhuman.getAlias();
        String cover = superhuman.getCover();
        int affiliationIndex = -1;
        if (superhuman.getAffiliation().equalsIgnoreCase("good")) {
            affiliationIndex = 0;
        } else {
            affiliationIndex = 1;
        }
        
        jdbcTemplate.update(SQL_UPDATE_SUPERHUMAN, handle, alias, cover, affiliationIndex, superhuman.getSuperhumanId());
        
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_DELETE_SUPERHUMAN
            = "DELETE FROM HeroesAndVillains WHERE SuperId = ?";
    
    @Override
    public void deleteSuperhuman(int superId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN, superId);
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    private static final class SuperhumanMapper implements RowMapper<Superhuman> {

        @Override
        public Superhuman mapRow(ResultSet rs, int i) throws SQLException {
            Superhuman superhuman = new Superhuman();
            
            superhuman.setSuperhumanId(rs.getInt("SuperId"));
            superhuman.setHandle(rs.getString("SuperName"));
            superhuman.setAlias(rs.getString("Alias"));
            superhuman.setCover(rs.getString("Cover"));
            int affiliationIndex = rs.getInt("IsVillain");
            
            if (affiliationIndex == 0) {
                superhuman.setAffiliation("Good");
            } else {
                superhuman.setAffiliation("Evil");
            }
            
            return superhuman;
        }
        
    }
    
    
    
    
    
    
    //include memberships within this dao
    //create an Organization DTO
    //create an organization DAO
    
    
    
    
    
    
    
    
    
    
    //----------------------\\
    //       Memberships    \\
    //----------------------\\
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_MEMBERSHIPS_FOR_HERO
            = "SELECT OrganizationId, Name, SuperId, SuperName"
            + "FROM Organizations"
            + "LEFT JOIN OrganizationsSupers USING (OrganizationId)"
            + "LEFT JOIN HeroesAndVillains USING (SuperId)"
            + "WHERE SuperId = ?;";
    
    @Override
    public List<Membership> getMembershipsOfHero(Superhuman superhuman) {
        List<Membership> superhumansMemberships = jdbcTemplate.query(SQL_GET_MEMBERSHIPS_FOR_HERO,
                                                    new MembershipMapper(), 
                                                    superhuman.getSuperhumanId());
        
        return superhumansMemberships;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_MEMBERSHIPS_FOR_ORGANIZATION
            = "= \"SELECT OrganizationId, Name, SuperId, SuperName\"\n" +
"            + \"FROM Organizations\"\n" +
"            + \"LEFT JOIN OrganizationsSupers USING (OrganizationId)\"\n" +
"            + \"LEFT JOIN HeroesAndVillains USING (SuperId)\"\n" +
"            + \"WHERE OrganizationId = ?;\";";
    
    @Override
    public List<Membership> getMembershipsOfOrganization(Organization organization) {
        List<Membership> memberships = jdbcTemplate.query(SQL_GET_MEMBERSHIPS_FOR_ORGANIZATION, new MembershipMapper(), organization.getOrganizationId());
        return memberships;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    

    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_MEMBERSHIP
            = "SELECT OrganizationId, Name, SuperId, SuperName"
            + "FROM Organizations"
            + "LEFT JOIN OrganizationsSupers USING (OrganizationId)"
            + "LEFT JOIN HeroesAndVillains USING (SuperId)"
            + "WHERE OrganizationId = ? AND SuperId = ?;";
    
    @Override
    public Membership getMembership(int organizationId, int superhumanId) {
        Membership membership = jdbcTemplate.queryForObject(SQL_GET_MEMBERSHIP, new MembershipMapper(), organizationId, superhumanId);
        return membership;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_UPDATE_MEMBERSHIP
            = "UPDATE OrganizationsSupers SET"
            + "OrganizationId = ?"
            + "Name = ?"
            + "SuperId = ?"
            + "SuperName = ?"
            + "WHERE OrganizaionId = ? AND SuperId = ?";
    
    @Override
    public void updateMembership(Membership membership) {
        jdbcTemplate.update(SQL_UPDATE_MEMBERSHIP,
                membership.getOrganizationId(),
                membership.getOrganizationName(),
                membership.getMemberId(),
                membership.getOrganizationName(),
                membership.getOrganizationId(), membership.getMemberId());
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_DELETE_MEMBERSHIP
            = "DELETE FROM OrganizationsSupers WHERE OrganizationId = ? AND SuperId = ?;";
    
    @Override
    public void deleteMembership(Membership membership) {
        jdbcTemplate.update(SQL_DELETE_MEMBERSHIP, membership.getOrganizationId(), membership.getMemberId());
    }
    
    
    //-------------------------------------------------------------------------------------------------------
    
    
    private static final class MembershipMapper implements RowMapper<Membership> {
    
        @Override
        public Membership mapRow(ResultSet rs, int i) throws SQLException {
            Membership membership = new Membership();
            
            membership.setOrganizationId(rs.getInt("OrganizationId"));
            membership.setOrganizationName(rs.getString("Name"));
            membership.setMemberId(rs.getInt("SuperId"));
            membership.setMemberName(rs.getString("SuperName"));
            
            
            return membership;
        }
    }
    

    
}
