/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Membership;
import com.sguild.superhumansightings.dto.Organization;
import com.sguild.superhumansightings.dto.Superhuman;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class OrganizationsDaoImpl implements OrganizationsDao {

    private static final String MYSQL_LAST_ID = "SELECT LAST_INSERT_ID();";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_ALL_ORGANIZATIONS
            = "SELECT * FROM Organizations;";
    
    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> organizations = jdbcTemplate.query(SQL_GET_ALL_ORGANIZATIONS, new OrganizationMapper());
        return organizations;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------

    private static final String SQL_GET_ORGANIZATION
            = "SELECT * FROM Organizations WHERE OrganizationId = ?";
    
    @Override
    public Organization getOrganization(int organizationId) {
        Organization organization = jdbcTemplate.queryForObject(SQL_GET_ORGANIZATION, new OrganizationMapper(), organizationId);
        return organization;
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_ADD_ORGANIZATION
            = "INSERT INTO Organizations (Name, Description, LocationId) "
            + "VALUES (?, ?, ?)";
    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Organization addOrganization(Organization newOrganization) {
        String name = newOrganization.getOrganizationName();
        String description = newOrganization.getOrganizationDescription();
        int locationId = newOrganization.getOrginizationLocationId();
        
        jdbcTemplate.update(SQL_ADD_ORGANIZATION, name, description, locationId);
        
        int organizationId = jdbcTemplate.queryForObject(MYSQL_LAST_ID, Integer.class);
        newOrganization.setOrganizationId(organizationId);
        
        return newOrganization;
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_UPDATE_ORGANIZATION
            = "UPDATE Organizations SET "
            + "Name = ?, "
            + "Description = ?, "
            + "LocationId = ?;";
    
    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION, 
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrginizationLocationId());
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_DELETE_ORGANIZATIONS_MEMBERS
            = "DELETE FROM OrganizationsSupers WHERE OrganizationId = ?;";
    
    private static final String SQL_DELETE_ORGANIZATION
            = "DELETE FROM Organizations WHERE OrganizationId = ?;";
    
    @Override
    public void deleteOrganization(Organization organization) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATIONS_MEMBERS, organization.getOrganizationId());
        
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organization.getOrganizationId());
    }
    
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    private static final class OrganizationMapper implements RowMapper<Organization> {
    
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            
            organization.setOrganizationId(rs.getInt("OrganizationId"));
            organization.setOrganizationName(rs.getString("Name"));
            organization.setOrganizationDescription(rs.getString("Description"));
            organization.setOrginizationLocationId(rs.getInt("LocationId"));
            
            return organization;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------

    private static final String SQL_GET_MEMBERSHIP
            = "SELECT OrganizationId, Name, SuperId, SuperName "
            + "FROM Organizations "
            + "LEFT JOIN OrganizationsSupers USING (OrganizationId) "
            + "LEFT JOIN HeroesAndVillains USING (SuperId) "
            + "WHERE OrganizationId = ? AND SuperId = ?;";
    
    @Override
    public Membership getMembership(int organizationId, int superhumanId) {
        Membership membership = jdbcTemplate.queryForObject(SQL_GET_MEMBERSHIP, new MembershipMapper(), organizationId, superhumanId);
        return membership;
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_GET_MEMBERSHIPS_FOR_ORGANIZATION
            = "SELECT OrganizationId, Name, SuperId, SuperName "
            + "FROM Organizations "  
            + "LEFT JOIN OrganizationsSupers USING (OrganizationId) "
            + "LEFT JOIN HeroesAndVillains USING (SuperId) "
            + "WHERE OrganizationId = ?;";
    
    @Override
    public List<Membership> getMembershipsOfOrganization(Organization organization) {
        List<Membership> memberships = jdbcTemplate.query(SQL_GET_MEMBERSHIPS_FOR_ORGANIZATION, new MembershipMapper(), organization.getOrganizationId());
        return memberships;
    }
    
    //-------------------------------------------------------------------------------------------------------

    
    
    
    
    //-------------------------------------------------------------------------------------------------------
    
    private static final String SQL_ADD_MEMBERSHIP
            = "INSERT INTO OrganizationsSupers (OrganizationId, SuperId) "
            + "VALUES (?, ?);";
    
    @Override
    public void assignSuperhumanToOrganization(Superhuman superhuman, Organization organization) {
        jdbcTemplate.update(SQL_ADD_MEMBERSHIP,
                organization.getOrganizationId(),
                superhuman.getSuperhumanId());
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
