package com.egt.challenge.service;

import com.egt.challenge.dto.PersonDto;
import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;
import com.egt.challenge.repo.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

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

    public boolean isValid(Person p, boolean isUpdate) throws Exception {

        if(isUpdate){
            Optional<Person> optionalPerson = personRepository.findById(p.getId()); //looking for the person to see if they exist in the DB

            if (!optionalPerson.isPresent()){
                throw new Exception("Person does not exist");
            }
        }

        if(!(StringUtils.isNotBlank(p.getFirstName()) && StringUtils.isNotBlank(p.getLastName()) && p.getBirthDate() != null && p.getMainAddress() != null)){
            throw new Exception("Person requires a valid First, LastName, Birthdate and Main Address");
        }
        return true;

    }

    public Person save(Person p) throws Exception {
        Person person = null;

        if(!isValid(p,false)){
            return null;
        }

        person = new Person(p.getFirstName(), p.getLastName(), p.getBirthDate(), p.getMainAddress());
        return personRepository.save(person);
    }

    public Person updatePerson(Person person) throws Exception {

       Person p = null;
        if(isValid(person,true)){
            p = personRepository.findById(person.getId()).get(); //getting the details of person object
            if(person.getMainAddress() != null){
                p.setFirstName(person.getFirstName());
                p.setLastName(person.getLastName());
                p.setMainAddress(person.getMainAddress());
                p.setAdditionalAddresses(person.getAdditionalAddresses());
                personRepository.save(p);
            }
        }

        return  p;//saving the new request body into the DB
    }

    public Optional<Person> findById(Long id) throws Exception {
         Optional<Person> person = personRepository.findById(id);
         Person p = person.get();

        if(!isValid(p,false)){
            return null;
        }

         return person;
    }

    public void delete(Person person) throws Exception{

        if(!isValid(person, false)){
            throw new Exception("Person does not exist");
        }

        personRepository.delete(person);
    }

    public List<Person> findByLastName(String lastName) throws Exception{
        List<Person> people = personRepository.findByLastName(lastName);
        for(Person p: people){
            System.out.println("hello!");
            System.out.println(p.toString());
        }

        return people;
    }

}
