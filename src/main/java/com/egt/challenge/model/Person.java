package com.egt.challenge.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Address mainAddress;
    private Set<Address> additionalAddresses;

    public Person(String firstName, String lastName, LocalDate birthDate, Address mainAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.mainAddress = mainAddress;
    }


}
