package com.egt.challenge.service;

import com.egt.challenge.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAll();
    Address save(Address a)  throws Exception;

    Optional<Address> findById(Long id) throws Exception;

    Address updateAddress(Address Address) throws Exception;
    void delete(Address address) throws Exception;


}
