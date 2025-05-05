package com.egt.challenge.controller;

import com.egt.challenge.dto.AddressDto;
import com.egt.challenge.dto.PersonMapper;
import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;
import com.egt.challenge.service.PersonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PersonController.BASE_URL)
@RequiredArgsConstructor
public class PersonController {
    public static final String BASE_URL = "/api/persons";

    @NonNull
    private final PersonService personService;
    @NonNull
    private final PersonMapper personMapper;

    // TODO create the appropriate endpoints as outlined in the README

    @RequestMapping(value="/person", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllUsers(){
        List<Person> people = personService.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);

    }

    @RequestMapping(value="/person", method = RequestMethod.POST)
    public ResponseEntity<Person> createUser(@RequestBody Person person) throws Exception {
        Person p = personService.save(person);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value="/person", method = RequestMethod.PUT)
    public ResponseEntity<Person> updateUser(@RequestBody Person person) throws Exception {
        personService.updatePerson(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //hello world
}
