package com.egt.challenge.controller;

import com.egt.challenge.dto.PersonDto;
import com.egt.challenge.dto.PersonMapper;
import com.egt.challenge.model.Person;
import com.egt.challenge.service.PersonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(PersonController.BASE_URL)
@RequiredArgsConstructor
public class PersonController {
    public static final String BASE_URL = "/api/persons";

    @NonNull
    private final PersonService personService;
    @NonNull
    @Autowired
    private final PersonMapper personMapper;

    // TODO create the appropriate endpoints as outlined in the README

    @RequestMapping(value="/person", method = RequestMethod.GET)
    public ResponseEntity<List<PersonDto>> getAllUsers(){
        List<Person> people = personService.findAll();
        List<PersonDto> peopleToReturn = new ArrayList<>();

        for(Person p: people){
            PersonDto p1 = new PersonDto();
            p1.setId(p.getId());
            p1.setFirstName(p.getFirstName());
            p1.setLastName(p.getLastName());
            p1.setBirthDate(p.getBirthDate());

            peopleToReturn.add(p1);
        }

        Collections.sort(peopleToReturn, new Comparator<PersonDto>() {
            @Override
            public int compare(PersonDto p1, PersonDto p2) {
                int lastNameComparison = p1.getLastName().compareTo(p2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                } else {
                    return p1.getFirstName().compareTo(p2.getFirstName());
                }
            }
        });

        return new ResponseEntity<>(peopleToReturn, HttpStatus.OK);

    }

    @RequestMapping(value="/person", method = RequestMethod.POST)
    public ResponseEntity<Person> createUser(@RequestBody PersonDto personDto) throws Exception {

        Person person = personMapper.toEntity(personDto);
        personService.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value="/person", method = RequestMethod.PUT)
    public ResponseEntity<Person> updateUser(@RequestBody Person person) throws Exception {
        personService.updatePerson(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value="/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Optional<Person>> deleteUser(@PathVariable Long id) throws Exception {
        Optional<Person> personToDelete = personService.findById(id);
        if(personToDelete.isPresent()){
            personService.delete(personToDelete.get());
        }
        return new ResponseEntity<>(personToDelete, HttpStatus.OK);
    }

    @RequestMapping(value="/person/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Person>> findUserById(@PathVariable Long id) throws Exception {
        Optional<Person> persontoFind = personService.findById(id);
        if(persontoFind.isPresent()){
            personService.findById(persontoFind.get().getId());
        }
        return new ResponseEntity<>(persontoFind, HttpStatus.OK);
    }

    @RequestMapping(value="/person/lastName", method = RequestMethod.POST)
    public ResponseEntity<List<Person>> findUserByLastName(@RequestBody String lastName) throws Exception{
        System.out.println("Last Name: " + lastName);
        List<Person> people = personService.findByLastName(lastName);
        for(Person p: people){
            System.out.println("hello!");
            System.out.println(p.getLastName());
        }
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    //hello world
}
