package com.egt.challenge.service;

import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();
    Person save(Person person);
    Person updatePerson(Person person);
}
