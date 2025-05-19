package com.egt.challenge.controller;

import com.egt.challenge.dto.AddressDto;
import com.egt.challenge.dto.AddressMapper;
import com.egt.challenge.dto.PersonDto;
import com.egt.challenge.dto.PersonMapper;
import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;
import com.egt.challenge.repo.AddressRepository;
import com.egt.challenge.service.AddressService;
import com.egt.challenge.service.PersonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping(AddressController.BASE_URL)
@RequiredArgsConstructor

public class AddressController {
    public static final String BASE_URL = "/api/addresses";

    @NonNull
    private final AddressService addressService;
    @NonNull
    private final PersonService personService;
    @Autowired
    private AddressRepository addressRepository;

    @NonNull
    @Autowired
    private final AddressMapper addressMapper;

    @RequestMapping(value="/address", method = RequestMethod.GET)
    public ResponseEntity<List<AddressDto>> getAllUsers(){

        List<Address> addresses = addressService.findAll();
        List<AddressDto> addresstoReturn = new ArrayList<>();

        for(Address a: addresses){
            addresstoReturn.add(addressMapper.toDto(a));
        }

        Collections.sort(addresstoReturn, new Comparator<AddressDto>() {
            @Override
            public int compare(AddressDto a1, AddressDto a2) {
                int street1Comparison = a1.getStreet1().compareTo(a2.getStreet1());
                if (street1Comparison != 0) {
                    return street1Comparison;
                } else {
                    return a1.getState().compareTo(a2.getState());
                }
            }
        });

        return new ResponseEntity<>(addresstoReturn, HttpStatus.OK);
    }

    @RequestMapping(value="/address", method = RequestMethod.POST)
    public ResponseEntity<Address> createAddress(@RequestBody AddressDto addressDto) throws Exception {

        Address address = addressMapper.toEntity(addressDto);
        //System.out.println(person.toString());
        addressService.save(address);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @RequestMapping(value="/address", method = RequestMethod.PUT)
    public ResponseEntity<Address> updateAddress(@RequestBody AddressDto addressDto) throws Exception {
        Address address = addressMapper.toEntity(addressDto);
        addressService.updateAddress(address);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @RequestMapping(value="/address/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id) throws Exception {
        Optional<Address> addressToDelete = addressService.findById(id);
        if(addressToDelete.isPresent()){
            addressService.delete(addressToDelete.get());
        }

        AddressDto addressDtoDto = addressMapper.toDto(addressToDelete.get());
        return new ResponseEntity<>(addressDtoDto, HttpStatus.OK);
    }

    @RequestMapping(value="/address/{id}", method = RequestMethod.GET)
    public ResponseEntity<AddressDto> findAddressById(@PathVariable Long id) throws Exception {
        Optional<Address> address = addressService.findById(id);
        Address address1 = null;
        if(address.isPresent()){
            address1 = address.get();
        }

        AddressDto addressDtoDto = addressMapper.toDto(address1);
        return new ResponseEntity<>(addressDtoDto, HttpStatus.OK);
    }
}
