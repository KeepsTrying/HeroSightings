/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.dao;
import com.sguild.superhumansightings.dto.Address;
import java.util.List;
/**
 *
 * @author apprentice
 */
public interface AddressDao {
    
    public List<Address> getAllAddresses();
    
    public Address getAddress(int addressId);
    
    public Address addAddress(Address address);
    
    public void updateAddress(Address address);
    
    public void deleteAddress(int addressId);
    
}
