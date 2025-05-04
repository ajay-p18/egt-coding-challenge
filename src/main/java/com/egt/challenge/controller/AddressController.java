package com.egt.challenge.controller;

import com.egt.challenge.dto.AddressDto;
import com.egt.challenge.model.Address;
import com.egt.challenge.repo.AddressRepository;
import com.egt.challenge.service.AddressService;
import com.egt.challenge.service.PersonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

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


    // TODO create the appropriate endpoints as outlined in the README



}
