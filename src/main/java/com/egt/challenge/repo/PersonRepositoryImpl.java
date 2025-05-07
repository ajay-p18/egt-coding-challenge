package com.egt.challenge.repo;

import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository {


    private final Map<Long, Person> repo = new HashMap<>();



    Person personReference = Person.builder().build(); // placeholder

    // Create addresses with placeholder person
    Address mainAddress = Address.builder()
            .id(101L)
            .street1("123 Main St")
            .street2("Apt 4B")
            .city("Springfield")
            .state("IL")
            .zipCode("62704")
            .person(personReference) // placeholder
            .build();



    // Now build the final Person with correct addresses
    Person person = Person.builder()
            .id(1L)
            .firstName("John")
            .lastName("Doe")
            .birthDate(LocalDate.of(1990, 5, 20))
            .mainAddress(mainAddress)
            .additionalAddresses(null)
            .build();





    @Override
    public List<Person> findAll() {
        repo.put(person.getId(),person);
        for (Map.Entry<Long, Person> entry : repo.entrySet()) {
            Long id = entry.getKey();
            Person person = entry.getValue();
            //System.out.println("ID: " + id + ", Name: " + person.getFirstName() + " " + person.getLastName());
        }

        return new ArrayList<>(repo.values());

    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(repo.get(id));
    }

    @Override
    public List<Person> findByLastName(String lastName) {

        for(Person p: repo.values()){
            System.out.println(p.getLastName());
            if(p.getLastName().equals(lastName)){
                System.out.println("It is equal");
            }

            else{
                System.out.println("it is not");
            }
        }

        return repo.values()
                .stream()
                .filter(p -> p.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public Person save(Person person) {
        if (person.getId() == null) {
            person.setId((long) (repo.size() + 1));
        }
        repo.put(person.getId(), person);
        return person;
    }

    @Override
    public void delete(Person person) {
        repo.remove(person.getId());
    }
}
