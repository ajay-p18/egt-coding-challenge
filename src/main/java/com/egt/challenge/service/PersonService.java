package com.egt.challenge.service;

import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> findAll();
    Person save(Person person) throws Exception;
    Person updatePerson(Person person) throws Exception;
    Optional<Person> findById(Long id) throws Exception;
    void delete(Person person) throws Exception;
    List<Person> findByLastName(String lastName) throws Exception;
}
