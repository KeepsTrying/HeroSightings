/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;

import com.sguild.superhumansightings.dto.Address;
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
public class AddressDaoImpl implements AddressDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_GET_ALL_ADDRESSES
            = "SELECT * FROM Addresses;";

    @Override
    public List<Address> getAllAddresses() {
        List<Address> addressList = jdbcTemplate.query(SQL_GET_ALL_ADDRESSES, new AddressMapper());

        return addressList;
    }

    private static final String SQL_GET_ADDRESS
            = "SELECT * FROM Addresses WHERE AddressId = ?";

    @Override
    public Address getAddress(int addressId) {
        Address requestedAddress = jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), addressId);

        return requestedAddress;
    }

    private static final String SQL_INSERT_ADDRESS
            = "INSERT INTO Addresses (StreetAddress, City, State, Zip, Latitude, Longitude) "
            + "values (?, ?, ?, ?, ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getStreetAddress(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getLatitude(),
                address.getLongitude());

        int addressId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        address.setAddressId(addressId);

        return address;
    }

    private static final String SQL_UPDATE_ADDRESS
            = "UPDATE Addresses SET "
            + "StreetAddress = ?, "
            + "City = ?, "
            + "State = ?, "
            + "Zip = ?, "
            + "Latitude = ?, "
            + "Longitude = ? "
            + "WHERE AddressId = ?";

    @Override
    public void updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getStreetAddress(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getLatitude(),
                address.getLongitude(),
                address.getAddressId());
    }

    private static final String SQL_DELETE_ADDRESS = "DELETE FROM Addresses WHERE AddressId = ?";

    @Override
    public void deleteAddress(int addressId) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressId);
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address address = new Address();

            address.setAddressId(rs.getInt("AddressId"));
            address.setStreetAddress(rs.getString("StreetAddress"));
            address.setCity(rs.getString("City"));
            address.setState(rs.getString("State"));
            address.setZipCode(rs.getInt("Zip"));
            address.setLatitude(rs.getBigDecimal("Latitude"));
            address.setLongitude(rs.getBigDecimal("Longitude"));

            return address;
        }

    }

}
