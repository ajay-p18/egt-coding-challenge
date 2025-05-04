package com.egt.challenge.service;

import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;
import com.egt.challenge.repo.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    @NonNull
    private final PersonRepository personRepository;

    // TODO create methods to create, read, update, and delete Persons as outlined in the README

    public List<Person> findAll(){
        List<Person> people = personRepository.findAll();
        return people;
    }

    public Person save(Person p){
        Person person = new Person(p.getFirstName(), p.getLastName(), p.getBirthDate(), p.getMainAddress());
        return personRepository.save(person);
    }

    public Person updatePerson(Person person){
        personRepository.findById(person.getId()); //looking for the person to see if they exist in the DB
        return personRepository.save(person); //saving the new request body into the DB
    }

}
